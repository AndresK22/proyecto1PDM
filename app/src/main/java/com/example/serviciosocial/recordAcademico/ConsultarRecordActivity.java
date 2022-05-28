package com.example.serviciosocial.recordAcademico;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;
import com.example.serviciosocial.materia.Materia;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarRecordActivity extends AppCompatActivity {

    RecyclerView recyclerViewRecord;
    ControlBD helper;
    ArrayList<String> id_record, carnet, id_area, materias_aprobadas, progreso, promedio;
    ArrayList<String> id_carrera, descrip_carrera;
    Spinner spinerCarrera;

    String idCarr;

    com.example.serviciosocial.recordAcademico.RecordAdaptador recordAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_record);

        helper = new ControlBD(this);
        id_record = new ArrayList<>();
        carnet = new ArrayList<>();
        id_area = new ArrayList<>();
        materias_aprobadas = new ArrayList<>();
        progreso = new ArrayList<>();
        promedio = new ArrayList<>();

        recyclerViewRecord = findViewById(R.id.recyclerViewRecord);


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
                    Toast.makeText(ConsultarRecordActivity.this, "Debe seleccionar una carrera", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        consultarRecord();

        recordAdaptador = new com.example.serviciosocial.recordAcademico.RecordAdaptador(ConsultarRecordActivity.this,this, id_record, carnet, id_area, materias_aprobadas, progreso, promedio);
        recyclerViewRecord.setAdapter(recordAdaptador);
        recyclerViewRecord.setLayoutManager(new LinearLayoutManager(ConsultarRecordActivity.this));
    }

    public void consultarRecord(){
        helper.abrir();
        ArrayList<RecordAcademico> registros = helper.consultarRecords(); //consultarRecordsPorCarrera(idCarrera)
        helper.cerrar();

        RecordAcademico rec;
        Iterator<RecordAcademico> it = registros.iterator();
        while(it.hasNext()) {
            rec = it.next();

            id_record.add(String.valueOf(rec.getId_record()));
            carnet.add(rec.getCarnet());
            id_area.add(rec.getId_area());
            materias_aprobadas.add(String.valueOf(rec.getMaterias_aprobadas()));
            progreso.add(String.valueOf(rec.getProgreso()));
            promedio.add(String.valueOf(rec.getPromedio()));
        }
    }
}