package com.example.serviciosocial.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.serviciosocial.R;
import com.example.serviciosocial.areaCarrera.AreaCarrera;
import com.example.serviciosocial.areaCarrera.ControlAreaCarrera;
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.carrera.ControlCarrera;
import com.example.serviciosocial.categoria.Categoria;
import com.example.serviciosocial.categoria.ControlCategoria;
import com.example.serviciosocial.docente.ControlDocente;
import com.example.serviciosocial.docente.Docente;
import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.estado.ControlEstado;
import com.example.serviciosocial.estado.Estado;
import com.example.serviciosocial.modalidad.ControlModalidad;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;
import java.util.Iterator;

public class CrearProyectoActivity extends AppCompatActivity {

    //Spinner categoria
    ArrayList<String> id_categoria, nombre_categoria;
    //Spinner modalidad
    ArrayList<String> id_modalidad, nombre_modalidad;
    //Spinner Docente
    ArrayList<String> dui_docente, nombres_docente;
    //Spinner Estado
    ArrayList<String> id_estado, nombre_estado;
    //Spinner Carrera
    ArrayList<String> id_carrera, nombre_carrera;
    //Spinner Area
    ArrayList<String> id_area, descrip_area;

    String id_cate, id_modal, dui_docentes, id_est,id_carr,id_are;

    ControlProyecto helper;
    ControlCategoria helperCat;
    ControlModalidad helperModalidad;
    ControlDocente helperDocente;
    ControlEstado helperEstado;
    ControlCarrera helperCarrera;
    ControlAreaCarrera helperArea;


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

    boolean campMod, campCategoria, campModalidad, campDocente, campEstado,campCarrera,campArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_proyecto);

        helper = new ControlProyecto(this);
        helperCat = new ControlCategoria(this);
        helperModalidad = new ControlModalidad(this);
        helperDocente = new ControlDocente(this);
        helperEstado = new ControlEstado(this);
        helperCarrera = new ControlCarrera(this);
        helperArea = new ControlAreaCarrera(this);

        spCategoria = (Spinner) findViewById(R.id.spCategoria);
        spModalidad = (Spinner) findViewById(R.id.spModalidad);
        spDocente = (Spinner)findViewById(R.id.spDocente);
        spEstado = (Spinner)findViewById(R.id.spEstado);
        spCarrera=  (Spinner)findViewById(R.id.spCarrera);
        spArea  = (Spinner)findViewById(R.id.spArea);
        txtNombreProyecto = (EditText) findViewById(R.id.editTextNombreProyecto);
        txtDescripcion = (EditText) findViewById(R.id.editTextDescripcionProyecto);
        txtLugar = (EditText) findViewById(R.id.editTextLugar);
        txtRequisito = (EditText) findViewById(R.id.editTextRequisito);
        btnGuardar = (Button) findViewById(R.id.btnGuardarProyecto);

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

        helperModalidad.abrir();
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
        //------------Spinner Docente-------------------
        dui_docente = new ArrayList<>();
        nombres_docente = new ArrayList<>();
        nombres_docente.add("Seleccione un docente");

        helperDocente.abrir();
        ArrayList<Docente> itemsSpinnerDoc = helperDocente.consultarDocente();
        helperDocente.cerrar();

        Docente doc;
        Iterator<Docente> itDocente = itemsSpinnerDoc.iterator();
        while(itDocente.hasNext()) {
            doc = itDocente.next();

            dui_docente.add(String.valueOf(doc.getDui_docente()));
            nombres_docente.add(doc.getNombres_docente()+" "+doc.getApellidos_docente());
        }
        ArrayAdapter<CharSequence> adaptadorDoc = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombres_docente);
        spDocente.setAdapter(adaptadorDoc);

        spDocente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    dui_docentes = dui_docente.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                     campDocente = true;
                } else {
                    campDocente= false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //------------Spinner Estado-------------------
        id_estado = new ArrayList<>();
        nombre_estado = new ArrayList<>();
        nombre_estado.add("Seleccione un estado");

        helperEstado.abrir();
        ArrayList<Estado> itemsSpinnerEst = helperEstado.consultarEstados();
        helperEstado.cerrar();

        Estado esta;
        Iterator<Estado> itEstado = itemsSpinnerEst.iterator();
        while(itEstado.hasNext()) {
            esta = itEstado.next();

            id_estado.add(String.valueOf(esta.getId_estado()));
            nombre_estado.add(esta.getEstado());
        }
        ArrayAdapter<CharSequence> adaptadorEst = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_estado);
        spEstado.setAdapter(adaptadorEst);

        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_est = id_estado.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                    campEstado = true;
                } else {
                    campEstado= false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //------------Spinner Carrear-------------------
        id_carrera = new ArrayList<>();
        nombre_carrera = new ArrayList<>();
        nombre_carrera.add("Seleccione una carrera");

        id_area = new ArrayList<>();
        descrip_area = new ArrayList<>();
        descrip_area.add("Seleccione un area");

        helperCarrera.abrir();
        ArrayList<Carrera> itemsSpinnerCarr = helperCarrera.consultarCarrera();
        helperCarrera.cerrar();

        Carrera carr;
        Iterator<Carrera> itCarrera = itemsSpinnerCarr.iterator();
        while(itCarrera.hasNext()) {
            carr = itCarrera.next();

            id_carrera.add(String.valueOf(carr.getId_carrera()));
            nombre_carrera.add(carr.getNombre_carrera());
        }
        ArrayAdapter<CharSequence> adaptadorCarr = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombre_carrera);
        spCarrera.setAdapter(adaptadorCarr);

        spCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i != 0) {
                    id_carr = id_carrera.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                    campCarrera = true;
                } else {
                    campCarrera= false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //------------Spinner Area-------------------


        helperArea.abrir();
        String[] id_car ={String.valueOf(id_carr)};
        ArrayList<AreaCarrera> itemsSpinnerACarr = helperArea.consultarArea();
        helperArea.cerrar();

        AreaCarrera areaC;
        Iterator<AreaCarrera> itACarrera = itemsSpinnerACarr.iterator();
        while(itACarrera.hasNext()) {
            areaC = itACarrera.next();

            id_area.add(String.valueOf(areaC.getId_area()));
            descrip_area.add(areaC.getDescrip_area());
        }
        ArrayAdapter<CharSequence> adaptadorACarr = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, descrip_area);
        spArea.setAdapter(adaptadorACarr);

        spArea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    id_are = id_area.get(i - 1); //Guarda el id seleccionado en la variable global, para usarla en el create
                    campArea = true;
                } else {
                    campArea= false;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
    public void guardarProyecto(View v) {
        if (verificarCamposLlenos()) {
            String nombreProyecto = txtNombreProyecto.getText().toString();
            String descripcionProyecto = txtDescripcion.getText().toString();
            String lugar = txtLugar.getText().toString();
            double requisito = Double.parseDouble(txtRequisito.getText().toString());
            String regInsertados;

            Proyecto pro = new Proyecto();
            pro.setId_categoria(Integer.parseInt(id_cate));
            pro.setId_modalidad(Integer.parseInt(id_modal));
            pro.setDui_docente(dui_docentes);
            pro.setId_estado(Integer.parseInt(id_est));
            pro.setId_carrera(id_carr);
            pro.setId_area(id_are);
            pro.setNombre_proyecto(nombreProyecto);
            pro.setDescripcion_proyecto(descripcionProyecto);
            pro.setLugar(lugar);
            pro.setRequisito_nota(requisito);


            helper.abrir();
            regInsertados = helper.insertar(pro);
            helper.cerrar();

            Toast.makeText(this, regInsertados, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, ConsultarProyectoActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Debe llenar el campo", Toast.LENGTH_LONG).show();
        }
    }

    public boolean verificarCamposLlenos() {
        if (txtNombreProyecto.getText().toString().isEmpty() || txtNombreProyecto.getText().toString() == null || txtDescripcion.getText().toString().isEmpty() || txtDescripcion.getText().toString() == null || txtLugar.getText().toString().isEmpty() || txtLugar.getText().toString() == null ||txtRequisito.getText().toString().isEmpty() || txtRequisito.getText().toString() == null) {
            //Si esta vacio devuelve falso
            return false;
        }else {
            return true;
        }
    }
}


