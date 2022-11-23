package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

public class PendingViewModel extends ViewModel {

    private LiveData<List<UserPendingMovies>> mRepos;
    private final Repository mRepository;

    public PendingViewModel(Repository repository) {
        this.mRepository = repository;
//        mRepos = mRepository.getPendingMovies();
    }

    public LiveData<List<UserPendingMovies>> getPendingMovies() {
        return mRepos;
    }
}