package com.example.devil_jin.customlistwithbuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        textView = (TextView) findViewById(R.id.textView);
        for(int i = 0; i < 5 ; i++){
            String text = textView.getText().toString();
            textView.setText(text + MainActivity.modelArrayList.get(i).getFruit() + "->" + MainActivity.modelArrayList.get(i).getNumber() + "\n");
        }
    }
}
