package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

@androidx.room.Database(entities = {Movie.class, User.class, UserFavoritesMovies.class, UserPendingMovies.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public static Database getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), Database.class, "movie.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        return instance;
    }

    public abstract MovieDAO movieDAO();
    public abstract UserDAO userDAO();

    public abstract UserFavoriteMoviesDAO userFavoriteMoviesDAO() ;
    public abstract  UserPendingMoviesDAO userPendingMoviesDAO();
}
