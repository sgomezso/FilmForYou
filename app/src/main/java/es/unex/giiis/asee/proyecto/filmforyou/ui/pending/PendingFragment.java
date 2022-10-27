package es.unex.giiis.asee.proyecto.filmforyou.ui.pending;

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

import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentPendingBinding;

public class PendingFragment extends Fragment {

    private PendingViewModel pendingViewModel;
    private FragmentPendingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pendingViewModel =
                new ViewModelProvider(this).get(PendingViewModel.class);

        binding = FragmentPendingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}