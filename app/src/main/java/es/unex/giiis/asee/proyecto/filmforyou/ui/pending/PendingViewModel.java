package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class PendingViewModel extends ViewModel {

    private UserMovieRepositoryPending mRepository;

    public PendingViewModel(UserMovieRepositoryPending repository) {
        this.mRepository = repository;
    }

    public LiveData<List<UserPendingMovies>> getPendingMovies(Long userId, UserMovieRepositoryPending.UserMovieRepositoryListener listener) {
        return mRepository.loadPendingMoviesByUser(userId, listener);
    }
}