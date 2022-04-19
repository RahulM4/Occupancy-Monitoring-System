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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDashboard extends AppCompatActivity {

    AppCompatButton setOccupancy,admin_logout_btn;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    TextView adminID,adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        setOccupancy=findViewById(R.id.setSeats);
        adminID=findViewById(R.id.ID_admin_dashboard);
        adminName=findViewById(R.id.name_admin_dashboard);
        admin_logout_btn=findViewById(R.id.logout_user_dashboard);
        firebaseAuth = FirebaseAuth.getInstance();


        admin_logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Toast.makeText(AdminDashboard.this, "logout success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AdminDashboard.this, AdminLogin.class));
                finish(); }});
    }

    public void setSetOccupancy(View view){
        final AlertDialog.Builder alert = new AlertDialog.Builder(AdminDashboard.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_dialog_box,null);

        final EditText txt_inputText = (EditText)mView.findViewById(R.id.txt_input);

        Button btn_cancel = (Button)mView.findViewById(R.id.btn_cancel);
        Button btn_okay = (Button)mView.findViewById(R.id.btn_okay);
        alert.setView(mView);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("message", txt_inputText.getText().toString());
                startActivity(intent);
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }


}