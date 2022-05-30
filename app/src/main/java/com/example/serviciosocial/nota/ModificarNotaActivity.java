package com.example.serviciosocial.nota;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.R;

public class ModificarNotaActivity extends AppCompatActivity {

    ControlNota helper;
    String extraCod_materia;
    String extraCarnet;
    String extraCalificacion;

    EditText txtCodMateria;
    EditText txtCarnet;
    EditText txtCalificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_nota);

        helper = new ControlNota(this);
        txtCodMateria = (EditText) findViewById(R.id.editTextCod_Materia);
        txtCarnet = (EditText) findViewById(R.id.editTextCarnet_Nota);
        txtCalificacion = (EditText) findViewById(R.id.editTextCalificacion);

        extraCod_materia = getIntent().getExtras().getString("cod_materia");
        extraCarnet = getIntent().getExtras().getString("carnet");
        extraCalificacion = getIntent().getExtras().getString("calificacion");
        txtCodMateria.setText(extraCod_materia);
        txtCarnet.setText(extraCarnet);
        txtCalificacion.setText(extraCalificacion);
    }

    public void modificarNota(View v) {
        if (verificarCamposLlenos()) {
            if (Double.valueOf(txtCalificacion.getText().toString()) > 0 && Double.valueOf(txtCalificacion.getText().toString()) <= 10){
                Nota nota = new Nota();
                nota.setCod_materia(extraCod_materia);
                nota.setCarnet(extraCarnet);
                nota.setCalificacion(Double.valueOf(txtCalificacion.getText().toString()));

                helper.abrir();
                String not = helper.actualizar(nota);
                helper.cerrar();

                Toast.makeText(this, not, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ConsultarNotaActivity.class);
                startActivity(intent);
            } else{
                Toast.makeText(this, "La nota debe ser mayor a 0 y menor o igual a 10 ", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarNota(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarNotaActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Nota nota = new Nota();
                        nota.setCod_materia(extraCod_materia);
                        nota.setCarnet(extraCarnet);

                        helper.abrir();
                        regEliminadas = helper.eliminar(nota);
                        helper.cerrar();

                        Toast.makeText(ModificarNotaActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarNotaActivity.this, ConsultarNotaActivity.class);
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
                .setMessage("¿Deseas eliminar la nota de la materia '" + extraCod_materia + "' y del alumno con carnet '" + extraCarnet + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }


    public boolean verificarCamposLlenos() {
        if (txtCodMateria.getText().toString().isEmpty() || txtCodMateria.getText().toString() == null){
            return false;
        }else if (txtCarnet.getText().toString().isEmpty() || txtCarnet.getText().toString() == null) {
            return false;
        }else if (txtCalificacion.getText().toString().isEmpty() || txtCalificacion.getText().toString() == null) {
            return false;

        }else {
            return true;
        }
    }
}