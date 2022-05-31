package com.example.serviciosocial.proyecto_estudiante;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

import java.util.ArrayList;
import java.util.List;

public class MainEstudiantesProyectoActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ListView mListView;
    private List<String> menuLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    String[] menu={"Proyectos Asignados","Proyectos no asignados"};
    String[] activities={"ConsultarEstudianteProyectoActivity","ConsultarProyectosNoAsignadosActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_estudiantes_proyecto);
        //inicializamos el menu
        mListView = findViewById(R.id.ListViewMenu);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,menu);
        mListView.setAdapter(mAdapter);

        //cargamos los clicks del menu
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nombreValue=activities[i];

                try{
                    Class<?> clase=Class.forName("com.example.serviciosocial.proyecto_estudiante." + nombreValue );
                    Intent intent = new Intent(MainEstudiantesProyectoActivity.this,clase);
                    MainEstudiantesProyectoActivity.this.startActivity(intent);
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        });
    }
}