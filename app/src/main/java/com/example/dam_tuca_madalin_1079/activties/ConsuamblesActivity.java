package com.example.dam_tuca_madalin_1079.activties;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.dam_tuca_madalin_1079.classes.Consumable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

public class ConsuamblesActivity extends AppCompatActivity {
    private Spinner spTypes;
    private EditText etPrice;
    private Button btnChart;
    private Button btnAdd;
    private ArrayAdapter<String> consumablesTypeAdapter;
    DatabaseReference dbRef;
    Consumable consumable;
    Globals globals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consuambles);
        initViews();
        List<String> consTypes = new ArrayList<>();
        consTypes.add("Oil");
        consTypes.add("Coolant");
        consTypes.add("Brake Fluid");
        consTypes.add("Brake Pads");
        consTypes.add("Freon");
        consumablesTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consTypes);
        consumablesTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypes.setAdapter(consumablesTypeAdapter);
        dbRef = FirebaseDatabase.getInstance().getReference("Consumables");
        globals = new Globals(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consumable = new Consumable(globals.returnUserSession(),spTypes.getSelectedItem().toString(),Float.parseFloat(etPrice.getText().toString()));
                //TODO: Solve to add to the sum of one type;
                dbRef.child("user"+globals.returnUserSession()).push().setValue(consumable);
              //  dbRef.setValue(consumable);
                StyleableToast.makeText(getApplicationContext(),spTypes.getSelectedItem().toString() + " Added!", Toast.LENGTH_LONG, R.style.successToast).show();
            }
        });

        btnChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chartActivity = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(chartActivity);
            }
        });
    }

    private void initViews(){
        spTypes = findViewById(R.id.spConsTypes);
        etPrice = findViewById(R.id.etConsPrice);
        btnAdd = findViewById(R.id.btnSubCons);
        btnChart = findViewById(R.id.btnPieChart);
    }
}