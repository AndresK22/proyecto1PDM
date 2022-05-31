package com.example.serviciosocial.resumensocial;

import androidx.appcompat.app.AppCompatActivity;

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

import com.example.serviciosocial.R;
import com.example.serviciosocial.docente.ControlDocente;
import com.example.serviciosocial.docente.Docente;
import com.example.serviciosocial.estudiante.ControlEstudiante;
import com.example.serviciosocial.estudiante.Estudiante;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearResumenActivity extends AppCompatActivity {

    ControlResumenSocial helper;
    ControlEstudiante helperE;
    ControlDocente helperD;
    Spinner spinerDocente, spinerCarnet;
    ArrayList<String> dui_docente, carnet; // Para el spinner
    String id_d, id_c;

    EditText txtId;
    EditText txtFechaApertura;
    EditText txtFechaEmision;
    EditText txtObservaciones;

    Button btnGuardar;

    boolean camp = false; // True si no se ha seleccionado nada
    //ArrayList<String> dui_docente, nombres_docente, apellidos_docente, email_docente, telefono_docente;
    //ArrayList<String> carnet, nombres_estudiante, apellidos_estudiante, email_estudiante, telefono_estudiante, domicilio, dui;
    //String id_resumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_resumen);

        helper = new ControlResumenSocial(this);
        helperD = new ControlDocente(this);
        helperE = new ControlEstudiante(this);
        spinerCarnet = (Spinner) findViewById(R.id.spinnerCarnetE);
        spinerDocente = (Spinner) findViewById(R.id.spinnerDui_docente);

        txtId = (EditText) findViewById(R.id.txtIdResumen);
        txtFechaApertura = (EditText) findViewById(R.id.txtFechaAperturaRS);
        txtFechaEmision = (EditText) findViewById(R.id.txtFechaEmisionRS);
        txtObservaciones = (EditText) findViewById(R.id.txtObservaciones);
        btnGuardar = (Button) findViewById(R.id.btnGuardarResumen);

        dui_docente = new ArrayList<>();
        carnet = new ArrayList<>();

        dui_docente.add("Seleccione DUI del docente");
        carnet.add("Seleccione el carnet del estudiante");
        //Resumen social
        helper.abrir();
        ArrayList<Resumensocial> itemsSpinner = helper.consultarResumen();
        helper.cerrar();
        //Estudiante
        helperE.abrir();
        ArrayList<Estudiante> itemsSpinnerE = helperE.consultarEstudiante();
        helperE.cerrar();
        //Docente
        helperD.abrir();
        ArrayList<Docente> itemsSpinnerD = helperD.consultarDocente();
        helperD.cerrar();

        Cursor cursor = helper.leerTodoResumen();
        if(cursor.getCount()==0){

        }else{
            Resumensocial a;
            Iterator<Resumensocial> it = itemsSpinner.iterator();
            while(it.hasNext()){
                a = it.next();
                dui_docente.add(String.valueOf(a.getDui_docente()));
            }
        }

        Cursor cursor1 = helperE.leerTodoEstudiante();
        if(cursor1.getCount()==0){

        }else{
            Estudiante b;
            Iterator<Estudiante> i = itemsSpinnerE.iterator();
            while(i.hasNext()){
                b = i.next();
                carnet.add(String.valueOf(b.getCarnet()));
            }
        }

        ArrayAdapter<CharSequence> adaptadorD = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dui_docente);
        spinerDocente.setAdapter(adaptadorD);

        ArrayAdapter<CharSequence> adaptadorE = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnet);
        spinerCarnet.setAdapter(adaptadorE);

        //Validaciones de campos vacios
        spinerCarnet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_c = carnet.get(i - 1);
                    //Guarda el carnet seleccionado en la variable global, para usarla en el create
                    camp = false;
                } else {
                    camp = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinerDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_d = dui_docente.get(i - 1);
                    //Guarda el dui del docente seleccionada en la variable global, para usarla en el create
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

    public void guardarResumen(View v){
        if(verificarCamposLlenos(camp)){
            String id = txtId.getText().toString();
            String docente = id_d;
            String carnet = id_c;
            String fechaA = txtFechaApertura.getText().toString();
            String fechaE = txtFechaEmision.getText().toString();
            String observaciones = txtObservaciones.getText().toString();
            String regInsertados;

            Resumensocial resumen = new Resumensocial();
            resumen.setId_resumen(Integer.parseInt(id));
            resumen.setDui_docente(docente);
            resumen.setCarnet(carnet);
            resumen.setFecha_apertura_expediente(fechaA);
            resumen.setFecha_emision_certificado(fechaE);
            resumen.setObservaciones(observaciones);

            helper.abrir();
            regInsertados = helper.insertar(resumen);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarResumenActivity.class);
            startActivity(intent);

        }else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }
    }
    public boolean verificarCamposLlenos(boolean camp){
        if(txtId.getText().toString().isEmpty() || txtId.getText().toString() == null){
            return false;
        }else if(txtFechaApertura.getText().toString().isEmpty() || txtFechaApertura.getText().toString() == null){
            return false;
        }else if(txtFechaEmision.getText().toString().isEmpty() || txtFechaEmision.getText().toString() == null){
            return false;
        }else if(txtObservaciones.getText().toString().isEmpty() || txtObservaciones.getText().toString() == null){
            return false;
        }else if(camp){
            return false;
        }else{
            return true;
        }
    }
}