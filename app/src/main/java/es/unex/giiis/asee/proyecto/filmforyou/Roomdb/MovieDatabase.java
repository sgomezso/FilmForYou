package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static MovieDatabase instance;

    public static MovieDatabase getDatabase(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie.db").build();
        return instance;
    }

    public abstract MovieDAO movieDAO();
}
