package com.example.railres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Display_activity extends AppCompatActivity {

    Intent i;
    TextView tName,tMail,tNOM, tFrom,tTo,tDate,tTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        tName = findViewById(R.id.dName);
        tMail = findViewById(R.id.dMail);
        tNOM = findViewById(R.id.dMembers);
        tFrom = findViewById(R.id.dFrom);
        tTo = findViewById(R.id.dTo);
        tDate = findViewById(R.id.dDate);
        tTime = findViewById(R.id.dTime);

        tName.setText(getIntent().getStringExtra("NAME"));
        tMail.setText(getIntent().getStringExtra("EMAIL"));
        tNOM.setText(getIntent().getStringExtra("MEMBERS"));
        tFrom.setText(getIntent().getStringExtra("FROM"));
        tTo.setText(getIntent().getStringExtra("TO"));
        tDate.setText(getIntent().getStringExtra("DATE"));
        tTime.setText(getIntent().getStringExtra("TIME"));

    }
}