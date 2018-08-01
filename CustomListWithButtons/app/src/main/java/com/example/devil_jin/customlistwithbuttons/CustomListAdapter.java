package com.example.devil_jin.customlistwithbuttons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

    private Context context;
    public CustomListAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getViewTypeCount(){
        return getCount();
    }

    @Override
    public int getItemViewType(int i){
        return i;
    }

    @Override
    public int getCount() {
        return MainActivity.modelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.modelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listviewitem, null, true);

            holder.textViewFruit = (TextView) view.findViewById(R.id.animalTextView);
            holder.textViewNumber = (TextView) view.findViewById(R.id.numberTextView);

            holder.plusButton = (Button) view.findViewById(R.id.plusButton);
            holder.minusButton = (Button) view.findViewById(R.id.minusButton);

            view.setTag(holder);
        } else holder = (ViewHolder) view.getTag();

        holder.textViewFruit.setText(MainActivity.modelArrayList.get(i).getFruit());
        holder.textViewNumber.setText(String.valueOf(MainActivity.modelArrayList.get(i).getNumber()));

        holder.plusButton.setTag(R.integer.button_plus_view, view);
        holder.plusButton.setTag(R.integer.button_plus_position, i);
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View tempView = (View) holder.plusButton.getTag(R.integer.button_plus_view);
                TextView textView = (TextView) tempView.findViewById(R.id.numberTextView);
                Integer pos = (Integer) holder.plusButton.getTag(R.integer.button_plus_position);

                int number = Integer.parseInt(textView.getText().toString()) + 1;
                textView.setText(String.valueOf(number));

                MainActivity.modelArrayList.get(pos).setNumber(number);
            }
        });

        holder.minusButton.setTag(R.integer.button_minus_view, view);
        holder.minusButton.setTag(R.integer.button_minus_position, i);
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View tempView = (View) holder.minusButton.getTag(R.integer.button_minus_view);
                TextView textView = (TextView) tempView.findViewById(R.id.numberTextView);
                Integer pos = (Integer) holder.minusButton.getTag(R.integer.button_minus_position);

                int number = Integer.parseInt(textView.getText().toString()) - 1;
                textView.setText(String.valueOf(number));

                MainActivity.modelArrayList.get(pos).setNumber(number);
            }
        });

        return view;
    }

    private class ViewHolder{
        protected Button plusButton, minusButton;
        private TextView textViewFruit, textViewNumber;
    }
}
