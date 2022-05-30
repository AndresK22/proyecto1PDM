package com.example.serviciosocial.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.R;
import com.example.serviciosocial.carrera.ConsultarCarreraActivity;

public class CrearEstudianteActivity extends AppCompatActivity {

    ControlEstudiante helper;
    EditText txtCarnet, txtNombres, txtApellidos, txtEmail, txtTelefono, txtDomicilio, txtDui;
    Button guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_estudiante);

        helper = new ControlEstudiante(this);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnetE);
        txtNombres = (EditText) findViewById(R.id.editTextNombresE);
        txtApellidos = (EditText) findViewById(R.id.editTextApellidosE);
        txtEmail = (EditText) findViewById(R.id.editTextEmailE);
        txtTelefono = (EditText) findViewById(R.id.editTextTelefonoE);
        txtDomicilio = (EditText) findViewById(R.id.editTextDomicilioE);
        txtDui = (EditText) findViewById(R.id.editTextDuiE);
        guardar = (Button) findViewById(R.id.btnGuardarEstudiante);
    }
    public void guardarEstudiante(View v){
        if(verificarCamposLlenos()){

            String carnet = txtCarnet.getText().toString();
            String nombres = txtNombres.getText().toString();
            String apellidos = txtApellidos.getText().toString();
            String email = txtEmail.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String domicilio = txtDomicilio.getText().toString();
            String dui = txtDui.getText().toString();
            String regInsertados;

            Estudiante e = new Estudiante();
            e.setCarnet(carnet);
            e.setNombres_estudiante(nombres);
            e.setApellidos_estudiante(apellidos);
            e.setEmail_estudiante(email);
            e.setTelefono_estudiante(telefono);
            e.setDomicilio(domicilio);
            e.setDui(dui);

            helper.abrir();
            regInsertados = helper.insertar(e);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarEstudianteActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }
    public boolean verificarCamposLlenos(){
        if(txtNombres.getText().toString().isEmpty() || txtNombres.getText().toString() == null){
            return false;
        }else if(txtCarnet.getText().toString().isEmpty() || txtCarnet.getText().toString() == null){
            return false;
        }else if(txtApellidos.getText().toString().isEmpty() || txtApellidos.getText().toString() == null){
            return false;
        }else if(txtEmail.getText().toString().isEmpty() || txtEmail.getText().toString() == null){
            return false;
        }else if(txtTelefono.getText().toString().isEmpty() || txtTelefono.getText().toString() == null){
            return false;
        }else if(txtDomicilio.getText().toString().isEmpty() || txtDomicilio.getText().toString() == null){
            return false;
        }else if(txtDui.getText().toString().isEmpty() || txtDui.getText().toString() == null){
            return false;
        }else{
            return true;
        }
    }
}