package com.example.serviciosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonPrueba;
    ControlBD controlBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlBD = new ControlBD(this);
        controlBD.abrir();
        controlBD.llenarBD();



        botonPrueba = findViewById(R.id.button);
        botonPrueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearEstudianteActivity.class);
                startActivity(intent);
            }
        });
    }
}