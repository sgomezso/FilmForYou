package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

@Dao
public interface UserPendingMoviesDAO {

    @Query("select * from userPendingMovies where idUser = :id")
    public List<UserPendingMovies> getFavoriteMoviesUserLogged(String id);
}
