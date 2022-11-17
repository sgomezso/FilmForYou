package es.unex.giiis.asee.proyecto.filmforyou.ui.login;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import es.unex.giiis.asee.proyecto.filmforyou.MainActivity;
import es.unex.giiis.asee.proyecto.filmforyou.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password2";


    private EditText username;
    private EditText password;
    private EditText password2;


    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        args.putString(USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
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

        v.findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable icon = AppCompatResources.getDrawable(requireContext(), R.drawable.ic_baseline_warning_24);
                icon.setBounds(0,0,icon.getIntrinsicWidth(),icon.getIntrinsicHeight());

                if( TextUtils.isEmpty(username.getText().toString().trim()))
                    username.setError("Please Enter Username",icon);
                if( TextUtils.isEmpty(password.getText().toString().trim()))
                    password.setError("Please Enter password",icon);
                if( TextUtils.isEmpty(password2.getText().toString().trim()))
                    password2.setError("Please Enter the confirm password",icon);

                if(password == password2) {
                    UserRepository userRepository = new UserRepository(getActivity());
                    userRepository.registerUser(username.toString(),password.toString());
                    userRepository.preference.edit().putLong("userId",userRepository.getUserId(username.toString(),password.toString()));
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                } else {
                    password2.setError("The passwords have to be the same",icon);
                }
            }
        });

        return v;
    }
}