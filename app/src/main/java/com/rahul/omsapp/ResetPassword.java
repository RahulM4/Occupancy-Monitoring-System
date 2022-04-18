package com.rahul.omsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    TextView gotoHomeFromResetPassword;
    AppCompatButton resetPasswordEmailLink;
    EditText et_email_input;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        gotoHomeFromResetPassword = findViewById(R.id.gotoHomeFromResetPassword);
        resetPasswordEmailLink = findViewById(R.id.getResetLink);
        et_email_input = findViewById(R.id.resetEmail);
        firebaseAuth=FirebaseAuth.getInstance();

        resetPasswordEmailLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_email = et_email_input.getText().toString().trim();
                if(TextUtils.isEmpty(input_email))
                {
                    Toast.makeText(ResetPassword.this, "Please enter email ID", Toast.LENGTH_SHORT).show();
                    et_email_input.setError("Invalid Email ID");
                    et_email_input.requestFocus();
                }
                else
                {
                    sendEmailForResetPassword(input_email);
                }




            }
        });

        gotoHomeFromResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendEmailForResetPassword(String input_email) {
        firebaseAuth.sendPasswordResetEmail(input_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    Toast.makeText(ResetPassword.this, "Email link has been sent to registered mail ID", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPassword.this,Home.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(ResetPassword.this, "Failed to send link ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}