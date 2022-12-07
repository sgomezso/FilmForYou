package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;

public class MostrarProfileViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public MostrarProfileViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MostrarProfileViewModel(mRepository);
    }
}