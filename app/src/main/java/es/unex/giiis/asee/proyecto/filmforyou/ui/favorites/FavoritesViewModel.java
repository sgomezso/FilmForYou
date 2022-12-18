package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class FavoritesViewModel extends ViewModel {

    private UserMovieFavoritesRepository mRepository;

    private final MutableLiveData<List<Movie>> mListFavorites = new MutableLiveData<>();
    LiveData<List<Movie>> listFav = mListFavorites;

    public FavoritesViewModel(UserMovieFavoritesRepository repository) {
        this.mRepository = repository;
    }

    public void getFavoriteMovies(Context context) {
        mRepository.getFavoriteMovies(context,new MoviesRepositoryListener() {
            @Override
            public void onMoviesResult(List<Movie> movies) {
                mListFavorites.postValue(movies);
            }

            @Override
            public void onMovieFailure() {

            }
        });
    }
}