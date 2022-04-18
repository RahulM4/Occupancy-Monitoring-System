package com.rahul.omsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    TextView gotoHomeFromAdminLogin;
    AppCompatButton adminLogin;
    EditText admin_email,admin_password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        gotoHomeFromAdminLogin=findViewById(R.id.gotoHomeFromAdminLogin);
        admin_email=findViewById(R.id.admin_email);
        admin_password=findViewById(R.id.admin_password);
        adminLogin=findViewById(R.id.adminLogin_btn);
        firebaseAuth =FirebaseAuth.getInstance();

        gotoHomeFromAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                startActivity(intent);
                finish();
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adm_email =admin_email.getText().toString().trim();
                String adm_password=admin_password.getText().toString().trim();

                if (TextUtils.isEmpty(adm_email)) {
                    Toast.makeText(AdminLogin.this, "Enter email ID!!", Toast.LENGTH_SHORT).show();
                    admin_email.setError("Email ID is required");
                    admin_email.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(adm_email).matches()) {
                    Toast.makeText(AdminLogin.this, "Enter valid Email!!", Toast.LENGTH_SHORT).show();
                    admin_email.setError("Invalid Email ID");
                   admin_email.requestFocus();
                }
                else if(TextUtils.isEmpty(adm_email))
                {
                    Toast.makeText(AdminLogin.this, "Enter password!!", Toast.LENGTH_SHORT).show();
                    admin_password.setError("Password is required");
                    admin_password.requestFocus();
                }
                else {
                    adminLogin(adm_email, adm_password);
                }

            }
        });


    }

    private void adminLogin(String adm_email, String adm_password) {

        firebaseAuth.signInWithEmailAndPassword(adm_email,adm_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(AdminLogin.this, "Login successful ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AdminLogin.this,AdminDashboard.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(AdminLogin.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}