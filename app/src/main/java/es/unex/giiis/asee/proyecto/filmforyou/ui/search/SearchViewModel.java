package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;

public class SearchViewModel extends ViewModel {
    private Repository mRepository;

    public SearchViewModel(Repository repository) {
        this.mRepository = repository;
    }

    public LiveData<List<Movie>> getSearchResults(String expression) {
        return this.mRepository.getSearchResultsExpresion(expression);
    }
}