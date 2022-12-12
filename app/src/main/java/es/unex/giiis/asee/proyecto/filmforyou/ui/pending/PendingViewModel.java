package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class PendingViewModel extends ViewModel {

    private MoviesRepository mRepository;
    private List<UserPendingMovies> listPending;

    public PendingViewModel(MoviesRepository repository) {
        this.mRepository = repository;
    }

//    public List<UserPendingMovies> getPendingMovies(Long userId) {
//        listPending = mRepository.getPendingMovies(userId);
//        return listPending;
//    }
}