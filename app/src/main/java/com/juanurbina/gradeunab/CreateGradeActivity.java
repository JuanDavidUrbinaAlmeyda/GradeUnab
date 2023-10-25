package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateGradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grade);
        Button btnBackAddGrade= findViewById(R.id.btnBackAddGrad);
        Button btnAddGrade=findViewById(R.id.btnAddGrade);
        btnBackAddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(CreateGradeActivity.this, GradesActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(CreateGradeActivity.this, GradesActivity.class);
                startActivity(myIntent2);
            }
        });
    }
}