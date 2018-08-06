package com.example.devil_jin.customlistwithbuttons;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteStudentActivity extends AppCompatActivity {

    private int department_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        ViewDepartmentActivity obj = new ViewDepartmentActivity();
        department_id = obj.storageId;
    }

    public void deleteStudent(final int i){
        class DeleteStudent extends AsyncTask<String, String, String> {

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
                    String urlWebService = "http://192.168.10.212:8000/students/" + v;
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
                Intent intent = new Intent(getApplicationContext(), ViewDepartmentActivity.class);
                intent.putExtra("id", department_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent); finish();
            }
        }

        new DeleteStudent().execute();
    }
}
