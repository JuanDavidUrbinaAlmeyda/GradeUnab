package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

import io.grpc.Context;
public class RegisterActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private StorageReference storageRef;
    private DocumentReference userDocRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnRegister=findViewById(R.id.btnRegister2);
        Button btnBackRegister= findViewById(R.id.btnBackRegister);
        FirebaseApp.initializeApp(this);
        EditText etName= findViewById(R.id.etRegName);
        EditText etLast= findViewById(R.id.etRegLastName);
        EditText etID= findViewById(R.id.etRegId);
        EditText etCareer= findViewById(R.id.etRegCareer);
        EditText etSem= findViewById(R.id.etRegSem);
        EditText etEmail= findViewById(R.id.etRegEmail);
        EditText etPass= findViewById(R.id.etRegPass);
        EditText etRepPass= findViewById(R.id.etRegRepPass);
        db=FirebaseFirestore.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario= etEmail.getText().toString();
                String contraseña= etPass.getText().toString();
                String nombre= etName.getText().toString();
                String apellido= etLast.getText().toString();
                String id= etID.getText().toString();
                String career=etCareer.getText().toString();
                String sem=etSem.getText().toString();
                String repcontr= etRepPass.getText().toString();
                if(usuario.isEmpty()||contraseña.isEmpty()||nombre.isEmpty()||apellido.isEmpty()||id.isEmpty()||career.isEmpty()||sem.isEmpty()||repcontr.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }
                else if(!contraseña.equals(repcontr)){
                    Toast.makeText(RegisterActivity.this, "La contraseña debe ser igual en ambos campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    try{
                        CollectionReference usuariosCollection=db.collection("Usuarios");
                        DocumentReference usuarioDocRef= usuariosCollection.document(usuario);
                        usuarioDocRef.get().addOnSuccessListener(documentSnapshot -> {
                            if(documentSnapshot.exists()){
                                Toast.makeText(RegisterActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                            }else{
                                Map<String, Object> userData= new HashMap<>();
                                userData.put("usuario", usuario);
                                userData.put("contraseña", contraseña);
                                userData.put("nombre", nombre);
                                userData.put("apellido", apellido);
                                userData.put("id", id);
                                userData.put("carrera", career);
                                userData.put("semestre", sem);
                                usuarioDocRef.set(userData).addOnSuccessListener(aVoid ->{
                                    Toast.makeText(RegisterActivity.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                                    Intent profileIntent = new Intent(RegisterActivity.this, ProfileActivity.class);
                                    profileIntent.putExtra("nombre", nombre);
                                    profileIntent.putExtra("apellido", apellido);
                                    profileIntent.putExtra("id", id);
                                    profileIntent.putExtra("carrera", career);
                                    profileIntent.putExtra("semestre", sem);
                                    profileIntent.putExtra("usuario", usuario);
                                    startActivity(profileIntent);
                                }).addOnFailureListener(e->{
                                    Toast.makeText(RegisterActivity.this, "Error al Crear", Toast.LENGTH_SHORT).show();
                                });
                            }
                        });
                    } catch(Exception e){
                        Toast.makeText(RegisterActivity.this, "Error inesperado: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        btnBackRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(myIntent2);
            }
        });
    }
}