package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieFavoritesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.LoginViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.LoginViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.UserMoviePendingRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.profile.ProfileViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.profile.ProfileViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.search.SearchViewModelFactory;

public class AppContainer {

    public MoviesRepository movieRepository;
    private Database database;
    public UserRepository userRepository;
    public UserMovieFavoritesRepository userMovieFavoritesRepository;
    public UserMoviePendingRepository userMoviePendingRepository;
    public FavoritesViewModelFactory favoritesViewModelFactory;
    public PendingViewModelFactory pendingViewModelFactory;
    public SearchViewModelFactory searchViewModelFactory;
    public MovieListViewModelFactory movieListViewModelFactory;
    public LoginViewModelFactory loginViewModelFactory;
    public ProfileViewModelFactory profileViewModelFactory;

    public AppContainer(Context context) {
        database = Database.getInstance(context);
        movieRepository = MoviesRepository.getInstance(database.movieDAO(), RepositoryNetworkDataSource.getInstance());
        userRepository = UserRepository.getInstance(context.getApplicationContext());
        userMovieFavoritesRepository = UserMovieFavoritesRepository.getInstance(context.getApplicationContext());
        userMoviePendingRepository = UserMoviePendingRepository.getInstance(context.getApplicationContext());
        profileViewModelFactory = new ProfileViewModelFactory(userRepository);
        favoritesViewModelFactory = new FavoritesViewModelFactory(userMovieFavoritesRepository);
        movieListViewModelFactory = new MovieListViewModelFactory(movieRepository);
        pendingViewModelFactory = new PendingViewModelFactory(userMoviePendingRepository);
        searchViewModelFactory = new SearchViewModelFactory(movieRepository);
        loginViewModelFactory = new LoginViewModelFactory(userRepository);
    }
}