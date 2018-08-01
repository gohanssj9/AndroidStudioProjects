package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static ArrayList<Model> modelArrayList;
    private CustomListAdapter customListAdapter;
    private Button buttonNext;
    private String [] fruitList = new String[] {"Apples", "Oranges", "Grapes", "Tomatoes", "Mangoes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        buttonNext = (Button) findViewById(R.id.nextActivity);

        modelArrayList = getModel();
        customListAdapter = new CustomListAdapter(this);
        listView.setAdapter(customListAdapter);

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<Model> getModel(){
        ArrayList<Model> list = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Model model = new Model();
            model.setNumber(1);
            model.setFruit(fruitList[i]);
            list.add(model);
        }
        return list;
    }
}
