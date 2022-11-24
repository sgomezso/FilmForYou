package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentProfileBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.LoginActivity;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private TextView username;
    private TextView edad;
    private TextView generoFav;
    private TextView peliculaFav;
    private TextView directorFav;
    private User user;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SharedPreferences settings = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);
        UserRepository userRepository = new UserRepository(getActivity());
        Long userId = settings.getLong("userId",-1);

        if(userId!=-1){
            username = (TextView) binding.UsernameValue;
            edad = (TextView) binding.EdadValue;
            generoFav = (TextView) binding.GeneroFavValue;
            peliculaFav = (TextView) binding.PeliculaFavValue;
            directorFav = (TextView)binding.DirectorFavValue;

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    user = userRepository.getUser(userId);
                    username.setText(user.getUsername());
                    edad.setText(user.getEdad());
                    generoFav.setText(user.getGeneroFav());
                    peliculaFav.setText(user.getPeliculaFav());
                    directorFav.setText(user.getDirectorFav());
                }
            });

            }

        binding.endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().clear().commit();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}