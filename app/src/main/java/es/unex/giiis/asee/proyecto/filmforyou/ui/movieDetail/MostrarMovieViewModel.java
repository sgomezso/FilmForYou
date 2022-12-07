package es.unex.giiis.asee.proyecto.filmforyou.ui.movieDetail;

import androidx.lifecycle.ViewModel;

import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;

/**
 * {@link ViewModel} for {@link MainActivity}
 */
class MostrarMovieViewModel extends ViewModel {

    private final Repository mRepository;

    public MostrarMovieViewModel(Repository repository) {
        mRepository = repository;
    }

}
