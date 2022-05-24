package com.example.serviciosocial.recordAcademico;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;
import com.example.serviciosocial.materia.Materia;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarRecordActivity extends AppCompatActivity {

    ControlBD helper;
    String descArea;
    String extraCarnet;
    String extraIdArea;
    String extraMateriasAprobadas;
    String extraProgreso;
    String extraPromedio;

    EditText txtCarnet;
    EditText txtArea;
    EditText txtMateriasAprobadas;
    EditText txtProgreso;
    EditText txtPromedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_record);

        helper = new ControlBD(this);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnet_Record);
        txtArea = (EditText) findViewById(R.id.editTextArea);
        txtMateriasAprobadas = (EditText) findViewById(R.id.editTextMat_aprob);
        txtProgreso = (EditText) findViewById(R.id.editTextProgreso);
        txtPromedio = (EditText) findViewById(R.id.editTextPromedio);

        extraCarnet = getIntent().getExtras().getString("carnet");
        extraIdArea = getIntent().getExtras().getString("id_area");
        extraMateriasAprobadas = getIntent().getExtras().getString("materias_aprobadas");
        extraProgreso = getIntent().getExtras().getString("progreso");
        extraPromedio = getIntent().getExtras().getString("promedio");

        txtCarnet.setText(extraCarnet);
        txtMateriasAprobadas.setText(extraMateriasAprobadas);
        txtProgreso.setText(extraProgreso);
        txtPromedio.setText(extraPromedio);

        String[] idArea = {"mat115"}; //Dentro de corchetes iria extraIdArea
        //Aqui se va a pedir el area
        helper.abrir();
        Materia area = helper.consultarMateria(idArea); //Metodo que busque el area por id
        helper.cerrar();

        descArea = area.getNombre_materia();
        txtArea.setText(descArea);
        //fin de lo del area
    }
}