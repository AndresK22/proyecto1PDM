package com.example.serviciosocial.docenteWS;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class IngresarDocenteActivityWS extends AppCompatActivity {

    EditText txtDUI;
    EditText txtNombres;
    EditText txtApellidos;
    EditText txtEmail;
    EditText txtTelefono;
    boolean comp=false;

    private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//insertar_docente.php";
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_docente_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtDUI = (EditText) findViewById(R.id.txtDui);
        txtNombres = (EditText) findViewById(R.id.txtNDocente);
        txtApellidos = (EditText) findViewById(R.id.txtADocente);
        txtEmail = (EditText) findViewById(R.id.txtEmailDocente);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoDocente);
    }
    public void guardarDocenteWS(View v) {
        if (verificarCamposLlenos(comp)) {

            String dui = txtDUI.getText().toString().trim().replace(" ","%20");;
            String nombres = txtNombres.getText().toString().trim().replace(" ","%20");;
            String apellidos = txtApellidos.getText().toString().trim().replace(" ","%20");;
            String email = txtEmail.getText().toString().trim().replace(" ","%20");;
            String telefono = txtTelefono.getText().toString().trim().replace(" ","%20");;

            String url = null;
            url = urlHostingGratuito + "?dui_docente=" + dui + "&nombres_docente=" + nombres + "&apellidos_docente=" + apellidos + "&email_docente=" + email + "&telefono_docente=" + telefono;
            int i = ControladorServicioDocente.insertarDocente(url, this);
            if (i == 1) {
                Intent intent = new Intent(this, ConsultarDocenteActivityWS.class);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();

        }
}


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtDUI.getText().toString().isEmpty() || txtDUI.getText().toString() == null){
            return false;
        }else if (txtNombres.getText().toString().isEmpty() || txtNombres.getText().toString() == null) {
            return false;
        }else if (txtApellidos.getText().toString().isEmpty() || txtApellidos.getText().toString() == null) {
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