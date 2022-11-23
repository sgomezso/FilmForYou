package es.unex.giiis.asee.proyecto.filmforyou.ui.favorites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.FavoriteMoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserFavoritesMovies;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentFavoritesBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MostrarMovieActivity;

public class FavoritesFragment extends Fragment implements FavoriteMoviesAdapter.OnListInteractionListener {

    private FavoritesViewModel favoritesViewModel;
    private FragmentFavoritesBinding binding;
    private FavoriteMoviesAdapter favMoviesAdapter;
    RecyclerView recyclerMovies;
    private LinearLayoutManager linearLayoutManager;
    private List<UserFavoritesMovies> favoriteMovies;
    private OnFragmentInteractionListener mListener;

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
        //favoritesViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        //binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        View vista = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerMovies = (RecyclerView) vista.findViewById(R.id.recyclerId);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMovies.setLayoutManager(linearLayoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        favoritesViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.favoritesVMFactory).get(FavoritesViewModel.class);

        favoriteMovies = new ArrayList<UserFavoritesMovies>();
        favMoviesAdapter = new FavoriteMoviesAdapter(favoriteMovies, this);
        favoritesViewModel.getFavoriteMovies().observe(getViewLifecycleOwner(), movie -> {
            favMoviesAdapter.clear();
            favMoviesAdapter.swap(movie);
        });

        favMoviesAdapter.setItemClickListener(new FavoriteMoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                // activity que esta siendo implementada por ventura
                Intent intent = new Intent(getContext(), MostrarMovieActivity.class);
                intent.putExtra("Movie", (Serializable) movie);
                startActivity(intent);
            }
        });

        recyclerMovies.setAdapter(favMoviesAdapter);
        return vista;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onListInteraction(String url) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}