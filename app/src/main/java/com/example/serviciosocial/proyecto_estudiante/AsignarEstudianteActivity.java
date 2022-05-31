package com.example.serviciosocial.proyecto_estudiante;

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
import com.example.serviciosocial.categoria.Categoria;
import com.example.serviciosocial.categoria.ConsultarCategoriaActivity;
import com.example.serviciosocial.categoria.ControlCategoria;

public class AsignarEstudianteActivity extends AppCompatActivity {

    ControlEstudianteProyecto helper;
    EditText txtCarnet;
    Button btnGuardar;
    String id_proyecto;

    boolean campCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_estudiante);

        helper = new ControlEstudianteProyecto(this);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnetSA);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEstudianteProyecto);
        id_proyecto = getIntent().getExtras().getString("id_proyecto");


        //Validaciones de campos vacios
        txtCarnet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campCat = true;
                }else{
                    campCat = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void guardarEstudianteProyecto(View v) {
        if (verificarCamposLlenos(campCat)) {
            String carnet = txtCarnet.getText().toString();

            String regInsertados;

            Estudiantes_Proyecto cat = new Estudiantes_Proyecto();
            cat.setId_proyecto(Integer.parseInt(id_proyecto));
            cat.setCarnet(carnet);

            helper.abrir();
            regInsertados = helper.insertar(cat);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarProyectosNoAsignadosActivity.class);
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