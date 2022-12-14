package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class ProfileViewModel extends ViewModel {

    public MutableLiveData<User> mUser = new MutableLiveData<>();
    public UserRepository userRepository;

    public ProfileViewModel(UserRepository mRepository) {
        this.userRepository = mRepository;

    }

    public void getUser() {
        User user = userRepository.getCurrentUser();
        mUser.postValue(user);
    }

    public void updateImage(String img) {
        userRepository.updateImage(img);
    }

    public void closeSession() {
        userRepository.closeSession();
    }
}