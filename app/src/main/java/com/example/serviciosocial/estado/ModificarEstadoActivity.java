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
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;

public class ModificarEstadoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText txtEstado;
    Button modificar;
    Button eliminar;
    String id_estado;
    String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_estado);

        helper = new ControlBD(this);
        id_estado = getIntent().getExtras().getString("id_estado");
        estado = getIntent().getExtras().getString("estado");
        txtEstado = (EditText) findViewById(R.id.editTextEstado);
        modificar = findViewById(R.id.btnModificar);
        eliminar = findViewById(R.id.btnEliminar);
        txtEstado.setText(estado);

    }

    public void modificarEstado(View v) {
        if (verificarCamposLlenos()) {
            Estado estado = new Estado();
            estado.setId_estado(Integer.valueOf(id_estado));
            estado.setEstado(txtEstado.getText().toString());

            helper.abrir();
            String estad = helper.actualizar(estado);
            helper.cerrar();

            Toast.makeText(this, estad, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarEstadoActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarEstado(View v) {

        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarEstadoActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Estado estado = new Estado();
                        estado.setId_estado(Integer.valueOf(id_estado));

                        helper.abrir();
                        regEliminadas = helper.eliminar(estado);
                        helper.cerrar();
                        Toast.makeText(ModificarEstadoActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarEstadoActivity.this, ConsultarEstadoActivity.class);
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
                .setMessage("¿Deseas eliminar el estado '" + estado + "'?") // El mensaje
                .create();// crea el AlertDialog


        dialogo.show();

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