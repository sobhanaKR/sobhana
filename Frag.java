package com.example.sobhana.first;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sobhana.first.Data_Adapter;
import com.example.sobhana.first.JSON_Response;
import com.example.sobhana.first.Product;
import com.example.sobhana.first.R;
import com.example.sobhana.first.Request_Interface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.sobhana.first.R.layout.mainpage;

/**
 * Created by sobhana on 27/6/17.
 */

public class Frag extends Fragment{
    OnHeadlineSelectedListener mCallback;

    private RecyclerView recyclerView;
    private ArrayList<Product> data;
    private Data_Adapter adapter;
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position,String name,String url);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }


    static Frag newInstance ( int num ) {
        Frag f = new Frag( );
        Bundle args = new Bundle ( );
        args.putInt ( "num", num );
        f.setArguments ( args );

        return f;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.mainpage, container, false);
        //  getFragmentManager().findFragmentById(R.id.FrameLayout1).getView().findViewById(R.id.cardrecycleview);


        recyclerView = (RecyclerView) v.findViewById( R.id.cardrecycleview);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(v.getContext(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.myjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Request_Interface request = retrofit.create(Request_Interface.class);
        final Activity activity = getActivity();
        Call<JSON_Response> call = request.getJSON();
        call.enqueue(new Callback<JSON_Response>() {
            @Override
            public void onResponse(Call<JSON_Response> call, Response<JSON_Response> response) {
                JSON_Response jsonResponse = response.body();
                data = new ArrayList<>(Arrays.asList(jsonResponse.getProduct_specific_data()));

                adapter = new Data_Adapter(activity,data,mCallback );

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSON_Response> call, Throwable t) {
                Log.d("Error", t.getMessage());

            }


        });

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



}


