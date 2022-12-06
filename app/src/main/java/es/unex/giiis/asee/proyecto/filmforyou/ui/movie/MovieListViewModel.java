package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class MovieListViewModel extends ViewModel {
    private Repository mRepository;
    private List<Movie> MovieList;

    public MovieListViewModel(Repository repository) {
        this.mRepository = repository;
        mRepository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
               MovieList = top250movies;
            }
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {}
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}
        });
    }

    public List<Movie> getTopMovies() {
        return MovieList;
    }
}