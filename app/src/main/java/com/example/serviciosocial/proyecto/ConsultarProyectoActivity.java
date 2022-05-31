package com.example.serviciosocial.proyecto;

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
import com.example.serviciosocial.areaCarrera.AreaAdaptadorActivity;
import com.example.serviciosocial.areaCarrera.AreaCarrera;
import com.example.serviciosocial.areaCarrera.ConsultarAreaActivity;
import com.example.serviciosocial.areaCarrera.ControlAreaCarrera;
import com.example.serviciosocial.areaCarrera.CrearAreaActivity;
import com.example.serviciosocial.modalidad.ConsultarModalidadActivity;
import com.example.serviciosocial.modalidad.CrearModalidadActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ConsultarProyectoActivity extends AppCompatActivity {
    RecyclerView recyclerViewArea;
    FloatingActionButton add_button;
    ControlProyecto helper;
    ImageView empty_imageview;
    TextView no_data;


    ArrayList<String> id_proyecto, id_categoria, id_modalidad,dui_docente,id_estado,id_carrera,id_area,nombre_proyecto,descripcion_proyecto,lugar,requisito_nota;
    ProyectoAdaptador proyectoAdaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_proyecto);

        helper = new ControlProyecto(this);
        id_proyecto = new ArrayList<>();
        id_categoria = new ArrayList<>();
        id_modalidad = new ArrayList<>();
        dui_docente = new ArrayList<>();
        id_estado = new ArrayList<>();
        id_carrera = new ArrayList<>();
        id_area = new ArrayList<>();
        nombre_proyecto = new ArrayList<>();
        descripcion_proyecto = new ArrayList<>();
        lugar = new ArrayList<>();
        requisito_nota = new ArrayList<>();
        add_button = findViewById(R.id.add_buttonP);
        recyclerViewArea = findViewById(R.id.recyclerViewxd);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarProyectoActivity.this, CrearProyectoActivity.class);
                startActivity(intent);
            }
        });

        storeDataInArrays();

                proyectoAdaptador = new ProyectoAdaptador(ConsultarProyectoActivity.this,this, id_proyecto,id_categoria, id_modalidad,dui_docente,id_estado,id_carrera,id_area,nombre_proyecto,descripcion_proyecto,lugar,requisito_nota);
        recyclerViewArea.setAdapter(proyectoAdaptador);
        recyclerViewArea.setLayoutManager(new LinearLayoutManager(ConsultarProyectoActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }
    void storeDataInArrays(){
        Cursor cursor = helper.leerTodoProyecto();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_proyecto.add(cursor.getString(0));
                id_categoria.add(cursor.getString(1));
                id_modalidad.add(cursor.getString(2));
                dui_docente.add(cursor.getString(3));
                id_estado.add(cursor.getString(4));
                id_carrera.add(cursor.getString(5));
                id_area.add(cursor.getString(6));
                nombre_proyecto.add(cursor.getString(7));
                descripcion_proyecto.add(cursor.getString(8));
                lugar.add(cursor.getString(9));
                requisito_nota.add(cursor.getString(10));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}