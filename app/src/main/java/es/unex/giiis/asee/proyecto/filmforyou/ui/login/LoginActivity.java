package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.R;

public class LoginActivity extends AppCompatActivity {

    private UserRepository userRepository ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userRepository  =  UserRepository.getInstance(this);
        //llevar a otro hilo
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (userRepository.checkIsUserIsLoged() ==true){
                    Intent i = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.activityLayout,new LoginFragment()).addToBackStack(null).commit();
                }
            }
        });

    }

}