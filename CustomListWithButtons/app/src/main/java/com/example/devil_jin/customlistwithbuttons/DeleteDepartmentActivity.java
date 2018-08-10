package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteDepartmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_department);
    }

//    public void returnBackDelete(){
//        Intent intent = new Intent(DeleteDepartmentActivity.this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//
//    }

    public void deleteDepartment(final int i){
        class DeleteDepartment extends AsyncTask<String, String, String>{

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... strings) {
                System.out.println(i);
                try {
                    System.out.println("Here!");
                    String v = Integer.toString(i);
                    String urlWebService = "http://192.168.10.212:8000/departments/" + v;
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setRequestMethod("DELETE");
//                    con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                    con.setRequestProperty("Accept", "application/json");
                    con.connect();


                    int responseCode = con.getResponseCode();
                    System.out.println(responseCode + "----->");
                    if(responseCode == con.HTTP_OK){
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        String response = "";

                        String line;
                        while((line = bufferedReader.readLine()) != null) response += line;
                        bufferedReader.close();
                        return response;

                    }
                    else{
                        System.out.println("Inside Else");
                        return null;
                    }
                } catch(Exception e){
                    System.out.println("Inside Exception");
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s){
                System.out.println(s);
                super.onPostExecute(s);
//                MainActivity activity = new MainActivity();
//                activity.recreate();
//                Intent intent = new Intent(getApplicationContext(), DummyFragmentActivity.class);
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intent); // finish();
            }
        }

        new DeleteDepartment().execute();
    }
}
