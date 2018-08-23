package com.example.devil_jin.oyoroomsdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {

    TextView textView, wifiTextView, acroomsTextView, booknowTextView, cleanTextView, getStartedTextView;
    Button gettingStartedButton;

    public static int layout_width, layout_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // TextView Animation

        textView = (TextView) findViewById(R.id.OyoTextView);
        wifiTextView = (TextView) findViewById(R.id.wifiTextView);
        acroomsTextView = (TextView) findViewById(R.id.acroomsTextView);
        booknowTextView = (TextView) findViewById(R.id.booknowpaylaterTextView);
        cleanTextView = (TextView) findViewById(R.id.cleanhygieneTextView);
        getStartedTextView = (TextView) findViewById(R.id.gethundredoffTextView);

        gettingStartedButton = (Button) findViewById(R.id.getStartedButton);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        layout_width = displayMetrics.widthPixels;
        layout_height = displayMetrics.heightPixels;

        System.out.println(layout_width + "-----" + layout_height);

        final Animation in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);

        final Animation out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);

        in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //Animation
                textView.animate().translationY(-layout_height/2 + layout_height/10).setDuration(500).start();

                wifiTextView.animate().translationY(-layout_height/2 + layout_height/8).setStartDelay(500).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        wifiTextView.setVisibility(View.VISIBLE);
                    }
                }).start();

                acroomsTextView.animate().translationY(-layout_height/2 + layout_height/6).setStartDelay(1000).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        acroomsTextView.setVisibility(View.VISIBLE);
                    }
                }).start();

                booknowTextView.animate().translationY(-layout_height/2 + layout_height/5).setStartDelay(1500).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        booknowTextView.setVisibility(View.VISIBLE);
                    }
                }).start();

                cleanTextView.animate().translationY(-layout_height/2 + layout_height/4).setStartDelay(2000).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        cleanTextView.setVisibility(View.VISIBLE);
                    }
                }).start();

                getStartedTextView.animate().translationY((float)(-layout_height/2 + layout_height * 1.0/2.5)).setStartDelay(2500).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        getStartedTextView.setVisibility(View.VISIBLE);
                    }
                }).start();

                gettingStartedButton.animate().translationY((float)(-layout_height/2 + layout_height*1.0/2.4)).setStartDelay(3000).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        gettingStartedButton.setVisibility(View.VISIBLE);
                    }
                }).start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                textView.setText("OYO");
                textView.setTextSize(64);
                textView.startAnimation(in);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(out);
    }

    public void goToLogin(View view){
        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(newIntent);
    }
}
