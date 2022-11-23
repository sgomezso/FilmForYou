package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

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

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.PendingMoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.UserPendingMovies;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MostrarMovieActivity;

public class PendingFragment extends Fragment implements PendingMoviesAdapter.OnListInteractionListener {

    private PendingViewModel pendingViewModel;
    private FragmentPendingBinding binding;
    private PendingMoviesAdapter pendingMoviesAdapter;
    RecyclerView recyclerMovies;
    private LinearLayoutManager linearLayoutManager;
    private List<UserPendingMovies> pendingMovies;
    private PendingFragment.OnFragmentInteractionListener mListener;

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
        // pendingViewModel = new ViewModelProvider(this).get(PendingViewModel.class);
        // binding = FragmentPendingBinding.inflate(inflater, container, false);

        View vista = inflater.inflate(R.layout.fragment_pending, container, false);
        recyclerMovies = (RecyclerView) vista.findViewById(R.id.recyclerId);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMovies.setLayoutManager(linearLayoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        pendingViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.pendingVMFactory).get(PendingViewModel.class);

        pendingMovies = new ArrayList<UserPendingMovies>();
        pendingMoviesAdapter = new PendingMoviesAdapter(pendingMovies, this);
        pendingViewModel.getPendingMovies().observe(getViewLifecycleOwner(), movie -> {
            pendingMoviesAdapter.clear();
            pendingMoviesAdapter.swap(movie);
        });

        pendingMoviesAdapter.setItemClickListener(new PendingMoviesAdapter.ItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                // activity que esta siendo implementada por ventura
                Intent intent = new Intent(getContext(), MostrarMovieActivity.class);
                intent.putExtra("Movie", (Serializable) movie);
                startActivity(intent);
            }
        });

        recyclerMovies.setAdapter(pendingMoviesAdapter);
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