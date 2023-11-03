package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        Button btnBackCreateRem= findViewById(R.id.btnBackCreateReminder);
        Button btnAddRem= findViewById(R.id.btnAñadirRecordatorio);
        btnBackCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(CreateReminderActivity.this, RemindersActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(CreateReminderActivity.this, RemindersActivity.class);
                startActivity(myIntent2);
            }
        });

    }
}