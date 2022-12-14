package es.unex.giiis.asee.proyecto.filmforyou;

import android.content.Context;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieFavoritesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingViewModelFactory;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.UserMoviePendingRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.search.SearchViewModelFactory;

public class InjectorUtils {

    public static MoviesRepository provideRepository(Context context) {
        Database database = Database.getInstance(context.getApplicationContext());
        return MoviesRepository.getInstance(database.movieDAO(), RepositoryNetworkDataSource.getInstance());
    }

//    public static FavoritesViewModelFactory provideFavoritesViewModelFactory(Context context) {
//        UserMovieFavoritesRepository repository = provideRepository(context.getApplicationContext());
//        return new FavoritesViewModelFactory(repository);
//    }

//    public static PendingViewModelFactory providePendingViewModelFactory(Context context){
//        UserMoviePendingRepository repository = provideRepository(context.getApplicationContext());
//        return new PendingViewModelFactory(repository);
//    }

    public static SearchViewModelFactory provideSearchViewModelFactory(Context context) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());
        return new SearchViewModelFactory(repository);
    }

    public static MovieListViewModelFactory provideMovieListViewModelFactory(Context context) {
        MoviesRepository repository = provideRepository(context.getApplicationContext());
        return new MovieListViewModelFactory(repository);
    }

}
