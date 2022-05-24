package com.example.serviciosocial.materia;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearMateriaActivity extends AppCompatActivity {

    ControlBD helper;
    Spinner spinerArea;
    ArrayList<String> id_area, descrip_area; //para el spinne
    String id_are;

    EditText txtCodMateria;
    EditText txtMateria;
    Button btnGuardar;

    boolean campCod, campAre, campMater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_materia);

        helper = new ControlBD(this);
        spinerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtCodMateria = (EditText) findViewById(R.id.txtCodMateria);
        txtMateria = (EditText) findViewById(R.id.txtMateria);
        btnGuardar = (Button) findViewById(R.id.btnGuardarMateria);

        id_area = new ArrayList<>();
        descrip_area = new ArrayList<>();
        descrip_area.add("Seleccione un area");


        //Aqui se va a pedir el area
        helper.abrir();
        ArrayList<Materia> itemsSpinner = helper.consultarMateria();
        helper.cerrar();

        Materia mat;
        Iterator<Materia> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            mat = it.next();

            id_area.add(String.valueOf(mat.getId_area()));
            descrip_area.add(mat.getNombre_materia());
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_area);
        spinerArea.setAdapter(adaptador);

        //Validaciones de campos vacios
        txtCodMateria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campCod = true;
                }else{
                    campCod = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_area.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el create
                    campAre = true;
                } else {
                    campAre = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtMateria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campMater = true;
                }else{
                    campMater = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    public void guardarMateria(View v) {
        if (verificarCamposLlenos(campCod, campAre, campMater)) {
            String codMateria = txtCodMateria.getText().toString();
            String idArea = id_are;
            String nomMateria = txtMateria.getText().toString();
            String regInsertados;

            Materia mater = new Materia();
            mater.setCod_materia(codMateria);
            mater.setId_area(idArea);
            mater.setNombre_materia(nomMateria);

            helper.abrir();
            regInsertados = helper.insertar(mater);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarMateriaActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCamposLlenos(boolean cmpCod, boolean cmpAre, boolean cmpMater) {
        if (cmpCod & cmpAre & cmpMater) {
            //Si los 3 campos son verdadero (tienen contenido) devuelve verdadero
            return true;
        }else {
            return false;
        }
    }
}