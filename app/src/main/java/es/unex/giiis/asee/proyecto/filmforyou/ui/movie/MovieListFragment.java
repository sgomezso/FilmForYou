package es.unex.giiis.asee.proyecto.filmforyou.ui.movie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentMovieListBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movieDetail.MostrarMovieActivity;

public class MovieListFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {

    private MovieListViewModel movieListViewModel;
    private FragmentMovieListBinding binding;
    private Repository mRepository = new Repository() ;
    private MoviesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieListViewModel =
                new ViewModelProvider(this).get(MovieListViewModel.class);

        binding = FragmentMovieListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable((repos)-> mAdapter.swap(repos)));
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                mRepository.getTopMovies(new Repository.RepositoryListener() {
                    @Override
                    public void onTopMoviesResponse(List<Movie> top250movies) {
                        binding.movieList.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false));
                        adapter = new MoviesAdapter(top250movies, MovieListFragment.this);
                            Log.i("MovieList","No es null");
                            adapter.setItemClickListener(new MoviesAdapter.ItemClickListener() {
                                public void onItemClick(Movie movie) {
                                    Log.i("MovieList", "Entra");
                                    Intent intent = new Intent(getContext(), MostrarMovieActivity.class);
                                    intent.putExtra("movie", (Serializable) movie);
                                    startActivity(intent);
                                }

                            });
                        binding.movieList.setAdapter(adapter);
                    }
                    @Override
                    public void onMovieDetailResponse(MovieDetail movieDetail) {

                    }
                }); ;
            }
        });
        return root;
    }

    public void getMovies (){
        Repository repository = new Repository();
        repository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
                adapter = new MoviesAdapter(top250movies, MovieListFragment.this );
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