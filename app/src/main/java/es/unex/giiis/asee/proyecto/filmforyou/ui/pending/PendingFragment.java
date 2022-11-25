package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;

public class PendingFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {

    private FragmentPendingBinding binding;
    private final Repository mRepository = new Repository() ;
    private MoviesAdapter adapter;

    // Required empty public favorites constructor
    public PendingFragment() {
    }

    public static PendingFragment newInstance(String param1, String param2) {
        PendingFragment fragment = new PendingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_favorites, container, false);
        AppExecutors.getInstance().networkIO().execute(() -> mRepository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
                binding.pendMovieList.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false));
                //mRepository.
                for (Movie movie: top250movies) {

                }
                adapter = new MoviesAdapter(top250movies, PendingFragment.this);
                binding.pendMovieList.setAdapter(adapter);
            }
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {}
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}

        }));
        return vista;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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