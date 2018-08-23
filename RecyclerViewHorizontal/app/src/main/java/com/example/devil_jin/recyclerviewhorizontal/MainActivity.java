package com.example.devil_jin.recyclerviewhorizontal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList imagesRelativeLayout = new ArrayList<>(Arrays.asList(R.drawable.gps, R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8, R.drawable.a_9, R.drawable.all_cities_2));
    ArrayList textUnderImages = new ArrayList<>(Arrays.asList("Nearby", "Hyderabad", "Chennai", "Delhi", "Mumbai", "Vizag", "Bengaluru", "Noida", "Trichy", "Kolkata", "All Cities"));


    ArrayList dontMissTheseImages = new ArrayList<>(Arrays.asList(R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8, R.drawable.a_9));

    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8));
    ArrayList roomPricesBefore = new ArrayList<>(Arrays.asList("₹2000", "₹1000", "₹1500", "₹1200", "₹5000", "₹2000", "₹10000", "₹1000"));
    ArrayList roomPricesAfter = new ArrayList<>(Arrays.asList("₹680", "₹720", "₹975", "₹504", "₹950", "₹920", "₹1000", "₹880"));
    ArrayList percentageOff = new ArrayList<>(Arrays.asList("66% OFF", "28% OFF", "35% OFF", "58% OFF", "81% OFF", "54% OFF", "90% OFF", "12% OFF"));
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

//        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, imagesRelativeLayout, textUnderImages);
//        recyclerView.setAdapter(customAdapter);

//        CustomAdapter2 customAdapter = new CustomAdapter2(MainActivity.this, dontMissTheseImages);
//        recyclerView.setAdapter(customAdapter);

        CustomAdapter3 customAdapter = new CustomAdapter3(MainActivity.this, roomImages, roomPricesBefore, roomPricesAfter, percentageOff);
        recyclerView.setAdapter(customAdapter);
    }
}