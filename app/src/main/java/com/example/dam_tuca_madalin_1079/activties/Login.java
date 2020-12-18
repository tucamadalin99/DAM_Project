package com.example.dam_tuca_madalin_1079.activties;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.dam_tuca_madalin_1079.AppDb;
import com.example.dam_tuca_madalin_1079.R;
import com.example.dam_tuca_madalin_1079.classes.User;
import com.muddzdev.styleabletoast.StyleableToast;

public class Login extends AppCompatActivity {

    private AppDb db;

   EditText etUserName;
   EditText etPassword;
    private Switch swRemember;
    String username;
    String password;
    boolean swOnOff;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USERNAME = "text";
    public static final String PASSWORD = "password";
    public static final String SW_REMEMBER = "remember";
    public static final String CURRENT_USER_SESSION = "session";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = Room.databaseBuilder(this, AppDb.class, "users").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        Bundle extras = getIntent().getExtras();
        String name = "";
        String surname = "";
        String email = "";
        String password;
        etUserName = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPass);
        swRemember = findViewById(R.id.swRemember);
        if(extras != null){
            name = extras.getString("nameKey");
            surname = extras.getString("surnameKey");
            email = extras.getString("emailKey");
            password = extras.getString("passKey");
            etUserName.setText(email);
            etPassword.setText(password);
        }
        Button btnLogin = (Button)findViewById(R.id.btnLog);
        final String finalName = name;
        final String finalSurname = surname;
        final String finalEmail = email;
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveData();
                User loginUser = db.userDAO().getLoginUser(etUserName.getText().toString(), etPassword.getText().toString());
                if(loginUser != null){
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt(CURRENT_USER_SESSION, loginUser.id);
                    editor.apply();
                    Log.v("Session", "ID: " + loginUser.id);
                    Log.v("SESSION RETRIEVE: ", "ID: " + sharedPreferences.getInt(CURRENT_USER_SESSION,0));
                    Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                    mainActivity.putExtra("nameKey", finalName);
                    mainActivity.putExtra("surnameKey", finalSurname);
                    mainActivity.putExtra("emailKey", finalEmail);
                    startActivity(mainActivity);
                }else{
                    StyleableToast.makeText(getApplicationContext(), "Invalid user or password!", Toast.LENGTH_LONG, R.style.errToast).show();
                }
            }
        });

        swRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    saveData();
                }if(!isChecked){
                    deleteData();
                }
            }
        });


        loadData();
        updateViews();
    }


    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME, etUserName.getText().toString());
        editor.putString(PASSWORD, etPassword.getText().toString());
        editor.putBoolean(SW_REMEMBER, swRemember.isChecked());
        editor.apply();

        Toast.makeText(this, "User remembered!", Toast.LENGTH_LONG).show();
    }

    public void deleteData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USERNAME, "");
        editor.putString(PASSWORD, "");
        editor.putBoolean(SW_REMEMBER, false);
        editor.apply();

        Toast.makeText(this, "User forgotten!", Toast.LENGTH_LONG).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME, "");
        password = sharedPreferences.getString(PASSWORD, "");
        swOnOff = sharedPreferences.getBoolean(SW_REMEMBER, false);

    }

    public void updateViews(){
        etUserName.setText(username);
        etPassword.setText(password);
        swRemember.setChecked(swOnOff);
    }

    public void onClick(View view) {
        Intent activityRegister = new Intent(getApplicationContext(), Register.class);
        startActivity(activityRegister);
    }
}