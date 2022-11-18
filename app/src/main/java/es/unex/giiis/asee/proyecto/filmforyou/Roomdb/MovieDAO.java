package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;

@Dao
public interface MovieDAO {

    @Query("select * from movies order by title asc")
    public LiveData<List<Movie>> getTop250Movies();

    @Query("select * from movies where id = :uId")
    public LiveData<List<Movie>> getMovie(String uId);


}
