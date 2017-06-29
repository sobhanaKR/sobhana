package com.example.sobhana.first;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sobhana on 23/6/17.
 */

public class Activitydisplaymessage extends Activity implements Frag.OnHeadlineSelectedListener{

    int i=1;
    String name;
    String url;
    public void onArticleSelected(int position,String name,String url) {
        this.name=name;
        this.url=url;
        ArticleFrag newFragment1 =new ArticleFrag(name,url);
        Bundle args = new Bundle();
        args.putInt(ArticleFrag.ARG_POSITION, position);

        newFragment1.setArguments(args);


        android.app.FragmentManager fm=getFragmentManager ();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayout1, newFragment1);
        ft.addToBackStack(null);
        ft.commit ();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag);
        if (savedInstanceState == null) {
            Fragment newFragment = Frag.newInstance(i);
            android.app.FragmentManager fm=getFragmentManager ();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.FrameLayout1, newFragment);
            // ft.replace(R.id.FrameLayout1, newFragment);
            // ft.addToBackStack(null);
            ft.commit ();
        } else {
            i = savedInstanceState.getInt("level");
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("level", i);
    }

}





