package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class UserMovieRepository {

    interface UserMovieRepositoryListener {
        public void onFavoriteMovies( List<Movie> userFavoritesMoviesList);
    }

    public UserFavoriteMoviesDAO database;
    public SharedPreferences preference;

    public UserMovieRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().userFavoriteMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public void loadFavoriteMoviesByUser(Long userId, UserMovieRepositoryListener userMovieRepositoryListener) {
        Repository apiRepository = new Repository();
        List<Movie> movies = new ArrayList<>();

        apiRepository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
                List<UserFavoritesMovies> userFavoritesMoviesList = (List<UserFavoritesMovies>) database.loadFavoriteMoviesByUser(userId.toString());
                for (Movie movie : top250movies) {
                    for (UserFavoritesMovies userFavoritesMovies : userFavoritesMoviesList) {
                        if (userFavoritesMovies.getIdMovie().equals(movie.getMovieId())) {
                            movies.add(movie);
                        }
                    }
                }
                userMovieRepositoryListener.onFavoriteMovies(movies);

            }

            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {

            }

            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {

            }
        });
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
