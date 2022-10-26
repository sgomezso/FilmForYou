package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentMovieListBinding;

public class MovieListFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {

    private MovieListViewModel movieListViewModel;
    private FragmentMovieListBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Repository repository;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieListViewModel =
                new ViewModelProvider(this).get(MovieListViewModel.class);

        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = (RecyclerView) recyclerView.findViewById(R.id.movieList);
//        recyclerView.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(MainActivity.class);
//        recyclerView.setLayoutManager(layoutManager);
        MoviesAdapter mAdapter = new MoviesAdapter(new ArrayList<Movie>(), this);

//        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable((repos)-> mAdapter.swap(repos)));
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                repository.getTopMovies(new Repository.RepositoryListener() {
                    @Override
                    public void onTopMoviesResponse(List<Movie> top250movies) {
                        mAdapter.swap(top250movies);
                    }
                    @Override
                    public void onMovieDetailResponse(MovieDetail movieDetail) {
                    }
                }); ;
            }
        });


        recyclerView.setAdapter(mAdapter);
        return root;
    }

    public void getMovies (){
        Repository repository = new Repository();
        repository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {

                MoviesAdapter moviesAdapter = new MoviesAdapter(top250movies, new MoviesAdapter.OnListInteractionListener() {
                    @Override
                    public void onListInteraction(String url) {

                    }

                });
                // no puedes hacer peticiones de red en hilo principal

            }

            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {

            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListInteraction(String url) {

    }
}