package es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model;

import java.util.List;

public class topMoviesObject {
    public String errorMessage;
    public List<top250movies> items;

    public String getErrorMessage() {
        return errorMessage;
    }
    public List<top250movies> getItems() {
        return items;
    }
}
