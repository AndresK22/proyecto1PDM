package com.example.serviciosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.materia.ConsultarMateriaActivity;
import com.example.serviciosocial.nota.ConsultarNotaActivity;
import com.example.serviciosocial.recordAcademico.ConsultarRecordActivity;

public class MainActivity extends AppCompatActivity {

    Button botonPrueba;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase("proyecto.s3db");

        preferences = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editor = preferences.edit();

        botonPrueba = findViewById(R.id.button);
        botonPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsultarEstadoActivity.class);
                startActivity(intent);
            }
        });
    }

}