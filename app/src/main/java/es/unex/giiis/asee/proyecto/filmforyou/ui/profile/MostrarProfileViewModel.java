package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import androidx.lifecycle.ViewModel;

import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;

/**
 * {@link ViewModel} for {@link MainActivity}
 */
class MostrarProfileViewModel extends ViewModel {

    private final Repository mRepository;

    public MostrarProfileViewModel(Repository repository) {
        mRepository = repository;
    }

}
