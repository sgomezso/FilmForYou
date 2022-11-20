package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

import java.io.Serializable;

public class MovieDetail implements Serializable {
    public String id;
    public String title;
    public String originalTitle;
    public String fullTitle;
    public String year;
    public String releaseDate;
    public String runtimeStr;
    public String plot;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntimeStr(String runtimeStr) {
        this.runtimeStr = runtimeStr;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

    public void setImDbRatingVotes(String imDbRatingVotes) {
        this.imDbRatingVotes = imDbRatingVotes;
    }

    public void setErrorMesssage(String errorMesssage) {
        this.errorMesssage = errorMesssage;
    }

    public String awards;
    public String image;
    public String directors;
    public String stars;
    public String genres;
    public String languages;
    public String imDbRating;
    public String imDbRatingVotes;
    public String errorMesssage;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public String getYear() {
        return year;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getRuntimeStr() {
        return runtimeStr;
    }

    public String getPlot() {
        return plot;
    }

    public String getAwards() {
        return awards;
    }

    public String getImage() {
        return image;
    }

    public String getDirectors() {
        return directors;
    }

    public String getStars() {
        return stars;
    }

    public String getGenres() {
        return genres;
    }

    public String getLanguages() {
        return languages;
    }

    public String getImdbRating() {
        return imDbRating;
    }

    public String getImdbRatingVotes() {
        return imDbRatingVotes;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }

    @Override
    public String toString() {
        return "MovieDetail{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", fullTitle='" + fullTitle + '\'' +
                ", year='" + year + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", runtimeStr='" + runtimeStr + '\'' +
                ", plot='" + plot + '\'' +
                ", awards='" + awards + '\'' +
                ", image='" + image + '\'' +
                ", directors='" + directors + '\'' +
                ", stars='" + stars + '\'' +
                ", genres='" + genres + '\'' +
                ", languages='" + languages + '\'' +
                ", imdbRating='" + imDbRating + '\'' +
                ", imdbRatingVotes='" + imDbRatingVotes + '\'' +
                ", errorMesssage='" + errorMesssage + '\'' +
                '}';
    }
}
