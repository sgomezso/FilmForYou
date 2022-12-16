package es.unex.giiis.asee.proyecto.filmforyou;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public interface MoviesRepositoryListener {

    void onMoviesResult(List<Movie> movies);

    void onMovieFailure();
}
