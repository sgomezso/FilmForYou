package es.unex.giiis.asee.proyecto.filmforyou.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

 import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieList;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "edad")
    private String edad;
    @ColumnInfo(name = "directorFav")
    private String directorFav;
    @ColumnInfo(name = "generoFav")
    private String generoFav;
    @ColumnInfo(name = "peliculaFav")
    private String peliculaFav;
    @ColumnInfo(name = "imagen")
    private String imagen;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public User(){}

    public String getEdad() {
        return edad;
    }

    public User(@NonNull Long id,String email, String username, String password, String edad, String directorFav, String generoFav, String peliculaFav, String imagen) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.edad = edad;
        this.directorFav = directorFav;
        this.generoFav = generoFav;
        this.peliculaFav = peliculaFav;
        this.imagen = imagen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDirectorFav() {
        return directorFav;
    }

    public void setDirectorFav(String directorFav) {
        this.directorFav = directorFav;
    }

    public String getGeneroFav() {
        return generoFav;
    }

    public void setGeneroFav(String generoFav) {
        this.generoFav = generoFav;
    }

    public String getPeliculaFav() {
        return peliculaFav;
    }

    public void setPeliculaFav(String peliculaFav) {
        this.peliculaFav = peliculaFav;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setId(Long id) {
        this.id = id;
    }
}