package com.rahul.omsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
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
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {

    AppCompatButton userLogin,gotoRegisterPage;
    TextView gotoHomePageFromLogin,forgetPassword;
    EditText emailL,passwordL;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userLogin=findViewById(R.id.userLogin_btn);
        gotoRegisterPage=findViewById(R.id.createAnAccount_btn);
        gotoHomePageFromLogin=findViewById(R.id.gotoHomeLogin);
        forgetPassword=findViewById(R.id.forgetPassword);
        emailL=findViewById(R.id.edit_text_emailL);
        passwordL=findViewById(R.id.edit_text_passwordL);
        firebaseAuth=FirebaseAuth.getInstance();

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                startActivity(intent);
                finish();
            }
        });

        gotoHomePageFromLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });
        gotoRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserRegister.class);
                startActivity(intent);
                finish();
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailLL= emailL.getText().toString().trim();
                String passwordLL = passwordL.getText().toString().trim();

                if (TextUtils.isEmpty(emailLL)) {
                    Toast.makeText(UserLogin.this, "Enter email ID!!", Toast.LENGTH_SHORT).show();
                    emailL.setError("Email ID is required");
                    emailL.requestFocus();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailLL).matches()) {
                    Toast.makeText(UserLogin.this, "Enter valid Email!!", Toast.LENGTH_SHORT).show();
                    emailL.setError("Invalid Email ID");
                    emailL.requestFocus();
                }
                else if(TextUtils.isEmpty(passwordLL))
                {
                    Toast.makeText(UserLogin.this, "Enter password!!", Toast.LENGTH_SHORT).show();
                    emailL.setError("Password is required");
                    emailL.requestFocus();
                }
                else {
                    userLogin(emailLL, passwordLL);
                }


            }
        });

    }

    private void userLogin(String emailLL, String passwordLL) {
        firebaseAuth.signInWithEmailAndPassword(emailLL,passwordLL).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser.isEmailVerified())
                    {
                        Toast.makeText(UserLogin.this, "Login successful ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserLogin.this,UserDashboard.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        firebaseUser.sendEmailVerification();
                        firebaseAuth.signOut();
                        alertDialogBox();
                    }

                }
                else
                {
                    Toast.makeText(UserLogin.this, "Login Failed ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserLogin.this,Home.class);
                    startActivity(intent);
                    finish();
                }


            }
        });



    }

    private void alertDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserLogin.this);
        builder.setTitle("Email still not verified");
        builder.setMessage("Please verify the email sent to your email ID");

        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Intent intent =new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseAuth.getCurrentUser() != null)
        {
            Toast.makeText(UserLogin.this, "Your are already logged in ", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserLogin.this,UserDashboard.class));
            finish();

        }
        else
        {
            Toast.makeText(UserLogin.this, "you can login now!", Toast.LENGTH_SHORT).show();
        }
    }
}