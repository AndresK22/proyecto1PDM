package com.example.serviciosocial.detalleBitacora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.example.serviciosocial.bitacora.ConsultaBitacoraActivity;


public class CrearDetalleBitacoraActivity extends AppCompatActivity {

    ControlDetalleBitacora helper;
    EditText txtDetalle;
    EditText txtActividad;
    EditText txtFecha;

    String extraID;

    boolean comp=false;


    Button btnGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_detalle_bitacora);

        helper = new ControlDetalleBitacora(this);
        txtDetalle= (EditText) findViewById(R.id.txtDetalle);
        txtActividad = (EditText) findViewById(R.id.txtActividad);
        txtFecha= (EditText) findViewById(R.id.txtFecha);

        //extraID = getIntent().getExtras().getString("id_proyecto");
        btnGuardar = (Button) findViewById(R.id.btnGuardarDetalleBitacora);
    }

    public void guardarDBitacora(View v) {
        if (verificarCamposLlenos(comp)) {
            String idDetalle = txtDetalle.getText().toString();
            String actividad = txtActividad.getText().toString();
            String fecha= txtFecha.getText().toString();
            Bundle extras = getIntent().getExtras();
            extraID = extras.getString("id_bitacora");

            String regInsertados;

            DetalleBitacora detalleBitacora = new DetalleBitacora();
            detalleBitacora.setId_detalle_bitacora(Long.parseLong(idDetalle));
            detalleBitacora.setId_bitacora(Long.parseLong(extraID));
            detalleBitacora.setActividad(actividad);
            detalleBitacora.setFecha_bitacora(fecha);


            helper.abrir();
            regInsertados = helper.insertar(detalleBitacora);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultaBitacoraActivity.class);
            intent.putExtra("id_bitacora",String.valueOf(extraID));
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }


    public boolean verificarCamposLlenos(boolean comp) {
        if (txtDetalle.getText().toString().isEmpty() || txtDetalle.getText().toString() == null){
            return false;
        }else if (txtActividad.getText().toString().isEmpty() || txtActividad.getText().toString() == null) {
            return false;
        }else if (txtFecha.getText().toString().isEmpty() || txtFecha.getText().toString() == null) {
            return false;
        }else {
            return true;
        }
    }
}