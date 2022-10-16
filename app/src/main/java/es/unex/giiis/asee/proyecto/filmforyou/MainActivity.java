package es.unex.giiis.asee.proyecto.filmforyou;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Interface.topMovies;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.top250movies;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.topMoviesObject;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        getTopMovies();
    }
    private void getTopMovies(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://imdb-api.com/en/API/").addConverterFactory(GsonConverterFactory.create()).build();
        topMovies topMoviesInterface = retrofit.create(topMovies.class);
        Call<topMoviesObject> call = topMoviesInterface.getTopMovies();
        call.enqueue(new Callback<topMoviesObject>() {
            @Override
            public void onResponse(Call<topMoviesObject> call, Response<topMoviesObject> response) {
                if(!response.isSuccessful()){
                    Log.i("Error response", response.body().getErrorMessage());
                }else{
                    for (top250movies movie : response.body().getItems())
                    Log.i("Movie", movie.getFullTitle());
                }
            }
            @Override
            public void onFailure(Call<topMoviesObject> call, Throwable t) {
                Log.i("Error failure", t.getMessage());
            }
        });
    }

}