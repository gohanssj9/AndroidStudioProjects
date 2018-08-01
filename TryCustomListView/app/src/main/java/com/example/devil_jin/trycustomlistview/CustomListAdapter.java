package com.example.devil_jin.trycustomlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageModel> imageModelArrayList;

    public CustomListAdapter(Context context, ArrayList<ImageModel> imageModelArrayList){
        this.context = context;
        this.imageModelArrayList = imageModelArrayList;
    }

    @Override
    public int getViewTypeCount(){
        return getCount();
    }

    @Override
    public int getItemViewType(int position){
        return position;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return imageModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listviewitem, null, true);

            holder.textViewName = (TextView) view.findViewById(R.id.textViewName);
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        holder.textViewName.setText(imageModelArrayList.get(i).getName());
        holder.imageView.setImageResource(imageModelArrayList.get(i).getImage_drawable());

        return view;
    }

    private class ViewHolder{
        protected TextView textViewName;
        private ImageView imageView;
    }
}
