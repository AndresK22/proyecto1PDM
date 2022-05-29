package com.example.serviciosocial.docente;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class CrearDocenteActivity extends AppCompatActivity {

    ControlDocente helper;
    EditText txtDui;
    EditText txtN;
    EditText txtA;
    EditText txtEmail;
    EditText txtTelefono;
    boolean comp=false;


    Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_docente);

        helper = new ControlDocente(this);
        txtDui = (EditText) findViewById(R.id.txtDui);
        txtN = (EditText) findViewById(R.id.txtNDocente);
        txtA= (EditText) findViewById(R.id.txtADocente);
        txtEmail = (EditText) findViewById(R.id.txtEmailDocente);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoDocente);

        btnGuardar = (Button) findViewById(R.id.btnGuardarDocente);
    }


    public void guardarDocente(View v) {
        if (verificarCamposLlenos(comp)) {
            String DUI = txtDui.getText().toString();
            String nombres = txtA.getText().toString();
            String apellidos = txtN.getText().toString();
            String email = txtEmail.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String regInsertados;

            Docente docente = new Docente();
            docente.setDui_docente(DUI);
            docente.setNombres_docente(nombres);
            docente.setApellidos_docente(apellidos);
            docente.setEmail_docente(email);
            docente.setTelefono_docente(telefono);

            helper.abrir();
            regInsertados = helper.insertar(docente);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarDocenteActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtDui.getText().toString().isEmpty() || txtDui.getText().toString() == null){
            return false;
        }else if (txtN.getText().toString().isEmpty() || txtN.getText().toString() == null) {
            return false;
        }else if (txtA.getText().toString().isEmpty() || txtA.getText().toString() == null) {
            return false;
        }else if (txtEmail.getText().toString().isEmpty() || txtEmail.getText().toString() == null) {
            return false;
        }else if (txtTelefono.getText().toString().isEmpty() || txtTelefono.getText().toString() == null) {
            return false;
        }else {
            return true;
        }
    }
}