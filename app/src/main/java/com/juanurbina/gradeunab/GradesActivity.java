package com.juanurbina.gradeunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GradesActivity extends AppCompatActivity {
    NotaAdapter notaAdapter;
    RecyclerView rvNota;
    private double pga = 0.0;
    private TextView txtPga;
    private String materiaSeleccionada;
    ArrayList<Nota> myArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);

        txtPga = findViewById(R.id.txt_pga_materia);

        materiaSeleccionada = getIntent().getStringExtra("idMateria");

        rvNota=findViewById(R.id.rvGrades);
        traerInfo();
        notaAdapter=new NotaAdapter(myArray);
        notaAdapter.setOnClickListener(new NotaAdapter.OnClickListener() {
            @Override
            public void onClickEliminar(Nota nota) {
                FirebaseFirestore firestore= FirebaseFirestore.getInstance();
                firestore.collection("Notas").document(nota.getIdNota()).delete();
                actulizarPga();
                traerInfo();
                notaAdapter.setDataSet(myArray);
            }
        });
        rvNota.setAdapter(notaAdapter);
        rvNota.setLayoutManager(new LinearLayoutManager(GradesActivity.this));
        Button btnBackGrades=findViewById(R.id.btnBackGrades);
        Button btnAddGrade= findViewById(R.id.btnAñadirNota);
        Button btnReminders= findViewById(R.id.btnDisplayTasks);
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
                myIntent2.putExtra("idMateria",materiaSeleccionada);
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
        btnResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent4= new Intent(GradesActivity.this, ResourcesActivity.class);
                startActivity(myIntent4);
            }
        });


    }
    private void traerInfo(){
        FirebaseFirestore firestore= FirebaseFirestore.getInstance();
        firestore.collection("Notas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                myArray.clear();
                for (QueryDocumentSnapshot document: task.getResult()){

                    Nota newNota= document.toObject(Nota.class);
                    newNota.setIdNota(document.getId());

                    if(newNota.getIdMateria().equals(materiaSeleccionada)){
                        myArray.add(newNota);
                    }
                }
                actulizarPga();

                notaAdapter.setDataSet(myArray);
            }
        });
    }

    public void actulizarPga(){
        pga = 0.0;
        for (Nota item:myArray){
            pga += item.getPorcNota()*item.getCalifNota();
        }
        txtPga.setText(String.valueOf(pga));
    }
}