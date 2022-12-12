package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class SearchViewModel extends ViewModel {
    private MoviesRepository mRepository;

    public SearchViewModel(MoviesRepository repository) {
        this.mRepository = repository;
    }

    public LiveData<List<Movie>> getSearchResults(String expression) {
        return this.mRepository.getSearchResultsExpresion(expression);
    }
}