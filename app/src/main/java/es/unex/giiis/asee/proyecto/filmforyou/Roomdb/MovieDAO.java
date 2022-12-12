package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

@Dao
public interface MovieDAO {

    @Query("DELETE FROM MOVIES")
    void deleteMovies();

    @Query("SELECT * FROM MOVIES")
    LiveData<List<Movie>>  getCurrentsMovies();


//    @Query("select * from movies where id = :uId")
//    public <List<Movie>> getMovie(String uId);


}
