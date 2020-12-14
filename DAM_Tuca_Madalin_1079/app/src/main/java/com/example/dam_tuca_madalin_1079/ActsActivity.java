package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActsActivity extends AppCompatActivity {

    AppDb db;
    Globals globals;
    private EditText etBrand;
    private EditText etModel;
    private Spinner spActType;
    private DatePicker dpStartDate;
    private DatePicker dpEndDate;
    private Button btnSubAct;
    private ArrayAdapter<String> actTypesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acts);
        initViews();
        List<String> actTypes = new ArrayList<>();
        actTypes.add("RCA");
        actTypes.add("Casco");
        actTypes.add("ITP");
        actTypes.add("Rovignette");
        actTypesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, actTypes);
        actTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spActType.setAdapter(actTypesAdapter);
        db = Room.databaseBuilder(this, AppDb.class, "users").allowMainThreadQueries().build();
        globals = new Globals(this);
        int session = globals.returnUserSession();

        btnSubAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                String brand = etBrand.getText().toString();
                String model = etModel.getText().toString();
                String type = spActType.getSelectedItem().toString();
                String startDate = formatDate(getDateFromDatePicker(dpStartDate));
                String endDate = formatDate(getDateFromDatePicker(dpEndDate));
                int carId = db.userDAO().getCarId(brand, model, globals.returnUserSession());
                Log.d("Car id", carId + "");
                Act act = new Act(globals.returnUserSession(),carId, type, brand, model, startDate, endDate);
                Log.d("Act", act.toString());
                db.userDAO().insertAct(act);
                StyleableToast.makeText(getApplicationContext(), type + " act added!", Toast.LENGTH_LONG, R.style.successToast).show();
            }

            }
        });

    }

    protected void initViews() {
        etBrand = findViewById(R.id.etActBrand);
        etModel = findViewById(R.id.etActModel);
        spActType = findViewById(R.id.spActTypes);
        dpStartDate = findViewById(R.id.dpStartActDate);
        dpEndDate = findViewById(R.id.dpEndActDate);
        btnSubAct = findViewById(R.id.btnSubAct);
    }

    private boolean validateData() {
        boolean valid = true;
        if (etModel.getText().toString().isEmpty() || etBrand.getText().toString().isEmpty()) {
            valid = false;
            StyleableToast.makeText(this, "Brand or Model field is empty", Toast.LENGTH_LONG, R.style.errToast).show();
        }
        int carId = -1;
        carId = db.userDAO().getCarId(etBrand.getText().toString(), etModel.getText().toString(), globals.returnUserSession());
        Log.d("Car ID TEST", carId+"");
        if(carId == 0){
            StyleableToast.makeText(this, "Cannot find the specified car in your account", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }

        return valid;
    }



    private String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try{
            return dateFormat.format(date);
        }catch(Exception e){
            return date.toString();
        }
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}