package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin= findViewById(R.id.btnLogin);
        Button btnRegister= findViewById(R.id.btnRegister);
        EditText etUser= findViewById(R.id.etLoginUser);
        EditText etPass= findViewById(R.id.etLoginPass);
        db=FirebaseFirestore.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario= etUser.getText().toString();
                String password= etPass.getText().toString();

                if(usuario.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    db.collection("Usuarios")
                            .whereEqualTo("usuario",usuario)
                            .whereEqualTo("contraseña", password)
                            .get().addOnSuccessListener(queryDocumentSnapshots -> {
                                if(!queryDocumentSnapshots.isEmpty()){
                                    Toast.makeText(LoginActivity.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                                    SharedPreferences sharedPreferences= getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sharedPreferences.edit();
                                    editor.putString("usuario",usuario);
                                    editor.apply();
                                    Intent myIntent= new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(myIntent);
                                } else{
                                    Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                                }

                            }).addOnFailureListener(e->{
                                Toast.makeText(LoginActivity.this, "Error en la consulta: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                }

            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(myIntent2);
            }
        });
    }
}