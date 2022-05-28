package com.example.serviciosocial.categoria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class ActualizarCategoriaActivity extends AppCompatActivity {

    ControlCategoria helper;
    EditText txtCategoria;
    Button modificar;
    Button eliminar;
    String id_categoria;
    String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_categoria);

        helper = new ControlCategoria(this);
        id_categoria = getIntent().getExtras().getString("id_categoria");
        categoria = getIntent().getExtras().getString("nombre_categoria");
        txtCategoria = (EditText) findViewById(R.id.txtCategoria);
        modificar = findViewById(R.id.btnModificarCategoria);
        eliminar = findViewById(R.id.btnEliminarCategoria);
        txtCategoria.setText(categoria);

    }

    public void modificarCategoria(View v) {
        if (verificarCamposLlenos()) {
            Categoria cate = new Categoria();
            cate.setId_categoria(Integer.valueOf(id_categoria));
            cate.setNombre_categoria(txtCategoria.getText().toString());

            helper.abrir();
            String categoria = helper.actualizar(cate);
            helper.cerrar();

            Toast.makeText(this, categoria, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarCategoriaActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarCategoria(View v) {

        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ActualizarCategoriaActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Categoria catego = new Categoria();
                        catego.setId_categoria(Integer.valueOf(id_categoria));

                        helper.abrir();
                        regEliminadas = helper.eliminar(catego);
                        helper.cerrar();
                        Toast.makeText(ActualizarCategoriaActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActualizarCategoriaActivity.this, ConsultarCategoriaActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón negativo, no confirmaron
                        // Simplemente descartamos el diálogo
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar la categoría '" + categoria + "'?") // El mensaje
                .create();// crea el AlertDialog


        dialogo.show();

    }

    public boolean verificarCamposLlenos() {
        if (txtCategoria.getText().toString().isEmpty() || txtCategoria.getText().toString() == null) {
            //Si esta vacio devuelve falso
            return false;
        }else {
            return true;
        }
    }
}