package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;

public class SearchViewModel extends ViewModel {
    private Repository mRepository;
    private List<Movie> MovieList;

    public SearchViewModel(Repository repository) {
        this.mRepository = repository;
    }

    public List<Movie> getSearchResults(String expression) {
        mRepository.getSearchResultsExpresion(expression, new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {}
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {
                MovieList = resultsSearch;
            }
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}
        });
        return MovieList;
    }
}