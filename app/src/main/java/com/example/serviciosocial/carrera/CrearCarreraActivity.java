package com.example.serviciosocial.carrera;

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

public class CrearCarreraActivity extends AppCompatActivity {

    ControlCarrera helper;
    EditText txtCarrera, txtTotalMaterias, txtIdCarrera;
    Button btnGuardar;

    boolean campCar0, campCar, campCar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carrera);

        helper = new ControlCarrera(this);
        txtIdCarrera = (EditText) findViewById(R.id.editTextIdCarrera);
        txtCarrera = (EditText) findViewById(R.id.editTextNombreCarreraC);
        txtTotalMaterias = (EditText) findViewById(R.id.editTextTotalMateriasC);
        btnGuardar = (Button) findViewById(R.id.btnGuardarCarrera);

        //Validaciones de campos vacios
        txtCarrera.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i2>0){
                    campCar = true;
                }else {
                    campCar = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtTotalMaterias.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i2>0){
                    campCar1 = true;
                }else {
                    campCar1 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        txtIdCarrera.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(i2>0){
                    campCar0 = true;
                }else {
                    campCar0 = false;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void guardarCarrera(View v){
        if(verificarCamposLlenos(campCar0, campCar, campCar1)){
            String idCarrera = txtIdCarrera.getText().toString();
            String nombreCarrera = txtCarrera.getText().toString();
            int total = Integer.valueOf(txtTotalMaterias.getText().toString());
            String regInsertados;

            Carrera car = new Carrera();
            car.setId_carrera(idCarrera);
            car.setNombre_carrera(nombreCarrera);
            car.setTotal_materias(total);

            helper.abrir();
            regInsertados = helper.insertar(car);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarCarreraActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }
    public boolean verificarCamposLlenos(boolean cmpCar0, boolean cmpCar, boolean cmpCar1){
        if(cmpCar && cmpCar1 && cmpCar0){
            return true;
        }else{
            return false;
        }
    }
}