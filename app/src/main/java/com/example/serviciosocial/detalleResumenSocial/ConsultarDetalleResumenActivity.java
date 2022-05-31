package com.example.serviciosocial.detalleResumenSocial;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serviciosocial.R;
import com.example.serviciosocial.resumensocial.ConsultarResumenActivity;
import com.example.serviciosocial.resumensocial.ControlResumenSocial;
import com.example.serviciosocial.resumensocial.CrearResumenActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarDetalleResumenActivity extends AppCompatActivity {
    RecyclerView recyclerViewDetalleResumen;
    FloatingActionButton add_button;
    ControlDetalleResumen helper;
    ImageView empty_imageview;
    TextView no_data;
    ArrayList<String> idDetalle, idResumen, idProyecto, fechaI, fechaF, horas, monto, bI, bD, estado;
    AdaptadorDetalleResumen adaptadorDetalleResumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_detalle_resumen);

        helper = new ControlDetalleResumen(this);
        idDetalle = new ArrayList<>();
        idResumen = new ArrayList<>();
        idProyecto = new ArrayList<>();
        fechaI = new ArrayList<>();
        fechaF = new ArrayList<>();
        horas = new ArrayList<>();
        monto = new ArrayList<>();
        bI = new ArrayList<>();
        bD = new ArrayList<>();
        estado = new ArrayList<>();

        recyclerViewDetalleResumen = findViewById(R.id.recyclerViewResumen);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.add_detalle_resumen);

        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(ConsultarDetalleResumenActivity.this, CrearDetalleResumenActivity.class);
                startActivity(intent);
            }
        });
        consultarDetalle();
        adaptadorDetalleResumen = new AdaptadorDetalleResumen(ConsultarDetalleResumenActivity.this, this, idDetalle, idResumen, idProyecto, fechaI, fechaF, horas, monto, bI, bD, estado);
        recyclerViewDetalleResumen.setAdapter(adaptadorDetalleResumen);
        recyclerViewDetalleResumen.setLayoutManager(new LinearLayoutManager(ConsultarDetalleResumenActivity.this));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    public void consultarDetalle(){
        Cursor cursor = helper.leerTodoDetalle();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            helper.abrir();
            ArrayList<DetalleResumenSocial> registros = helper.consultarDetalleResumen();
            helper.cerrar();

            DetalleResumenSocial d;
            Iterator<DetalleResumenSocial> it = registros.iterator();
            while(it.hasNext()){
                d = it.next();
                idDetalle.add(String.valueOf(d.getId_det_res()));
                idResumen.add(String.valueOf(d.getId_resumen()));
                idProyecto.add(String.valueOf(d.getId_proyecto()));
                fechaI.add(d.getFecha_inicio());
                fechaF.add(d.getFecha_final());
                horas.add(String.valueOf(d.getHoras_asignadas()));
                monto.add(String.valueOf(d.getMonto()));
                bI.add(String.valueOf(d.getBenef_indir()));
                bD.add(String.valueOf(d.getBenef_dir()));
                estado.add(String.valueOf(d.getEstado_det()));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}