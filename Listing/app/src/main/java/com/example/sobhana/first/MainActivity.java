package com.example.sobhana.first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button signupbtn;
    Button loginbtn;







    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        editText=(EditText) findViewById ( R.id.skip );
        signupbtn = (Button) findViewById ( R.id.signup );
        loginbtn=(Button)findViewById ( R.id.login);
        editText.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {


                Intent intent = new Intent ( MainActivity.this, Activitydisplaymessage.class );
                startActivity ( intent );
                /*Intent i = new Intent (Intent.ACTION_VIEW);

                i.setData ( Uri.parse ("test://hhh.sobhana.com/listing/"));
                startActivity ( i );*/
            }
        } );
        signupbtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                Log.i ( "Info", "workingsignup" );

                Intent in = new Intent (MainActivity.this, signup.class );

                startActivity ( in );
            }
        } );
        loginbtn.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent in = new Intent (MainActivity.this, LoginActivitycheck.class );

                startActivity ( in );

            }
        } );
    }

}
