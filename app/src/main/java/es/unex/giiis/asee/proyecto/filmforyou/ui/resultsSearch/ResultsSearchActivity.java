package es.unex.giiis.asee.proyecto.filmforyou.ui.resultsSearch;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.Adapters.SearchMovieResultsAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.ActivityResultsSearchBinding;
import es.unex.giiis.asee.proyecto.filmforyou.loadingDialog;
import es.unex.giiis.asee.proyecto.filmforyou.ui.search.SearchViewModel;

public class ResultsSearchActivity extends AppCompatActivity implements SearchMovieResultsAdapter.OnListInteractionListener, MoviesAdapter.OnListInteractionListener {
    private ActivityResultsSearchBinding binding;
    public String resultSearch;
    private SearchMovieResultsAdapter adapter;
    private SearchViewModel searchViewModel;
    private FloatingActionButton back;
    private TextView searchExpr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultsSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadingDialog loadingDialog = new loadingDialog(this);
        loadingDialog.startLoadingDialog();
        resultSearch = (String) getIntent().getSerializableExtra("busqueda");
        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        searchViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.searchViewModelFactory).get(SearchViewModel.class);
        binding.searchResults.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false));
        List<Movie> resultsList = new ArrayList<>();
        adapter = new SearchMovieResultsAdapter(resultsList, this);
        searchViewModel.getSearchResults(resultSearch);
        searchViewModel.movieList.observe(this, movies -> {
            if (movies != null) {
                Log.i("Update data", "Search");
                adapter.swap(movies);
            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.dismisDialog();
            }
        }, 3000);
        binding.searchResults.setAdapter(adapter);
        searchExpr = findViewById(R.id.searchExp);
        searchExpr.setText(resultSearch);
        back = findViewById(R.id.back);
        back.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
        back.setRippleColor(Color.BLACK);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onListInteraction(String url) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}