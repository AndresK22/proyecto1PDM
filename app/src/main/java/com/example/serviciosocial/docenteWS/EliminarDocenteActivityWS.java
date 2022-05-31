package com.example.serviciosocial.docenteWS;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;


public class EliminarDocenteActivityWS extends AppCompatActivity {


        EditText txtDui;
        private final String urlHostingGratuito = "https://cc19114pdm115.000webhostapp.com//eliminar_docente.php";
        private final String urlHostingGratuito2 = "https://cc19114pdm115.000webhostapp.com//eliminar_docente_all.php";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_docente_ws);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        txtDui = (EditText) findViewById(R.id.eliminarDUI);


    }
        public void eliminarPorDui(View v) {
        String dui = txtDui.getText().toString().trim().replace(" ","%20");;
        String url = "";
        url = urlHostingGratuito + "?dui_docente=" + dui;

            int i = ControladorServicioDocente.eliminarDocente(url, this);
            if (i == 1) {
                Intent intent = new Intent(this, ConsultarDocenteActivityWS.class);
                startActivity(intent);
            }
    }

        public void eliminarDocentesTodos(View v) {
            String url = "";
            url = urlHostingGratuito2;
            int i = ControladorServicioDocente.eliminarDocenteAll(url, this);
            if (i == 1) {
                Intent intent = new Intent(this, ConsultarDocenteActivityWS.class);
                startActivity(intent);
            }
    }



}