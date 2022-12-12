package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class FavoritesViewModel extends ViewModel {

    private MoviesRepository mRepository;
    private LiveData<List<UserFavoritesMovies>> listFavorites;

    public FavoritesViewModel(MoviesRepository repository) {
        this.mRepository = repository;
    }

//    public LiveData<List<UserFavoritesMovies>> getFavoriteMovies(Long userId) {
//        listFavorites = mRepository.getFavoritesMovies(userId);
//        return listFavorites;
//    }
}