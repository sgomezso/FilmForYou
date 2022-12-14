package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieFavoritesRepository;

public class PendingViewModel extends ViewModel {

    private UserMoviePendingRepository mRepository;

    private final MutableLiveData<List<Movie>> mListPending = new MutableLiveData<>();
    LiveData<List<Movie>> listPend = mListPending;

    public PendingViewModel(UserMoviePendingRepository repository) {
        this.mRepository = repository;
    }

    public void getPendingMovies() {
        mRepository.getPendingMovies(new MoviesRepositoryListener() {
            @Override
            public void onMoviesResult(List<Movie> movies) {
                mListPending.postValue(movies);
            }

            @Override
            public void onMovieFailure() {

            }
        });
    }

}