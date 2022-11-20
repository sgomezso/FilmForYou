package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

public class Movie {
    public String id;
    public String rank;
    public String title;
    public String fullTitle;
    public String year;

    public void setId(String id) {
        this.id = id;
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

    public String image;
    public String crew;
    public String imDbRating;
    public String imDbRatingCount;

    public String getId() {
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
}
