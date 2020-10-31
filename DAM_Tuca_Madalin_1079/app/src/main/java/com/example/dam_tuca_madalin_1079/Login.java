package com.example.dam_tuca_madalin_1079;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button)findViewById(R.id.btnLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked login!");
            }
        });
    }




    public void onClick(View view) {
        Intent activityRegister = new Intent(getApplicationContext(),Register.class);
        startActivity(activityRegister);
    }
}