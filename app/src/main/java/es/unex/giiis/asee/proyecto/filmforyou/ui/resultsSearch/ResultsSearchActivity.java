package es.unex.giiis.asee.proyecto.filmforyou.ui.resultsSearch;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.Adapters.SearchMovieResultsAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.ActivityResultsSearchBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListFragment;

public class ResultsSearchActivity extends AppCompatActivity implements SearchMovieResultsAdapter.OnListInteractionListener {
    private ActivityResultsSearchBinding binding;
    private final Repository mRepository = new Repository() ;
    public String resultSearch;
    private SearchMovieResultsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultsSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        resultSearch = (String) getIntent().getSerializableExtra("busqueda");
        AppExecutors.getInstance().networkIO().execute(() -> mRepository.getSearchResultsExpresion(resultSearch, new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {}
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {
                if(resultsSearch.size() == 0){
                    setContentView(R.layout.empty_search);
                }else {
                    binding.searchResults.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(), LinearLayoutManager.VERTICAL, false));
                    adapter = new SearchMovieResultsAdapter(resultsSearch, ResultsSearchActivity.this);
                    binding.searchResults.setAdapter(adapter);
                }
            }
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}
        }));


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
}