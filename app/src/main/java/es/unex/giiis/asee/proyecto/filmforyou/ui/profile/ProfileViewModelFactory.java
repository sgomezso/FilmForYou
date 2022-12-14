package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class ProfileViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final UserRepository mRepository;

    public ProfileViewModelFactory(UserRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProfileViewModel(mRepository);
    }
}
