package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        Button btnBackReso= findViewById(R.id.btnBackResources);
        Button btnBackToMenu=findViewById(R.id.btnBackToMenu);
        btnBackReso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(ResourcesActivity.this, GradesActivity.class);
                startActivity(myIntent);
            }
        });
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(ResourcesActivity.this, MainActivity.class);
                startActivity(myIntent2);
            }
        });

    }
}