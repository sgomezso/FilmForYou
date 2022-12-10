package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.MovieDAO;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class MovieRepository {
    public MovieDAO database;
    public SharedPreferences preference;

    public MovieRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().movieDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

//    public String getMovieId (String title,String year){
//        return database.getMovieId(title,year);
//    }



}
