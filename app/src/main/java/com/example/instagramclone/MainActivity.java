package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParseInstallation.getCurrentInstallation().saveInBackground();
        final ParseUser appUser = new ParseUser();
        if ( appUser.getUsername() == null){
            startActivity( new Intent(MainActivity.this,SignUp.class));
        }


    }
}
