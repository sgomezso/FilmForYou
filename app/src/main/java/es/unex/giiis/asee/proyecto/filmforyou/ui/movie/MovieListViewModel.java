package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class MovieListViewModel extends ViewModel {
    private MoviesRepository mRepository;

    private final MutableLiveData<List<Movie>> mTopList = new MutableLiveData<>();
    LiveData<List<Movie>> topList = mTopList;


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
}