package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.loadingDialog;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.RepositoryNetworkDataSource;
import es.unex.giiis.asee.proyecto.filmforyou.Roomdb.Database;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentFavoritesBinding;

public class FavoritesFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {
    //implements FavoriteMoviesAdapter.OnListInteractionListener
    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;
    private UserMovieFavoritesRepository userMovieFavoritesRepository;
    private LinearLayoutManager layoutManager;
    private List<UserFavoritesMovies> favoriteMovies;
    private loadingDialog loadingDialog;
    private MoviesAdapter adapter;

    // Required empty public favorites constructor
    public FavoritesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        favoritesViewModel.getFavoriteMovies();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFavoritesBinding.bind(view);

        layoutManager = new LinearLayoutManager(getActivity());
        binding.favList.setLayoutManager(layoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        favoritesViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.favoritesViewModelFactory).get(FavoritesViewModel.class);

        List<Movie> movieList = new ArrayList<>();
        adapter = new MoviesAdapter(movieList,this);
        binding.favList.setAdapter(adapter);

        userMovieFavoritesRepository = UserMovieFavoritesRepository.getInstance(getContext());
        loadingDialog = new loadingDialog(getActivity());
        loadingDialog.startLoadingDialog();

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
        favoritesViewModel.listFav.observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                Log.i("Update data", "NEW FAVORITE MOVIE");
                adapter.swap(movies);
                loadingDialog.dismisDialog();
            }
        });
    }
}