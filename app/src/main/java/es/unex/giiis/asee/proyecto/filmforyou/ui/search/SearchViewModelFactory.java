package es.unex.giiis.asee.proyecto.filmforyou.ui.search;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModel;

public class SearchViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final Repository mRepository;

    public SearchViewModelFactory(Repository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new SearchViewModel(mRepository);
    }
}
