package com.example.devil_jin.recyclerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class MainActivity extends AppCompatActivity {

    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.recommended_1, R.drawable.recommended_2, R.drawable.recommended_3, R.drawable.recommended_4,
            R.drawable.recommended_5, R.drawable.recommended_6, R.drawable.recommended_7, R.drawable.recommended_8));
    ArrayList roomPricesBefore = new ArrayList<>(Arrays.asList("₹2000", "₹1000", "₹1500", "₹1200", "₹5000", "₹2000", "₹10000", "₹1000"));
    ArrayList roomPricesAfter = new ArrayList<>(Arrays.asList("₹680", "₹720", "₹975", "₹504", "₹950", "₹920", "₹1000", "₹880"));
    ArrayList percentageOff = new ArrayList<>(Arrays.asList("66% OFF", "28% OFF", "35% OFF", "58% OFF", "81% OFF", "54% OFF", "90% OFF", "12% OFF"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int j=0; j<=7; j++)
            for(int i=0;i<=7;i++){
                roomImages.add(roomImages.get(i));
                roomPricesBefore.add(roomPricesBefore.get(i));
                roomPricesAfter.add(roomPricesAfter.get(i));
                percentageOff.add(percentageOff.get(i));
            }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, roomImages, roomPricesBefore, roomPricesAfter, percentageOff);
        recyclerView.setAdapter(customAdapter);
    }
}
