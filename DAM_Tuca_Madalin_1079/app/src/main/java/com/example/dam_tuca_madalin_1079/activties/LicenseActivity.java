package com.example.dam_tuca_madalin_1079.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.classes.DriverLicense;
import com.example.dam_tuca_madalin_1079.Globals;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.User;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Date;

public class LicenseActivity extends AppCompatActivity {
    AppDb db;
    Globals globals;
    private DatePicker dpLicenseIssueDate;
    private DatePicker dpLicenseEndDate;
    private EditText etPolice;
    private EditText etCNP;
    private EditText etLicenseId;
    private CheckBox[] licenseTypes;
    private Button btnSubmitLicense;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        db = Room.databaseBuilder(this, AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        initViews();
        AsyncTask.execute(()->{
        btnSubmitLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateData()){
                    globals = new Globals(getApplicationContext());
                   User currentUserData =  db.userDAO().getUserById(globals.returnUserSession());
                    Date issueDate = globals.getDateFromDatePicker(dpLicenseIssueDate);
                    String issueDateStr = globals.formatDate(issueDate);
                    Date endDate = globals.getDateFromDatePicker(dpLicenseEndDate);
                    String endDateStr = globals.formatDate(endDate);
                    String policeStation = etPolice.getText().toString();
                    String CNP = etCNP.getText().toString();
                    String licenseId = etLicenseId.getText().toString();
                    String type = "";
                    for(CheckBox cb : licenseTypes){
                        if(cb.isChecked()){
                            type += cb.getText().toString();
                        }
                    }
                    DriverLicense license = new DriverLicense(globals.returnUserSession(), currentUserData.getName(),
                            currentUserData.getSurname(), currentUserData.getBirthDate(), currentUserData.getCity(),
                            currentUserData.getCounty(),issueDateStr,endDateStr,policeStation, CNP,licenseId,type);
                    Log.d("License",license.toString() );
                    db.userDAO().insertLicense(license);
                    StyleableToast.makeText(getApplicationContext(), "Driver license added!", Toast.LENGTH_LONG, R.style.successToast).show();
                }
            }
        });
        });

    }

    private void initViews(){
        dpLicenseIssueDate = findViewById(R.id.dpStartDate);
        dpLicenseEndDate = findViewById(R.id.dpEndDate);
        etPolice = findViewById(R.id.etPoliceDep);
        etCNP = findViewById(R.id.etCNP);
        etLicenseId = findViewById(R.id.etLicenseNumber);
        licenseTypes = new CheckBox[4];
        licenseTypes[0] = findViewById(R.id.A_license);
        licenseTypes[1] = findViewById(R.id.B_license);
        licenseTypes[2] = findViewById(R.id.C_license);
        licenseTypes[3] = findViewById(R.id.D_license);
        btnSubmitLicense = findViewById(R.id.btnAddLicense);
    }

    private boolean validateData(){
        boolean valid = true;
        if(etPolice.getText().toString().isEmpty()){
            StyleableToast.makeText(this, "Police station field empty!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        if(etCNP.getText().toString().isEmpty()){
            StyleableToast.makeText(this,"CNP field empty!", Toast.LENGTH_LONG,R.style.errToast).show();
            valid = false;
        }
        if(etCNP.getText().toString().length() != 13){
            StyleableToast.makeText(this, "CNP length must be 13 characters long", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        if(etLicenseId.getText().toString().isEmpty()){
            StyleableToast.makeText(this, "License Id field empty!", Toast.LENGTH_LONG, R.style.errToast).show();
            valid = false;
        }
        return valid;
    }
}