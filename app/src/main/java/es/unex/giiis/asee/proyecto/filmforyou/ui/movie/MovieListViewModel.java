package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModel;

public class MovieListViewModel extends ViewModel {
    private MoviesRepository mRepository;

    private final MutableLiveData<List<Movie>> mTopList = new MutableLiveData<>();
    LiveData<List<Movie>> topList = mTopList;

    private final MutableLiveData<Void> mDeleteMoviesEvent = new MutableLiveData<>();
    LiveData<Void> deleteMoviesEvent = mDeleteMoviesEvent;


    public MovieListViewModel(MoviesRepository repository) {
        this.mRepository = repository;
    }

    public void getTopMovies() {
         mRepository.getTopMovies(new MoviesRepositoryListener() {
             @Override
             public void onMoviesResult(List<Movie> movies) {
                 mTopList.postValue(movies);
             }

             @Override
             public void onMovieFailure() {

             }
         });
    }

    public void removeMovies() {
        mRepository.removeMovies();
    }

}