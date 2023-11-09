package com.juanurbina.gradeunab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = FirebaseFirestore.getInstance();
        profileImg=findViewById(R.id.imgProfile);
        SharedPreferences sharedPref = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String usuario = sharedPref.getString("usuario", "");
        // Obtén los datos del Intent
        CollectionReference usuariosCollection = db.collection("Usuarios");
        DocumentReference usuarioDocRef = usuariosCollection.document(usuario);
        usuarioDocRef.get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        // Obtiene los datos del usuario y actualiza los TextViews en la actividad ProfileActivity.
                        String nombre = documentSnapshot.getString("nombre");
                        String apellido = documentSnapshot.getString("apellido");
                        String id = documentSnapshot.getString("id");
                        String carrera = documentSnapshot.getString("carrera");
                        String semestre = documentSnapshot.getString("semestre");

                        TextView name = findViewById(R.id.txtNameProfile);
                        TextView lastname = findViewById(R.id.txtApellidoProfile);
                        TextView idProf = findViewById(R.id.txtIDProfile);
                        TextView career = findViewById(R.id.txtCarreraProfile);
                        TextView sem = findViewById(R.id.txtSemestreProfile);
                        TextView email = findViewById(R.id.txtCorreoProfile);

                        name.setText(nombre);
                        lastname.setText(apellido);
                        idProf.setText(id);
                        career.setText(carrera);
                        sem.setText(semestre);
                        email.setText(usuario);

                    }



    });

     Button editImg=findViewById(R.id.btnEditProfileImg);
     editImg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent galleryIntent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
             startActivityForResult(galleryIntent,1);
         }
     });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data != null){
            Uri imageUri= data.getData();
            try {
                // Escala la imagen a las dimensiones deseadas (por ejemplo, 200x200 dp)
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, convertDpToPx(500), convertDpToPx(500), true);

                // Muestra la imagen ajustada en el ImageView
                profileImg.setImageBitmap(scaledBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private int convertDpToPx(int dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}