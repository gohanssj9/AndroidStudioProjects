package com.example.devil_jin.oyoroomsdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ArrayList imagesRelativeLayout = new ArrayList<>(Arrays.asList(R.drawable.gps, R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8, R.drawable.a_9, R.drawable.all_cities_2));
    ArrayList textUnderImages = new ArrayList<>(Arrays.asList("Nearby", "Hyderabad", "Chennai", "Delhi", "Mumbai", "Vizag", "Bengaluru", "Noida", "Trichy", "Kolkata", "All Cities"));

    ArrayList dontMissTheseImages = new ArrayList<>(Arrays.asList(R.drawable.dont_1, R.drawable.dont_2, R.drawable.dont_3, R.drawable.dont_4,
            R.drawable.dont_5, R.drawable.dont_6, R.drawable.dont_7, R.drawable.dont_8, R.drawable.dont_9));

    ArrayList latestOYOImages = new ArrayList<>(Arrays.asList(R.drawable.latest_1, R.drawable.latest_2, R.drawable.latest_3, R.drawable.latest_4));

    ArrayList roomImages = new ArrayList<>(Arrays.asList(R.drawable.recommended_1, R.drawable.recommended_2, R.drawable.recommended_3, R.drawable.recommended_4,
            R.drawable.recommended_5, R.drawable.recommended_6, R.drawable.recommended_7, R.drawable.recommended_8));
    ArrayList roomPricesBefore = new ArrayList<>(Arrays.asList("₹2000", "₹1000", "₹1500", "₹1200", "₹5000", "₹2000", "₹10000", "₹1000"));
    ArrayList roomPricesAfter = new ArrayList<>(Arrays.asList("₹680", "₹720", "₹975", "₹504", "₹950", "₹920", "₹1000", "₹880"));
    ArrayList percentageOff = new ArrayList<>(Arrays.asList("66% OFF", "28% OFF", "35% OFF", "58% OFF", "81% OFF", "54% OFF", "90% OFF", "12% OFF"));
//    ArrayList ratingValue = new ArrayList<>(Arrays.asList("4.6", "3.9", "4.0", "4.9", "4.1", "3.9", "4.6", "4.2"));
//    ArrayList comments = new ArrayList<>(Arrays.asList("Very Good", "Good", "Good", "Excellent", "Good", "Good", "Very Good", "Good"));
//    Array


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//      ----------------------------------------------------------------------------------------------------------------------------------

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomLocationsAdapter customAdapter = new CustomLocationsAdapter(MainActivity.this, imagesRelativeLayout, textUnderImages);
        recyclerView.setAdapter(customAdapter);

//      ------------------------------------------------------------------------------------------------------------------------------------

        RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.dont_miss_these_recycler_view);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        CustomDontMissTheseAdapter customAdapter2 = new CustomDontMissTheseAdapter(MainActivity.this, dontMissTheseImages);
        recyclerView2.setAdapter(customAdapter2);

//      ------------------------------------------------------------------------------------------------------------------------------------

        RecyclerView recyclerView3 = (RecyclerView) findViewById(R.id.latest_oyo_recycler_view);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(linearLayoutManager3);

        CustomDontMissTheseAdapter customAdapter3 = new CustomDontMissTheseAdapter(MainActivity.this, latestOYOImages);
        recyclerView3.setAdapter(customAdapter3);

//      -----------------------------------------------------------------------------------------------------------------------------------

        RecyclerView recyclerView4 = (RecyclerView) findViewById(R.id.recommended_recycler_view);

        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(linearLayoutManager4);

        CustomRecommendedAdapter customAdapter4 = new CustomRecommendedAdapter(MainActivity.this, roomImages, roomPricesBefore, roomPricesAfter, percentageOff);
        recyclerView4.setAdapter(customAdapter4);

//      ------------------------------------------------------------------------------------------------------------------------------------
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_saved_bookings:
                    return true;
                case R.id.navigation_bookings:
                    return true;
                case R.id.navigation_profile:
                    return true;
                case R.id.navigation_invite:
                    return true;
            }
            return false;
        }
    };

//    private void loadFragment(Fragment fragment, int drawable_resource){
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(drawable_resource, fragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//    }

    public void nextActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
        startActivity(intent);
    }
}
