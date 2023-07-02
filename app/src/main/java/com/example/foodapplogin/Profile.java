package com.example.foodapplogin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Profile extends Fragment {
    TextView profilename,profilenumber,profilemail;
    private static UserDatabase userDatabase;
    private static UserDao userDao;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;

    public Profile() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        userDatabase = Room.databaseBuilder(
                getActivity().getApplicationContext(),
                UserDatabase.class,
                "user_database"
        ).allowMainThreadQueries().build();
        userDao = userDatabase.userDao();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();


        profilename = view.findViewById(R.id.profname);
        profilemail = view.findViewById(R.id.profmail);
        profilenumber = view.findViewById(R.id.profphone);

        User user = userDao.getUser(firebaseUser.getEmail());

        profilename.setText((user.getFullName()));
        profilenumber.setText(("Phone number: "+user.getPhoneNumber()));
        profilemail.setText(("E-mail: "+user.getEmail()));

        return view;
    }
}
