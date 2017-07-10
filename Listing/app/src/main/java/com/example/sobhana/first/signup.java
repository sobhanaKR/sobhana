package com.example.sobhana.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.sobhana.first.Constants.ConfirmPassword;
import static com.example.sobhana.first.Constants.Password;


public class signup extends AppCompatActivity {
   // private static final String TAG = "SignupActivity";

    TextInputEditText username;
    TextInputEditText email;
    TextInputEditText password;
    TextInputEditText confirmpassword;
    Button createaccount;
    boolean cancel = false;
    View focusView = null;
    private View confirmpasswordView;







    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_signup );
        username = (TextInputEditText) findViewById ( R.id.input_name );
        email=(TextInputEditText)findViewById ( R.id. input_email);
        password=(TextInputEditText)findViewById ( R.id.input_password );
        confirmpassword=(TextInputEditText)findViewById ( R.id.input_confirmpassword );
        createaccount=(Button)findViewById ( R.id.btn_signup );
        createaccount.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                Constants.userName = username.getText().toString ();
                Constants.Email=email.getText ().toString ();
                Password=password.getText ().toString ();
                ConfirmPassword=confirmpassword.getText ().toString ();
                if(ConfirmPassword.equals(Password)) {
                    Log.i ( "Info", "working" );
                    Intent i = new Intent ( signup.this, LoginActivitycheck.class );
                    startActivity ( i );
                    Log.i ( "Info", "success" );

                }
                else
                {
                    cancel=true;
                    confirmpassword.setError ("passwords donot match" );
                    focusView=confirmpasswordView;
                }


                if (cancel) {
                    // There was an error; don't attempt login and focus the first
                    // form field with an error.
                    if(focusView!=null){
                        focusView.requestFocus();
                    }
                }
            }
        } );
    }




}
