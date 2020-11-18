package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

   EditText etUserName;
   EditText etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bundle extras = getIntent().getExtras();
        String email;
        String password;
        etUserName = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPass);
        if(extras != null){
            email = extras.getString("emailKey");
            password = extras.getString("passKey");
            etUserName.setText(email);
            etPassword.setText(password);
        }
        Button btnLogin = (Button)findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
    }




    public void onClick(View view) {
        Intent activityRegister = new Intent(getApplicationContext(),Register.class);
        startActivity(activityRegister);
    }
}