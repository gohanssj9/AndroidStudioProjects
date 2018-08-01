package com.example.devil_jin.splashscreen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Storing Arrays -->

    public int [] slide_images = {R.drawable.eat_icon, R.drawable.sleep_icon, R.drawable.code_icon};
    public String [] slide_headings = {"EAT", "SLEEP", "CODE"};
    public  String [] slide_content = {
            "Eat as much fill as you always want, for it will help you focus!",
            "Sleep as much as you can, for it will help you to focus!",
            "Code as much as you can, for it will help you attain a different level!"
    };



    @Override
    public int getCount(){
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o){
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.iconImageView);
        TextView slideHeader = (TextView) view.findViewById(R.id.slideHeader);
        TextView slideContent = (TextView) view.findViewById(R.id.slideContent);

        slideImageView.setImageResource(slide_images[position]);
        slideHeader.setText(slide_headings[position]);
        slideContent.setText(slide_content[position]);

        container.addView(view);

        return view;
    };

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
