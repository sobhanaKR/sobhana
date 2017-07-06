package com.example.sobhana.first;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by sobhana on 4/7/17.
 */

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    ArrayList<Product> product_specific_data;
   int p;

    public MyFragmentPageAdapter ( FragmentManager fm, ArrayList<Product> productArrayList,int position ) {
        super (fm );
        product_specific_data=productArrayList;
        p=position;
    }

    @Override
    public int getCount () {
        int count = product_specific_data.size();
        Log.i ( "anisham","anisham adpater size" +count);
        return count;
    }

    @Override
    public Fragment getItem ( int args0 ) {
        ListviewpagerFrag myFragment=new ListviewpagerFrag ();
        Bundle data = new Bundle ();
        data.putInt("current_page",p);

        data.putString ("name",product_specific_data.get(p).getName () );
        data.putString ("url",product_specific_data.get (p).getImage () );
        p++;
        data.putInt ("position",args0);
        Log.i ( "anisham","anisham name"+ product_specific_data.get(p).getName ());
        Log.i ( "anisham","anisham url"+ product_specific_data.get(p).getImage ());
        myFragment.setArguments(data);
        return myFragment;
    }
}
