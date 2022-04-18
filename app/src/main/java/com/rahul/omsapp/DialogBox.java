package com.rahul.omsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DialogBox extends AppCompatActivity {

    TextView myCustomMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_box);

        myCustomMessage = findViewById(R.id.myCustommessage);
    }
}