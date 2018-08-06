package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DummyFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy_fragment);

        Intent intent = new Intent(DummyFragmentActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
