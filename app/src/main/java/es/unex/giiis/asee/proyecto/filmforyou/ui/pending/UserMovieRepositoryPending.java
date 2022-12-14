package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;

public class UserMovieRepositoryPending {

    interface UserMovieRepositoryListener {
        public void onPendingMovies(List<Movie> userPendingMoviesList);
    }

    public UserPendingMoviesDAO database;
    public SharedPreferences preference;

    public UserMovieRepositoryPending(Context context) {
        database = Database.getInstance(context).userPendingMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public void loadPendingMoviesByUser(Long userId, UserMovieRepositoryListener userMovieRepositoryListener) {
//        MoviesRepository apiRepository = new MoviesRepository();
//        List<Movie> movies = new ArrayList<>();
//        List<UserPendingMovies> userPendingMoviesList = database.loadPendingMoviesByUser(userId.toString());
//        for (Movie movie : apiRepository.getTopMovies().getValue()) {
//            for (UserPendingMovies userPendingMovies : userPendingMoviesList) {
//                if (userPendingMovies.getIdMovie().equals(movie.getMovieId())) {
//                    movies.add(movie);
//                }
//            }
//        }
//        userMovieRepositoryListener.onPendingMovies(movies);
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
