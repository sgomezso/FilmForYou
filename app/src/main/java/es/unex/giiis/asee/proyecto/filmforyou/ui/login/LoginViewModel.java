package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;

public class LoginViewModel extends ViewModel {
    private UserRepository userRepository;
    public MutableLiveData<User> user = new MutableLiveData<>();


    public LoginViewModel(UserRepository mUserRepository) {
        this.userRepository = mUserRepository;
    }

    public void createUser (String username, String Password, String email, String generoFav, String peliculaFav, String directorFav, String imagen){
        userRepository.registerUser(username, Password,email,generoFav,peliculaFav,directorFav,imagen);
        user.postValue(userRepository.getUser(userRepository.getUserId(username, Password)));
    }

    }
