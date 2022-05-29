package com.example.serviciosocial.bitacora;

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

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarBitacoraActivity extends AppCompatActivity {


    ControlBitacora helper;
    Spinner spinerProyecto,spinerCarnet;
    ArrayList<String> id_proyecto, carnet; //para el spinner
    String id_p,id_c;

    String extraID;
    String extraP;
    String extraC;
    String extraM;
    String extraT;

    EditText txtID;
    EditText txtMes;
    EditText txtTotal;
    Button btnGuardar;

    boolean camp = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_bitacora);

        helper = new ControlBitacora(this);
        spinerProyecto = (Spinner) findViewById(R.id.spinnerProyectoM);
        spinerCarnet= (Spinner) findViewById(R.id.spinnerCarnetM);

        txtID = (EditText) findViewById(R.id.txt_id_bitacoraM);
        txtMes = (EditText) findViewById(R.id.txtMesM);
        txtTotal = (EditText) findViewById(R.id.txt_total_horasM);


        extraID = getIntent().getExtras().getString("id_bitacora");
        extraP = getIntent().getExtras().getString("id_proyecto");
        extraC = getIntent().getExtras().getString("carnet");
        extraM = getIntent().getExtras().getString("mes");
        extraT = getIntent().getExtras().getString("total_horas_realizadas");

        txtID.setText(extraID);
        txtMes.setText(extraM);
        txtTotal.setText(extraT);
        spinerProyecto.setSelection(0);


        id_proyecto = new ArrayList<>();
        // nombre_carrera = new ArrayList<>();
        //nombre_carrera.add("Seleccione la carrera");

        carnet= new ArrayList<>();
        //nombre_carrera = new ArrayList<>();
        //nombre_carrera.add("Seleccione la carrera");


        //Aqui se va a pedir el proyecto
        /* helper.abrir();
        ArrayList<Proyecto> itemsSpinner = helper.consultarProyecto();
        helper.cerrar();
        *
        * */
        helper.abrir();
        ArrayList<Bitacora> itemsSpinner = helper.consultarBitacora();
        helper.cerrar();

        Bitacora a;
        Iterator<Bitacora> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            a = it.next();

            id_proyecto.add(String.valueOf(a.getId_proyecto()));
            //nombre_carrera.add(a.getDescrip_area());
        }

        Bitacora b;
        Iterator<Bitacora> i = itemsSpinner.iterator();
        while(i.hasNext()) {
            b = i.next();

            carnet.add(String.valueOf(b.getCarnet()));
            //nombre_carrera.add(a.getDescrip_area());
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, id_proyecto);
        spinerProyecto.setAdapter(adaptador);
        int aux = id_proyecto.indexOf(extraP);
        spinerProyecto.setSelection(aux);

        ArrayAdapter<CharSequence> adaptador1 = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, carnet);
        spinerCarnet.setAdapter(adaptador1);
        int aux1 = carnet.indexOf(extraC);
        spinerCarnet.setSelection(aux1);

        //Validaciones de campos vacios
        spinerProyecto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_p = id_proyecto.get(i-1);
                    //Guarda el id del area seleccionada en la variable global, para usarla en el create
                    camp = false;
                } else {
                    camp = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinerCarnet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_c = carnet.get(i-1);
                    //Guarda el id del carnet seleccionada en la variable global, para usarla en el create
                    camp = false;
                } else {
                    camp = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void modificarBitacora(View v) {
        if (verificarCamposLlenos(camp)) {
            Bitacora bitacora = new Bitacora();
            bitacora.setId_bitacora(Long.parseLong(extraID));
            bitacora.setId_proyecto(Long.parseLong(id_p));
            bitacora.setCarnet(id_c);
            bitacora.setMes(txtMes.getText().toString());
            bitacora.setTotal_horas_realizadas(Float.parseFloat(txtTotal.getText().toString()));

            helper.abrir();
            String mater = helper.actualizar(bitacora);
            helper.cerrar();

            Toast.makeText(this, mater, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultaBitacoraActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarBitacora(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarBitacoraActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el botón positivo, así que la acción está confirmada
                        String regEliminadas;
                        Bitacora bitacora = new Bitacora();
                        bitacora.setId_bitacora(Long.parseLong(extraID));

                        helper.abrir();
                        regEliminadas = helper.eliminar(bitacora);
                        helper.cerrar();

                        Toast.makeText(ModificarBitacoraActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarBitacoraActivity.this, ConsultaBitacoraActivity.class);
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
                .setMessage("¿Deseas eliminar la bitacora '" + extraID + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }

    public boolean verificarCamposLlenos(boolean camp) {
        if (txtID.getText().toString().isEmpty() || txtID.getText().toString() == null){
            return false;
        }else if (txtMes.getText().toString().isEmpty() || txtMes.getText().toString() == null) {
            return false;
        }else if (camp) {
            return false;

        }else {
            return true;
        }
    }
}