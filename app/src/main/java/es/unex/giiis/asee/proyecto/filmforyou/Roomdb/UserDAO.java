package es.unex.giiis.asee.proyecto.filmforyou.Roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;

@Dao
public interface UserDAO {

    @Query("select * from movies order by title asc")
    public LiveData<List<Movie>> getTop250Movies();

    @Query("select * from User where id = :id")
    public User getUserById(String id);

    @Query("select * from user where username= :username and password = :password")
    User getUser(String username, String password);

    @Query("Insert into User (username,password,email,edad,generoFav,peliculaFav,directorFav,imagen) values (:username, :password,:email,:edad,:generoFav,:peliculaFav,:directorFav,:imagen)")
    void CreateNewUser(String username, String password,String email, String edad, String generoFav, String peliculaFav,String directorFav,String imagen);

    @Query("select id from User where username = :username and password= :password")
    long getUserId(String username, String password);



//    @Query("select * from User")
//    public LiveData<List<UserFavoritesMovies>> getFavoriteMoviesUserLogged();
//
//    @Query("select * from User")
//    public LiveData<List<UserPendingMovies>> getPendingMoviesUserLogged();
//
//    @Query("Insert into UserFavoritesMovies (id,movieId) values (:id,:idMovie)")
//    void addFav(String id, String idMovie);

}
