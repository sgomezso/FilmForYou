package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.search.SearchViewModelFactory;

public class AppContainer {

    public Repository repository;
    private Database database;
    public FavoritesViewModelFactory favoritesViewModelFactory;
    public PendingViewModelFactory pendingViewModelFactory;
    public SearchViewModelFactory searchViewModelFactory;
    public MovieListViewModelFactory movieListViewModelFactory;

    public AppContainer(Context context) {
        database = Database.getInstance(context);
        repository = Repository.getInstance(database.movieDAO(), RepositoryNetworkDataSource.getInstance());
        favoritesViewModelFactory = new FavoritesViewModelFactory(repository);
        movieListViewModelFactory = new MovieListViewModelFactory(repository);
        pendingViewModelFactory = new PendingViewModelFactory(repository);
        searchViewModelFactory = new SearchViewModelFactory(repository);
    }
}
