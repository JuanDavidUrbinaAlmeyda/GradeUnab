package com.juanurbina.gradeunab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ResourcesActivity extends AppCompatActivity {
    ResourceAdapter resourceAdapter;
    RecyclerView rvResources;
    ArrayList<Resource> myArray= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        rvResources=findViewById(R.id.rvResources);
        loadData();
        resourceAdapter=new ResourceAdapter(myArray);
        resourceAdapter.setOnClickListener(new ResourceAdapter.OnClickListener() {
            @Override
            public void onClickGo(Resource myResource) {
                Uri uri=Uri.parse(myResource.getUrlResource());
                Intent intent=new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        rvResources.setAdapter(resourceAdapter);
        rvResources.setLayoutManager(new LinearLayoutManager(ResourcesActivity.this));
        Button btnBackReso= findViewById(R.id.btnBackResources);
        Button btnBackToMenu=findViewById(R.id.btnBackToMenu);
        btnBackReso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent= new Intent(ResourcesActivity.this, MainActivity.class);
                startActivity(myIntent);

            }
        });
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2= new Intent(ResourcesActivity.this, MainActivity.class);
                startActivity(myIntent2);
            }
        });

    }
    private void loadData(){
        Resource myResource1= new Resource("Link Ecuaciones","https://es.wikipedia.org/wiki/Ecuaci%C3%B3n_diferencial");
        myArray.add(myResource1);
    }
}