package com.example.serviciosocial.bitacora;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.example.serviciosocial.estudiante.ControlEstudiante;
import com.example.serviciosocial.estudiante.Estudiante;
import com.example.serviciosocial.proyecto.ControlProyecto;
import com.example.serviciosocial.proyecto.Proyecto;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearBitacoraActivity extends AppCompatActivity {

    ControlBitacora helper;
    ControlProyecto helper2;
    ControlEstudiante helper1;
    Spinner spinerProyecto,spinerCarnet;
    ArrayList<String> id_proyecto, nombre_proyecto, carnet; //para el spinner
    String id_p,id_c;

    EditText txtID;
    EditText txtMes;
    EditText txtTotal;
    Button btnGuardar;

    boolean camp = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_bitacora);

        helper = new ControlBitacora(this);
        helper1 = new ControlEstudiante(this);
        helper2 = new ControlProyecto(this);
        spinerProyecto = (Spinner) findViewById(R.id.spinnerProyecto);
        spinerCarnet= (Spinner) findViewById(R.id.spinnerCarnet);

        txtID = (EditText) findViewById(R.id.txt_id_bitacora);
        txtMes = (EditText) findViewById(R.id.txtMes);
        txtTotal = (EditText) findViewById(R.id.txt_total_horas);
        btnGuardar = (Button) findViewById(R.id.btnGuardarBitacora);

        id_proyecto = new ArrayList<>();
        nombre_proyecto = new ArrayList<>();
        nombre_proyecto.add("Seleccione el nombre del proyecto");

        carnet= new ArrayList<>();
        carnet.add("Seleccione el carnet del estudiante");

        //nombre_carrera = new ArrayList<>();
        //nombre_carrera.add("Seleccione la carrera");


        //Aqui se va a pedir el proyecto
        /* helper.abrir();
        ArrayList<Proyecto> itemsSpinner = helper.consultarProyecto();
        helper.cerrar();
        *
        * */
        helper2.abrir();
        ArrayList<Proyecto> itemsSpinner = helper2.consultarProyecto();
        helper2.cerrar();

        helper1.abrir();
        ArrayList<Estudiante> itemsSpinner2 = helper1.consultarEstudiante();
        helper1.cerrar();

        Cursor cursor = helper2.leerTodoProyecto();
        if (cursor.getCount()==0){

        }else {
            Proyecto a;
            Iterator<Proyecto> it = itemsSpinner.iterator();
            while (it.hasNext()) {
                a = it.next();

                id_proyecto.add(String.valueOf(a.getId_proyecto()));
                nombre_proyecto.add(a.getNombre_proyecto());
            }
        }

        Cursor cursor1 = helper1.leerTodoEstudiante();
        if (cursor1.getCount()==0){

        }else{
        Estudiante b;
        Iterator<Estudiante> i = itemsSpinner2.iterator();
        while(i.hasNext()) {
            b = i.next();

            carnet.add(String.valueOf(b.getCarnet()));
            //nombre_carrera.add(a.getDescrip_area());
        }}
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_proyecto);
        spinerProyecto.setAdapter(adaptador);

        ArrayAdapter<CharSequence> adaptador1 = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnet);
        spinerCarnet.setAdapter(adaptador1);

        //Validaciones de campos vacios
        spinerProyecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_p = id_proyecto.get(i - 1);
                    //Guarda el id del proyecto seleccionada en la variable global, para usarla en el create
                    camp = false;
                } else {
                    camp = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinerCarnet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_c = carnet.get(i);
                    //Guarda el id del carnet seleccionada en la variable global, para usarla en el create
                    camp = false;
                } else {
                    camp = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void guardarBitacora(View v) {
        if (verificarCamposLlenos(camp)) {
            String id = txtID.getText().toString();
            String proyecto = id_p ;
            String carnet = id_c;
            String mes = txtMes.getText().toString();
            String totalH = txtTotal.getText().toString();
            String regInsertados;

            Bitacora  bitacora= new Bitacora();
            bitacora.setId_bitacora(Long.parseLong(id));
            bitacora.setId_proyecto(Long.parseLong(proyecto));
            bitacora.setCarnet(carnet);
            bitacora.setMes(mes);
            bitacora.setTotal_horas_realizadas(Float.parseFloat(totalH));


            helper.abrir();
            regInsertados = helper.insertar(bitacora);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultaBitacoraActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCamposLlenos(boolean camp) {
        if (txtID.getText().toString().isEmpty() || txtID.getText().toString() == null){
            return false;
        }else if (txtMes.getText().toString().isEmpty() || txtMes.getText().toString() == null) {
            return false;
        }else if (txtTotal.getText().toString().isEmpty() || txtTotal.getText().toString() == null) {
            return false;
        }else if (camp) {
            return false;

        }else {
            return true;
        }
    }
}