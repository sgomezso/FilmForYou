package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;

public class UserRepository {
    public UserDAO database;
    public SharedPreferences preference;

    public UserRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").allowMainThreadQueries().build().userDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public boolean checkIsUserIsLoged() {
        return preference.getLong("userId", 0) != 0;
    }

    public User getUser(Long id) {
        return database.getUserById(id.toString());
    }

    public boolean checkUser(String username, String password) {
        if(database.getUser(username, password) !=null){
            preference.edit().putLong("userId",getUserId(username,password)).commit();
            return true;
        } else {
            return false;
        }
    }

    public void registerUser(String username, String Password,String email, String generoFav, String peliculaFav,String directorFav,String imagen) {
            database.CreateNewUser(username, Password,email,generoFav,peliculaFav,directorFav,imagen);
            preference.edit().putLong("userId",getUserId(username,Password)).commit();

    }

    public long getUserId(String username, String password) {
        return database.getUserId(username,password);
    }

    public User getUser(String username, String password) {
        return database.getUser(username,password);
    }

    public void updateImage(String img, Long id) {
        database.updateImage(img,id.toString());
    }
}