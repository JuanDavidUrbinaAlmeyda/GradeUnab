package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GradesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        Button btnBackGrades=findViewById(R.id.btnBackGrades);
        Button btnAddGrade= findViewById(R.id.btnAñadirNota);
        btnBackGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(GradesActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(GradesActivity.this, CreateGradeActivity.class);
                startActivity(myIntent2);
            }
        });

    }
}