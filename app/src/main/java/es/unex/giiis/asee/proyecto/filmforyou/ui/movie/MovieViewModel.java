package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class MovieViewModel extends ViewModel {
    private final MoviesRepository mRepository;

    public MovieViewModel(MoviesRepository repository) {
        mRepository = repository;
    }

    public void actualizarMovie(Movie movie){
        mRepository.updateMovie(movie);
    }
}