package com.example.serviciosocial.categoria;

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

public class CrearCategoriaActivity extends AppCompatActivity {

    ControlCategoria helper;
    EditText txtCategoria;
    Button btnGuardar;

    boolean campCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);

        helper = new ControlCategoria(this);
        txtCategoria = (EditText) findViewById(R.id.editTextCategoria);
        btnGuardar = (Button) findViewById(R.id.btnGuardarCategoria);


        //Validaciones de campos vacios
        txtCategoria.addTextChangedListener(new TextWatcher() {
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

    public void guardarCategoria(View v) {
        if (verificarCamposLlenos(campCat)) {
            String categoria = txtCategoria.getText().toString();
            String regInsertados;

            Categoria cat = new Categoria();
            cat.setNombre_categoria(categoria);

            helper.abrir();
            regInsertados = helper.insertar(cat);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarCategoriaActivity.class);
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