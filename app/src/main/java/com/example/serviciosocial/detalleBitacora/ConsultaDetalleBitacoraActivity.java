package com.example.serviciosocial.detalleBitacora;

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

public class ConsultaDetalleBitacoraActivity extends AppCompatActivity {



    RecyclerView recyclerViewDeta;

    FloatingActionButton add_button;

    ControlDetalleBitacora helper;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<String> id,id_bitacora, actividad, fecha;
    com.example.serviciosocial.detalleBitacora.DetalleBitacoraAdaptadorActivity DetalleBitacoraAdaptadorActivity;

    String extra;
    String foo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_detalle_bitacora);

        helper = new ControlDetalleBitacora(this);
        id= new ArrayList<>();
        id_bitacora = new ArrayList<>();
        actividad = new ArrayList<>();
        fecha = new ArrayList<>();

        //extra = getIntent().getExtras().getString("id_bitacora");

        recyclerViewDeta = findViewById(R.id.recyclerViewDetalle);
        add_button = findViewById(R.id.addBut2);
        empty_imageview = findViewById(R.id.empty_imageView);

        no_data = findViewById(R.id.no_dataTextView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaDetalleBitacoraActivity.this, CrearDetalleBitacoraActivity.class);
                intent.putExtra("id_bitacora",String.valueOf(foo));
                startActivity(intent);
            }
        });

        consultarDetalle();

        DetalleBitacoraAdaptadorActivity = new DetalleBitacoraAdaptadorActivity(ConsultaDetalleBitacoraActivity.this,this, id,id_bitacora,actividad,fecha);
        recyclerViewDeta.setAdapter(DetalleBitacoraAdaptadorActivity);
        recyclerViewDeta.setLayoutManager(new LinearLayoutManager(ConsultaDetalleBitacoraActivity.this));

    }


    public void consultarDetalle(){

        Cursor cursor = helper.leerTodoDetalleBitacora();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
        Bundle extras = getIntent().getExtras();
        foo = extras.getString("id_bitacora");

        helper.abrir();
        ArrayList<DetalleBitacora> registros = helper.consultarDetalleBitacora(foo);
        helper.cerrar();


        DetalleBitacora detalleBitacora;
        Iterator<DetalleBitacora> it = registros.iterator();
        while(it.hasNext()) {
            detalleBitacora= it.next();
            id.add(String.valueOf(detalleBitacora.getId_detalle_bitacora()));
            id_bitacora.add(String.valueOf(detalleBitacora.getId_bitacora()));
            actividad.add(String.valueOf(detalleBitacora.getActividad()));
            fecha.add(String.valueOf(detalleBitacora.getFecha_bitacora()));
        }
    }}

}