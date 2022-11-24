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
    private TextView year;
    private ImageView image;
    private ImageView imageReparto;
    private TextView crew;
    private TextView imDbRating;
    private TextView imDbRatingCount;
    private TextView directors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.movie_activity);

        rank = (TextView) findViewById(R.id.textRankIMDB);
        year = (TextView) findViewById(R.id.textYear);
        directors = (TextView) findViewById(R.id.textDirector);
        imDbRating = (TextView) findViewById(R.id.textRating);
        imDbRatingCount = (TextView) findViewById(R.id.textRatingCount);
        crew = (TextView) findViewById(R.id.textReparto);
        image = (ImageView) findViewById(R.id.idImagenMovie);
        imageReparto=(ImageView) findViewById(R.id.idImagenReparto);


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

//        if(movie.getDirectors()==null){
//            directors.setText("No hay directores");
//
//        }else{
//            directors.setText(movie.getDirectors());
//        }

        if(movie.getImDbRating()==null){
            imDbRating.setText("0");
        }else{
            imDbRating.setText(movie.getImDbRating());
        }

        if(movie.getImDbRatingCount()==null){
            imDbRatingCount.setText("0");
        }else{
            imDbRatingCount.setText(movie.getImDbRatingCount());
        }

        if(movie.getImage() != null){
            Picasso.get().load(movie.getImage()).into(image);
        }else{
            Picasso.get().load("https://png.pngtree.com/element_our/png_detail/20181227/movie-icon-which-is-designed-for-all-application-purpose-new-png_287896.jpg").into(image);
        }

        if(movie.getCrew()==null){
            crew.setText("Ninguno");
        }else{
            crew.setText(movie.getCrew());
        }

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/74/74472.png").into(imageReparto);

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