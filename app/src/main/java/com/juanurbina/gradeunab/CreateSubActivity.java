package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sub);
        Button btnBackAddSub= findViewById(R.id.btnBackAddSub);
        Button btnAddSub= findViewById(R.id.btnAddSub);
        btnBackAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(CreateSubActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(CreateSubActivity.this, MainActivity.class);
                startActivity(myIntent2);
            }
        });
    }
}