package com.example.serviciosocial.estado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarEstadoActivity extends AppCompatActivity {

    RecyclerView recyclerViewEstado;
    FloatingActionButton add_button;
    ControlBD helper;
    ArrayList<String> id_estado, estado;
    com.example.serviciosocial.estado.EstadoAdaptador estadoAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estado);

        helper = new ControlBD(this);
        id_estado = new ArrayList<>();
        estado = new ArrayList<>();
        recyclerViewEstado = findViewById(R.id.recyclerViewEstado);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarEstadoActivity.this, CrearEstadoActivity.class);
                startActivity(intent);
            }
        });

        consultarEstado();

        estadoAdaptador = new com.example.serviciosocial.estado.EstadoAdaptador(ConsultarEstadoActivity.this,this,id_estado,estado);
        recyclerViewEstado.setAdapter(estadoAdaptador);
        recyclerViewEstado.setLayoutManager(new LinearLayoutManager(ConsultarEstadoActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }


    public void consultarEstado(){
        helper.abrir();
        ArrayList<Estado> registros = helper.consultarEstados();
        helper.cerrar();

        Estado estad;
        Iterator<Estado> it = registros.iterator();
        while(it.hasNext()) {
            estad = it.next();

            id_estado.add(String.valueOf(estad.getId_estado()));
            estado.add(estad.getEstado());
        }
    }
}