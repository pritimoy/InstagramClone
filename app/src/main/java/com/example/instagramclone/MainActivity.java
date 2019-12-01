package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {
    private Button btnLogout;

    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;

    @Override
    protected void onStart() {
        super.onStart();

        ParseUser parseUser = new ParseUser();
        if (parseUser.getCurrentUser() == null){
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btnLogout = findViewById(R.id.userLogout);

        setTitle("Social Media App!");
        toolbar = findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,true);




        ParseInstallation.getCurrentInstallation().saveInBackground();


        //String userName = ParseUser.getCurrentUser().getUsername();



        //Log.i("user","user"+userName);
        if (  ParseUser.getCurrentUser() == null){
            startActivity( new Intent(MainActivity.this,SignUp.class));
        }
//        else {
//            btnLogout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    ParseUser.getCurrentUser().logOut();
//                    startActivity(new Intent(MainActivity.this, Login.class));
//                }
//            });
//        }



    }
}
