package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.MoviesRepository;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentMovieListBinding;
import es.unex.giiis.asee.proyecto.filmforyou.loadingDialog;


public class MovieListFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {

    private FragmentMovieListBinding binding;
    private MoviesRepository mRepository;
    private MoviesAdapter adapter;
    private MovieListViewModel movieListViewModel;
    private RecyclerView recyclerMovies;
    private LinearLayoutManager LayoutManager;
    private loadingDialog loadingDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerMovies = view.findViewById(R.id.movieList);
        LayoutManager = new LinearLayoutManager(getActivity());
        recyclerMovies.setLayoutManager(LayoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        movieListViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.movieListViewModelFactory).get(MovieListViewModel.class);

        List<Movie> movieList = new ArrayList<>();
        adapter = new MoviesAdapter(movieList, this);
        mRepository = MoviesRepository.getInstance(Database.getInstance(getContext()).movieDAO(), RepositoryNetworkDataSource.getInstance());
        loadingDialog = new loadingDialog(getActivity());
        loadingDialog.startLoadingDialog();
        movieListViewModel.getTopMovies();

        observeViewModel();
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

    public void observeViewModel() {
         movieListViewModel.topList.observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                Log.i("Update data", "NEW MOVIE LIST");
                adapter.swap(movies);
                loadingDialog.dismisDialog();
            }
        });
        recyclerMovies.setAdapter(adapter);
    }
}