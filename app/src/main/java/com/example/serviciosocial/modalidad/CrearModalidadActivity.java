package com.example.serviciosocial.modalidad;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class CrearModalidadActivity extends AppCompatActivity {

    ControlModalidad helper;
    EditText txtModalidad;
    Button btnGuardar;

    boolean campMod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_modalidad);

        helper = new ControlModalidad(this);
        txtModalidad = (EditText) findViewById(R.id.editTextModalidad);
        btnGuardar = (Button) findViewById(R.id.btnGuardarModalidad);


        //Validaciones de campos vacios
        txtModalidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campMod = true;
                }else{
                    campMod = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void guardarModalidad(View v) {
        if (verificarCamposLlenos(campMod)) {
            String modalidad = txtModalidad.getText().toString();
            String regInsertados;

            Modalidad mod = new Modalidad();
            mod.setNombre_modalidad(modalidad);

            helper.abrir();
            regInsertados = helper.insertar(mod);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarModalidadActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }


    public boolean verificarCamposLlenos(boolean cmpEst) {
        if (cmpEst) {
            //Si el campo es verdadero (tiene contenido) devuelve verdadero
            return true;
        }else {
            return false;
        }
    }
}