package es.unex.giiis.asee.proyecto.filmforyou.data.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class UserFavoritesMovies {

    @Embedded
    public User user;
    @Relation(parentColumn = "id",entityColumn = "movieId")
    public Movie movie;

}
