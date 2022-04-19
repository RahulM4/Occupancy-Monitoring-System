package com.rahul.omsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Home extends AppCompatActivity {

    AppCompatButton gotoSignIn,gotoAdminLogin;
    TextView maxLimit, availableSeats;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    public  String setMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gotoSignIn = findViewById(R.id.gotoSignUp);
        gotoAdminLogin=findViewById(R.id.gotoAdminLogin);
        availableSeats=findViewById(R.id.available_seat);
        maxLimit=findViewById(R.id.max_capacity);

        MaxCapacityLimit();

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

    private void MaxCapacityLimit() {


        DatabaseReference ref =FirebaseDatabase.getInstance().getReference("Occupancy Status");
        ref.child("Max Capacity").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MaxCapacity maxCapacity = snapshot.getValue(MaxCapacity.class);
                if(maxCapacity !=null)
                {
                    setMax =maxCapacity.maxCapacity;
                    maxLimit.setText(setMax);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }});

    }



}