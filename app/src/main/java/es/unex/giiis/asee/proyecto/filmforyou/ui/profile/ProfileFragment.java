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
import androidx.lifecycle.ViewModelProvider;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
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
            generoFav = (TextView) binding.GeneroFavValue;
            peliculaFav = (TextView) binding.PeliculaFavValue;
            directorFav = (TextView)binding.DirectorFavValue;
            ImageView img = binding.idImagenMovie;
            Boolean aux= false;

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    user = userRepository.getUser(userId);
                    username.setText(user.getUsername());
                    generoFav.setText(user.getGeneroFav());
                    peliculaFav.setText(user.getPeliculaFav());
                    directorFav.setText(user.getDirectorFav());

//                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            if (!user.getImagen().equals("")){
//                                img.setImageURI(Uri.parse(user.getImagen()));
//                            }
//                        }
//                    });

                }
            });

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent,3);
                }
            });


        binding.endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings.edit().clear().commit();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });

        }
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data!=null){
            Uri selectedImage = data.getData();
            ImageView img = getActivity().findViewById(R.id.idImagenMovie);
            img.setImageURI(selectedImage);

            SharedPreferences settings = getActivity().getSharedPreferences("preference", Context.MODE_PRIVATE);
            Long userId = settings.getLong("userId",-1);

            //TO DO guardar en bd
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
//                    UserRepository userRepository = new UserRepository(getActivity());
//                    userRepository.updateImage(selectedImage.toString(),userId);
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