package com.juanurbina.gradeunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {
    NotaAdapter notaAdapter;
    RecyclerView rvNota;
    ArrayList<Nota> myArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        rvNota=findViewById(R.id.rvGrades);
        traerInfo();
        notaAdapter=new NotaAdapter(myArray);
        notaAdapter.setOnClickListener(new NotaAdapter.OnClickListener() {
            @Override
            public void onClickEliminar(Nota nota) {
                FirebaseFirestore firestore= FirebaseFirestore.getInstance();
                firestore.collection("Notas").document(nota.getIdNota()).delete();
                traerInfo();
                notaAdapter.setDataSet(myArray);
            }
        });
        rvNota.setAdapter(notaAdapter);
        rvNota.setLayoutManager(new LinearLayoutManager(GradesActivity.this));
        Button btnBackGrades=findViewById(R.id.btnBackGrades);
        Button btnAddGrade= findViewById(R.id.btnAñadirNota);
        Button btnReminders= findViewById(R.id.btnDisplayResour);
        Button btnResources= findViewById(R.id.btnDisplayResour);
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
        btnReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent3= new Intent(GradesActivity.this, RemindersActivity.class);
                startActivity(myIntent3);
            }
        });


    }
    private void traerInfo(){
        FirebaseFirestore firestore= FirebaseFirestore.getInstance();
        firestore.collection("Notas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot document: task.getResult()){
                    Nota newNota= document.toObject(Nota.class);
                    newNota.setIdNota(document.getId());
                    myArray.add(newNota);
                }
                notaAdapter.setDataSet(myArray);
            }
        });
    }
}