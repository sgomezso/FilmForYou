package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class PendingViewModel extends ViewModel {

    private Repository mRepository;
    private LiveData<List<UserPendingMovies>> listPending;

    public PendingViewModel(Repository repository) {
        this.mRepository = repository;
    }

    public LiveData<List<UserPendingMovies>> getPendingMovies(Long userId) {
        listPending = mRepository.getPendingMovies(userId);
        return listPending;
    }
}