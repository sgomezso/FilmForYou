package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import es.unex.giiis.asee.proyecto.filmforyou.AppExecutors;
import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.R;
import es.unex.giiis.asee.proyecto.filmforyou.data.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void checkUser(String username, String password) {
        UserRepository userRepository = new UserRepository(getActivity());
        if (userRepository.checkUser(username, password)) {
            userRepository.preference.edit().putLong("userId", userRepository.getUserId(username, password));
            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
        } else {
            // TODO Mostrar error
        }
    }

    public void goRegister() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activityLayout, new RegisterFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        EditText usernameET = v.findViewById(R.id.usernameLogin);
        EditText passwordET = v.findViewById(R.id.passwordLogin);
        UserRepository userRepository = new UserRepository(getActivity());

        v.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        checkUser(usernameET.getText().toString(), passwordET.getText().toString());
                    }
                });
            }
        });
        v.findViewById(R.id.registerLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goRegister();
            }
        });
        return v;
    }
}