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

public class CreateGradeActivity extends AppCompatActivity {

    private String materiaSeleccionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_grade);
        materiaSeleccionada = getIntent().getStringExtra("idMateria");
        Button btnBackAddGrade= findViewById(R.id.btnBackAddGrad);
        Button btnAddGrade=findViewById(R.id.btnAddGrade);
        EditText txtNameGrade= findViewById(R.id.etNameGrade);
        EditText txtPorcenGrade=findViewById(R.id.etPercenGrade);
        EditText txtCalifGrade=findViewById(R.id.etCalifGrade);
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
                String nameGrade=txtNameGrade.getText().toString();
                String porcNota= txtPorcenGrade.getText().toString();
                double porcGrade= Double.parseDouble(porcNota);
                String califNota= txtCalifGrade.getText().toString();
                double califGrade= Double.parseDouble(califNota);
                Nota myNota= new Nota(nameGrade,porcGrade,califGrade,materiaSeleccionada);
                FirebaseFirestore firestore=FirebaseFirestore.getInstance();
                firestore.collection("Notas").add(myNota).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(CreateGradeActivity.this, "Ha sido creado", Toast.LENGTH_SHORT).show();
                            Intent myIntent2= new Intent(CreateGradeActivity.this, GradesActivity.class);
                            myIntent2.putExtra("idMateria",materiaSeleccionada);
                            startActivity(myIntent2);
                        } else{
                            Toast.makeText(CreateGradeActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
    }
}