package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class UserMovieFavoritesRepository {

    interface UserMovieRepositoryListener {
        public void onFavoriteMovies(List<Movie> userFavoritesMoviesList);
    }

    public UserFavoriteMoviesDAO userFavoriteMoviesDAO;
    public MoviesRepository moviesRepository;
    public UserRepository userRepository;
    private static UserMovieFavoritesRepository sInstance;


    public UserMovieFavoritesRepository(Context context) {
        userFavoriteMoviesDAO = Database.getInstance(context).userFavoriteMoviesDAO();
        this.moviesRepository = MoviesRepository.getInstance(Database.getInstance(context).movieDAO(), RepositoryNetworkDataSource.getInstance());
        this.userRepository = UserRepository.getInstance(context);
    }

    public static UserMovieFavoritesRepository getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new UserMovieFavoritesRepository(context);
        }
        return sInstance;
    }

    public void getFavoriteMovies(MoviesRepositoryListener moviesRepositoryListener) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                moviesRepository.getTopMovies(new MoviesRepositoryListener() {
                    @Override
                    public void onMoviesResult(List<Movie> movies) {
                        User user = userRepository.getCurrentUser();
                        List<UserFavoritesMovies> listUserFavoritesMovies = userFavoriteMoviesDAO.getFavoriteMoviesUserLogged(user.getId().toString());
                        List<Movie> result = new ArrayList<>();
                        for (Movie movie : movies) {
                            for (UserFavoritesMovies userFavoritesMovies : listUserFavoritesMovies) {
                                if (userFavoritesMovies.getIdMovie().equals(movie.getMovieId())) {
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