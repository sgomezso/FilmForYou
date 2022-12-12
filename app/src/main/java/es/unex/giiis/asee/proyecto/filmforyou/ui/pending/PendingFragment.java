package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.LoadingDialog;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentFavoritesBinding;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.FavoritesViewModel;
import es.unex.giiis.asee.proyecto.filmforyou.ui.favorites.UserMovieRepositoryFavorite;

public class PendingFragment extends Fragment implements  MoviesAdapter.OnListInteractionListener {

    private FragmentPendingBinding binding;
    private MoviesAdapter adapter;
    private PendingViewModel PendingViewModel;
    RecyclerView recyclerMovies;
    private LinearLayoutManager linearLayoutManager;
    private List<Movie> pendingMovies;

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
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoadingDialog();
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        recyclerMovies = view.findViewById(R.id.pendingList);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerMovies.setLayoutManager(linearLayoutManager);
        UserMovieRepositoryPending mPendingRepository = new UserMovieRepositoryPending(getActivity());

        SharedPreferences settings = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);
        Long userId = settings.getLong("userId", -1);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        PendingViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.pendingViewModelFactory).get(PendingViewModel.class);

        pendingMovies = new ArrayList<>();
        adapter = new MoviesAdapter(pendingMovies, this);

        PendingViewModel.getPendingMovies(userId).observe(getViewLifecycleOwner(), movies -> {
            if(movies != null) {
                adapter.clear();
                adapter.swap(movies);
            }
        });

        binding.pendingList.setAdapter(adapter);

        return view;
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