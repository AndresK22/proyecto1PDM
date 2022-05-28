package com.example.serviciosocial.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.serviciosocial.R;

import java.util.ArrayList;

public class CrearEstudianteActivity extends AppCompatActivity {

    Spinner spinnerCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_estudiante);

        spinnerCarrera = findViewById(R.id.spinnerCarrera);

        /*Carreras de prueba, estas deben venir de la DB*/
        ArrayList<String> carreras = new ArrayList<String>();

        carreras.add( "Ingenieria en Sistemas");
        carreras.add( "Administrador con casco");
        carreras.add( "Payasito");

        ArrayAdapter<String>  adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,carreras);
        spinnerCarrera.setAdapter(adapter);
    }
}