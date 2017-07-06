package com.example.sobhana.first;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by sobhana on 5/7/17.
 */

public class ListviewpagerFrag extends android.support.v4.app.Fragment {

int pos;

    @Nullable
    @Override
    public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View v = inflater.inflate(R.layout.cardviewnew, container, false);

        TextView tv=(TextView)v.findViewById ( R.id.key_name );
        ImageView iv=(ImageView)v.findViewById(R.id.key_image);
        Bundle args=getArguments ();

        Log.i("anisham","anisham++++"+args.getString ( "name" ));
        tv.setText(args.getString ( "name" ));
        //Log.i("url",args.getString(url));
        Picasso.with(getActivity ()).load(args.getString("url")).into(iv);
        pos=args.getInt("position");

        return  v;
    }



}

