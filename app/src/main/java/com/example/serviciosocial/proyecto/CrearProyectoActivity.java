package com.example.serviciosocial.proyecto;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import com.example.serviciosocial.R;
import com.example.serviciosocial.categoria.Categoria;
import com.example.serviciosocial.categoria.ControlCategoria;
import com.example.serviciosocial.modalidad.ControlModalidad;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearProyectoActivity extends AppCompatActivity {

    //Spinner categoria
    ArrayList<String> id_categoria, nombre_categoria;
    //Spinner modalidad
    ArrayList<String> id_modalidad, nombre_modalidad;


    String id_cate, id_modal;

    ControlProyecto helper;
    ControlCategoria helperCat;
    ControlModalidad helperModalidad;

    Spinner spCategoria;
    Spinner spModalidad;
    Spinner spDocente;
    Spinner spEstado;
    Spinner spCarrera;
    Spinner spArea;

    EditText txtNombreProyecto;
    EditText txtDescripcion;
    EditText txtLugar;
    EditText txtRequisito;

    Button btnGuardar;

    boolean campMod, campCategoria, campModalidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        helper = new ControlProyecto(this);
        helperCat = new ControlCategoria(this);
        helperModalidad = new ControlModalidad(this);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spModalidad = (Spinner) findViewById(R.id.spModalidad);

        btnGuardar = (Button) findViewById(R.id.btnGuardarModalidad);

        //------------Spinner Categoria-------------------
        id_categoria = new ArrayList<>();
        nombre_categoria = new ArrayList<>();
        nombre_categoria.add("Seleccione una categoría");

        helperCat.abrir();
        ArrayList<Categoria> itemsSpinnerCat = helperCat.consultarCategoria();
        helperCat.cerrar();

        Categoria cat;
        Iterator<Categoria> it = itemsSpinnerCat.iterator();
        while(it.hasNext()) {
            cat = it.next();

            id_categoria.add(String.valueOf(cat.getId_categoria()));
            nombre_categoria.add(cat.getNombre_categoria());
        }
        ArrayAdapter<CharSequence> adaptadorCat = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_categoria);
        spCategoria.setAdapter(adaptadorCat);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_cate = id_categoria.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                    campCategoria = true;
                } else {
                    campCategoria = false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //------------Spinner Modalidades-------------------
        id_modalidad = new ArrayList<>();
        nombre_modalidad = new ArrayList<>();
        nombre_modalidad.add("Seleccione una modalidad");

        helperModalidad.abrirParaLeer();
        ArrayList<Modalidad> itemsSpinnerMod = helperModalidad.consultarModalidad();
        helperModalidad.cerrar();

        Modalidad mod;
        Iterator<Modalidad> itModalidad = itemsSpinnerMod.iterator();
        while(itModalidad.hasNext()) {
            mod = itModalidad.next();

            id_modalidad.add(String.valueOf(mod.getId_modalidad()));
            nombre_modalidad.add(mod.getNombre_modalidad());
        }
        ArrayAdapter<CharSequence> adaptadorMod = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_modalidad);
        spModalidad.setAdapter(adaptadorMod);

        spModalidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_modal = id_modalidad.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                    campModalidad = true;
                } else {
                    campModalidad= false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
}


