package com.example.serviciosocial.estudianteWS;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;
import com.example.serviciosocial.docenteWS.ControladorServicioDocente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ConsultarEstudianteActivityWS extends AppCompatActivity {

    static List<Estudiante> listaEstudiantes;
    static List<String> nombreEstudiante;
    EditText txtCarnet;
    ListView listViewEsudiante;
    FloatingActionButton add_button;

    private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//consultar_estudiante.php";
    private final String urlHostingGratuito2 = "https://cc19114pdm115.000webhostapp.com//consultar_estudiante_all.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estudiante_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        listaEstudiantes = new ArrayList<Estudiante>();
        nombreEstudiante = new ArrayList<String>();
        txtCarnet = (EditText) findViewById(R.id.buscarCarnet);
        listViewEsudiante = (ListView) findViewById(R.id.listView2);
        add_button = findViewById(R.id.agregarEstudiante);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarEstudianteActivityWS.this, IngresarEstudianteActivityWS.class);
                startActivity(intent);
            }
        });


    }

    public void consultarPorCarnet(View v) {
        String carnet = txtCarnet.getText().toString().trim().replace(" ","%20");;

        String url = "";
        url = urlHostingGratuito + "?carnet="+carnet;

        String estudiantes = ControladorServicioDocente.obtenerRespuestaPeticion(url, this);
        try {
            listaEstudiantes.clear();
            listaEstudiantes.addAll(ControladorServicioEstudiante.obtenerEstudianteExterno(estudiantes, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultarEstudiantes(View v) {
        String url = "";
        url = urlHostingGratuito2;
        String estudiantes = ControladorServicioEstudiante.obtenerRespuestaPeticion(url, this);
        try {
            listaEstudiantes.clear();
            listaEstudiantes.addAll(ControladorServicioEstudiante.obtenerEstudianteExternoAll(estudiantes, this));
            actualizarListView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarListView() {
        String dato = "";
        nombreEstudiante.clear();
        for (int i = 0; i < listaEstudiantes.size(); i++) {
            dato = "Carnet:  " + listaEstudiantes.get(i).getCarnet() +
                    "\nNombres:  " + listaEstudiantes.get(i).getNombres_estudiante() + "     \nApellidos:  " + listaEstudiantes.get(i).getApellidos_estudiante() +
                    "\nEmail:  " + listaEstudiantes.get(i).getEmail_estudiante()+"     \nTelefono:  " + listaEstudiantes.get(i).getTelefono_estudiante() +
                    "\nDomicilio:   " + listaEstudiantes.get(i).getDomicilio()+ "       DUI:  " + listaEstudiantes.get(i).getDui() +"\n\n";
            nombreEstudiante.add(dato);
        }
        eliminarElementosDuplicados();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombreEstudiante);
        listViewEsudiante.setAdapter(adaptador);
    }

    private void eliminarElementosDuplicados() {
        HashSet<Estudiante> conjuntoMateria = new HashSet<Estudiante>();
        conjuntoMateria.addAll(listaEstudiantes);
        listaEstudiantes.clear();
        listaEstudiantes.addAll(conjuntoMateria);
        HashSet<String> conjuntoNombre = new HashSet<String>();
        conjuntoNombre.addAll(nombreEstudiante);
        nombreEstudiante.clear();
        nombreEstudiante.addAll(conjuntoNombre);
    }

    public void ClearData() {
        listViewEsudiante.setAdapter(null);
    }
}