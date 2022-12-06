package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;

public class InjectorUtils {

    public static Repository provideRepository(Context context) {
        Database database = Database.getInstance(context.getApplicationContext());
        return Repository.getInstance(database.movieDAO(), database.userDAO(), database.userPendingMoviesDAO(), database.userFavoriteMoviesDAO());
    }

    public static FavoritesViewModelFactory provideFavoritesViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new FavoritesViewModelFactory(repository);
    }
}
