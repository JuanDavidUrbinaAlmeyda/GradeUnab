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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MateriaAdapter materiaAdapter;
    RecyclerView rvMateria;
    ArrayList<Materia> myArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMateria = findViewById(R.id.rv_Materias);
        traerInfo();
        materiaAdapter = new MateriaAdapter(myArray);
        materiaAdapter.setOnClickListener(new MateriaAdapter.OnClickListener() {
            @Override
            public void onClickEliminar(Materia materia) {
                FirebaseFirestore firestore = FirebaseFirestore.getInstance();
                firestore.collection("Materias").document(materia.getId()).delete();
                traerInfo();
                materiaAdapter.setDataSet(myArray);
            }

            @Override
            public void onClickDetalle(Materia materia) {
                Intent myIntent = new Intent(MainActivity.this, GradesActivity.class);
                myIntent.putExtra("idMateria",materia.getId());
                startActivity(myIntent);
            }
        });

        rvMateria.setAdapter(materiaAdapter);
        rvMateria.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        Button btnCuenta = findViewById(R.id.btnCuenta);
        Button btnBackMain = findViewById(R.id.btnBackMain);
        Button btnCredits = findViewById(R.id.btnCreditos);
        Button btnAddSub = findViewById(R.id.btnAñadirMateria);
        btnCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(myIntent);
            }
        });
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(myIntent2);
            }
        });
        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent3 = new Intent(MainActivity.this, CreditsActivity.class);
                startActivity(myIntent3);
            }
        });
        btnAddSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent4 = new Intent(MainActivity.this, CreateSubActivity.class);
                startActivity(myIntent4);
            }
        });


    }

    private void traerInfo() {

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("Materias").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()) {
                    myArray.clear();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Materia newMateria = document.toObject(Materia.class);
                        newMateria.setId(document.getId());
                        myArray.add(newMateria);
                    }
                    materiaAdapter.setDataSet(myArray);
                }
            }
        });
    }
}