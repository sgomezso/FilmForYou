package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepositoryListener;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class SearchViewModel extends ViewModel {
    private MoviesRepository mRepository;
    private final MutableLiveData<List<Movie>> movieListResult = new MutableLiveData<>();
    public LiveData<List<Movie>> movieList = movieListResult;


    public SearchViewModel(MoviesRepository repository) {
        this.mRepository = repository;
    }

    public void getSearchResults(String expression) {
       mRepository.getSearchResultsExpresion(expression, new MoviesRepositoryListener() {
            @Override
            public void onMoviesResult(List<Movie> movies) {
                movieListResult.postValue(movies);
            }
            @Override
            public void onMovieFailure() {
            }
        });
    }

}