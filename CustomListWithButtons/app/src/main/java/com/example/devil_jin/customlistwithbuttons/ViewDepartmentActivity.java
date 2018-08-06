package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ViewDepartmentActivity extends AppCompatActivity {

    private ListView studentListView;
    public static ArrayList<StudentModel> modelArrayList;
    private CustomStudentListAdapter customStudentListAdapter;

    public static int storageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_department);

        Intent intent = getIntent();
        storageId = Integer.parseInt(intent.getStringExtra("id"));

        studentListView = (ListView) findViewById(R.id.studentListView);
        downloadJSON("http://192.168.10.212:8000/departments/" + storageId + "/students");
    }

    public void addNewStudent(View view){
        Intent newIntent = new Intent(ViewDepartmentActivity.this, AddStudentActivity.class);
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
                    loadIntoStudentListView(s);
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
                    return "0";
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }

    private void loadIntoStudentListView(String json) throws JSONException{
        JSONArray jsonArray = new JSONArray(json);
        int [] ages = new int[jsonArray.length()];
        String [] names = new String[jsonArray.length()];
        int [] ids = new int[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = jsonArray.getJSONObject(i);
            ages[i] = obj.getInt("age");
            ids[i] = obj.getInt("id");
            names[i] = obj.getString("name");
        }
        modelArrayList = getModel(ages, names, ids);
        customStudentListAdapter = new CustomStudentListAdapter(this);

        studentListView.setAdapter(customStudentListAdapter);
    }

    private ArrayList<StudentModel> getModel(int [] ages, String [] names, int [] ids){
        ArrayList<StudentModel> list = new ArrayList<>();
        for(int i = 0; i < ages.length; i++){
            StudentModel model = new StudentModel();
            model.setStudentName(names[i]);
            model.setStudentId(ids[i]);
            model.setStudentAge(ages[i]);
            list.add(model);
        }
        return list;
    }


}
