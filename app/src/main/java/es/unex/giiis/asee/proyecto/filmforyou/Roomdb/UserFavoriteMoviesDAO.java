package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;

@Dao
public interface UserFavoriteMoviesDAO {

    @Query("select * from userFavoriteMovies where idUser = :id")
    public List<UserFavoritesMovies> getFavoriteMoviesUserLogged(String id);

    @Query("select * from userFavoriteMovies where idUser = :id")
    public LiveData<List<UserFavoritesMovies>> loadFavoriteMoviesByUser(String id);

    @Query("select * from userFavoriteMovies where idUser = :idUser and idMovie = :idMovie")
    public UserFavoritesMovies checkUserFavoriteMovie(String idUser, String idMovie);

    @Query("insert into userFavoriteMovies(idUser, idMovie) values (:idUser, :idMovie)")
    public void insertFavorite(String idUser, String idMovie);

    @Query("delete from userFavoriteMovies where idUser = :idUser and idMovie = :idMovie")
    public void deleteFavorite(String idUser, String idMovie);
}
