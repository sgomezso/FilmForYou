package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.unex.giiis.asee.proyecto.filmforyou.AppContainer;
import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.MyApplication;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.ui.movie.MovieListViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText email;
    private EditText peliculaFav;
    private EditText directorFav;
    private EditText generoFav;
    private LoginViewModel loginViewModel;







    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // TODO Viewgroup cual es?

        View v = inflater.inflate(R.layout.fragment_register, container, false);
        username = v.findViewById(R.id.username);
        password = v.findViewById(R.id.password);
        password2 = v.findViewById(R.id.password2);
        email = v.findViewById(R.id.email);
        peliculaFav = v.findViewById(R.id.FavoriteMovie);
        directorFav = v.findViewById(R.id.FavoriteDirector);
        generoFav = v.findViewById(R.id.FavoriteGenre);

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        loginViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.loginViewModelFactory).get(LoginViewModel.class);


        v.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_warning_24);
                icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());

                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                String password2Text = password2.getText().toString();
                String emailText = email.getText().toString();
                String peliculaFavText = peliculaFav.getText().toString();
                String directorFavText = directorFav.getText().toString();
                String generoFavext = generoFav.getText().toString();


                if (TextUtils.isEmpty(usernameText.trim()))
                    username.setError("Please Enter Username", icon);
                if (TextUtils.isEmpty(passwordText.trim()))
                    password.setError("Please Enter password", icon);
                if (TextUtils.isEmpty(password2Text.trim()))
                    password2.setError("Please Enter the confirm password", icon);

                if (passwordText.equals(password2Text)) {

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {

                            loginViewModel.createUser(usernameText,passwordText,emailText,generoFavext,peliculaFavText,directorFavText,"");
                            AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            startActivity(i);
                            requireActivity().finish();
                        }
                    });
                } else

            {
                password2.setError("The passwords have to be the same", icon);
            }
        }
    });

        return v;
}


}