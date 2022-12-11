package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;

public class FavoritesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserMovieRepositoryFavorite mRepository;

    public FavoritesViewModelFactory(UserMovieRepositoryFavorite repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new FavoritesViewModel(mRepository);
    }
}
