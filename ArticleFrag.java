package com.example.sobhana.first;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.contextUri;
import static android.R.attr.data;
import static com.example.sobhana.first.R.id.key_image;
import static com.example.sobhana.first.R.id.key_name;

/**
 * Created by sobhana on 27/6/17.
 */

public class ArticleFrag extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private String name;
    private String url;


    public ArticleFrag(String name,String url) {
        this.name=name;
        this.url=url;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous article selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.cardview, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
            // updateArticleView();
        } else if (mCurrentPosition != -1) {
            // Set article based on saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }




    private void updateArticleView(int position) {
        TextView tv = (TextView) getActivity().findViewById(key_name);
        ImageView iv=(ImageView)getActivity().findViewById(key_image);
        tv.setText(name);
        Picasso.with(getActivity()).load(url).into(iv);

        //        iv.setImageResource(position);
        mCurrentPosition = position;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current article selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }
}
