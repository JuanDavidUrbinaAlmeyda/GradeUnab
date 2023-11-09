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

public class CreateReminderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder);
        Button btnBackCreateRem= findViewById(R.id.btnBackCreateReminder);
        Button btnAddRem= findViewById(R.id.btnAñadirRecordatorio);
        EditText txtNameReminder=findViewById(R.id.txtNameRem);
        EditText txtBodyReminder= findViewById(R.id.txtTextRem);
        btnBackCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(CreateReminderActivity.this, RemindersActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameReminder= txtNameReminder.getText().toString();
                String bodyReminder=txtBodyReminder.getText().toString();
                Reminder myReminder= new Reminder(nameReminder,bodyReminder);
                FirebaseFirestore firestore=FirebaseFirestore.getInstance();
                firestore.collection("Recordatorios").add(myReminder).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isComplete()){
                            Toast.makeText(CreateReminderActivity.this, "Ha sido creado", Toast.LENGTH_SHORT).show();
                            Intent myIntent2= new Intent(CreateReminderActivity.this, RemindersActivity.class);
                            startActivity(myIntent2);
                        }else{
                            Toast.makeText(CreateReminderActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}