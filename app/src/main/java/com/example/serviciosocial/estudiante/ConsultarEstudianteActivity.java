package com.example.serviciosocial.estudiante;

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
import java.util.Iterator;

public class ConsultarEstudianteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlEstudiante myDB;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String> nombres, apellidos, carnet, email, telefono, domicilio, dui;
    EstudianteAdaptador estudianteAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante);

        myDB = new ControlEstudiante(this);
        carnet = new ArrayList<>();
        nombres = new ArrayList<>();
        apellidos = new ArrayList<>();
        email = new ArrayList<>();
        telefono = new ArrayList<>();
        domicilio = new ArrayList<>();
        dui = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewEstudiante);
        add_button = findViewById(R.id.add_button_estudiante);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarEstudianteActivity.this, CrearEstudianteActivity.class);
                startActivity(intent);
            }
        });
        consultarEstudiante();
        estudianteAdaptador = new EstudianteAdaptador(ConsultarEstudianteActivity.this, this, carnet, nombres, apellidos, email, telefono, domicilio, dui);
        recyclerView.setAdapter(estudianteAdaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarEstudianteActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
    void consultarEstudiante(){
        Cursor cursor = myDB.leerTodoEstudiante();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            myDB.abrir();
            ArrayList<Estudiante> registros = myDB.consultarEstudiante();
            myDB.cerrar();

            Estudiante e;
            Iterator<Estudiante> it = registros.iterator();
            while (it.hasNext()){
                e = it.next();
                carnet.add(String.valueOf(e.getCarnet()));
                nombres.add(e.getNombres_estudiante());
                apellidos.add(e.getApellidos_estudiante());
                email.add(e.getEmail_estudiante());
                telefono.add(e.getTelefono_estudiante());
                domicilio.add(e.getDomicilio());
                dui.add(e.getDui());
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    /*public void consultarEstudiante(){
        myDB.abrir();
        ArrayList<Estudiante> registros = myDB.consultarEstudiante();
        myDB.cerrar();

        Estudiante estu;
        Iterator<Estudiante> it = registros.iterator();
        while(it.hasNext()){
            estu = it.next();

            carnet.add((estu.getCarnet()));
            nombres.add(estu.getNombres_estudiante());
            apellidos.add(estu.getApellidos_estudiante());
            email.add(estu.getEmail_estudiante());
            telefono.add(estu.getTelefono_estudiante());
            domicilio.add(estu.getDomicilio());
            dui.add(estu.getDui());
        }
    }*/
}