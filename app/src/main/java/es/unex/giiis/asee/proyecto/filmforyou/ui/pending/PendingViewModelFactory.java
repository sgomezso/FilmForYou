package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;

public class PendingViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MoviesRepository mRepository;

    public PendingViewModelFactory(MoviesRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new PendingViewModel(mRepository);
    }
}
