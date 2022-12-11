package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Search;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.search.SearchViewModelFactory;

public class InjectorUtils {

    public static Repository provideRepository(Context context) {
        Database database = Database.getInstance(context.getApplicationContext());
        return Repository.getInstance(database.movieDAO(), database.userDAO(), database.userPendingMoviesDAO(), database.userFavoriteMoviesDAO());
    }

    public static FavoritesViewModelFactory provideFavoritesViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new FavoritesViewModelFactory(repository);
    }

    public static PendingViewModelFactory providePendingViewModelFactory(Context context){
        Repository repository = provideRepository(context.getApplicationContext());
        return new PendingViewModelFactory(repository);
    }

    public static SearchViewModelFactory provideSearchViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new SearchViewModelFactory(repository);
    }

    public static MovieListViewModelFactory provideMovieListViewModelFactory(Context context) {
        Repository repository = provideRepository(context.getApplicationContext());
        return new MovieListViewModelFactory(repository);
    }

}
