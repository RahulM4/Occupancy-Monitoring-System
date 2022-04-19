package com.rahul.omsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    AppCompatButton gotoSignIn,gotoAdminLogin;
    TextView maxLimit, availableSeats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotoSignIn = findViewById(R.id.gotoSignUp);
        gotoAdminLogin=findViewById(R.id.gotoAdminLogin);
        availableSeats=findViewById(R.id.available_seat);
        //maxLimit=findViewById(R.id.max_capacity);



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