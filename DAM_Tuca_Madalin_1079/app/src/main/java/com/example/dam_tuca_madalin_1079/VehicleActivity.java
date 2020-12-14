package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class VehicleActivity extends AppCompatActivity {
    private AppDb db;
    private JSONObject data;
    private JSONArray dataArray;
    private ArrayList<String> models;
    private ArrayList<String> brands;
    private ArrayList<String> brandsUnique;
    ArrayAdapter<String> brandAdapter;
    ArrayAdapter<String> modelAdapter;
    ArrayAdapter<String> yearAdapter;
    ArrayAdapter<String> capacityAdapter;
    private Spinner spBrand;
    private Spinner spModel;
    private RadioGroup rgFuelType;
    private Spinner spYear;
    private RadioGroup rgBodyType;
    private RadioButton rbSedan;
    private RadioButton rbHatch;
    private RadioButton rbBreak;
    private RadioButton rbSuv;
    private RadioButton rbPetrol;
    private RadioButton rbDiesel;
    private Spinner spCapacity;
    private Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        final Spinner spinnerBrand = (Spinner)findViewById(R.id.spinnerBrand);
        final Spinner spinnerModel = (Spinner)findViewById(R.id.spinnerModel);
        final Spinner spinnerYear = (Spinner)findViewById(R.id.spinnerYear);
        final Spinner spinnerCapacities = (Spinner)findViewById(R.id.spinnerCapacity);
        initViews();
        models = new ArrayList<>();
        brands = new ArrayList<>();
        db = Room.databaseBuilder(this, AppDb.class, "users").allowMainThreadQueries().build();
        models.add(0, "None");
        brands.add(0, "None");

        (new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL("https://api.mocki.io/v1/95b287ec");
                    HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                    try{
                        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line = "";
                        while((line = reader.readLine()) != null){
                            sb.append(line);
                        }
                        Log.d("Tag", sb.toString());
                        data = new JSONObject(sb.toString());
                       dataArray = new JSONArray();
                        dataArray = (JSONArray) data.get("cars");
                        JSONObject elem = dataArray.getJSONObject(0);
                        Log.d("Tag Test", elem.toString());
                        brands.add(elem.get("brand").toString());
                        int count = 1;
                        for(int i = 0; i < dataArray.length(); i++){
                            elem = dataArray.getJSONObject(i);
                            if(elem.get("brand").toString().compareTo(brands.get(count)) != 0){
                                brands.add(elem.get("brand").toString());
                                count++;
                            }
                            //models.add(elem.get("model").toString());
                        }
                    } finally {
                        Collections.sort(brands);
                        urlConnection.disconnect();
                    }
                }catch(Exception e){
                    Log.e("Vehicle", e.toString());
                }
            }
        })).start();
        brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, brands);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBrand.setAdapter(brandAdapter);

        modelAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, models);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModel.setAdapter(modelAdapter);
        Integer startYear = 1990;
        Integer currentYear = 2020;
        Integer diff = currentYear-startYear + 1;
        String[] years = new String[diff];
        for(int i = 0; i < diff; i++){
            years[i] = (startYear).toString();
            ++startYear;
        }
        yearAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYear.setAdapter(yearAdapter);

        String[] capacities = new String[20];
        Integer cap = 1000;
        for(Integer i = 0; i < 20; i++){
            capacities[i] = cap.toString();
            cap+=200;
        }
        capacityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, capacities);
        capacityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCapacities.setAdapter(capacityAdapter);

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("None")){
                    //nmc
                }else{
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(),"Selected item: " + item, Toast.LENGTH_LONG).show();
                    try{
                        ArrayList<String>updatedModels = new ArrayList<>();
                        int count = 1;
                        for(int i = 0; i < dataArray.length(); i++){
                            JSONObject element = dataArray.getJSONObject(i);
                            if(item.equals(element.get("brand").toString())){
                                updatedModels.add(element.get("model").toString());
                            }
                        }
                        ArrayAdapter<String> newAdapt = new ArrayAdapter<String>(VehicleActivity.this,android.R.layout.simple_list_item_1, updatedModels);
                        newAdapt.notifyDataSetChanged();
                        spinnerModel.setAdapter(newAdapt);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerModel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("None")){
                    //nmc
                }else{
                    String item = parent.getItemAtPosition(position).toString();
//                    Toast.makeText(parent.getContext(),"Selected item: " + item, Toast.LENGTH_LONG).show();
//                    spinnerModel.setSelection(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateContent()){
                    Globals globals = new Globals(getApplicationContext());
                    int sessionId = globals.returnUserSession();
                    String brand = spinnerBrand.getSelectedItem().toString();
                    String model = spinnerModel.getSelectedItem().toString();
                    int bodyId = rgBodyType.getCheckedRadioButtonId();
                    RadioButton rbBody = findViewById(bodyId);
                    String body = rbBody.getText().toString();
                    int year = Integer.parseInt(spinnerYear.getSelectedItem().toString());
                    int fuelId = rgFuelType.getCheckedRadioButtonId();
                    RadioButton rbFuel = findViewById(fuelId);
                    String fuelType = rbFuel.getText().toString();
                    int capacity = Integer.parseInt(spinnerCapacities.getSelectedItem().toString());

                    Car car = new Car(sessionId,brand,model,body, year,fuelType,capacity);
                    db.userDAO().insertCar(car);
                    Log.d("CAR", car.toString());
                    StyleableToast.makeText(getApplicationContext(), brand + " " + model + " added!", Toast.LENGTH_LONG, R.style.successToast).show();
                }
            }
        });


    }

    public void populateUniqueBrandList(){
       brandsUnique = new ArrayList<>( new HashSet<>(brands));
       Log.d("text", brandsUnique.get(1));
    }

    public void initViews(){
        spBrand = findViewById(R.id.spinnerBrand);
        spModel = findViewById(R.id.spinnerModel);
        rgFuelType = findViewById(R.id.fuelTypeGroup);
        spYear = findViewById(R.id.spinnerYear);
        rgBodyType = findViewById(R.id.bodyNormalTypeGroup);
        rbSedan = findViewById(R.id.rbSedan);
        rbHatch = findViewById(R.id.rbHatch);
        rbBreak = findViewById(R.id.rbBreak);
        rbSuv = findViewById(R.id.rbSuv);
        rbPetrol = findViewById(R.id.radioPetrol);
        rbDiesel = findViewById(R.id.radioDiesel);
        spCapacity = findViewById(R.id.spinnerYear);
        submitBtn = findViewById(R.id.btnAddCar);
    }

    public boolean validateContent(){
        boolean valid = true;
        if(spBrand.getSelectedItem().toString().compareTo("None") == 0){
            valid = false;
            StyleableToast.makeText(this, "You have to select your brand.", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        else if(spModel.getSelectedItem().toString().compareTo("None") == 0){
            valid = false;
            StyleableToast.makeText(this, "You have to select your model.", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        else if(!rbPetrol.isChecked() && !rbDiesel.isChecked()){
            valid = false;
            StyleableToast.makeText(this, "You have to select your fucking fuel type.", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        else if(spYear.getSelectedItem().toString() == null){
            valid = false;
            StyleableToast.makeText(this, "You have to select a production year.", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        else if(!rbHatch.isChecked() && !rbSedan.isChecked() && !rbBreak.isChecked() && !rbSuv.isChecked()){
            valid = false;
            StyleableToast.makeText(this, "You have to select your body type.", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        else if(spCapacity.getSelectedItem().toString() == null){
            valid = false;
            StyleableToast.makeText(this, "You have to select your capacity.", Toast.LENGTH_LONG, R.style.errToast).show();
        }

        return valid;
    }
}