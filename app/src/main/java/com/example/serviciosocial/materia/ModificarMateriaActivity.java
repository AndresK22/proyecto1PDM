package com.example.serviciosocial.materia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.serviciosocial.R;
import com.example.serviciosocial.areaCarrera.AreaCarrera;
import com.example.serviciosocial.areaCarrera.ControlAreaCarrera;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarMateriaActivity extends AppCompatActivity {

    ControlMateria helper;
    ControlAreaCarrera helper2;
    Spinner spinerArea;
    ArrayList<String> id_area, descrip_area; //para el spinner
    String id_are;
    String extraCod_materia;
    String extraIdArea;
    String extraMateria;

    EditText txtCodMateria;
    EditText txtMateria;

    boolean campAre = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_materia);

        helper = new ControlMateria(this);
        helper2 = new ControlAreaCarrera(this);
        spinerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtCodMateria = (EditText) findViewById(R.id.txtCodMateria);
        txtMateria = (EditText) findViewById(R.id.txtMateria);

        extraCod_materia = getIntent().getExtras().getString("cod_materia");
        extraIdArea = getIntent().getExtras().getString("id_area");
        extraMateria = getIntent().getExtras().getString("nombre_materia");
        txtCodMateria.setText(extraCod_materia);
        txtMateria.setText(extraMateria);

        id_area = new ArrayList<>();
        descrip_area = new ArrayList<>();
        descrip_area.add("Seleccione un area");


        //Aqui se va a pedir el area
        helper2.abrir();
        ArrayList<AreaCarrera> itemsSpinner = helper2.consultarArea();
        helper2.cerrar();

        AreaCarrera are;
        if (itemsSpinner == null){

        }else {
            Iterator<AreaCarrera> it = itemsSpinner.iterator();
            while(it.hasNext()) {
                are = it.next();
                id_area.add(String.valueOf(are.getId_area()));
                descrip_area.add(are.getDescrip_area());
            }
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_area);
        spinerArea.setAdapter(adaptador);
        int aux = id_area.indexOf(extraIdArea);
        spinerArea.setSelection(aux + 1);

        //Validaciones de campos vacios
        spinerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_area.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el update
                    campAre = false;
                } else {
                    campAre = true;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void modificarMateria(View v) {
        if (verificarCamposLlenos(campAre)) {
            Materia materia = new Materia();
            materia.setCod_materia(extraCod_materia);
            materia.setId_area(id_are);
            materia.setNombre_materia(txtMateria.getText().toString());

            helper.abrir();
            String mater = helper.actualizar(materia);
            helper.cerrar();

            Toast.makeText(this, mater, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarMateriaActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarMateria(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarMateriaActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el bot??n positivo, as?? que la acci??n est?? confirmada
                        String regEliminadas;
                        Materia materia = new Materia();
                        materia.setCod_materia(extraCod_materia);

                        helper.abrir();
                        regEliminadas = helper.eliminar(materia);
                        helper.cerrar();

                        Toast.makeText(ModificarMateriaActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarMateriaActivity.this, ConsultarMateriaActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el bot??n negativo, no confirmaron
                        // Simplemente descartamos el di??logo
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El t??tulo
                .setMessage("??Deseas eliminar la materia '" + extraMateria + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtCodMateria.getText().toString().isEmpty() || txtCodMateria.getText().toString() == null){
            return false;
        }else if (txtMateria.getText().toString().isEmpty() || txtMateria.getText().toString() == null) {
            return false;
        }else if (cmpAre) {
            return false;

        }else {
            return true;
        }
    }
}