package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = findViewById(R.id.userLogout);



        ParseInstallation.getCurrentInstallation().saveInBackground();


        //String userName = ParseUser.getCurrentUser().getUsername();



        //Log.i("user","user"+userName);
        if (  ParseUser.getCurrentUser() == null){
            startActivity( new Intent(MainActivity.this,SignUp.class));
        }
        else {
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ParseUser.getCurrentUser().logOut();
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
            });
        }



    }
}
