package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class VehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        Spinner spinnerBrand = (Spinner)findViewById(R.id.spinnerBrand);
        Spinner spinnerModel = (Spinner)findViewById(R.id.spinnerModel);
        ArrayList<String> brands = new ArrayList<>();
        //Todo: BD load from table
        brands.add(0, "Choose Brand");
        brands.add("Audi");
        brands.add("Alpha Romeo");
        brands.add("BMW");
        brands.add("Bentley");
        brands.add("Citroen");
        brands.add("Chevrolet");
        brands.add("Seat");
        brands.add("Kia");
        final ArrayList<String> models = new ArrayList<String>();

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("https://parseapi.back4app.com/classes/Car_Model_List_Audi?limit=10");
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.setRequestProperty("X-Parse-Application-Id", "hlhoNKjOvEhqzcVAJ1lxjicJLZNVv36GdbboZj3Z"); // This is the fake app's application id
                    urlConnection.setRequestProperty("X-Parse-Master-Key", "SNMJJF0CZZhTPhLDIqGhTlUNV9r60M2Z5spyWfXW");
                    try{
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while((line = reader.readLine()) != null){
                            sb.append(line);
                        }
                        JSONObject data = new JSONObject(sb.toString());
                        JSONArray dataArray = new JSONArray();
                        dataArray = (JSONArray) data.get("results");
                        for(int i = 0; i < dataArray.length(); i++){
                            JSONObject elem = dataArray.getJSONObject(i);
                            models.add(elem.get("Model").toString());
                            Log.d("Vehicle", models.get(i));
                        }
                    } finally {
                        urlConnection.disconnect();
                    }
                }catch(Exception e){
                    Log.e("Vehicle", e.toString());
                }
            }
        })).start();


        ArrayAdapter<String> brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, brands);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, models);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(modelAdapter);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Choose Brand")){
                    //nmc
                }else{
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(),"Selected item: " + item, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}