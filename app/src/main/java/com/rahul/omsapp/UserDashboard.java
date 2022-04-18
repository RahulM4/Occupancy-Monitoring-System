package com.rahul.omsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.backup.FullBackupDataOutput;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDashboard extends AppCompatActivity {

    AppCompatButton userLogout,gotoHomeFromUserDashboard,entry,exit;
    private TextView name_user_dashboard1,enrollment_user_dashboard1,mobile_user_dashboard1,email_user_dashboard1;
    private String fullName, enrollment, mobileNo,email;
    private ImageView profilePic;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        userLogout=findViewById(R.id.logout_user_dashboard);
        gotoHomeFromUserDashboard=findViewById(R.id.gotoHomeFromUserDashboard);
        entry=findViewById(R.id.entry);
        exit=findViewById(R.id.exit);
        name_user_dashboard1=findViewById(R.id.name_user_dashboard);
        enrollment_user_dashboard1=findViewById(R.id.enrollment_user_dashboard);
        mobile_user_dashboard1=findViewById(R.id.mobile_user_dashboard);
        email_user_dashboard1=findViewById(R.id.email_user_dashboard);

        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();



        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(UserDashboard.this, "logout success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDashboard.this, Home.class));
                finish(); }});
        gotoHomeFromUserDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserDashboard.this,Home.class));
                finish(); }});

        if(firebaseUser ==null)
        {
            Toast.makeText(UserDashboard.this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
        else
        {
            updateUI(firebaseUser);
        }

    }

    private void updateUI(FirebaseUser firebaseUser) {
        String userID =firebaseUser.getUid();
        DatabaseReference ref =FirebaseDatabase.getInstance().getReference("Registered Users");
        ref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserData userData = snapshot.getValue(UserData.class);
                if(userData !=null)
                {
                    fullName =userData.userName;
                    email =firebaseUser.getEmail();
                    mobileNo =userData.mobileNo;
                    enrollment = userData.enrollment;

                    name_user_dashboard1.setText("Hi, "+fullName);
                    enrollment_user_dashboard1.setText(enrollment);
                    mobile_user_dashboard1.setText(mobileNo);
                    email_user_dashboard1.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserDashboard.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }});

    }
}