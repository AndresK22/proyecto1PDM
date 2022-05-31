package com.example.serviciosocial.estudianteWS;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;


public class EliminarEstudianteActivityWS extends AppCompatActivity {

    EditText txtCarnet;

    private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//eliminar_estudiante.php";
    private final String urlHostingGratuito2 = "https://cc19114pdm115.000webhostapp.com//eliminar_estudiante_all.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_estudiante_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtCarnet = (EditText) findViewById(R.id.eliminarCarnet);


    }
    public void eliminarPorCarnet(View v) {
        String c = txtCarnet.getText().toString().trim().replace(" ","%20");;
        String url = "";
        url = urlHostingGratuito + "?carnet=" +c;

        int i = ControladorServicioEstudiante.eliminarEstudiante(url, this);
        if (i == 1) {
            Intent intent = new Intent(this, ConsultarEstudianteActivityWS.class);
            startActivity(intent);
        }
    }

    public void eliminarEstudianteTodos(View v) {
        String url = "";
        url = urlHostingGratuito2;
        int i = ControladorServicioEstudiante.eliminarEstudianteAll(url, this);
        if (i == 1) {
            Intent intent = new Intent(this, ConsultarEstudianteActivityWS.class);
            startActivity(intent);
        }
    }

}