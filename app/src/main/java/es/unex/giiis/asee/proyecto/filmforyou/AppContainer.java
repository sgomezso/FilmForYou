package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;

public class AppContainer {

    private Database database;
    public FavoritesViewModelFactory favoritesVMFactory;
    public PendingViewModelFactory pendingVMFactory;
    public Repository repository;

    public AppContainer(Context context) {
        database = Database.getInstance(context);
        repository = Repository.getInstance(database.movieDAO());
        favoritesVMFactory = new FavoritesViewModelFactory(repository);
        pendingVMFactory = new PendingViewModelFactory(repository);
    }
}
