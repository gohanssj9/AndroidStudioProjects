package com.example.devil_jin.oyoroomsdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new LocationsFragment(), R.id.locationsFrameLayout);
        //loadFragment(new RecommendedFragment(), R.id.recommendedFrameLayout);
        loadFragment(new LatestOYOFragment(), R.id.latestOyoFrameLayout);
        loadFragment(new DontMissTheseFragment(), R.id.dontMissTheseFrameLayout);

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
