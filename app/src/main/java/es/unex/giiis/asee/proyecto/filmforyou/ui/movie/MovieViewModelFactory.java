package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class MovieViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final MoviesRepository mRepository;

    public MovieViewModelFactory(MoviesRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MovieViewModel(mRepository);
    }
}
