package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserFavoriteMoviesDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

public class UserMovieRepository {

    public UserFavoriteMoviesDAO database;
    public SharedPreferences preference;

    public UserMovieRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").build().userFavoriteMoviesDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public List<UserFavoritesMovies> loadFavoriteMoviesByUser(Long userId) {
        return database.loadFavoriteMoviesByUser(userId.toString());
    }

    public boolean checkFav(Long idUser, String idMovie) {
        if (database.checkUserFavoriteMovie(idUser.toString(), idMovie) ==null)
            return false;
        else
            return true;
    }

    public void addFav(Long idUser, String idMovie) {
        database.insertFavorite(idUser.toString(), idMovie);
    }

    public void deleteFav(Long idUser, String idMovie) {
        database.deleteFavorite(idUser.toString(), idMovie);
    }
}
