package com.example.serviciosocial.carrera;

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

public class ConsultarCarreraActivity extends AppCompatActivity {

    RecyclerView recyclerViewCarrera;
    FloatingActionButton add_button;
    ControlCarrera myDB;
    ImageView empty_imageview;
    TextView no_data;
    CarreraAdaptador carreraAdaptador;


    ArrayList<String> id_carrera, nombre_carrera;
    ArrayList<Integer> total_materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_carrera);

        myDB = new ControlCarrera(ConsultarCarreraActivity.this);
        id_carrera = new ArrayList<String>();
        nombre_carrera = new ArrayList<>();
        total_materias = new ArrayList<>();

        recyclerViewCarrera = findViewById(R.id.recyclerViewCarrera);
        add_button = findViewById(R.id.add_button_carrera);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarCarreraActivity.this, CrearCarreraActivity.class);
                startActivity(intent);
            }
        });

        consultarCarrera();
        carreraAdaptador = new CarreraAdaptador(ConsultarCarreraActivity.this, this, id_carrera, nombre_carrera, total_materias);
        recyclerViewCarrera.setAdapter(carreraAdaptador);
        recyclerViewCarrera.setLayoutManager(new LinearLayoutManager(ConsultarCarreraActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
    void consultarCarrera(){
        Cursor cursor = myDB.leerTodoCarrera();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            myDB.abrir();
            ArrayList<Carrera> registros = myDB.consultarCarrera();
            myDB.cerrar();

            Carrera c;
            Iterator<Carrera> it = registros.iterator();
            while (it.hasNext()){
                c = it.next();
                id_carrera.add(c.getId_carrera());
                nombre_carrera.add(c.getNombre_carrera());
                total_materias.add(c.getTotal_materias());
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    /*public void consultarCarrera(){
        myDB.abrir();
        ArrayList<Carrera> registros = myDB.consultarCarrera();
        myDB.cerrar();

        Carrera care;
        Iterator<Carrera> it = registros.iterator();
        while(it.hasNext()){
            care = it.next();
            id_carrera.add(care.getId_carrera());
            nombre_carrera.add(care.getNombre_carrera());
            total_materias.add(care.getTotal_materias());
        }
    }*/
}