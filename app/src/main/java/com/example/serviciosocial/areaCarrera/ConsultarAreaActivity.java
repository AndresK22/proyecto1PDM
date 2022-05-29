package com.example.serviciosocial.areaCarrera;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarAreaActivity extends AppCompatActivity {
    RecyclerView recyclerViewArea;
    FloatingActionButton add_button;
    ControlAreaCarrera helper;
    ImageView empty_imageview;
    TextView no_data;


    ArrayList<String> id_area, id_carrera, descrip_area;
    AreaAdaptadorActivity areaAdaptadorActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_area);

        helper = new ControlAreaCarrera(this);
        id_area = new ArrayList<>();
        id_carrera = new ArrayList<>();
        descrip_area = new ArrayList<>();

        recyclerViewArea = findViewById(R.id.recyclerViewArea);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.add);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarAreaActivity.this, CrearAreaActivity.class);
                startActivity(intent);
            }
        });

        consultarArea();

        areaAdaptadorActivity = new AreaAdaptadorActivity(ConsultarAreaActivity.this,this, id_area, id_carrera,descrip_area);
        recyclerViewArea.setAdapter(areaAdaptadorActivity);
        recyclerViewArea.setLayoutManager(new LinearLayoutManager(ConsultarAreaActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    public void consultarArea(){

        Cursor cursor = helper.leerTodoAreaCarrera();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            helper.abrir();
            ArrayList<AreaCarrera> registros = helper.consultarArea();
            helper.cerrar();

            AreaCarrera a;
            Iterator<AreaCarrera> it = registros.iterator();
            while(it.hasNext()) {
                a = it.next();

                id_area.add(String.valueOf(a.getId_area()));
                id_carrera.add(a.getId_carrera());
                descrip_area.add(a.getDescrip_area());
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}