package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieRepositoryFavorite;
import es.unex.giiis.asee.proyecto.filmforyou.ui.pending.UserMovieRepositoryPending;


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
        imageReparto = (ImageView) findViewById(R.id.idImagenReparto);

        UserMovieRepositoryFavorite userMovieRepositoryFavorite = new UserMovieRepositoryFavorite(this);
        UserMovieRepositoryPending userMovieRepositoryPending = new UserMovieRepositoryPending(this);
        Movie movie = (Movie) getIntent().getSerializableExtra("Movie");
        SharedPreferences settings = getSharedPreferences("preference", Context.MODE_PRIVATE);
        Long userId = settings.getLong("userId", -1);

        findViewById(R.id.addFavButton).setOnClickListener(v -> AppExecutors.getInstance().diskIO().execute(() -> {
            if (userMovieRepositoryFavorite.checkFav(userId, movie.getMovieId())) {
                userMovieRepositoryFavorite.deleteFav(userId, movie.getMovieId());
            } else {
                userMovieRepositoryFavorite.addFav(userId, movie.getMovieId());
            }
        }));
        findViewById(R.id.addPendingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (userMovieRepositoryPending.checkPending(userId, movie.getMovieId())) {
                            userMovieRepositoryPending.deletePending(userId, movie.getMovieId());
                        } else {
                            userMovieRepositoryPending.addPending(userId, movie.getMovieId());
                        }
                    }
                });
            }
        });
        if (movie != null) {
            Log.i("Pulsar", movie.getTitle() + " " + movie.getRank());
        }
        if (movie.getRank() == null) {
            rank.setText("");
        } else {
            rank.setText(movie.getRank());
        }

        if (movie.getYear() == null) {
            year.setText("2022");
        } else {
            year.setText(movie.getYear());
        }

//        if(movie.getDirectors()==null){
//            directors.setText("No hay directores");
//
//        }else{
//            directors.setText(movie.getDirectors());
//        }

        if (movie.getImDbRating() == null) {
            imDbRating.setText("0");
        } else {
            imDbRating.setText(movie.getImDbRating());
        }

        if (movie.getImDbRatingCount() == null) {
            imDbRatingCount.setText("0");
        } else {
            imDbRatingCount.setText(movie.getImDbRatingCount());
        }

        if (movie.getImage() != null) {
            Picasso.get().load(movie.getImage()).into(image);
        } else {
            Picasso.get().load("https://png.pngtree.com/element_our/png_detail/20181227/movie-icon-which-is-designed-for-all-application-purpose-new-png_287896.jpg").into(image);
        }

        if (movie.getCrew() == null) {
            crew.setText("Ninguno");
        } else {
            crew.setText(movie.getCrew());
        }
        if(movie.getFullTitle() != null){
            getSupportActionBar().setTitle(movie.getFullTitle());
        }

        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/74/74472.png").into(imageReparto);

    }

    /*
       CONTROLA EL BOTÓN QUE PULSAMOS EN EL MENÚ
        */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

}