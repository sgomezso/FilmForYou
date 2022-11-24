package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public MovieListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is movie fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getMovies (){
        Repository repository = new Repository();
        repository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {}
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {}
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}
        });
    }
}