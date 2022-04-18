package com.rahul.omsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    AppCompatButton gotoSignIn,gotoAdminLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotoSignIn = findViewById(R.id.gotoSignUp);
        gotoAdminLogin=findViewById(R.id.gotoAdminLogin);


        gotoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
            }
        });

        gotoAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(intent);
            }
        });

    }
}