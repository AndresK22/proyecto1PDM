package com.example.serviciosocial.nota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;

public class CrearNotaActivity extends AppCompatActivity {

    ControlNota helper;

    EditText txtCodMateria;
    EditText txtCarnet;
    EditText txtCalificacion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        helper = new ControlNota(this);
        txtCodMateria = (EditText) findViewById(R.id.editTextCod_Materia);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnet_Nota);
        txtCalificacion = (EditText) findViewById(R.id.editTextCalificacion);
        btnGuardar = (Button) findViewById(R.id.btnGuardarNota);
    }

    public void guardarNota(View v) {
        if (verificarCamposLlenos()) {
            if (Double.valueOf(txtCalificacion.getText().toString()) > 0 && Double.valueOf(txtCalificacion.getText().toString()) <= 10){
                Nota nota = new Nota();
                nota.setCod_materia(txtCodMateria.getText().toString());
                nota.setCarnet(txtCarnet.getText().toString());
                nota.setCalificacion(Double.valueOf(txtCalificacion.getText().toString()));
                String regInsertados;

                helper.abrir();
                regInsertados = helper.insertar(nota);
                helper.cerrar();

                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ConsultarNotaActivity.class);
                startActivity(intent);
            } else{
                Toast.makeText(this, "La nota debe ser mayor a 0 y menor o igual a 10 ", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public boolean verificarCamposLlenos() {
        if (txtCodMateria.getText().toString().isEmpty() || txtCodMateria.getText().toString() == null){
            return false;
        }else if (txtCarnet.getText().toString().isEmpty() || txtCarnet.getText().toString() == null) {
            return false;
        }else if (txtCalificacion.getText().toString().isEmpty() || txtCalificacion.getText().toString() == null) {
            return false;

        }else {
            return true;
        }
    }
}