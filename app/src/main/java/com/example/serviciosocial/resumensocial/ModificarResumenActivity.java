package com.example.serviciosocial.resumensocial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.serviciosocial.R;
import com.example.serviciosocial.areaCarrera.AreaCarrera;
import com.example.serviciosocial.areaCarrera.ConsultarAreaActivity;
import com.example.serviciosocial.areaCarrera.ModificarAreaActivity;
import com.example.serviciosocial.docente.Docente;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarResumenActivity extends AppCompatActivity {

    ControlResumenSocial helper;
    Spinner spinnerDuiDocente;
    Spinner spinnerCarnet;
    ArrayList<String> dui_docente, nombres_docente, apellidos_docente, email_docente, telefono_docente; // Para el spinner de docente
    ArrayList<String> carnetEstudiante, nombres_estudiante, apellidos_estudiante, email_estudiante, telefono_estudiante, domicilio, dui;
    String  extraDui_resumen, extraCarnet_resumen, extraAperturaR, extraEmisionR, extraObservacionesR;

    int extraId_resumen, id_resumen;
    String duiD;

    EditText txtIdResumen, txtFechaAE, txtFechaE, txtObservaciones;

    boolean campDoc=false, campEst=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_resumen);

        helper = new ControlResumenSocial(this);
        //SPINNERS
        spinnerDuiDocente = (Spinner) findViewById(R.id.spinnerDui_docente);
        spinnerCarnet = (Spinner) findViewById(R.id.spinnerCarnet);

        extraId_resumen = getIntent().getExtras().getInt("id_resumen");
        extraDui_resumen = getIntent().getExtras().getString("dui_docente");
        extraCarnet_resumen = getIntent().getExtras().getString("carnet");
        extraAperturaR = getIntent().getExtras().getString("fecha_apertura_expediente");
        extraEmisionR = getIntent().getExtras().getString("fecha_emision_certificado");
        extraObservacionesR = getIntent().getExtras().getString("observaciones");

        //Array para spinner docente
        dui_docente = new ArrayList<>();
        nombres_docente = new ArrayList<>();
        apellidos_docente = new ArrayList<>();
        email_docente = new ArrayList<>();
        telefono_docente = new ArrayList<>();
        nombres_docente.add("Seleccione el docente");
        //Array para spinner carnet
        carnetEstudiante = new ArrayList<>();
        nombres_estudiante = new ArrayList<>();
        apellidos_estudiante = new ArrayList<>();
        email_estudiante = new ArrayList<>();
        telefono_estudiante = new ArrayList<>();
        domicilio = new ArrayList<>();
        dui = new ArrayList<>();
        carnetEstudiante.add("Seleccione el carnet");
        //Pedir Docentes
        helper.abrir();
        ArrayList<Resumensocial> itemsSpinnerDocente = helper.consultarResumen();
        helper.cerrar();

        //Pedir Carnet
        helper.abrir();
        ArrayList<Resumensocial> itemsSpinnerCarnet = helper.consultarResumen();
        helper.cerrar();

        //Crear el objeto Docente
        Resumensocial r;
        Iterator<Resumensocial> it = itemsSpinnerDocente.iterator();
        while(it.hasNext()){
            r = it.next();
            //Dui de la tabla docente
            dui_docente.add(String.valueOf(r.getId_resumen()));
            //Nombre docente r.getNombreDocente
            nombres_docente.add(String.valueOf(r.getId_resumen()));

        }
        ArrayAdapter<CharSequence> adaptadorD = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombres_docente);
        spinnerDuiDocente.setAdapter(adaptadorD);
        int aux = dui_docente.indexOf(extraDui_resumen);
        spinnerDuiDocente.setSelection(aux + 1);

        //Validaciones de campos vacios
        spinnerDuiDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0 ){
                    duiD = dui_docente.get(i -1); //Guarda el dui del docente seleccionado
                    campDoc = false;
                }else{
                    campDoc = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //Crear el objeto Estudiante
        Resumensocial e;
        Iterator<Resumensocial> itE = itemsSpinnerCarnet.iterator();
        while(itE.hasNext()){
            e = itE.next();
            //Carnet del estudiante
            carnetEstudiante.add(String.valueOf(e.getCarnet()));
            //Nombre del estudiante
            nombres_estudiante.add(String.valueOf(e.getCarnet()));
        }
        ArrayAdapter<CharSequence> adaptadorE = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombres_estudiante);
        spinnerCarnet.setAdapter(adaptadorE);
        int aux1 = carnetEstudiante.indexOf(extraCarnet_resumen);
        spinnerDuiDocente.setSelection(aux + 1);

        //Validaciones de campos vacios
        spinnerDuiDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0 ){
                    duiD = dui_docente.get(i -1); //Guarda el dui del docente seleccionado
                    campDoc = false;
                }else{
                    campDoc = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void modificarResumen(View v){
        if(verificarCamposLlenos(campDoc, campEst)){
            Resumensocial resumen = new Resumensocial();
            resumen.setId_resumen(extraId_resumen);
            resumen.setDui_docente(extraDui_resumen);
            resumen.setCarnet(extraCarnet_resumen);
            resumen.setFecha_apertura_expediente(extraAperturaR);
            resumen.setFecha_emision_certificado(extraEmisionR);
            resumen.setObservaciones(extraObservacionesR);

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
                        resumen.setId_resumen(extraId_resumen);

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
                .setMessage("¿Deseas eliminar el resumen '" + extraId_resumen + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();
    }
    public boolean verificarCamposLlenos(boolean cmp1, boolean cmp2){
        if (txtIdResumen.getText().toString().isEmpty() || txtIdResumen.getText().toString() == null){
            return false;
        }else if(txtFechaAE.getText().toString().isEmpty() || txtFechaAE.getText().toString() == null){
            return false;
        }else if(txtFechaE.getText().toString().isEmpty() || txtFechaE.getText().toString() == null){
            return false;
        }else if(cmp1 || cmp2){
            return false;
        }else{
            return true;
        }
    }

}