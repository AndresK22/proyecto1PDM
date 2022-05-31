package com.example.serviciosocial.resumensocial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;
import com.example.serviciosocial.areaCarrera.ControlAreaCarrera;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarResumenActivity extends AppCompatActivity {

    RecyclerView recyclerViewResumen;
    FloatingActionButton add_button;
    ControlResumenSocial helper;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String>  id_resumen, dui_docente, carnet, fecha_apertura_expediente, fecha_emision_certificado, observaciones;
    AdaptadorResumenSocial adaptadorResumenSocial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_resumen);

        helper = new ControlResumenSocial(this);
        id_resumen = new ArrayList<>();
        dui_docente = new ArrayList<>();
        carnet = new ArrayList<>();
        fecha_apertura_expediente = new ArrayList<>();
        fecha_emision_certificado = new ArrayList<>();
        observaciones = new ArrayList<>();

        recyclerViewResumen = findViewById(R.id.recyclerViewResumen);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.add_resumen);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ConsultarResumenActivity.this, CrearResumenActivity.class);
                startActivity(intent);
            }
        });
        consultarResumen();
        adaptadorResumenSocial = new AdaptadorResumenSocial(ConsultarResumenActivity.this, this, id_resumen, dui_docente, carnet, fecha_apertura_expediente, fecha_emision_certificado, observaciones);
        recyclerViewResumen.setAdapter(adaptadorResumenSocial);
        recyclerViewResumen.setLayoutManager(new LinearLayoutManager(ConsultarResumenActivity.this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    public void consultarResumen(){
        Cursor cursor = helper.leerTodoResumen();
        if(cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            helper.abrir();
            ArrayList<Resumensocial> registros = helper.consultarResumen();
            helper.cerrar();

            Resumensocial r;
            Iterator<Resumensocial> it = registros.iterator();
            while(it.hasNext()){
                r = it.next();

                id_resumen.add(String.valueOf(r.getId_resumen()));
                dui_docente.add(r.getDui_docente());
                carnet.add(r.getCarnet());
                fecha_apertura_expediente.add(r.getFecha_apertura_expediente());
                fecha_emision_certificado.add(r.getFecha_emision_certificado());
                observaciones.add(r.getObservaciones());
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}