package com.example.sobhana.first;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class Data_Adapter extends RecyclerView.Adapter<Data_Adapter.ViewHolder> {
    Frag.OnHeadlineSelectedListener mCallback;
    private final Activity activity;
    private ArrayList<Product> product_specific_data;
    int width, height;


    public Data_Adapter( Activity activity, ArrayList<Product> product_specific_data, Frag.OnHeadlineSelectedListener mCallback) {
        this.product_specific_data=product_specific_data;
        this.activity = activity;
        this.mCallback=mCallback;
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;
        //width=width/2;
       // height=height/2;
    }


    @Override
    public Data_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.cardview, viewGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder( final Data_Adapter.ViewHolder viewHolder, final int i) {
        viewHolder.key_name.setText(product_specific_data.get(i).getName());
        Picasso.with(activity)
                .load(product_specific_data.get(i).getImage())
                .into(viewHolder.key_image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onArticleSelected(i, product_specific_data);
            }
        });
    }


    @Override
    public int getItemCount() {
        return product_specific_data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView key_name;
        ImageView key_image;
        public ViewHolder(View view) {
            super(view);
            key_name= (TextView) view.findViewById(R.id.key_name);
            key_image =(ImageView)view.findViewById(R.id.key_image);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width/2);
            LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams ( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            key_name.setLayoutParams ( layoutParams1 );
            key_image.setLayoutParams ( layoutParams );

        }
    }
}
