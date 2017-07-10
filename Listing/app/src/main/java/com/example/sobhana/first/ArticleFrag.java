package com.example.sobhana.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by sobhana on 27/6/17.
 */

public class ArticleFrag extends Fragment {




    ArrayList<Product> productArrayList = new ArrayList<>();
    int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("anisham","anisham2 ");
        View v = inflater.inflate(R.layout.articlefrag, container, false);
        ViewPager pager = (ViewPager)v.findViewById(R.id.pager);

        FragmentManager fm = getChildFragmentManager ();
        Bundle args = getArguments();
        if (args != null) {
            productArrayList = (ArrayList<Product>) args.getSerializable(Constants.PRODUCT);
            pos=args.getInt ( Constants.ARG_POSITION );
            Log.i ("anisham","anisham size="+productArrayList.size());
            MyFragmentPageAdapter pagerAdapter = new MyFragmentPageAdapter(fm, productArrayList,pos);
          //  HashMap<String, Object> selected = (HashMap<String, Object>) ListviewpagerFrag.getItemAtPosition(pos);
            pager.setAdapter( pagerAdapter);
        }
        return v;
    }


}
