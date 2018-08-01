package com.example.devil_jin.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    Button moveBackMain;
    TextView receivedInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().hide();
        receivedInput = (TextView) findViewById(R.id.greetingsTxt);

        Intent txtIntent = getIntent();
        String receivedInputString = txtIntent.getStringExtra("Name");
        receivedInput.setText("Greetings --> '" + receivedInputString + "'");

        moveBackMain = (Button) findViewById(R.id.returnMeButton);
        moveBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newOneIntent = new Intent(SecondActivity.this, MainActivity.class);
                newOneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newOneIntent);
            }
        });
    }
}
