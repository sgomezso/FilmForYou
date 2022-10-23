package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

public class searchMovie {
    public String id;
    public String image;
    public String title;
    public String description;

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "searchMovie{" +
                "id='" + id + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
