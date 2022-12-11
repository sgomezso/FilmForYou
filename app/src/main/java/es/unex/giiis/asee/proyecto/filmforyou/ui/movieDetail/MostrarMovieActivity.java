package es.unex.giiis.asee.proyecto.filmforyou.ui.movieDetail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;


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
    private TextView director;
    FloatingActionButton EFbutton;
    private Movie movie;
    //private MostrarMovieViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Movie","Pasa por aqui");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.fragment_movie);

        UserRepository userRepository = new UserRepository(this);

        rank = (TextView) findViewById(R.id.textRankIMDB);
        year = (TextView) findViewById(R.id.textYear);
        fullTitle = (TextView) findViewById(R.id.textDirector);
        imDbRating = (TextView) findViewById(R.id.textRating);
        imDbRatingCount = (TextView) findViewById(R.id.textRatingCount);
        crew = (TextView) findViewById(R.id.textReparto);
        image = (ImageView) findViewById(R.id.idImagenMovie);
        director = (TextView) findViewById(R.id.textDirector);

        movie = (Movie) getIntent().getSerializableExtra("movie");


        Button addFav = findViewById(R.id.addFav);
        SharedPreferences settings = getSharedPreferences("preference", Context.MODE_PRIVATE);

        if(movie.getRank()==null){
            rank.setText("0º");
        }else{
            rank.setText(movie.getRank());
        }

        if(movie.getYear()==null){
            year.setText("0");
        }else{
            year.setText(movie.getYear());
        }

        if(movie.getFullTitle()==null){
            fullTitle.setText("0");

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


    /*private void deleteComment() {
        movie.setComentario(null);
        mViewModel.actualizarMovie(movie);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Comentario eliminado con éxito", Toast.LENGTH_LONG).show();
    }

    public void addComent(){
        EditText comentario;
        comentario = new EditText(MostrarMovieActivity.this);
        AlertDialog.Builder alertaP = new AlertDialog.Builder(MostrarMovieActivity.this);
        alertaP.setView(comentario)
                .setMessage("Comentar")
                .setCancelable(false)
                .setPositiveButton("Comentar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        movie.setComentario(comentario.getText().toString());
                        mViewModel.actualizarMovie(movie);
                        Toast.makeText(getApplicationContext(), "Comentario añadido", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog tituloP = alertaP.create();
        tituloP.setTitle("Añadir comentario al movie");
        tituloP.show();
    }*/
}