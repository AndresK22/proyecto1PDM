package com.example.serviciosocial.resumensocial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.serviciosocial.R;
import com.example.serviciosocial.docente.ControlDocente;
import com.example.serviciosocial.docente.Docente;
import com.example.serviciosocial.estudiante.ControlEstudiante;
import com.example.serviciosocial.estudiante.Estudiante;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarResumenActivity extends AppCompatActivity {

    ControlResumenSocial helper;
    ControlEstudiante helperE;
    ControlDocente helperD;
    Spinner spinnerDuiDocente, spinnerCarnet;
    ArrayList<String> dui_docente, carnet;
    String id_d, id_c;

    String extraID, extraD, extraC, extraFechaA, extraFechaE, extraObservaciones;

    EditText txtId, txtFechaAE, txtFechaEC, txtObservaciones;
    Button btnModificar, btnEliminar;

    boolean camp = false;

    boolean campDoc=false, campEst=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_resumen);

        helper = new ControlResumenSocial(this);
        helperD = new ControlDocente(this);
        helperE = new ControlEstudiante(this);
        spinnerCarnet = (Spinner) findViewById(R.id.spinnerCarnet);
        spinnerDuiDocente = (Spinner) findViewById(R.id.spinnerDui_docente);

        txtId = (EditText) findViewById(R.id.txtId_det_res);
        txtFechaAE = (EditText) findViewById(R.id.txtFechaAperturaRS);
        txtFechaEC = (EditText) findViewById(R.id.txtId_proyecto);
        txtObservaciones = (EditText) findViewById(R.id.txtMonto);

        extraID = getIntent().getExtras().getString("id_resumen");
        extraD = getIntent().getExtras().getString("dui_docente");
        extraC = getIntent().getExtras().getString("carnet");
        extraFechaA = getIntent().getExtras().getString("fecha_apertura_expediente");
        extraFechaE = getIntent().getExtras().getString("fecha_emision_certificado");
        extraObservaciones = getIntent().getExtras().getString("observaciones");

        txtId.setText(extraID);
        txtFechaAE.setText(extraFechaA);
        txtFechaEC.setText(extraFechaE);
        txtObservaciones.setText(extraObservaciones);
        //spinnerDuiDocente.setSelection(0);
        //spinnerCarnet.setSelection(0);

        dui_docente = new ArrayList();
        carnet = new ArrayList();
        //Resumen social
        helper.abrir();
        ArrayList<Resumensocial> itemsSpinnerR = helper.consultarResumen();
        helper.cerrar();
        //Estudiante
        helperE.abrir();
        ArrayList<Estudiante> itemsSpinnerE = helperE.consultarEstudiante();
        helperE.cerrar();
        //Docente
        helperD.abrir();
        ArrayList<Docente> itemsSpinnerD = helperD.consultarDocente();
        helperD.cerrar();

        Resumensocial a;
        Iterator<Resumensocial> it = itemsSpinnerR.iterator();
        while(it.hasNext()){
            a = it.next();
            dui_docente.add(String.valueOf(a.getDui_docente()));
        }
        Estudiante b;
        Iterator<Estudiante> itE = itemsSpinnerE.iterator();
        while(itE.hasNext()){
            b = itE.next();
            carnet.add(String.valueOf(b.getCarnet()));
        }

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dui_docente);
        spinnerDuiDocente.setAdapter(adaptador);
        int aux = dui_docente.indexOf(extraD);
        spinnerDuiDocente.setSelection(aux + 1);

        ArrayAdapter<CharSequence> adaptadorE = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnet);
        spinnerCarnet.setAdapter(adaptadorE);
        int auxE = carnet.indexOf(extraC);
        spinnerCarnet.setSelection(auxE + 1);

        //Validaciones de campos vacios
        spinnerDuiDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0 ){
                    id_d = dui_docente.get(i-1);
                    camp = false;
                }else{
                    camp = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCarnet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0 ){
                    id_c = dui_docente.get(i -1);
                    camp = false;
                }else{
                    camp = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void modificarResumen(View v){
        if(verificarCamposLlenos(camp)){
            Resumensocial resumen = new Resumensocial();
            resumen.setId_resumen(Integer.parseInt(extraID));
            resumen.setDui_docente(id_d);
            resumen.setCarnet(id_c);
            resumen.setFecha_apertura_expediente(txtFechaAE.getText().toString());
            resumen.setFecha_emision_certificado(txtFechaEC.getText().toString());
            resumen.setObservaciones(txtObservaciones.getText().toString());

            helper.abrir();
            String resu = helper.actualizar(resumen);
            helper.cerrar();

            Toast.makeText(this, resu, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarResumenActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarResumen(View v){
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarResumenActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Resumensocial resumen = new Resumensocial();
                        resumen.setId_resumen(Integer.parseInt(extraID));

                        helper.abrir();
                        regEliminadas = helper.eliminar(resumen);
                        helper.cerrar();

                        Toast.makeText(ModificarResumenActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarResumenActivity.this, ConsultarResumenActivity.class);
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
                .setMessage("¿Deseas eliminar el resumen '" + extraID + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();
    }
    public boolean verificarCamposLlenos(boolean cmp){
        if (txtId.getText().toString().isEmpty() || txtId.getText().toString() == null){
            return false;
        }else if(txtFechaAE.getText().toString().isEmpty() || txtFechaAE.getText().toString() == null){
            return false;
        }else if(txtFechaEC.getText().toString().isEmpty() || txtFechaEC.getText().toString() == null){
            return false;
        }else if(txtObservaciones.getText().toString().isEmpty() || txtObservaciones.getText().toString() == null){
            return false;
        }else if(cmp){
            return false;
        }else{
            return true;
        }
    }

}