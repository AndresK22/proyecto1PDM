package com.example.serviciosocial.estudiante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serviciosocial.R;
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.CarreraAdaptador;
import com.example.serviciosocial.carrera.ConsultarCarreraActivity;
import com.example.serviciosocial.carrera.ControlCarrera;
import com.example.serviciosocial.carrera.CrearCarreraActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarEstudianteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlEstudiante myDB;
    ArrayList<String> nombres, apellidos, carnet, email, telefono, domicilio, dui;
    EstudianteAdaptador estudianteAdaptador;
    ImageView empty_imageview;
    TextView no_data;

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
        //consultarEstudiante();
        storeDataInArrays();
        estudianteAdaptador = new EstudianteAdaptador(this, ConsultarEstudianteActivity.this, carnet, nombres, apellidos, email, telefono, domicilio, dui);
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
    void storeDataInArrays(){
        Cursor cursor = myDB.leerTodoEstudiante();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                carnet.add(cursor.getString(0));
                nombres.add(cursor.getString(1));
                apellidos.add(cursor.getString(2));
                email.add(cursor.getString(3));
                telefono.add(cursor.getString(4));
                domicilio.add(cursor.getString(5));
                dui.add(cursor.getString(6));
                //id_carrera.add(cursor.getString(0));
                //nombre_carrera.add(cursor.getString(1));
                //total_materias.add(cursor.getInt(2));
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