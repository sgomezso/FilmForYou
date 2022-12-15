package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

public class PendingMovies {
    public String idMovie;
    public Long idUser;
    public Boolean esPendiente;
    public String imDbRatingCount;
    public String imDbRating;
    public String crew;
    public String image;
    public String year;
    public String fullTitle;
    public String title;
    public String rank;

    public Movie toMovie() {
        return new Movie(idMovie, rank, title, fullTitle, year, image, crew, imDbRating, imDbRatingCount);
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Boolean getEsPendiente() {
        return esPendiente;
    }

    public void setEsPendiente(Boolean esPendiente) {
        this.esPendiente = esPendiente;
    }

    public String getImDbRatingCount() {
        return imDbRatingCount;
    }

    public void setImDbRatingCount(String imDbRatingCount) {
        this.imDbRatingCount = imDbRatingCount;
    }

    public String getImDbRating() {
        return imDbRating;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
