package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    EditText inputName;
    EditText inputAge;
    int storageIdInStudent;
    Intent newIntent;
    private static String urlWebService = "http://192.168.10.212:8000/students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        inputName = (EditText) findViewById(R.id.inputName);
        inputAge = (EditText) findViewById(R.id.inputAge);

        ViewDepartmentActivity obj = new ViewDepartmentActivity();
        storageIdInStudent = obj.storageId;
        System.out.println("In student:" + storageIdInStudent + "In other activity:" + obj.storageId);
    }

    public void innerAddStudent(View view){
        new AddStudentActivity.CreateStudent().execute();
    }

    class CreateStudent extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args){
            String name = inputName.getText().toString();
            String age = inputAge.getText().toString();

            try {
                URL url = new URL(urlWebService);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.connect();

                JSONObject json = new JSONObject();
                json.put("name", name);
                json.put("age", Integer.parseInt(age));
                json.put("department_id", storageIdInStudent);

                System.out.println(name + "------" + age + "--------" + storageIdInStudent);

                //String jsonToSend = "title=" + title + "&body=" + body;
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

            newIntent = new Intent(AddStudentActivity.this, ViewDepartmentActivity.class);
            newIntent.putExtra("id", Integer.toString(storageIdInStudent));
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newIntent);
        }
    }
}
