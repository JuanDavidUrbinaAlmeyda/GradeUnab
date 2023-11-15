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

public class RemindersActivity extends AppCompatActivity {
    ReminderAdapter reminderAdapter;
    RecyclerView rvRemind;
    ArrayList<Reminder> myArray= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        rvRemind=findViewById(R.id.rvReminder);
        traerInfo();
        reminderAdapter= new ReminderAdapter(myArray);
        reminderAdapter.setOnClickListener(new ReminderAdapter.OnClickListener() {
            @Override
            public void onClickEliminar(Reminder reminder) {
                FirebaseFirestore firestore= FirebaseFirestore.getInstance();
                firestore.collection("Recordatorios").document(reminder.getIdReminder()).delete();
                traerInfo();
                reminderAdapter.setDataSet(myArray);
            }
        });
        rvRemind.setAdapter(reminderAdapter);
        rvRemind.setLayoutManager(new LinearLayoutManager(RemindersActivity.this));
        Button btnBackReminder= findViewById(R.id.btnBackReminders);
        Button btnAddReminder= findViewById(R.id.btnAddReminder);
        btnBackReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(RemindersActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
        btnAddReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(RemindersActivity.this, CreateReminderActivity.class);
                startActivity(myIntent2);
            }
        });
    }
    private void traerInfo(){
        FirebaseFirestore firestore= FirebaseFirestore.getInstance();
         firestore.collection("Recordatorios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
             @Override
             public void onComplete(@NonNull Task<QuerySnapshot> task) {
                 for (QueryDocumentSnapshot document: task.getResult()){
                     Reminder newReminder= document.toObject(Reminder.class);
                     newReminder.setIdReminder(document.getId());
                     myArray.add(newReminder);
                 }
                 reminderAdapter.setDataSet(myArray);
             }
         });
    }
}