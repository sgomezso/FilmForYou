package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;


public class MostrarMovieActivity extends AppCompatActivity {

    private TextView id;
    private TextView rank;
    private TextView title;
    private TextView fullTitle;
    private TextView year;
    private ImageView image;
    private TextView crew;
    private TextView imDbRating;
    private TextView imDbRatingCount;
    FloatingActionButton EFbutton;
    //private MostrarMovieViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Movie","Pasa por aqui");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.movie_activity);

        rank = (TextView) findViewById(R.id.textRankIMDB);
        year = (TextView) findViewById(R.id.textYear);
        fullTitle = (TextView) findViewById(R.id.textDirector);
        imDbRating = (TextView) findViewById(R.id.textRating);
        imDbRatingCount = (TextView) findViewById(R.id.textRatingCount);
        crew = (TextView) findViewById(R.id.textReparto);
        image = (ImageView) findViewById(R.id.idImagenMovie);

        //AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        // mViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) appContainer.mostrarMovieFactory).get(MostrarMovieViewModel.class);
        Movie movie = new Movie();
        movie = (Movie) getIntent().getSerializableExtra("Movie");
        if(movie != null){
            Log.i("Pulsar", movie.getTitle() + " " + movie.getRank());
        }
        if(movie.getRank()==null){
            rank.setText("");
        }else{
            rank.setText(movie.getRank());
        }

        if(movie.getYear()==null){
            year.setText("2022");
        }else{
            year.setText(movie.getYear());
        }

        if(movie.getFullTitle()==null){
            fullTitle.setText("Ejemplo");

        }else{
            fullTitle.setText(movie.getFullTitle());
        }

        if(movie.getImDbRating()==null){
            imDbRating.setText("0");
        }else{
            imDbRating.setText(movie.getImDbRating());
        }

        if(movie.getImDbRatingCount()==null){
            imDbRatingCount.setText("0");
        }else{
            imDbRating.setText(movie.getImDbRatingCount());
        }

        if(movie.getImage() != null){
            Picasso.get().load(movie.getImage()).into(image);
        }else{
            Picasso.get().load("https://http2.mlstatic.com/storage/mshops-appearance-api/images/15/254304515/logo-2020060212005277900.png").into(image);
        }

    }
}