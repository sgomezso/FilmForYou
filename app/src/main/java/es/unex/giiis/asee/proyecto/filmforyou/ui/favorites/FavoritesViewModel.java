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

    private Repository mRepository;
    private LiveData<List<UserFavoritesMovies>> listFavorites;

    public FavoritesViewModel(Repository repository) {
        this.mRepository = repository;
        listFavorites = mRepository.getFavoritesMovies();
    }

    public LiveData<List<UserFavoritesMovies>> getFavoriteMovies() {
        return listFavorites;
    }
}