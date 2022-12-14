package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

@Dao
public interface MovieDAO {

    @Query("DELETE FROM MOVIES")
    void deleteMovies();

    @Query("SELECT * FROM MOVIES")
    List<Movie> getCurrentsMovies();

    @Insert
    void insertMovies(List<Movie> movies);

    @Update
    public void updateMovie(Movie item);


//    @Query("select * from movies where id = :uId")
//    public <List<Movie>> getMovie(String uId);


}
