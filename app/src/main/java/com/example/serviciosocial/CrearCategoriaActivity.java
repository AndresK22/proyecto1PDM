package com.example.serviciosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CrearCategoriaActivity extends Activity {

    ControlBD helper;
    EditText id_categoria;
    EditText nombre_categoria;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_categoria);
        helper = new ControlBD(this);
        nombre_categoria = (EditText) findViewById(R.id.editNombre);

    }
    public void insertarAlumno(View v) {
        String nombre=nombre_categoria.getText().toString();
        String regInsertados;
        Categoria categoria=new Categoria();
        categoria.setNombre_categoria(nombre);
        helper.abrir();
        regInsertados=helper.insertar(categoria);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        nombre_categoria.setText("");
    }

}