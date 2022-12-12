package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.MovieDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class UserMovieRepositoryPending {

    private static UserMovieRepositoryPending sInstance;
    private static UserPendingMoviesDAO mUserPendingMoviesDAO;

    interface UserMovieRepositoryListener {
        public void onPendingMovies(List<Movie> userPendingMoviesList);
    }

    public UserPendingMoviesDAO database;
    public SharedPreferences preference;
    private final MutableLiveData<List<Movie>> pendMovies = new MutableLiveData<>();

    public UserMovieRepositoryPending(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().userPendingMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    private UserMovieRepositoryPending(UserPendingMoviesDAO userPendingMoviesDAO) {
        mUserPendingMoviesDAO = userPendingMoviesDAO;
    }

    public synchronized static UserMovieRepositoryPending getInstance(UserPendingMoviesDAO userPendingMoviesDAO) {
        if (sInstance == null) {
            sInstance = new UserMovieRepositoryPending(userPendingMoviesDAO);
        }
        return sInstance;
    }

    public LiveData<List<Movie>> loadPendingMoviesByUser(Long userId) {
        Repository apiRepository = new Repository();
        List<Movie> movies = new ArrayList<>();
        LiveData<List<UserPendingMovies>> userPendingMoviesList = database.loadPendingMoviesByUser(userId.toString());
        for (Movie movie : apiRepository.getTopMovies().getValue()) {
            for (UserPendingMovies userPendingMovies : userPendingMoviesList.getValue()) {
                if (userPendingMovies.getIdMovie().equals(movie.getMovieId())) {
                    movies.add(movie);
                }
            }
        }
        pendMovies.postValue(movies);
        return pendMovies;
    }
    public boolean checkPending(Long idUser, String idMovie) {
        if (database.checkUserPendingMovie(idUser.toString(), idMovie) == null)
            return false;
        else
            return true;
    }

    public void addPending(Long idUser, String idMovie) {
        database.insertPending(idUser.toString(), idMovie);
    }

    public void deletePending(Long idUser, String idMovie) {
        database.deletePending(idUser.toString(), idMovie);
    }
}
