package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Repository;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.MovieDetail;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {
//implements FavoriteMoviesAdapter.OnListInteractionListener
    private FragmentFavoritesBinding binding;
    private final UserMovieRepository mRepository = new UserMovieRepository(getActivity()) ;
    private final Repository apiRepository = new Repository();
    private MoviesAdapter adapter;

    // Required empty public favorites constructor
    public FavoritesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment= new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_favorites, container, false);
        AppExecutors.getInstance().networkIO().execute(() -> apiRepository.getTopMovies(new Repository.RepositoryListener() {
            @Override
            public void onTopMoviesResponse(List<Movie> top250movies) {
                binding.favMovieList.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false));
                SharedPreferences settings = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);
                Long userId = settings.getLong("userId",-1);

                List<UserFavoritesMovies> userFavoritesMoviesList = mRepository.loadFavoriteMoviesByUser(userId);
                List<Movie> movies = new ArrayList<>();
                for (Movie movie: top250movies) {
                    for (UserFavoritesMovies userFavoritesMovies : userFavoritesMoviesList) {
                        if(userFavoritesMovies.getIdMovie().equals(movie.getMovieId())) {
                            movies.add(movie);
                        }
                    }
                }
                adapter = new MoviesAdapter(top250movies, FavoritesFragment.this);
                binding.favMovieList.setAdapter(adapter);
            }
            @Override
            public void onSearchResultsExpresionResponse(List<Movie> resultsSearch) {}
            @Override
            public void onMovieDetailResponse(MovieDetail movieDetail) {}

        }));
//
//        recyclerMovies.setAdapter(favMoviesAdapter);
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

//    @Override
//    public void onListInteraction(String url) {
//
//    }
}