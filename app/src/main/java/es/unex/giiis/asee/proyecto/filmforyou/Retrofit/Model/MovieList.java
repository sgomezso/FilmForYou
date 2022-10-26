package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("items")
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
