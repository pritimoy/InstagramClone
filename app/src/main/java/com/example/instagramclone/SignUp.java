package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    
    private EditText edtUserName, edtEmail, edtPassword;
    private Button btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUserName = findViewById(R.id.edtsignupusername);
        edtPassword = findViewById(R.id.edtsignuppassword);
        edtEmail = findViewById(R.id.edtsignupemail);
        btnSignup = findViewById(R.id.btnsignup);
        btnLogin = findViewById(R.id.btnlogin);

        //method for keyboard enter or return key for signup
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCord, KeyEvent event) {
                if (keyCord == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignup);
                }
                return false;
            }
        });
        
        btnSignup = findViewById(R.id.btnsignup);
        btnLogin = findViewById(R.id.btnlogin);


        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);


        
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnsignup:

                //check condition for empty input of user
                if (edtUserName.getText().toString().equals("") ||
                        edtEmail.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(SignUp.this,
                            "UserName, Email and Password is  Required " ,
                            Toast.LENGTH_SHORT, FancyToast.INFO, false).show();

                }else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUp.this, appUser.getUsername()
                                                + "is successfully sign up", Toast.LENGTH_SHORT,
                                        FancyToast.SUCCESS, false).show();
                                startActivity(new Intent(SignUp.this,MainActivity.class));

                                //progressDialog.dismiss();
                            } else {
                                FancyToast.makeText(SignUp.this, "There was a error "
                                                + e.getMessage(), Toast.LENGTH_SHORT,
                                        FancyToast.ERROR, false).show();
                            }
                        }
                    });
                }
                break;
            case R.id.btnlogin:
                startActivity(new Intent(SignUp.this, Login.class));
                break;
        }


    }

    //rootLayoutTapped method for tapped outside hide soft keyboard

    public void rootLayoutTapped(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
