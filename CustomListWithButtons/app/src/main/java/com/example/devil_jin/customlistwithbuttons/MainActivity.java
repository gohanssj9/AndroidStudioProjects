package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static ArrayList<Model> modelArrayList;
    private CustomListAdapter customListAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
//    private Button addDeptButton;
//    private String [] fruitList = new String[] {"Apples", "Oranges", "Grapes", "Tomatoes", "Mangoes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);

        listView = (ListView) findViewById(R.id.listView);
        downloadJSON("http://192.168.10.212:8000/departments");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downloadJSON("http://192.168.10.212:8000/departments");
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    public void addNewDepartment(View view){
        Intent newIntent = new Intent(MainActivity.this, AddDepartmentActivity.class);
        startActivity(newIntent);
    }


    private void downloadJSON(final String urlWebService){
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
            super.onPreExecute();
        }

            @Override
            protected void onPostExecute(String s) {
                System.out.println(s);
                super.onPostExecute(s);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try{
                    System.out.println("Inside Try");
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    System.out.println("After Http");
                    System.out.println(con.getResponseCode());

                    String sb = "";
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb = sb + json + "\n";
                    }
                    System.out.println(sb + "-------SB");
                    return sb.toString().trim();

                } catch (Exception e){
                    System.out.println("Inside Catch");
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        String [] bodies = new String[jsonArray.length()];
        String [] titles = new String[jsonArray.length()];
        int [] ids = new int[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            bodies[i] = obj.getString("body");
            ids[i] = obj.getInt("id");
            titles[i] = obj.getString("title");

        }
        modelArrayList = getModel(bodies, titles, ids);
        customListAdapter = new CustomListAdapter(this);

        listView.setAdapter(customListAdapter);
    }

    private ArrayList<Model> getModel(String [] bodies, String [] titles, int [] ids){
        ArrayList<Model> list = new ArrayList<>();
        for(int i = 0; i < bodies.length; i++){
            Model model = new Model();
            model.setDepartmentName(bodies[i]);
            model.setDepartmentId(ids[i]);
            model.setDepartmentTitle(titles[i]);
            list.add(model);
        }
        return list;
    }
}
