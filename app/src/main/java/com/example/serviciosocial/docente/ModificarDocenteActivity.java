package com.example.serviciosocial.docente;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class ModificarDocenteActivity extends AppCompatActivity {

    ControlDocente helper;
    TextView txtDui;
    EditText txtN;
    EditText txtA;
    EditText txtEmail;
    EditText txtTelefono;
    boolean comp=false;

    String extraDUI;
    String extraN;
    String extraA;
    String extraE;
    String extraT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_docente);

        helper = new ControlDocente(this);
        txtDui = (TextView) findViewById(R.id.txtDuiM);
        txtN = (EditText) findViewById(R.id.txtNDocenteM);
        txtA= (EditText) findViewById(R.id.txtADocenteM);
        txtEmail = (EditText) findViewById(R.id.txtEmailDocenteM);
        txtTelefono = (EditText) findViewById(R.id.txtTelefonoDocenteM);



        extraDUI = getIntent().getExtras().getString("dui_docente");
        extraN = getIntent().getExtras().getString("nombres_docente");
        extraA = getIntent().getExtras().getString("apellidos_docente");
        extraE = getIntent().getExtras().getString("email_docente");
        extraT = getIntent().getExtras().getString("telefono_docente");
        txtDui.setText(extraDUI);
        txtN.setText(extraN);
        txtA.setText(extraA);
        txtEmail.setText(extraE);
        txtTelefono.setText(extraT);




    }

    public void modificarDocente(View v) {
        if (verificarCamposLlenos(comp)) {
            Docente docente = new Docente();

            docente.setDui_docente(txtDui.getText().toString());
            docente.setNombres_docente(txtN.getText().toString());
            docente.setApellidos_docente(txtA.getText().toString());
            docente.setEmail_docente(txtEmail.getText().toString());
            docente.setTelefono_docente(txtTelefono.getText().toString());

            helper.abrir();
            String d = helper.actualizar(docente);
            helper.cerrar();

            Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarDocenteActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarDocente(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarDocenteActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Docente docente = new Docente();
                        docente.setDui_docente(extraDUI);

                        helper.abrir();
                        regEliminadas = helper.eliminar(docente);
                        helper.cerrar();

                        Toast.makeText(ModificarDocenteActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarDocenteActivity.this, ConsultarDocenteActivity.class);
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
                .setMessage("¿Deseas eliminar al Docente '" + extraA + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtDui.getText().toString().isEmpty() || txtDui.getText().toString() == null){
            return false;
        }else if (txtN.getText().toString().isEmpty() || txtN.getText().toString() == null) {
            return false;
        }else if (txtA.getText().toString().isEmpty() || txtA.getText().toString() == null) {
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