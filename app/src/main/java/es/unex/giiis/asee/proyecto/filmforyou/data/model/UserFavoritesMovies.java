package es.unex.giiis.asee.proyecto.filmforyou.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class UserFavoritesMovies extends Movie {

    @Embedded(prefix = "fav_")
    public User user;
    @Relation(parentColumn = "id",entityColumn = "id")
    public List<Movie> movies;

    public UserFavoritesMovies(@NonNull String id, String rank, String title, String fullTitle, String year, String image, String crew, String imDbRating, String imDbRatingCount) {
        super(id, rank, title, fullTitle, year, image, crew, imDbRating, imDbRatingCount);
    }
}
