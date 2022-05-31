package com.example.serviciosocial.proyecto_estudiante;

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
import com.example.serviciosocial.categoria.Categoria;
import com.example.serviciosocial.categoria.ConsultarCategoriaActivity;
import com.example.serviciosocial.categoria.ControlCategoria;

public class ModificarEstudiantesAsignadosActivity extends AppCompatActivity {

    ControlEstudianteProyecto helper;
    EditText txtCarnet;
    EditText txtNombre;
    EditText txtApellido;
    Button eliminar;
    String carnet;
    String nombres_estudiante;
    String apellidos_estudiante;
    String id_proyecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_estudiante_asignado);

        helper = new ControlEstudianteProyecto(this);
        id_proyecto = getIntent().getExtras().getString("id_proyecto");
        carnet = getIntent().getExtras().getString("carnet");
        nombres_estudiante = getIntent().getExtras().getString("nombres_estudiante");
        txtCarnet = (EditText) findViewById(R.id.txtCarnetEA);
        txtNombre = (EditText) findViewById(R.id.txtNombreEA);
        eliminar = findViewById(R.id.btnEliminarEstudianteAsignado);
        txtCarnet.setText(carnet);
        txtNombre.setText(nombres_estudiante);

    }

    public void eliminarEstudiantesAsignados(View v) {

        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarEstudiantesAsignadosActivity.this)
                .setPositiveButton("Remover", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Estudiantes_Proyecto estPro = new Estudiantes_Proyecto();
                        estPro.setId_proyecto(Integer.valueOf(id_proyecto));
                        estPro.setCarnet(carnet);

                        helper.abrir();
                        regEliminadas = helper.eliminar(estPro);
                        helper.cerrar();
                        Toast.makeText(ModificarEstudiantesAsignadosActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarEstudiantesAsignadosActivity.this, ConsultarEstudiantesAsignados.class);
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
                .setMessage("¿Deseas remover al estudiante con carnet '" + carnet + "'?" ) // El mensaje
                .create();// crea el AlertDialog


        dialogo.show();

    }


}