package com.example.serviciosocial.proyecto;

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
import com.example.serviciosocial.modalidad.ConsultarModalidadActivity;
import com.example.serviciosocial.modalidad.Modalidad;

public class ModificarProyectoActivity extends AppCompatActivity {

    ControlProyecto helper;
    EditText txtModalidad;
    Button modificar;
    Button eliminar;
    String id_modalidad;
    String modalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_modalidad);

        helper = new ControlProyecto(this);
        id_modalidad = getIntent().getExtras().getString("id_modalidad");
        modalidad = getIntent().getExtras().getString("nombre_modalidad");
        txtModalidad = (EditText) findViewById(R.id.editTextModalidad);
        modificar = findViewById(R.id.btnModificarModalidad);
        eliminar = findViewById(R.id.btnEliminarModalidad);
        txtModalidad.setText(modalidad);

    }

    public void modificarModalidad(View v) {
        if (verificarCamposLlenos()) {
            Modalidad moda = new Modalidad();
            moda.setId_modalidad(Integer.valueOf(id_modalidad));
            moda.setNombre_modalidad(txtModalidad.getText().toString());

            helper.abrir();
            String modalidad = helper.actualizar(moda);
            helper.cerrar();

            Toast.makeText(this, modalidad, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarModalidadActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarModalidad(View v) {

        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarProyectoActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Modalidad modalidad1 = new Modalidad();
                        modalidad1.setId_modalidad(Integer.valueOf(id_modalidad));

                        helper.abrir();
                        regEliminadas = helper.eliminar(modalidad1);
                        helper.cerrar();
                        Toast.makeText(ModificarProyectoActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarProyectoActivity.this, ConsultarModalidadActivity.class);
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
                .setMessage("¿Deseas eliminar la modalidad '" + modalidad + "'?") // El mensaje
                .create();// crea el AlertDialog


        dialogo.show();

    }

    public boolean verificarCamposLlenos() {
        if (txtModalidad.getText().toString().isEmpty() || txtModalidad.getText().toString() == null) {
            //Si esta vacio devuelve falso
            return false;
        }else {
            return true;
        }
    }
}