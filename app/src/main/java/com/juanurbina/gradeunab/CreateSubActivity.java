package com.juanurbina.gradeunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateSubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sub);
        Button btnBackAddSub = findViewById(R.id.btnBackAddSub);
        Button btnAddSub = findViewById(R.id.btnAddSub);
        EditText txtNameSub = findViewById(R.id.txtNameSub);
        EditText txtCredSub = findViewById(R.id.txtCredSub);
        btnBackAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(CreateSubActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameSub = txtNameSub.getText().toString();
                String credMat = txtCredSub.getText().toString();
                int credSub = Integer.parseInt(credMat);
                Materia myMateria = new Materia(nameSub, credSub);
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                firestore.collection("Materias").add(myMateria).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isComplete()) {
                            Toast.makeText(CreateSubActivity.this, "Se creo", Toast.LENGTH_SHORT).show();
                            Intent myIntent2 = new Intent(CreateSubActivity.this, MainActivity.class);
                            startActivity(myIntent2);
                        } else {
                            Toast.makeText(CreateSubActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
    }
}