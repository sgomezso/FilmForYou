package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;

public class AppContainer {

    public Repository repository;
    private Database database;
    public FavoritesViewModelFactory favoritesViewModelFactory;

    public AppContainer(Context context) {
        database = Database.getInstance(context);
        repository = Repository.getInstance(database.movieDAO(),database.userDAO(),database.userPendingMoviesDAO(), database.userFavoriteMoviesDAO());
        favoritesViewModelFactory = new FavoritesViewModelFactory(repository);
    }
}
