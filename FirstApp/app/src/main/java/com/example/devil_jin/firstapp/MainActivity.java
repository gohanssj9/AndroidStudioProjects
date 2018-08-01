package com.example.devil_jin.firstapp;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button moveToSecond;
    EditText editInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        editInput = (EditText) findViewById(R.id.nameEditInput);

        moveToSecond = (Button) findViewById(R.id.clickMeButton);
        moveToSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String takeInput = editInput.getText().toString();

                Intent newIntent = new Intent(MainActivity.this, SecondActivity.class);
                newIntent.putExtra("Name", takeInput);
                startActivity(newIntent);
            }
        });
    }
}
