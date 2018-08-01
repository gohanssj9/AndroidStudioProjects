package com.example.devil_jin.testforsql;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONException;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.util.Pair;

import static java.net.Proxy.Type.HTTP;

public class AddDepartmentActivity extends AppCompatActivity {

    EditText inputTitle;
    EditText inputBody;
    private static String urlWebService = "http://192.168.10.212:8000/departments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_department);

        inputTitle = (EditText) findViewById(R.id.inputTitle);
        inputBody = (EditText) findViewById(R.id.inputBody);

    }

    public void innerAddDept(View view){
        new CreateDepartment().execute();
    }

    class CreateDepartment extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args){
            String title = inputTitle.getText().toString();
            String body = inputBody.getText().toString();

            List<Pair<String, String>>params = new ArrayList<>();
            params.add(new Pair<>("title", title));
            params.add(new Pair<>("body", body));

            try {
                URL url = new URL(urlWebService);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.connect();

                JSONObject json = new JSONObject();
                json.put("title", title);
                json.put("body", body);

                String jsonToSend = "title=" + title + "&body=" + body;
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
                bufferedWriter.write(json.toString());

                bufferedWriter.flush();
                bufferedWriter.close();

                int responseCode = con.getResponseCode();
                if(responseCode == con.HTTP_OK){
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String response = "";

                    String line;
                    while((line = bufferedReader.readLine()) != null) response += line;
                    bufferedReader.close();
                    return json;
                    //return json.toString();
                }
                else return null;
            } catch(Exception e){
                return null;
            }



        }
        @Override
        protected void onPostExecute(JSONObject s){
            super.onPostExecute(s);

            Intent newIntent = new Intent(AddDepartmentActivity.this, MainActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newIntent);
        }
    }
}
