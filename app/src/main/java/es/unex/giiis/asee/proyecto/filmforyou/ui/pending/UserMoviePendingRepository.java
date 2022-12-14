package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserPendingMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieFavoritesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class UserMoviePendingRepository {

    interface UserMovieRepositoryListener {
        public void onPendingMovies(List<Movie> userPendingMoviesList);
    }

    public UserPendingMoviesDAO userPendingMoviesDAO;
    public MoviesRepository moviesRepository;
    public UserRepository userRepository;
    private static UserMoviePendingRepository sInstance;

    public UserMoviePendingRepository(Context context) {
        userPendingMoviesDAO = Database.getInstance(context).userPendingMoviesDAO();
        this.moviesRepository = MoviesRepository.getInstance(Database.getInstance(context).movieDAO(), RepositoryNetworkDataSource.getInstance());
        this.userRepository = UserRepository.getInstance(context);
    }

    public static UserMoviePendingRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserMoviePendingRepository(context);
        }
        return sInstance;
    }

    public void getPendingMovies(MoviesRepositoryListener moviesRepositoryListener) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                moviesRepository.getTopMovies(new MoviesRepositoryListener() {
                    @Override
                    public void onMoviesResult(List<Movie> movies) {
                        User user = userRepository.getCurrentUser();
                        List<UserPendingMovies> listUserPendingMovies = userPendingMoviesDAO.getPendingMoviesUserLogged(user.getId().toString());
                        List<Movie> result = new ArrayList<>();
                        for (Movie movie : movies) {
                            for (UserPendingMovies userPendingMovies : listUserPendingMovies) {
                                if (userPendingMovies.getIdMovie().equals(movie.getMovieId())) {
                                    result.add(movie);
                                }
                            }
                        }
                    }

                    @Override
                    public void onMovieFailure() {

                    }
                });

            }
        });
    }

    public boolean checkPending(Long idUser, String idMovie) {
        if (userPendingMoviesDAO.checkUserPendingMovie(idUser.toString(), idMovie) == null)
            return false;
        else
            return true;
    }

    public void addPending(Long idUser, String idMovie) {
        userPendingMoviesDAO.insertPending(idUser.toString(), idMovie);
    }

    public void deletePending(Long idUser, String idMovie) {
        userPendingMoviesDAO.deletePending(idUser.toString(), idMovie);
    }
}
