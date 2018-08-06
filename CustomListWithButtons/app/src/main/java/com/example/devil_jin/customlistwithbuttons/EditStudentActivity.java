package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
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

public class EditStudentActivity extends AppCompatActivity {

    private EditText mname, mage;
    private int storableId, department_id;
    private Button updateStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        mname = (EditText) findViewById(R.id.inputNameEdit);
        mage = (EditText) findViewById(R.id.inputAgeEdit);

        Intent intent = getIntent();
        mname.setText(intent.getStringExtra("name"));
        mage.setText(intent.getStringExtra("age"));

        ViewDepartmentActivity obj = new ViewDepartmentActivity();
        department_id = obj.storageId;

        System.out.println("IN EDIT:" + department_id + "IN VIEW" + obj.storageId + "IN EDIT STORAGE:" + storableId);

        storableId = Integer.parseInt(intent.getStringExtra("id"));
        updateStudentButton = (Button) findViewById(R.id.innerEditStudent);

        updateStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EditStudentActivity.UpdateStudent().execute();
            }
        });

        System.out.println("Above Printable values");
    }

    class UpdateStudent extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args){
            String name = mname.getText().toString();
            int age = Integer.parseInt(mage.getText().toString());

            String urlWebService = "http://192.168.10.212:8000/students/" + storableId;

            try {
                URL url = new URL(urlWebService);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.setRequestProperty("Accept", "application/json");
                con.connect();

                JSONObject json = new JSONObject();
                json.put("name", name);
                json.put("age", age);


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

            System.out.println("IN ONPOST:" + department_id);

            Intent newIntent = new Intent(getApplicationContext(), ViewDepartmentActivity.class);
            newIntent.putExtra("id", Integer.toString(department_id));
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newIntent);
        }
    }
}
