package com.example.devil_jin.recyclerviewhorizontal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{


    ArrayList imagesRelativeLayout;
    ArrayList textUnderImages;

    Context context;
    public CustomAdapter(Context context, ArrayList imagesRelativeLayout, ArrayList textUnderImages){
        this.context = context;
        this.imagesRelativeLayout = imagesRelativeLayout;
        this.textUnderImages = textUnderImages;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_horizontal,viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {

        holder.textView.setText((String) textUnderImages.get(i));
        holder.imageView.setImageResource((Integer) imagesRelativeLayout.get(i));
    }

    @Override
    public int getItemCount() {
        return imagesRelativeLayout.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageView;
        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.cityImageView);
            textView = (TextView) itemView.findViewById(R.id.textUnderCity);
        }
    }
}