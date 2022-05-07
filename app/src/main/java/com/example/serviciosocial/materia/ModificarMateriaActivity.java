package com.example.serviciosocial.materia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;
import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.estado.Estado;
import com.example.serviciosocial.estado.ModificarEstadoActivity;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarMateriaActivity extends AppCompatActivity {

    ControlBD helper;
    Spinner spinerArea;
    ArrayList<String> id_area, descrip_area; //para el spinner
    String id_are;
    String extraCod_materia;
    String extraIdArea;
    String extraMateria;

    EditText txtCodMateria;
    EditText txtMateria;
    Button btnGuardar;

    boolean campCod, campAre, campMater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_materia);

        helper = new ControlBD(this);
        spinerArea = (Spinner) findViewById(R.id.spinnerArea);
        txtCodMateria = (EditText) findViewById(R.id.txtCodMateria);
        txtMateria = (EditText) findViewById(R.id.txtMateria);
        btnGuardar = (Button) findViewById(R.id.btnGuardarMateria);

        extraCod_materia = getIntent().getExtras().getString("cod_materia");
        extraIdArea = getIntent().getExtras().getString("id_area");
        extraMateria = getIntent().getExtras().getString("nombre_materia");
        txtCodMateria.setText(extraCod_materia);
        txtMateria.setText(extraMateria);

        campCod = true;
        campAre = true;
        campMater = true;

        id_area = new ArrayList<>();
        descrip_area = new ArrayList<>();
        descrip_area.add("Seleccione un area");


        //Aqui se va a pedir el area
        helper.abrir();
        ArrayList<Materia> itemsSpinner = helper.consultarMateria();
        helper.cerrar();

        Materia mat;
        Iterator<Materia> it = itemsSpinner.iterator();
        while(it.hasNext()) {
            mat = it.next();

            id_area.add(String.valueOf(mat.getId_area()));
            descrip_area.add(mat.getNombre_materia());
        }
        //fin de lo del area


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_area);
        spinerArea.setAdapter(adaptador);
        int aux = id_area.indexOf(extraIdArea);
        spinerArea.setSelection(aux + 1);

        //Validaciones de campos vacios
        txtCodMateria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campCod = true;
                }else{
                    campCod = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinerArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_area.get(i - 1); //Guarda el id del area seleccionada en la variable global, para usarla en el update
                    campAre = true;
                } else {
                    campAre = false;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        txtMateria.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int before, int count) {
                if (count>0){ //count es cantidad de caracteres que tiene
                    campMater = true;
                }else{
                    campMater = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void modificarMateria(View v) {
        if (verificarCamposLlenos(campCod, campAre, campMater)) {
            Materia materia = new Materia();
            materia.setCod_materia(txtCodMateria.getText().toString());
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
                        // Hicieron click en el botón positivo, así que la acción está confirmada
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
                        // Hicieron click en el botón negativo, no confirmaron
                        // Simplemente descartamos el diálogo
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar la materia '" + extraMateria + "'?") // El mensaje
                .create();// crea el AlertDialog


        dialogo.show();

    }


    public boolean verificarCamposLlenos(boolean cmpCod, boolean cmpAre, boolean cmpMater) {
        if (cmpCod & cmpAre & cmpMater) {
            //Si los 3 campos son verdadero (tienen contenido) devuelve verdadero
            return true;
        }else {
            return false;
        }
    }
}