package com.example.serviciosocial.resumensocial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.serviciosocial.R;

import java.util.ArrayList;

public class CrearResumenActivity extends AppCompatActivity {

    ControlResumenSocial helper;
    Spinner spinerDocente;
    Spinner spinerCarnet;
    ArrayList<String> dui_docente, nombres_docente, apellidos_docente, email_docente, telefono_docente;
    ArrayList<String> carnet, nombres_estudiante, apellidos_estudiante, email_estudiante, telefono_estudiante, domicilio, dui;
    String id_resumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_resumen);
    }
}