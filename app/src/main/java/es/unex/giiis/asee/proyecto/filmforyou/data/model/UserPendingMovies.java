package es.unex.giiis.asee.proyecto.filmforyou.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class UserPendingMovies extends Movie{

    @Embedded(prefix = "pend_")
    public User user;
    @Relation(parentColumn = "id", entityColumn = "id")
    public List<Movie> movies;

    public UserPendingMovies(@NonNull String id, String rank, String title, String fullTitle, String year, String image, String crew, String imDbRating, String imDbRatingCount) {
        super(id, rank, title, fullTitle, year, image, crew, imDbRating, imDbRatingCount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "UserPendingMovies{" +
                "user=" + user +
                ", movies=" + movies +
                '}';
    }
}
