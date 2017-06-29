package com.example.sobhana.first;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sobhana.first.R.id.input_name;

/**
 * Created by sobhana on 8/6/17.
 */

public class activity_display_messagesignup extends Activity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_messagesignup);

            // Get the Intent that started this activity and extract the string
            Intent intent = getIntent();
           //String message = intent.getStringExtra(signup.EXTRA_MESSAGE);

            // Capture the layout's TextView and set the string as its text
            TextView textView = (TextView) findViewById(R.id.texttt);
            textView.setText("welcome "+ Constants.userName);
           // Toast.makeText (activity_display_messagesignup,this,,Toast.LENGTH_SHORT).show();

        }

    @Override
    public Context getApplicationContext () {
        return super.getApplicationContext ( );
    }
}




