package com.example.devil_jin.oyoroomsdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class RecommendedActivity extends AppCompatActivity {

    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.recommended_1, R.drawable.recommended_2, R.drawable.recommended_3, R.drawable.recommended_4,
            R.drawable.recommended_5, R.drawable.recommended_6, R.drawable.recommended_7, R.drawable.recommended_8));
    ArrayList roomPricesBefore = new ArrayList<>(Arrays.asList("₹2000", "₹1000", "₹1500", "₹1200", "₹5000", "₹2000", "₹10000", "₹1000"));
    ArrayList roomPricesAfter = new ArrayList<>(Arrays.asList("₹680", "₹720", "₹975", "₹504", "₹950", "₹920", "₹1000", "₹880"));
    ArrayList percentageOff = new ArrayList<>(Arrays.asList("66% OFF", "28% OFF", "35% OFF", "58% OFF", "81% OFF", "54% OFF", "90% OFF", "12% OFF"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(RecommendedActivity.this, roomImages, roomPricesBefore, roomPricesAfter, percentageOff);
        recyclerView.setAdapter(customAdapter);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
