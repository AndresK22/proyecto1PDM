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
import com.example.serviciosocial.estudiante.ControlEstudiante;
import com.example.serviciosocial.estudiante.Estudiante;
import com.example.serviciosocial.proyecto.ControlProyecto;
import com.example.serviciosocial.proyecto.Proyecto;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarBitacoraActivity extends AppCompatActivity {


    ControlBitacora helper;
    ControlEstudiante helper1;
    ControlProyecto helper2;
    Spinner spinerProyecto,spinerCarnet;
    ArrayList<String> id_proyecto, carnet, nombre_proyecto; //para el spinner
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
        helper1 = new ControlEstudiante(this);
        helper2 = new ControlProyecto(this);
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
        nombre_proyecto = new ArrayList<>();
        nombre_proyecto.add("Seleccione el nombre del proyecto");

        carnet= new ArrayList<>();
        carnet.add("Seleccione el carnet del estudiante");

        //nombre_carrera = new ArrayList<>();
        //nombre_carrera.add("Seleccione la carrera");


        //Aqui se va a pedir el proyecto
        /* helper.abrir();
        ArrayList<Proyecto> itemsSpinner = helper.consultarProyecto();
        helper.cerrar();
        *
        * */
        helper2.abrir();
        ArrayList<Proyecto> itemsSpinner1 = helper2.consultarProyecto();
        helper2.cerrar();

        helper1.abrir();
        ArrayList<Estudiante> itemsSpinner2 = helper1.consultarEstudiante();
        helper1.cerrar();


        Proyecto a;
        Iterator<Proyecto> it = itemsSpinner1.iterator();
        while(it.hasNext()) {
            a = it.next();

            id_proyecto.add(String.valueOf(a.getId_proyecto()));
            nombre_proyecto.add(a.getNombre_proyecto());
        }

        Estudiante b;
        Iterator<Estudiante> i = itemsSpinner2.iterator();
        while(i.hasNext()) {
            b = i.next();

            carnet.add(String.valueOf(b.getCarnet()));
            //nombre_carrera.add(a.getDescrip_area());
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_proyecto);
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
                    id_c = carnet.get(i);
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
            String x = extraID;
            String[] ex={x};
            bitacora.setId_bitacora(Long.parseLong(txtID.getText().toString()));
            bitacora.setId_proyecto(Long.parseLong(id_p));
            bitacora.setCarnet(id_c);
            bitacora.setMes(txtMes.getText().toString());
            bitacora.setTotal_horas_realizadas(Float.parseFloat(txtTotal.getText().toString()));

            helper.abrir();
            String mater = helper.actualizar(bitacora,ex);
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