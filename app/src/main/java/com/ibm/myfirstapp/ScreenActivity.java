package com.ibm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        TextView tvEmail = findViewById(R.id.EmailAddress);

        String email = getIntent().getStringExtra("KeyEmail");
        tvEmail.setText(email);

    }
}