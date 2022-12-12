package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.UserMovieRepositoryPending;

public class UserMovieRepositoryFavorite {

    interface UserMovieRepositoryListener {
        public void onFavoriteMovies( List<Movie> userFavoritesMoviesList);
    }

    private static UserMovieRepositoryFavorite sInstance;
    private static UserFavoriteMoviesDAO mUserFavoriteMoviesDAO;
    public UserFavoriteMoviesDAO database;
    public SharedPreferences preference;
    private final MutableLiveData<List<Movie>> favMovies = new MutableLiveData<>();

    public UserMovieRepositoryFavorite(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().userFavoriteMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    private UserMovieRepositoryFavorite(UserFavoriteMoviesDAO userFavoriteMoviesDAO) {
        mUserFavoriteMoviesDAO = userFavoriteMoviesDAO;
    }

    public synchronized static UserMovieRepositoryFavorite getInstance(UserFavoriteMoviesDAO userFavoriteMoviesDAO) {
        if (sInstance == null) {
            sInstance = new UserMovieRepositoryFavorite(userFavoriteMoviesDAO);
        }
        return sInstance;
    }

    public LiveData<List<Movie>> loadFavoriteMoviesByUser(Long userId) {
        Repository apiRepository = new Repository();
        List<Movie> movies = new ArrayList<>();
        LiveData<List<UserFavoritesMovies>> userFavMovies = database.loadFavoriteMoviesByUser(userId.toString());
        for (Movie movie :  apiRepository.getTopMovies().getValue()) {
            for (UserFavoritesMovies userFavoritesMovies : userFavMovies.getValue()) {
                if (userFavoritesMovies.getIdMovie().equals(movie.getMovieId())) {
                    movies.add(movie);
                }
            }
        }
        favMovies.postValue(movies);
        return favMovies;
    }

    public boolean checkFav(Long idUser, String idMovie) {
        if (database.checkUserFavoriteMovie(idUser.toString(), idMovie) == null)
            return false;
        else
            return true;
    }

    public void addFav(Long idUser, String idMovie) {
        database.insertFavorite(idUser.toString(), idMovie);
    }

    public void deleteFav(Long idUser, String idMovie) {
        database.deleteFavorite(idUser.toString(), idMovie);
    }
}
