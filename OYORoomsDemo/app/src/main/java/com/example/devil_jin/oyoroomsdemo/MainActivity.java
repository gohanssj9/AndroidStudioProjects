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

    ArrayList imagesRelativeLayout;
    ArrayList dontMissTheseImages;
    ArrayList latestOYOImages;

//    ArrayList imagesRelativeLayout = new ArrayList<>(Arrays.asList(R.drawable.gps, R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
//            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8, R.drawable.a_9, R.drawable.all_cities_2));
    ArrayList textUnderImages = new ArrayList<>(Arrays.asList("Nearby", "Hyderabad", "Chennai", "Delhi", "Mumbai", "Vizag", "Bengaluru", "Noida", "Trichy", "Kolkata", "All Cities"));
//    ArrayList dontMissTheseImages = new ArrayList<>(Arrays.asList(R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4,
//            R.drawable.a_5, R.drawable.a_6, R.drawable.a_7, R.drawable.a_8, R.drawable.a_9));

//    ArrayList latestOYOImages = new ArrayList<>(Arrays.asList(R.drawable.a_1, R.drawable.a_2, R.drawable.a_3, R.drawable.a_4));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

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

        //loadFragment(new LocationsFragment(), R.id.locationsFrameLayout);
        //loadFragment(new RecommendedFragment(), R.id.recommendedFrameLayout);
//        loadFragment(new LatestOYOFragment(), R.id.latestOyoFrameLayout);
//        loadFragment(new DontMissTheseFragment(), R.id.dontMissTheseFrameLayout);
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

    private void loadFragment(Fragment fragment, int drawable_resource){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(drawable_resource, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void nextActivity(View view){
        Intent intent = new Intent(getApplicationContext(), LocationsActivity.class);
        startActivity(intent);
    }

    public void offersActivity(View view){
        Intent intent = new Intent(getApplicationContext(), RecommendedActivity.class);
        startActivity(intent);
    }
}
