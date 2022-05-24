package com.example.serviciosocial.nota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;
import com.example.serviciosocial.materia.ConsultarMateriaActivity;
import com.example.serviciosocial.materia.CrearMateriaActivity;
import com.example.serviciosocial.materia.Materia;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarNotaActivity extends AppCompatActivity {

    RecyclerView recyclerViewRecord;
    FloatingActionButton add_button;
    ControlBD helper;
    ArrayList<String> cod_materia, carnet, calificacion;
    ArrayList<String> id_carrera, descrip_carrera;
    Spinner spinerCarrera;

    String idCarr;

    com.example.serviciosocial.nota.NotaAdaptador notaAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_nota);

        helper = new ControlBD(this);
        cod_materia = new ArrayList<>();
        carnet = new ArrayList<>();
        calificacion = new ArrayList<>();

        recyclerViewRecord = findViewById(R.id.recyclerViewNota);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarNotaActivity.this, CrearNotaActivity.class);
                startActivity(intent);
            }
        });


        spinerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        id_carrera = new ArrayList<>();
        descrip_carrera = new ArrayList<>();
        descrip_carrera.add("Seleccione una carrera");


        //Aqui se va a pedir las carreras
        helper.abrir();
        ArrayList<Materia> itemsSpinner = helper.consultarMateria(); //Metodo que consulta las carreras
        helper.cerrar();

        Materia carr;
        Iterator<Materia> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            carr = it.next();

            id_carrera.add(String.valueOf(carr.getId_area())); //getId_carrera
            descrip_carrera.add(carr.getNombre_materia()); //getNombre_carrera
        }
        //fin de lo de las carreras


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_carrera);
        spinerCarrera.setAdapter(adaptador);

        //Validaciones de campos vacios
        spinerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    idCarr = id_carrera.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el SELECT

                    //Consultariamos los records en base a la carrera

                } else {
                    Toast.makeText(ConsultarNotaActivity.this, "Debe seleccionar una carrera", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        consultarRecord();

        notaAdaptador = new com.example.serviciosocial.nota.NotaAdaptador(ConsultarNotaActivity.this,this, cod_materia, carnet, calificacion);
        recyclerViewRecord.setAdapter(notaAdaptador);
        recyclerViewRecord.setLayoutManager(new LinearLayoutManager(ConsultarNotaActivity.this));
    }

    public void consultarRecord(){
        helper.abrir();
        ArrayList<Nota> registros = helper.consultarNotas(); //consultarRecordsPorCarrera(idCarrera)
        helper.cerrar();

        Nota not;
        Iterator<Nota> it = registros.iterator();
        while(it.hasNext()) {
            not = it.next();

            cod_materia.add(String.valueOf(not.getCod_materia()));
            carnet.add(not.getCarnet());
            calificacion.add(String.valueOf(not.getCalificacion()));
        }
    }
}