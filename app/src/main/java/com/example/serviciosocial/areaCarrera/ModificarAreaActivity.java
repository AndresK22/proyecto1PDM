package com.example.serviciosocial.areaCarrera;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.ControlCarrera;

import java.util.ArrayList;
import java.util.Iterator;

public class ModificarAreaActivity extends AppCompatActivity {

    ControlAreaCarrera helper;
    ControlCarrera helper1;
    Spinner spinerCarrera;
    ArrayList<String> id_carrera, nombre_carrera; //para el spinner de Carrera
    String id_are;

    String extraIdCarrera;
    String extraIdArea;
    String extraDe;

    EditText txtIDArea;
    EditText txtDescrip;

    boolean campAre = false; //True si no se ha seleccionado nada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_area);

        helper = new ControlAreaCarrera(this);
        helper1 = new ControlCarrera(this);
        spinerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        txtIDArea = (EditText) findViewById(R.id.txtAreaCarrera);
        txtDescrip = (EditText) findViewById(R.id.txtdescripArea);


        extraIdArea = getIntent().getExtras().getString("id_area");
        extraIdCarrera = getIntent().getExtras().getString("id_carrera");
        extraDe = getIntent().getExtras().getString("descrip_area");
        txtIDArea.setText(extraIdArea);
        txtDescrip.setText(extraDe);

        id_carrera = new ArrayList<>();
        nombre_carrera = new ArrayList<>();
        nombre_carrera.add("Seleccione la carrera");


        //Aqui se va a pedir la carrera
        helper1.abrir();
        ArrayList<Carrera> Text = helper1.consultarCarrera();
        helper1.cerrar();

        //Crear el objeto de Carrera
        Cursor cursor = helper1.leerTodoCarrera();
        if (cursor.getCount()==0){

        }else{
            Carrera a;
            Iterator<Carrera> it = Text.iterator();
            while(it.hasNext()) {
                a = it.next();
                id_carrera.add(String.valueOf(a.getId_carrera()));
                nombre_carrera.add(a.getNombre_carrera());
            }}
        //fin de lo del area

        //fin de lo de carrera


        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_carrera);
        spinerCarrera.setAdapter(adaptador);
        int aux = id_carrera.indexOf(extraIdCarrera);
        spinerCarrera.setSelection(aux + 1);

        //Validaciones de campos vacios
        spinerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_carrera.get(i - 1); //Guarda el id del carrera seleccionada en la variable global, para usarla en el updage
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

    public void modificarArea(View v) {
        if (verificarCamposLlenos(campAre)) {
            AreaCarrera areaCarrera = new AreaCarrera();
            String x = extraIdArea;
            String[] ex={x};
            areaCarrera.setId_area(txtIDArea.getText().toString());
            areaCarrera.setId_carrera(id_are);
            areaCarrera.setDescrip_area(txtDescrip.getText().toString());

            helper.abrir();
            String mater = helper.actualizar(areaCarrera,ex);
            helper.cerrar();

            Toast.makeText(this, mater, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarAreaActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_LONG).show();
        }

    }

    public void eliminarArea(View v) {
        //Confirmacion de eliminacion
        AlertDialog dialogo = new AlertDialog
                .Builder(ModificarAreaActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hicieron click en el bot??n positivo, as?? que la acci??n est?? confirmada
                        String regEliminadas;
                        AreaCarrera areaCarrera = new AreaCarrera();
                        areaCarrera.setId_area(extraIdArea);

                        helper.abrir();
                        regEliminadas = helper.eliminar(areaCarrera);
                        helper.cerrar();

                        Toast.makeText(ModificarAreaActivity.this, regEliminadas, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarAreaActivity.this, ConsultarAreaActivity.class);
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
                .setMessage("??Deseas eliminar el area '" + extraDe + "'?") // El mensaje
                .create();// crea el AlertDialog

        dialogo.show();

    }


    public boolean verificarCamposLlenos(boolean cmpAre) {
        if (txtIDArea.getText().toString().isEmpty() || txtIDArea.getText().toString() == null){
            return false;
        }else if (txtDescrip.getText().toString().isEmpty() || txtDescrip.getText().toString() == null) {
            return false;
        }else if (cmpAre) {
            return false;

        }else {
            return true;
        }
    }
}