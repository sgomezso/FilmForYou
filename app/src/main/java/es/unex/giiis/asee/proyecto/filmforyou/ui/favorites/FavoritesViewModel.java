package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class FavoritesViewModel extends ViewModel {

    private UserMovieRepositoryFavorite mRepository;

    public FavoritesViewModel(UserMovieRepositoryFavorite repository) {
        this.mRepository = repository;
    }

    public LiveData<List<Movie>> getFavoriteMovies(Long userId) {
        return mRepository.loadFavoriteMoviesByUser(userId);
    }
}