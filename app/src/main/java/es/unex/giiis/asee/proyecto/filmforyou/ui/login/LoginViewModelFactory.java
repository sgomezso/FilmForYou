package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final UserRepository mUserRepository;

    public LoginViewModelFactory(UserRepository repository) {
        this.mUserRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new LoginViewModel(mUserRepository);
    }
}
