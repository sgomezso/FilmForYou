package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.UserDAO;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;

public class UserRepository {
    UserDAO database;
    SharedPreferences preference;

    public UserRepository(Context context) {
        database = Room.databaseBuilder(context, Database.class, "database").build().userDAO();
        preference = context.getSharedPreferences("preference", Context.MODE_PRIVATE);
    }

    public boolean checkIsUserIsLoged() {
        return preference.getLong("userId", 0) != 0;
    }

    public User getUser(String id) {
        return database.getUserById(id);
    }

    public boolean checkUser(String username, String password) {
        return database.getUser(username, password) !=null;
    }

    public void registerUser(String username, String Password) {
            database.CreateNewUser(username, Password);
    }

    public long getUserId(String username, String password) {
        return database.getUserId(username,password);
    }
}