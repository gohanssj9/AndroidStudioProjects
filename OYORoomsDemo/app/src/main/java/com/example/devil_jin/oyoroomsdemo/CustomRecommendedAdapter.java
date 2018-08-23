package com.example.devil_jin.oyoroomsdemo;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;

public class CustomRecommendedAdapter extends RecyclerView.Adapter<CustomRecommendedAdapter.MyViewHolder>{

    ArrayList roomImages;
    ArrayList roomPricesBefore;
    ArrayList roomPricesAfter;
    ArrayList percentageOff;

    Context context;

    public CustomRecommendedAdapter(Context context, ArrayList roomImages, ArrayList roomPricesBefore, ArrayList roomPricesAfter, ArrayList percentageOff){
        this.context = context;
        this.roomImages = roomImages;
        this.roomPricesBefore = roomPricesBefore;
        this.roomPricesAfter = roomPricesAfter;
        this.percentageOff = percentageOff;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_recommended_rooms_horizontal,viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int i) {

        holder.strikeOut.setText((String) roomPricesBefore.get(i));
        holder.strikeOut.setPaintFlags(holder.strikeOut.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        holder.nonStrikeOut.setText((String) roomPricesAfter.get(i));
        holder.percentageText.setText((String) percentageOff.get(i));

        Drawable background = holder.relativeLayout.getContext().getResources().getDrawable((Integer) roomImages.get(i));
        holder.relativeLayout.setBackground(background);
    }

    @Override
    public int getItemCount() {
        return roomImages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout relativeLayout;
        TextView strikeOut, nonStrikeOut, percentageText;

        public MyViewHolder(View itemView) {
            super(itemView);

            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.innerImage);
            strikeOut = (TextView) itemView.findViewById(R.id.strikeOutTextView);
            nonStrikeOut = (TextView) itemView.findViewById(R.id.newPriceTextView);
            percentageText = (TextView) itemView.findViewById(R.id.percentOffTextView);
        }
    }
}