package es.unex.giiis.asee.proyecto.filmforyou.ui.profileDetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Profile;


public class MostrarProfileActivity extends AppCompatActivity {

    private TextView nombreU;
    private TextView edad;
    private TextView generoFav;
    private TextView peliculaFav;
    private TextView directorFav;
    FloatingActionButton EFbutton;
    private Profile profile;
    //private MostrarProfileViewModel mViewModel;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.fragment_profile);

        nombreU = (TextView) findViewById(R.id.textNombreU);
        edad = (TextView) findViewById(R.id.textEdad);
        generoFav = (TextView) findViewById(R.id.textGeneroFav);
        peliculaFav = (TextView) findViewById(R.id.textPeliculaFav);
        directorFav = (TextView) findViewById(R.id.textDirectorFav);

        //Obtener los perfiles
       // AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
       // mViewModel = new ViewModelProvider(this, appContainer.mostrarProfileFactory).get(MostrarProfileViewModel.class);

        profile = (Profile) getIntent().getSerializableExtra("profile");

        if(profile.getNombreU()==null){
            nombreU.setText("Usuario");
        }else{
            nombreU.setText(profile.getNombreU());
        }

        if(profile.getEdad()==0){
            edad.setText("0");
        }else{
            edad.setText(profile.getEdad());
        }

        if(profile.getGeneroFav()==null){
            generoFav.setText("Ninguno");

        }else{
            generoFav.setText(profile.getGeneroFav());
        }

        if(profile.getPeliculaFav()==null){
            peliculaFav.setText("Ninguno");
        }else{
            peliculaFav.setText(profile.getPeliculaFav());
        }

        if(profile.getDirectorFav()==null){
            directorFav.setText("Ninguno");
        }else{
            directorFav.setText(profile.getDirectorFav());
        }

    }

}