package com.example.serviciosocial.carrera;

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
import com.example.serviciosocial.categoria.CategoriaAdpatador;
import com.example.serviciosocial.categoria.ControlCategoria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarCarreraActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlCarrera myDB;
    ArrayList<String> nombre_carrera;
    ArrayList<String> id_carrera;
    ArrayList<Integer> total_materias;
    CarreraAdaptador carreraAdaptador;
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_carrera);

        myDB = new ControlCarrera(ConsultarCarreraActivity.this);
        id_carrera = new ArrayList<String>();
        nombre_carrera = new ArrayList<>();
        total_materias = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerViewCarrera);
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

        //consultarCarrera();
        storeDataInArrays();
        carreraAdaptador = new CarreraAdaptador(ConsultarCarreraActivity.this, this, id_carrera, nombre_carrera, total_materias);
        recyclerView.setAdapter(carreraAdaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarCarreraActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = myDB.leerTodoCarrera();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_carrera.add(cursor.getString(0));
                nombre_carrera.add(cursor.getString(1));
                total_materias.add(cursor.getInt(2));
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