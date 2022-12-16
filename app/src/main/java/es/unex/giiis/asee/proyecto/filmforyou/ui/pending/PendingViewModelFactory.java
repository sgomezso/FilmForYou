package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieFavoritesRepository;

public class PendingViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final UserMoviePendingRepository mRepository;

    public PendingViewModelFactory(UserMoviePendingRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new PendingViewModel(mRepository);
    }
}
