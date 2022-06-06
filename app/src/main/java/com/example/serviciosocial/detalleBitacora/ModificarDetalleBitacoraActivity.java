package com.example.serviciosocial.detalleBitacora;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.example.serviciosocial.bitacora.ConsultaBitacoraActivity;

public class ModificarDetalleBitacoraActivity extends AppCompatActivity {

    ControlDetalleBitacora helper;
    EditText txtID;
    EditText txtA;
    EditText txtF;
    public static String gg;

    boolean comp=false;

    String extraID;
    String extraId_bitacora;
    String extraA;
    String extraF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_detalle_bitacora);

        helper = new ControlDetalleBitacora(this);
        txtID = (EditText) findViewById(R.id.txtDetalleM);
        txtA = (EditText) findViewById(R.id.txtActividadM);
        txtF= (EditText) findViewById(R.id.txtFechaM);



       extraID = getIntent().getExtras().getString("id_detalle_bitacora");
       extraId_bitacora= getIntent().getExtras().getString("id_bitacora");
       gg=extraId_bitacora;
        extraA = getIntent().getExtras().getString("actividad");
        extraF = getIntent().getExtras().getString("fecha_bitacora");
        txtID.setText(extraID);
        txtA.setText(extraA);
        txtF.setText(extraF);




    }

    public void modificarDetalle(View v) {
        extraId_bitacora= getIntent().getExtras().getString("id_bitacora");

        if (verificarCamposLlenos(comp)) {

            DetalleBitacora detalleBitacora = new DetalleBitacora();
            String x = extraID;
            String[] ex={x};
            detalleBitacora.setId_detalle_bitacora(Long.parseLong(txtID.getText().toString()));
            detalleBitacora.setId_bitacora(Long.parseLong(extraId_bitacora));
            detalleBitacora.setActividad(txtA.getText().toString());
            detalleBitacora.setFecha_bitacora(txtF.getText().toString());

            helper.abrir();
            String d = helper.actualizar(detalleBitacora,ex);
            helper.cerrar();

            Toast.makeText(this, d, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultaDetalleBitacoraActivity.class);
            intent.putExtra("id_bitacora",String.valueOf(extraId_bitacora));

            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarDetalle(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarDetalleBitacoraActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        DetalleBitacora detalleBitacora = new DetalleBitacora();
                        detalleBitacora.setId_detalle_bitacora(Long.parseLong(extraID));

                        helper.abrir();
                        regEliminadas = helper.eliminar(detalleBitacora);
                        helper.cerrar();

                        Toast.makeText(ModificarDetalleBitacoraActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarDetalleBitacoraActivity.this, ConsultaBitacoraActivity.class);
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
                .setMessage("¿Deseas eliminar detalle '" + extraID + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtID.getText().toString().isEmpty() || txtID.getText().toString() == null){
            return false;
        }else if (txtF.getText().toString().isEmpty() || txtF.getText().toString() == null) {
            return false;
        }else if (txtA.getText().toString().isEmpty() || txtA.getText().toString() == null) {
            return false;
     }else {
            return true;
        }
    }
}