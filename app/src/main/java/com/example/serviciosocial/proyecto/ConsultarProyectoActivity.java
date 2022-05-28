package com.example.serviciosocial.proyecto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConsultarProyectoActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlProyecto myDB;
    ArrayList<String> id_proyecto,nombre_proyecto;
    ProyectoAdaptador proyectoAdaptador;
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_proyecto);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarProyectoActivity.this, CrearProyectoActivity.class);
                startActivity(intent);
            }
        });

        myDB = new ControlProyecto(ConsultarProyectoActivity.this);
        id_proyecto = new ArrayList<>();
        nombre_proyecto = new ArrayList<>();

        storeDataInArrays();

        proyectoAdaptador = new ProyectoAdaptador(ConsultarProyectoActivity.this,this,id_proyecto,nombre_proyecto);
        recyclerView.setAdapter(proyectoAdaptador);recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarProyectoActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.leerTodoModalidad();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_proyecto.add(cursor.getString(0));
                nombre_proyecto.add(cursor.getString(1));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    }
