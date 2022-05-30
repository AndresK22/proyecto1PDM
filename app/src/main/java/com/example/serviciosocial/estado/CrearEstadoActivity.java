package com.example.serviciosocial.estado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.R;

public class CrearEstadoActivity extends AppCompatActivity {

    ControlEstado helper;
    EditText txtEstado;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_estado);

        helper = new ControlEstado(this);
        txtEstado = (EditText) findViewById(R.id.editTextEstado);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEstado);
    }

    public void guardarEstado(View v) {
        if (verificarCamposLlenos()) {
            String estado = txtEstado.getText().toString();
            String regInsertados;

            Estado esta = new Estado();
            esta.setEstado(estado);

            helper.abrir();
            regInsertados = helper.insertar(esta);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarEstadoActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }


    public boolean verificarCamposLlenos() {
        if (txtEstado.getText().toString().isEmpty() || txtEstado.getText().toString() == null) {
            //Si esta vacio devuelve falso
            return false;
        }else {
            return true;
        }
    }
}