package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.ImdbApiEndPoint;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;

public class InjectorUtils {

//    public static Repository provideRepository(Context context) {
//        Database db = Database.getInstance(context.getApplicationContext());
//        return Repository.getInstance(db.movieDAO());
//    }
//
//    public static FavoritesViewModelFactory provideFavoriteViewModelFactory(Context context) {
//        Repository repository = provideRepository(context.getApplicationContext());
//        return new FavoritesViewModelFactory(repository);
//    }
//
//    public static PendingViewModelFactory providePendingViewModelFactory(Context context) {
//        Repository repository = provideRepository(context.getApplicationContext());
//        return new PendingViewModelFactory(repository);
//    }
}
