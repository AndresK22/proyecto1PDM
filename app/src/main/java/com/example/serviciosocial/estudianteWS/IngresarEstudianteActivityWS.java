package com.example.serviciosocial.estudianteWS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class IngresarEstudianteActivityWS extends AppCompatActivity {

    EditText txtCarnet;
    EditText txtNombres;
    EditText txtApellidos;
    EditText txtEmail;
    EditText txtTelefono;
    EditText txtDui;
    EditText txtDomicilio;

    boolean comp=false;

    private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//insertar_estudiante.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_estudiante_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnetE);
        txtNombres = (EditText) findViewById(R.id.editTextNombresE);
        txtApellidos = (EditText) findViewById(R.id.editTextApellidosE);
        txtEmail = (EditText) findViewById(R.id.editTextEmailE);
        txtTelefono = (EditText) findViewById(R.id.editTextTelefonoE);
        txtDui = (EditText) findViewById(R.id.editTextDuiE);
        txtDomicilio = (EditText) findViewById(R.id.editTextDomicilioE);

    }
    public void guardarEstudianteWS(View v) {
        if (verificarCamposLlenos(comp)) {
            String carnet = txtCarnet.getText().toString().trim().replace(" ","%20");;
            String nombres = txtNombres.getText().toString().trim().replace(" ","%20");;
            String apellidos = txtApellidos.getText().toString().trim().replace(" ","%20");;
            String email = txtEmail.getText().toString().trim().replace(" ","%20");;
            String telefono = txtTelefono.getText().toString().trim().replace(" ","%20");;
            String dui = txtDui.getText().toString().trim().replace(" ","%20");;
            String domicilio = txtDomicilio.getText().toString().trim().replace(" ","%20");;

            String url = null;
            url = urlHostingGratuito + "?carnet=" + carnet + "&nombres_estudiante=" + nombres + "&apellidos_estudiante=" + apellidos + "&email_estudiante=" + email + "&telefono_estudiante=" + telefono + "&domicilio="+domicilio+"&dui="+dui;
            int i = ControladorServicioEstudiante.insertarEstudiante(url, this);
            if (i == 1) {
                Intent intent = new Intent(this, ConsultarEstudianteActivityWS.class);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();

        }
    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtCarnet.getText().toString().isEmpty() || txtCarnet.getText().toString() == null){
            return false;
        }else if (txtNombres.getText().toString().isEmpty() || txtNombres.getText().toString() == null) {
            return false;
        }else if (txtApellidos.getText().toString().isEmpty() || txtApellidos.getText().toString() == null) {
            return false;
        }else if (txtEmail.getText().toString().isEmpty() || txtEmail.getText().toString() == null) {
            return false;
        }else if (txtTelefono.getText().toString().isEmpty() || txtTelefono.getText().toString() == null) {
            return false;
        }else if (txtDui.getText().toString().isEmpty() || txtTelefono.getText().toString() == null) {
            return false;
        }else if (txtTelefono.getText().toString().isEmpty() || txtTelefono.getText().toString() == null) {
            return false;
        }else {
            return true;
        }
    }
}