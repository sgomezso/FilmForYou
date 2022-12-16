package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;

public class FavoritesViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserMovieFavoritesRepository mRepository;

    public FavoritesViewModelFactory(UserMovieFavoritesRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new FavoritesViewModel(mRepository);
    }
}