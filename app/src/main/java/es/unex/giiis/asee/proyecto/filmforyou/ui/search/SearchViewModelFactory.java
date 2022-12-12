package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;

public class SearchViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final MoviesRepository mRepository;

    public SearchViewModelFactory(MoviesRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SearchViewModel(mRepository);
    }
}
