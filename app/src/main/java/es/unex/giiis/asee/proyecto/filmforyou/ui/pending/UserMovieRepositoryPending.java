package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;

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
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class UserMovieRepositoryPending {

    interface UserMovieRepositoryListener {
        public void onPendingMovies(List<Movie> userPendingMoviesList);
    }

    public UserPendingMoviesDAO database;
    public SharedPreferences preference;

    public UserMovieRepositoryPending(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().userPendingMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public void loadPendingMoviesByUser(Long userId, UserMovieRepositoryListener userMovieRepositoryListener) {
        Repository apiRepository = new Repository();
        List<Movie> movies = new ArrayList<>();

        apiRepository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
                List<UserPendingMovies> userPendingMoviesList = database.loadPendingMoviesByUser(userId.toString());
                for (Movie movie : top250movies) {
                    for (UserPendingMovies userPendingMovies : userPendingMoviesList) {
                        if (userPendingMovies.getIdMovie().equals(movie.getMovieId())) {
                            movies.add(movie);
                        }
                    }
                }
                userMovieRepositoryListener.onPendingMovies(movies);

            }

            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {

            }

            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {

            }
        });
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