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
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegister extends AppCompatActivity {


    AppCompatButton userRegister, gotoLoginPage;
    TextView gotoHomePageFromRegister;
    EditText mName,mEnrollment,mEmail,mMobileNo,mPassword,mConfirmPassword;

    //databases
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        userRegister =findViewById(R.id.userRegister_btn);
        gotoLoginPage =findViewById(R.id.alreadyHaveAnAccount_btn);
        gotoHomePageFromRegister=findViewById(R.id.gotoHomeRegister);

        mName=findViewById(R.id.edit_text_name);
        mEnrollment=findViewById(R.id.edit_text_enrollment);
        mEmail=findViewById(R.id.edit_text_email);
        mMobileNo=findViewById(R.id.edit_text_phone);
        mPassword=findViewById(R.id.edit_text_password);
        mConfirmPassword=findViewById(R.id.edit_text_confirm_password);



        userRegister.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                String fullName = mName.getText().toString().trim();
                                                String enrollmentNo = mEnrollment.getText().toString().trim();
                                                String emailID = mEmail.getText().toString().trim();
                                                String mobileNo = mMobileNo.getText().toString().trim();
                                                String Password = mPassword.getText().toString().trim();
                                                String confirmPassword = mConfirmPassword.getText().toString().trim();

                                                String mobileRegex ="[6-9][0-9]{9}";
                                                Matcher mobileMatcher;
                                                Pattern mobilePattern =Pattern.compile(mobileRegex);
                                                mobileMatcher =mobilePattern.matcher(mobileNo);

                                                if (TextUtils.isEmpty(fullName)) {
                                                    Toast.makeText(UserRegister.this, "Enter full name!!", Toast.LENGTH_SHORT).show();
                                                } else if (TextUtils.isEmpty(enrollmentNo)) {
                                                    Toast.makeText(UserRegister.this, "Enter Enrollment No.!!", Toast.LENGTH_SHORT).show();
                                                } else if (TextUtils.isEmpty(emailID)) {
                                                    Toast.makeText(UserRegister.this, "Enter email ID!!", Toast.LENGTH_SHORT).show();
                                                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()) {
                                                    Toast.makeText(UserRegister.this, "Enter valid Email!!", Toast.LENGTH_SHORT).show();
                                                } else if (TextUtils.isEmpty(mobileNo)) {
                                                    Toast.makeText(UserRegister.this, "Enter Mobile no.!!", Toast.LENGTH_SHORT).show();
                                                }
                                                else if (!mobileMatcher.find()) {
                                                    Toast.makeText(UserRegister.this, "Enter valid Mobile no.!!", Toast.LENGTH_SHORT).show();
                                                }
                                                else if (mobileNo.length() != 10) {
                                                    Toast.makeText(UserRegister.this, "Enter 10 digits mobile no.!!", Toast.LENGTH_SHORT).show();
                                                } else if (TextUtils.isEmpty(Password)) {
                                                    Toast.makeText(UserRegister.this, "Enter Password!!", Toast.LENGTH_SHORT).show();
                                                } else if (TextUtils.isEmpty(confirmPassword)) {
                                                    Toast.makeText(UserRegister.this, "Enter confirm Password!!", Toast.LENGTH_SHORT).show();
                                                } else if (!Password.equals(confirmPassword)) {
                                                    Toast.makeText(UserRegister.this, "Password not matched!!", Toast.LENGTH_SHORT).show();
                                                } else
                                                {

                                                    registerUser(fullName, enrollmentNo, emailID, mobileNo,Password);
                                                }
                                            }
                                        });

        gotoHomePageFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish(); }});
        gotoLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UserLogin.class);
                startActivity(intent);
                finish(); }});

    }

    private void registerUser(String fullName, String enrollmentNo, String emailID, String mobileNo, String password) {

        firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(emailID,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(UserRegister.this, "Please Verify!! An email sent to your emailID \n Then You will able to login!!", Toast.LENGTH_LONG).show();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                    firebaseUser.sendEmailVerification();

                    UserData userData =new UserData(fullName,enrollmentNo,emailID,mobileNo);
                    DatabaseReference dataRef =FirebaseDatabase.getInstance().getReference("Registered Users");
                    dataRef.child(firebaseUser.getUid()).setValue(userData);

                    Intent intent =new Intent(UserRegister.this,UserLogin.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(UserRegister.this, "Something went wrong \nRegistration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}