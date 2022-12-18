package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class UserMovieFavoritesRepository {

    interface UserMovieRepositoryListener {
        public void onFavoriteMovies(List<Movie> userFavoritesMoviesList);
    }

    public UserFavoriteMoviesDAO userFavoriteMoviesDAO;
    private static UserMovieFavoritesRepository sInstance;


    public UserMovieFavoritesRepository(Context context) {
        userFavoriteMoviesDAO = Database.getInstance(context).userFavoriteMoviesDAO();
    }

    public static UserMovieFavoritesRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserMovieFavoritesRepository(context);
        }
        return sInstance;
    }

    public void getFavoriteMovies(Context context,MoviesRepositoryListener callback) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
                List<FavoriteMovies> favoriteMovies = userFavoriteMoviesDAO.getFavoriteMovies(preference.getLong("userId", 0));
                List<Movie> movies = new ArrayList<>();
                for (FavoriteMovies favoriteMovie : favoriteMovies) {
                    movies.add(favoriteMovie.toMovie());
                }
                callback.onMoviesResult(movies);
            }
        });
    }

    public boolean checkFav(Long idUser, String idMovie) {
        if (userFavoriteMoviesDAO.checkUserFavoriteMovie(idUser.toString(), idMovie) == null)
            return false;
        else
            return true;
    }

    public void addFav(Long idUser, String idMovie) {
        userFavoriteMoviesDAO.insertFavorite(idUser.toString(), idMovie);
    }

    public void deleteFav(Long idUser, String idMovie) {
        userFavoriteMoviesDAO.deleteFavorite(idUser.toString(), idMovie);
    }
}