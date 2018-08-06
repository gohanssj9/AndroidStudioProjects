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

public class EditDepartmentActivity extends AppCompatActivity {

    private EditText mtitle, mbody;
    private int storableId;
    private Button updateDeptButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_department);

        mtitle = (EditText) findViewById(R.id.inputTitleEdit);
        mbody = (EditText) findViewById(R.id.inputBodyEdit);

        Intent intent = getIntent();
        mtitle.setText(intent.getStringExtra("title"));
        mbody.setText(intent.getStringExtra("body"));

        storableId = Integer.parseInt(intent.getStringExtra("id"));
        updateDeptButton = (Button) findViewById(R.id.innerEditDept);

        updateDeptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UpdateDepartment().execute();
            }
        });

        System.out.println("Above Printable values");
    }

    //For Populating Values;

//    public void editDepartment(final int i, final String title, final String body){
//        System.out.println(i + "----" + title + "-----" +  body + "---->");
//        mtitle.setText(title);
//        mbody.setText(body);
//        storableId = i;
//
//    }

    class UpdateDepartment extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args){
            String title = mtitle.getText().toString();
            String body = mbody.getText().toString();

            List<Pair<String, String>> params = new ArrayList<>();
            params.add(new Pair<>("title", title));
            params.add(new Pair<>("body", body));

            String urlWebService = "http://192.168.10.212:8000/departments/" + storableId;

            try {
                URL url = new URL(urlWebService);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("PUT");
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

            Intent newIntent = new Intent(EditDepartmentActivity.this, MainActivity.class);
            newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(newIntent);
        }
    }
}
