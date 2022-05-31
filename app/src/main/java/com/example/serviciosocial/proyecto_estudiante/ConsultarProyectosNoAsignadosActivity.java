package com.example.serviciosocial.proyecto_estudiante;

import android.annotation.SuppressLint;
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
import com.example.serviciosocial.categoria.ConsultarCategoriaActivity;
import com.example.serviciosocial.categoria.CrearCategoriaActivity;
import com.example.serviciosocial.proyecto.ControlProyecto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConsultarProyectosNoAsignadosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ControlProyecto myDB;
    FloatingActionButton add_button;
    ArrayList<String> id_proyecto,nombre_proyecto;
    EstudianteNoAsignadoAdaptador estudianteProyectoAdaptador;
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante_proyecto_sin_asignar);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.add_button);

        myDB = new ControlProyecto(ConsultarProyectosNoAsignadosActivity.this);
        id_proyecto = new ArrayList<>();
        nombre_proyecto = new ArrayList<>();

        storeDataInArrays();

        estudianteProyectoAdaptador = new EstudianteNoAsignadoAdaptador(ConsultarProyectosNoAsignadosActivity.this,this,id_proyecto,nombre_proyecto);
        recyclerView.setAdapter(estudianteProyectoAdaptador);recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarProyectosNoAsignadosActivity.this));

    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.leerProyectosNoAsignados();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_proyecto.add(cursor.getString(0));
                nombre_proyecto.add(cursor.getString(7));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    }
