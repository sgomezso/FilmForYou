package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentFavoritesBinding;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesFragment;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModel;

public class PendingFragment extends Fragment implements MoviesAdapter.OnListInteractionListener {

    private PendingViewModel pendingViewModel;
    private FragmentPendingBinding binding;
    private MoviesAdapter moviesAdapter;
    RecyclerView recyclerMovies;
    private LinearLayoutManager linearLayoutManager;
    private List<Movie> pendingMovies;
    private PendingFragment.OnFragmentInteractionListener mListener;

    public PendingFragment() {

    }

    public static PendingFragment newInstance(String param1, String param2) {
        PendingFragment fragment= new PendingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // pendingViewModel = new ViewModelProvider(this).get(PendingViewModel.class);
        // binding = FragmentPendingBinding.inflate(inflater, container, false);
        View vista = inflater.inflate(R.layout.fragment_pending, container, false);
        recyclerMovies = (RecyclerView) vista.findViewById(R.id.recyclerId);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMovies.setLayoutManager(linearLayoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        pendingViewModel = new ViewModelProvider(this, appContainer.pendingVMFactory).get(PendingViewModel.class);

        pendingMovies = new ArrayList<Movie>();
        moviesAdapter = new MoviesAdapter(pendingMovies,this);
        pendingViewModel.getPendingMovies().observe(getViewLifecycleOwner(), movie -> {
            moviesAdapter.clear();
            moviesAdapter.swap(movie);
        });

        moviesAdapter.setItemClickListener(new MoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("Movie", (Serializable) movie);
                startActivity(intent);
            }
        });

        recyclerMovies.setAdapter(moviesAdapter);
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