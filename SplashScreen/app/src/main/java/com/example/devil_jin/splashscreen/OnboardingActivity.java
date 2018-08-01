package com.example.devil_jin.splashscreen;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager slideViewPager;
    private LinearLayout dotsLayout;

    private TextView[] dots;
    private SliderAdapter sliderAdapter;

    private Button backButton;
    private Button nextButton;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        getSupportActionBar().hide();

        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        backButton = (Button) findViewById(R.id.backButton);
        nextButton = (Button) findViewById(R.id.nextButton);

        sliderAdapter = new SliderAdapter(this);
        slideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        slideViewPager.addOnPageChangeListener(viewListener);
    }

    public void nextPage(View view){
        if(currentPage + 1 == dots.length){
            Intent intent = new Intent(OnboardingActivity.this, MainActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        if(currentPage < dots.length) slideViewPager.setCurrentItem(currentPage + 1);
    }

    public void backPage(View view){
        slideViewPager.setCurrentItem(currentPage - 1);
    }

    public void addDotsIndicator(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();

        for(int i=0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(45);
            dots[i].setTextColor(getResources().getColor(R.color.onboardingTransparentWhite));

            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.onboardingWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage = i;

            if(i == 0){
                nextButton.setEnabled(true);
                backButton.setEnabled(false);

                backButton.setVisibility(View.INVISIBLE);

                nextButton.setText("Next>");
                backButton.setText("");
            }
            else if(i == dots.length - 1){
                nextButton.setEnabled(true);
                backButton.setEnabled(true);

                backButton.setVisibility(View.VISIBLE);

                backButton.setText("<Back");
                nextButton.setText("Finish>");
            }
            else{
                nextButton.setEnabled(true);
                backButton.setEnabled(true);

                backButton.setVisibility(View.VISIBLE);

                backButton.setText("<Back");
                nextButton.setText("Next>");

            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
