package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RemindersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        Button btnBackReminder= findViewById(R.id.btnBackReminders);
        Button btnAddReminder= findViewById(R.id.btnAddReminder);
        btnBackReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(RemindersActivity.this, GradesActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(RemindersActivity.this, CreateReminderActivity.class);
                startActivity(myIntent2);
            }
        });
    }
}