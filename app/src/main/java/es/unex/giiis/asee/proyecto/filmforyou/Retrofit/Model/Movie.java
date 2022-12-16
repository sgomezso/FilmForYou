package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "movies")
public class Movie implements Serializable {
    @NonNull
    @PrimaryKey
    public String id;
    public String rank;
    public String title;
    public String fullTitle;
    public String year;
    public String image;
    public String crew;
    public String imDbRating;
    public String imDbRatingCount;
    private boolean esFavorito;
    private boolean esPendiente;

    public Movie(){}

    public Movie(@NonNull String movieId, String rank, String title, String fullTitle, String year, String image, String crew, String imDbRating, String imDbRatingCount) {
        this.id = movieId;
        this.rank = rank;
        this.title = title;
        this.fullTitle = fullTitle;
        this.year = year;
        this.image = image;
        this.crew = crew;
        this.imDbRating = imDbRating;
        this.imDbRatingCount = imDbRatingCount;
    }

    public String getMovieId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public String getCrew() {
        return crew;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setMovieId(@NonNull String movieId) {
        this.id = movieId;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public boolean isEsPendiente() {
        return esPendiente;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }

    public void setEsPendiente(boolean esPendiente) {
        this.esPendiente = esPendiente;
    }
}
