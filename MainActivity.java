package com.example.sobhana.first;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.sobhana.first.R.id.login;
import static com.example.sobhana.first.R.id.skip;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.First.MESSAGE";
   /* public void  skip(View view){
        EditText skip=(EditText) findViewById(R.id.skip);
        Toast.makeText(getApplicationContext(),"welcome",Toast.LENGTH_SHORT).show();



    }*/
   public void skip(View view)
   {
       EditText text = (EditText)view;

       if(text.length() == 0)
           return;
       Intent intent = new Intent(this,Activitydisplaymessage.class);
       EditText editText = (EditText) findViewById(R.id.skip);
      // String message = "welcome to caratlane";
      // intent.putExtra(EXTRA_MESSAGE, message);
       startActivity(intent);
   }

    public void loginfirst(View view)
    {
        Log.i("Info","working");
       // Intent intent = new Intent(this,LoginActivitycheck.class);
       // Button btn = (Button) findViewById(R.id.login);
        Intent i = new Intent(this, LoginActivitycheck.class);

        startActivity(i);
    }
    public void signupclick(View view)
    {
        Log.i("Info","workingsignup");
        Intent intent = new Intent(this,signup.class);
        Button btn = (Button) findViewById(R.id.signup);
        Intent in = new Intent(this, signup.class);

        startActivity(in);
    }

    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    private class DisplayMessageActivity {
    }
}
