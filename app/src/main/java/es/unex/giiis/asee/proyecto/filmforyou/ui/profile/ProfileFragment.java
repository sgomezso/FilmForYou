package es.unex.giiis.asee.proyecto.filmforyou.ui.profile;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;
import es.unex.giiis.asee.proyecto.filmforyou.databinding.FragmentProfileBinding;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.LoginActivity;
import es.unex.giiis.asee.proyecto.filmforyou.ui.login.UserRepository;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        profileViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.profileViewModelFactory).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileViewModel.getUser();
        profileViewModel.mUser.observe(getViewLifecycleOwner(),user -> {setUserData(user);});

        binding.endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO VIEMWODEL BORRAR USUARIO = BORRAR PELICULAS
                profileViewModel.closeSession();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                requireActivity().finish();
            }
        });


    }

    private void setUserData(User user) {
        binding.UsernameValue.setText(user.getUsername());
        binding.GeneroFavValue.setText(user.getGeneroFav());
        binding.PeliculaFavValue.setText(user.getPeliculaFav());
        binding.DirectorFavValue.setText(user.getDirectorFav());

        binding.idImagenMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });

        AppExecutors.getInstance().mainThread().execute(new Runnable() {
            @Override
            public void run() {
                if (!user.getImagen().equals("")) {
                    binding.idImagenMovie.setImageURI(Uri.parse(user.getImagen()));
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            ImageView img = getActivity().findViewById(R.id.idImagenMovie);
            img.setImageURI(selectedImage);

            //TO DO guardar en bd
             AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
//                    profileViewModel.updateImage(selectedImage.toString());
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}