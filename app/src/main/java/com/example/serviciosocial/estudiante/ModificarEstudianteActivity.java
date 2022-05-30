package com.example.serviciosocial.estudiante;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.R;
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.ConsultarCarreraActivity;
import com.example.serviciosocial.carrera.ControlCarrera;
import com.example.serviciosocial.carrera.ModificarCarreraActivity;

public class ModificarEstudianteActivity extends AppCompatActivity {

    ControlEstudiante myDB;
    EditText txtCarnet, txtNombres, txtApellidos, txtEmail, txtTelefono, txtDomicilio, txtDui;
    Button modificar, eliminar;
    String carnet, nombres, apellidos, email, telefono, domicilio, dui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_estudiante);

        myDB = new ControlEstudiante(this);
        carnet = getIntent().getExtras().getString("carnet");
        nombres = getIntent().getExtras().getString("nombres_estudiante");
        apellidos = getIntent().getExtras().getString("apellidos_estudiante");
        email = getIntent().getExtras().getString("email_estudiante");
        telefono = getIntent().getExtras().getString("telefono_estudiante");
        domicilio = getIntent().getExtras().getString("domicilio");
        dui = getIntent().getExtras().getString("dui");

        txtCarnet = findViewById(R.id.editTextCarnetE);
        txtNombres = findViewById(R.id.editTextNombresE);
        txtApellidos = findViewById(R.id.editTextApellidosE);
        txtEmail = findViewById(R.id.editTextEmailE);
        txtTelefono = findViewById(R.id.editTextTelefonoE);
        txtDomicilio = findViewById(R.id.editTextDomicilioE);
        txtDui = findViewById(R.id.editTextDuiE);

        modificar = findViewById(R.id.btnModificarEstudiante);
        eliminar = findViewById(R.id.btnEliminarEstudiante);

        txtCarnet.setText(carnet);
        txtNombres.setText(nombres);
        txtApellidos.setText(apellidos);
        txtEmail.setText(email);
        txtTelefono.setText(telefono);
        txtDomicilio.setText(domicilio);
        txtDui.setText(dui);
    }
    public void modificarEstudiante(View v){
        if(verificarCamposLlenos()){
            Estudiante e = new Estudiante();
            e.setCarnet(String.valueOf(carnet));
            e.setNombres_estudiante(String.valueOf(nombres));
            e.setApellidos_estudiante(String.valueOf(apellidos));
            e.setEmail_estudiante(String.valueOf(email));
            e.setTelefono_estudiante(String.valueOf(telefono));
            e.setDomicilio(String.valueOf(domicilio));
            e.setDui(String.valueOf(dui));

            myDB.abrir();
            String est = myDB.actualizar(e);
            myDB.cerrar();

            Toast.makeText(this, est, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarEstudianteActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
        }
    }
    public void eliminarEstudiante(View v){
        AlertDialog dialogo = new AlertDialog.Builder(ModificarEstudianteActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String regEliminados;
                        Estudiante estudiante = new Estudiante();
                        estudiante.setCarnet(String.valueOf(carnet));

                        myDB.abrir();
                        regEliminados = myDB.eliminar(estudiante);
                        myDB.cerrar();
                        Toast.makeText(ModificarEstudianteActivity.this, regEliminados, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarEstudianteActivity.this, ConsultarEstudianteActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Confirmar")
                .setMessage("¿Deseas eliminar el estudiante '" + carnet + "'?")
                .create();
        dialogo.show();
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