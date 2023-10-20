package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCuenta=findViewById(R.id.btnCuenta);
        Button btnBackMain= findViewById(R.id.btnBackCredits);
        Button btnCredits= findViewById(R.id.btnCreditos);
        Button btnAddSub= findViewById(R.id.btnAñadirMateria);
        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(myIntent);
            }
        });
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(myIntent2);
            }
        });
        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent3= new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(myIntent3);
            }
        });
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent4= new Intent(MainActivity.this, CreateSubActivity.class);
                startActivity(myIntent4);
            }
        });
    }
}