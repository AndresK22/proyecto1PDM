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
import com.example.serviciosocial.estudiante.ControlEstudiante;
import com.example.serviciosocial.estudiante.EstudianteAdaptador;
import com.example.serviciosocial.proyecto.ControlProyecto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ConsultarEstudiantesAsignados extends AppCompatActivity {

    RecyclerView recyclerView;
    ControlEstudianteProyecto myDB;
    ArrayList<String> id_proyecto, nombres, apellidos, carnet, email, telefono, domicilio, dui;
    String idExtra;
    EstudiantesAdaptador estudianteAdaptador;
    ImageView empty_imageview;
    TextView no_data;
    FloatingActionButton add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante_asignado);

        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.add_button);
        idExtra = getIntent().getExtras().getString("id_proyecto");
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarEstudiantesAsignados.this, AsignarMasEstudiantesActivity.class);
                intent.putExtra("id_proyecto",idExtra);
                startActivity(intent);
            }
        });
        myDB = new ControlEstudianteProyecto(this);
        id_proyecto = new ArrayList<>();
        carnet = new ArrayList<>();
        nombres = new ArrayList<>();
        apellidos = new ArrayList<>();
        email = new ArrayList<>();
        telefono = new ArrayList<>();
        domicilio = new ArrayList<>();
        dui = new ArrayList<>();



        storeDataInArrays();

        estudianteAdaptador = new EstudiantesAdaptador(ConsultarEstudiantesAsignados.this,this,carnet,nombres,idExtra);
        recyclerView.setAdapter(estudianteAdaptador);recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarEstudiantesAsignados.this));

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
        int idPro = Integer.parseInt(idExtra);
        Cursor cursor = myDB.leerEstudiantesProyecto(idPro);
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                carnet.add(cursor.getString(1));
                nombres.add(cursor.getString(2)+" "+ cursor.getString(3));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    }
