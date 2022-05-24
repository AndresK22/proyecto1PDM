package com.example.serviciosocial.estado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;

public class CrearEstadoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText txtEstado;
    Button btnGuardar;

    boolean campEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_estado);

        helper = new ControlBD(this);
        txtEstado = (EditText) findViewById(R.id.editTextEstado);
        btnGuardar = (Button) findViewById(R.id.btnGuardarEstado);


        //Validaciones de campos vacios
        txtEstado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campEst = true;
                }else{
                    campEst = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void guardarEstado(View v) {
        if (verificarCamposLlenos(campEst)) {
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


    public boolean verificarCamposLlenos(boolean cmpEst) {
        if (cmpEst) {
            //Si el campo es verdadero (tiene contenido) devuelve verdadero
            return true;
        }else {
            return false;
        }
    }
}