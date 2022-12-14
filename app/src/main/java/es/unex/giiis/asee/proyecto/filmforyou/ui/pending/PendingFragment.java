package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
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

import java.util.ArrayList;
import java.util.List;

import es.unex.giiis.asee.proyecto.filmforyou.Adapters.MoviesAdapter;
import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.Retrofit.Model.Movie;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;
import es.unex.giiis.asee.proyecto.filmforyou.loadingDialog;

public class PendingFragment extends Fragment implements  MoviesAdapter.OnListInteractionListener {

    private FragmentPendingBinding binding;
    private PendingViewModel pendingViewModel;
    private loadingDialog loadingDialog;
    private MoviesAdapter adapter;
    private LinearLayoutManager layoutManager;
    private UserMoviePendingRepository userMoviePendingRepository;

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
        return inflater.inflate(R.layout.fragment_pending,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPendingBinding.bind(view);

        layoutManager = new LinearLayoutManager(getActivity());
        binding.pendingList.setLayoutManager(layoutManager);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        pendingViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.pendingViewModelFactory).get(PendingViewModel.class);

        List<Movie> movieList = new ArrayList<>();
        adapter = new MoviesAdapter(movieList,this);
        binding.pendingList.setAdapter(adapter);

        userMoviePendingRepository = UserMoviePendingRepository.getInstance(getContext());
        loadingDialog = new loadingDialog(getActivity());
        loadingDialog.startLoadingDialog();
        pendingViewModel.getPendingMovies();;

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
        pendingViewModel.listPend.observe(getViewLifecycleOwner(), movies -> {
            if (movies != null) {
                Log.i("Update data", "NEW PENDING MOVIE");
                adapter.swap(movies);
                loadingDialog.dismisDialog();
            }
        });
    }
}