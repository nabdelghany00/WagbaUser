package com.example.foodapplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class tracking extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        if(findViewById(R.id.track_fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.track_fragment_container,new TrackingFragment(),null).commit();
        }
    }

}