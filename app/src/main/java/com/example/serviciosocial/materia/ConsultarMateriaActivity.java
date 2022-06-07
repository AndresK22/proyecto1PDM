package com.example.serviciosocial.materia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;

import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.ControlCarrera;
import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.estado.Estado;
import com.example.serviciosocial.recordAcademico.ConsultarRecordActivity;
import com.example.serviciosocial.recordAcademico.RecordAcademico;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarMateriaActivity extends AppCompatActivity {

    RecyclerView recyclerViewMateria;
    FloatingActionButton add_button;
    ControlMateria helper;
    ControlCarrera helper2;
    ArrayList<String> cod_materia, id_area, nombre_materia;
    com.example.serviciosocial.materia.MateriaAdaptador materiaAdaptador;

    ArrayList<String> id_carrera, descrip_carrera;
    Spinner spinerCarrera;
    String idCarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_materia);

        helper = new ControlMateria(this);
        helper2 = new ControlCarrera(this);
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


        spinerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        id_carrera = new ArrayList<>();
        descrip_carrera = new ArrayList<>();
        descrip_carrera.add("Seleccione una carrera");

        //Aqui se va a pedir las carreras
        helper2.abrir();
        ArrayList<Carrera> itemsSpinner = helper2.consultarCarrera();
        helper2.cerrar();

        Carrera carr;
        if (itemsSpinner == null){

        } else{
            Iterator<Carrera> it = itemsSpinner.iterator();
            while(it.hasNext()) {
                carr = it.next();
                id_carrera.add(String.valueOf(carr.getId_carrera()));
                descrip_carrera.add(carr.getNombre_carrera());
            }
        }
        //fin de lo de las carreras


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_carrera);
        spinerCarrera.setAdapter(adaptador);


        //Validaciones de campos vacios
        spinerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    idCarr = id_carrera.get(i - 1); //Guarda el id de la carrera seleccionada en la variable global, para usarla en el SELECT

                    //Consultariamos los records en base a la carrera
                    cod_materia.clear();
                    id_area.clear();
                    nombre_materia.clear();

                    if(consultarMateria()){
                        materiaAdaptador = new com.example.serviciosocial.materia.MateriaAdaptador(ConsultarMateriaActivity.this,ConsultarMateriaActivity.this, cod_materia, id_area, nombre_materia);
                        recyclerViewMateria.setAdapter(materiaAdaptador);
                        recyclerViewMateria.setLayoutManager(new LinearLayoutManager(ConsultarMateriaActivity.this));
                    }
                } else {
                    Toast.makeText(ConsultarMateriaActivity.this, "Debe seleccionar una carrera", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });







        /*if(consultarMateria()){
            materiaAdaptador = new com.example.serviciosocial.materia.MateriaAdaptador(ConsultarMateriaActivity.this,this, cod_materia, id_area, nombre_materia);
            recyclerViewMateria.setAdapter(materiaAdaptador);
            recyclerViewMateria.setLayoutManager(new LinearLayoutManager(ConsultarMateriaActivity.this));
        }*/

        /*materiaAdaptador = new com.example.serviciosocial.materia.MateriaAdaptador(ConsultarMateriaActivity.this,this, cod_materia, id_area, nombre_materia);
        recyclerViewMateria.setAdapter(materiaAdaptador);
        recyclerViewMateria.setLayoutManager(new LinearLayoutManager(ConsultarMateriaActivity.this));*/

    }

    public boolean consultarMateria(){
        String[] id =  {idCarr};
        helper.abrir();
        ArrayList<Materia> registros = helper.consultarMateriasPorCarrera(id);
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