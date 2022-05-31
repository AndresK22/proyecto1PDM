package com.example.serviciosocial.docenteWS;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConsultarDocenteActivityWS extends AppCompatActivity {


    static List<Docente> listaDocentes;
    static List<String> nombreDocente;
    EditText txtDui;
    ListView lisViewDocente;
    FloatingActionButton add_button,delete;

    private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//consultar_docente.php";
    private final String urlHostingGratuito2 = "https://cc19114pdm115.000webhostapp.com//consultar_docente_all.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_docente_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listaDocentes = new ArrayList<Docente>();
        nombreDocente = new ArrayList<String>();
        txtDui = (EditText) findViewById(R.id.editText_fecha);
        lisViewDocente = (ListView) findViewById(R.id.listView1);
        add_button = findViewById(R.id.addBut);
        delete = findViewById(R.id.addBut2);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarDocenteActivityWS.this, IngresarDocenteActivityWS.class);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarDocenteActivityWS.this, EliminarDocenteActivityWS.class);
                startActivity(intent);
            }
        });


    }
    public void consultarPorDui(View v) {
        String dui = txtDui.getText().toString().trim().replace(" ","%20");;
        String url = "";
        url = urlHostingGratuito + "?dui_docente=" + dui;

        String materiasExternas = ControladorServicioDocente.obtenerRespuestaPeticion(url, this);
        try {
            listaDocentes.clear();
            listaDocentes.addAll(ControladorServicioDocente.obtenerDocenteExterno(materiasExternas, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarDocentes(View v) {
        String url = "";
        url = urlHostingGratuito2;
        String docentesExternos = ControladorServicioDocente.obtenerRespuestaPeticion(url, this);
        try {
            listaDocentes.clear();
            listaDocentes.addAll(ControladorServicioDocente.obtenerDocenteExternoAll(docentesExternos, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void actualizarListView() {
        String dato = "";
        nombreDocente.clear();
        for (int i = 0; i < listaDocentes.size(); i++) {
            dato = "DUI:  "+ listaDocentes.get(i).getDui_docente() +
                    "\n\nNombres:  " +listaDocentes.get(i).getNombres_docente()+"       Apellidos:  "+listaDocentes.get(i).getApellidos_docente()+
            "\n\nEmail:  "+listaDocentes.get(i).getEmail_docente()+
            "\n\nTelefono:   "+listaDocentes.get(i).getTelefono_docente()+"\n\n";
            nombreDocente.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nombreDocente);
        lisViewDocente.setAdapter(adaptador);
    }
    private void eliminarElementosDuplicados() {
        HashSet<Docente> conjuntoMateria = new HashSet<Docente>();
        conjuntoMateria.addAll(listaDocentes);
        listaDocentes.clear();
        listaDocentes.addAll(conjuntoMateria);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreDocente);
        nombreDocente.clear();
        nombreDocente.addAll(conjuntoNombre);
    }
public void ClearData(){
    lisViewDocente.setAdapter(null);
}

}