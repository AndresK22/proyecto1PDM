package com.example.serviciosocial.areaCarrera;

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
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.ControlCarrera;

import java.util.ArrayList;
import java.util.Iterator;


public class CrearAreaActivity extends AppCompatActivity {

    ControlAreaCarrera helper;
    ControlCarrera helper1;
    Spinner spinerCarrera;
    ArrayList<String> id_carrera, nombre_carrera; //para el spinner de Carrera
    String id_are;

    EditText txtIDArea;
    EditText txtDescrip;
    Button btnGuardar;

    boolean campAre = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_area);

        helper = new ControlAreaCarrera(this);
        helper1 = new ControlCarrera(this);

        spinerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        txtIDArea = (EditText) findViewById(R.id.txtAreaCarrera);
        txtDescrip = (EditText) findViewById(R.id.txtdescripArea);
        btnGuardar = (Button) findViewById(R.id.btnGuardarArea);

        id_carrera = new ArrayList<>();
        nombre_carrera = new ArrayList<>();
       nombre_carrera.add("Seleccione la carrera");


        //Aqui se va a pedir el area
        helper1.abrir();
        ArrayList<Carrera> itemsSpinner = helper1.consultarCarrera();
        helper1.cerrar();

        Cursor cursor = helper1.leerTodoCarrera();
        if (cursor.getCount()==0){

        }else{
        Carrera a;
        Iterator<Carrera> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            a = it.next();
            id_carrera.add(String.valueOf(a.getId_carrera()));
            nombre_carrera.add(a.getNombre_carrera());
        }}
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_carrera);
        spinerCarrera.setAdapter(adaptador);

        //Validaciones de campos vacios
        spinerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_carrera.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el create
                    campAre = false;
                } else {
                    campAre = true;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void guardarArea(View v) {
        if (verificarCamposLlenos(campAre)) {
            String idArea = txtIDArea.getText().toString();
            String idCarrera = id_are;
            String descrip = txtDescrip.getText().toString();
            String regInsertados;

            AreaCarrera areaCarrera = new AreaCarrera();
            areaCarrera.setId_area(idArea);
            areaCarrera.setId_carrera(idCarrera);
            areaCarrera.setDescrip_area(descrip);

            helper.abrir();
            regInsertados = helper.insertar(areaCarrera);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarAreaActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtIDArea.getText().toString().isEmpty() || txtIDArea.getText().toString() == null){
            return false;
        }else if (txtDescrip.getText().toString().isEmpty() || txtDescrip.getText().toString() == null) {
            return false;
        }else if (cmpAre) {
            return false;

        }else {
            return true;
        }
    }
}