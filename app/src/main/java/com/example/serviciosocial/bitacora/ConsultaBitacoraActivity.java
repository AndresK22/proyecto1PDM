package com.example.serviciosocial.bitacora;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultaBitacoraActivity extends AppCompatActivity {
    RecyclerView recyclerViewBitacora;
    FloatingActionButton add_button;
    ControlBitacora helper;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<String> id_bitacora, id_proyecto, carnet, mes, total_horas_realizadas;
    BitacoraAdaptadorActivity bitacoraAdaptadorActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_bitacora);

        helper = new ControlBitacora(this);
        id_bitacora = new ArrayList<>();
        id_proyecto = new ArrayList<>();
        carnet = new ArrayList<>();
        mes = new ArrayList<>();
        total_horas_realizadas = new ArrayList<>();

        recyclerViewBitacora= findViewById(R.id.recyclerViewBitacora);
        add_button = findViewById(R.id.addBitacora);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaBitacoraActivity.this, CrearBitacoraActivity.class);
                startActivity(intent);
            }
        });

        consultarBitacora();

        bitacoraAdaptadorActivity = new BitacoraAdaptadorActivity(ConsultaBitacoraActivity.this,this, id_bitacora,id_proyecto,carnet,mes,total_horas_realizadas);
        recyclerViewBitacora.setAdapter(bitacoraAdaptadorActivity);
        recyclerViewBitacora.setLayoutManager(new LinearLayoutManager(ConsultaBitacoraActivity.this));

    }


    public void consultarBitacora(){

        Cursor cursor = helper.leerTodoBitacora();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
        helper.abrir();
        ArrayList<Bitacora> registros = helper.consultarBitacora();
        helper.cerrar();

        Bitacora a;
        Iterator<Bitacora> it = registros.iterator();
        while(it.hasNext()) {
            a = it.next();
            id_bitacora.add(String.valueOf(a.getId_bitacora()));
            id_proyecto.add(String.valueOf(a.getId_proyecto()));
            carnet.add(a.getCarnet());
            mes.add(a.getMes());
            total_horas_realizadas.add(String.valueOf(a.getTotal_horas_realizadas()));
        }
        }
    }
}