package com.example.devil_jin.trycustomlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CustomListAdapter customListAdapter;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[] {
        R.drawable.benz, R.drawable.bike, R.drawable.car, R.drawable.carrera, R.drawable.ferrari, R.drawable.harly,
        R.drawable.lamborghini, R.drawable.silver
    };

    private String [] myImageNameList = new String[]{
            "Benz", "Bike", "Car", "Carrera", "Ferrari", "Harly", "Lamborghini", "Silver"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageModelArrayList = populateList();
        listView = (ListView) findViewById(R.id.listView);
        customListAdapter = new CustomListAdapter(this, imageModelArrayList);
        listView.setAdapter(customListAdapter);
    }

    private ArrayList<ImageModel> populateList(){
        ArrayList<ImageModel> list = new ArrayList<>();
        for(int i = 0; i < 8; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setName(myImageNameList[i]);
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }
        return list;
    }
}
