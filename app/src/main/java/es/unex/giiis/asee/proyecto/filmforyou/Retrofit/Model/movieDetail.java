package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

public class movieDetail {
    public String id;
    public String title;
    public String originalTitle;
    public String fullTitle;
    public String year;
    public String releaseDate;
    public String runtimeStr;
    public String plot;
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
        return "movieDetail{" +
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
