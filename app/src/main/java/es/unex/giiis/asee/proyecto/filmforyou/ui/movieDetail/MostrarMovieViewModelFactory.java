package es.unex.giiis.asee.proyecto.filmforyou.ui.movieDetail;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;

public class MostrarMovieViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Repository mRepository;

    public MostrarMovieViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MostrarMovieViewModel(mRepository);
    }
}