package com.example.serviciosocial.materia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.areaCarrera.AreaCarrera;
import com.example.serviciosocial.areaCarrera.ControlAreaCarrera;
import com.example.serviciosocial.R;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearMateriaActivity extends AppCompatActivity {

    ControlMateria helper;
    ControlAreaCarrera helper2;
    Spinner spinerArea;
    ArrayList<String> id_area, descrip_area; //para el spinner
    String id_are;

    EditText txtCodMateria;
    EditText txtMateria;
    Button btnGuardar;

    boolean campAre = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_materia);

        helper = new ControlMateria(this);
        helper2 = new ControlAreaCarrera(this);
        spinerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtCodMateria = (EditText) findViewById(R.id.txtCodMateria);
        txtMateria = (EditText) findViewById(R.id.txtMateria);
        btnGuardar = (Button) findViewById(R.id.btnGuardarMateria);

        id_area = new ArrayList<>();
        descrip_area = new ArrayList<>();
        descrip_area.add("Seleccione un area");


        //Aqui se va a pedir el area
        helper2.abrir();
        ArrayList<AreaCarrera> itemsSpinner = helper2.consultarArea();
        helper2.cerrar();

        AreaCarrera are;
        Iterator<AreaCarrera> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            are = it.next();
            id_area.add(String.valueOf(are.getId_area()));
            descrip_area.add(are.getDescrip_area());
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_area);
        spinerArea.setAdapter(adaptador);

        //Validaciones de campos vacios
        spinerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_area.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el create
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


    public void guardarMateria(View v) {
        if (verificarCamposLlenos(campAre)) {
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


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtCodMateria.getText().toString().isEmpty() || txtCodMateria.getText().toString() == null){
            return false;
        }else if (txtMateria.getText().toString().isEmpty() || txtMateria.getText().toString() == null) {
            return false;
        }else if (cmpAre) {
            return false;

        }else {
            return true;
        }
    }
}