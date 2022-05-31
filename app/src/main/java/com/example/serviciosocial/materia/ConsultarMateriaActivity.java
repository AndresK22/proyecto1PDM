package com.example.serviciosocial.materia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;

import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.estado.Estado;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarMateriaActivity extends AppCompatActivity {

    RecyclerView recyclerViewMateria;
    FloatingActionButton add_button;
    ControlMateria helper;
    ArrayList<String> cod_materia, id_area, nombre_materia;
    com.example.serviciosocial.materia.MateriaAdaptador materiaAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_materia);

        helper = new ControlMateria(this);
        cod_materia = new ArrayList<>();
        id_area = new ArrayList<>();
        nombre_materia = new ArrayList<>();

        recyclerViewMateria = findViewById(R.id.recyclerViewMateria);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarMateriaActivity.this, CrearMateriaActivity.class);
                startActivity(intent);
            }
        });

        if(consultarMateria()){
            materiaAdaptador = new com.example.serviciosocial.materia.MateriaAdaptador(ConsultarMateriaActivity.this,this, cod_materia, id_area, nombre_materia);
            recyclerViewMateria.setAdapter(materiaAdaptador);
            recyclerViewMateria.setLayoutManager(new LinearLayoutManager(ConsultarMateriaActivity.this));
        }

        /*materiaAdaptador = new com.example.serviciosocial.materia.MateriaAdaptador(ConsultarMateriaActivity.this,this, cod_materia, id_area, nombre_materia);
        recyclerViewMateria.setAdapter(materiaAdaptador);
        recyclerViewMateria.setLayoutManager(new LinearLayoutManager(ConsultarMateriaActivity.this));*/

    }

    public boolean consultarMateria(){
        helper.abrir();
        ArrayList<Materia> registros = helper.consultarMateria();
        helper.cerrar();

        Materia mater;
        if (registros == null){
            return false;
        }else {
            Iterator<Materia> it = registros.iterator();
            while(it.hasNext()) {
                mater = it.next();

                cod_materia.add(String.valueOf(mater.getCod_materia()));
                id_area.add(mater.getId_area());
                nombre_materia.add(mater.getNombre_materia());
            }
            return true;
        }

    }
}