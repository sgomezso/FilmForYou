package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class FavoritesViewModel extends ViewModel {

    private LiveData<List<Movie>> mRepos;
    private final Repository mRepository;

    public FavoritesViewModel(Repository repo) {
        mRepository = repo;
        mRepos = mRepository.getFavoritesUserMovies();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return mRepos;
    }
}