package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

@androidx.room.Database(entities = {Movie.class, User.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "movie.db").allowMainThreadQueries().build();
        return instance;
    }

    public abstract MovieDAO movieDAO();
    public abstract UserDAO userDAO();

}
