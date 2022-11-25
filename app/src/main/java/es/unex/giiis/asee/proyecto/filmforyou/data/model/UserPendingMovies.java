package es.unex.giiis.asee.proyecto.filmforyou.data.model;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

@Entity(tableName = "userPendingMovies", primaryKeys = {"idUser", "idMovie"})
public class UserPendingMovies {

    @NonNull
    private Long idUser;
    @NonNull
    private String idMovie;

    public UserPendingMovies(@NonNull Long idUser, @NonNull String idMovie) {
        this.idUser = idUser;
        this.idMovie = idMovie;
    }

    @NonNull
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(@NonNull Long idUser) {
        this.idUser = idUser;
    }

    @NonNull
    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(@NonNull String idMovie) {
        this.idMovie = idMovie;
    }
}
