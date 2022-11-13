package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.MovieDatabase;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;

public class AppContainer {

    private MovieDatabase movieDatabase;
    public FavoritesViewModelFactory favoritesVMFactory;
    public PendingViewModelFactory pendingVMFactory;
    public Repository repository;

    public AppContainer(Context context) {
        movieDatabase = MovieDatabase.getDatabase(context);
        favoritesVMFactory = new FavoritesViewModelFactory(repository);
        pendingVMFactory = new PendingViewModelFactory(repository);
    }
}
