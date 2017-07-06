package com.example.sobhana.first;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;

/**
 * Created by sobhana on 23/6/17.
 */

public class Activitydisplaymessage extends AppCompatActivity implements Frag.OnHeadlineSelectedListener{

    int i=1;
    String name;
    String url;
    Activity activity;
   // ArrayList<Product> product_specific_data;
    public void onArticleSelected(int position,ArrayList<Product> product_specific_data) {
        ArticleFrag newFragment1 =new ArticleFrag();
        Bundle args = new Bundle();
        args.putInt(ArticleFrag.ARG_POSITION, position);
        args.putSerializable( ArticleFrag.b ,product_specific_data);
        activity = this;
        Log.i ( "anisham","anisham**"+product_specific_data.size () );
        newFragment1.setArguments(args);
        FragmentManager fm =  getSupportFragmentManager ();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.FrameLayout1,newFragment1 );
        ft.addToBackStack(null);
        ft.commit ();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.frag );
        Log.i ( "anisham", "anisham1 " );

        android.support.v4.app.Fragment newFragment = Frag.newInstance ( i );
        FragmentManager fm = getSupportFragmentManager ( );
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction ( );
        ft.add ( R.id.FrameLayout1, newFragment );
        ft.commit ( );

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.articlefrag, menu);
        return true;
    }
}





