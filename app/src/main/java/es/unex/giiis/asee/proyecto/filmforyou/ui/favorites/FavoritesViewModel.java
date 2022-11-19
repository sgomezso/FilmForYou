package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class FavoritesViewModel extends ViewModel {

    private LiveData<List<UserFavoritesMovies>> mRepos;
    private final Repository mRepository;

    public FavoritesViewModel(Repository repo) {
        mRepository = repo;
        mRepos = mRepository.getFavoritesUserMovies();
    }

    public LiveData<List<UserFavoritesMovies>> getFavoriteMovies() {
        return mRepos;
    }
}