package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.PendingMovies;

@Dao
public interface UserPendingMoviesDAO {

    @Query("select * from userPendingMovies where idUser = :id")
    public List<UserPendingMovies> getPendingMoviesUserLogged(String id);

    @Query("select * from userPendingMovies where idUser = :id")
    public List<UserPendingMovies> loadPendingMoviesByUser(String id);

    @Query("select * from userPendingMovies where idUser = :idUser and idMovie = :idMovie")
    public UserPendingMovies checkUserPendingMovie(String idUser, String idMovie);

    @Query("insert into userPendingMovies(idUser, idMovie) values (:idUser, :idMovie)")
    public void insertPending(String idUser, String idMovie);

    @Query("delete from userPendingMovies where idUser = :idUser and idMovie = :idMovie")
    public void deletePending(String idUser, String idMovie);

    @Query("select * from movies m join userPendingMovies p where m.id = p.idMovie")
    public List<PendingMovies> getPendingMovies();
}

