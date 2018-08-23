package com.example.devil_jin.oyoroomsdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;


public class CustomDontMissTheseAdapter extends RecyclerView.Adapter<CustomDontMissTheseAdapter.MyViewHolder>{


    ArrayList dontMissTheseImages;

    Context context;
    public CustomDontMissTheseAdapter(Context context, ArrayList dontMissTheseImages){
        this.context = context;
        this.dontMissTheseImages = dontMissTheseImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_dont_miss_out_horizontal,viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {

        holder.imageView.setImageResource((Integer) dontMissTheseImages.get(i));
    }

    @Override
    public int getItemCount() {
        return dontMissTheseImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.dontMissTheseImageView);
        }
    }
}