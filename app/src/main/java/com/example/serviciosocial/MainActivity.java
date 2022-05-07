package com.example.serviciosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.serviciosocial.estado.ConsultarEstadoActivity;
import com.example.serviciosocial.materia.ConsultarMateriaActivity;

public class MainActivity extends AppCompatActivity {

    Button botonPrueba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.deleteDatabase("proyecto.s3db");

        botonPrueba = findViewById(R.id.button);
        botonPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConsultarMateriaActivity.class);
                startActivity(intent);
            }
        });
    }
}