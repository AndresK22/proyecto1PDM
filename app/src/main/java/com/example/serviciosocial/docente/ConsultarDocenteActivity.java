package com.example.serviciosocial.docente;

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

public class ConsultarDocenteActivity extends AppCompatActivity {

    RecyclerView recyclerViewD;

    FloatingActionButton add_button;
    ControlDocente helper;
    ArrayList<String> dui_docente,nombres_docentes,apellidos_docentes,email_docente,telefono_docente;
    com.example.serviciosocial.docente.DocenteAdaptadorActivity DocenteAdaptadorActivity;
    ImageView empty_imageview;
    TextView no_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_docente);

        helper = new ControlDocente(this);
        dui_docente= new ArrayList<>();
        nombres_docentes = new ArrayList<>();
        apellidos_docentes = new ArrayList<>();
        email_docente = new ArrayList<>();
        telefono_docente = new ArrayList<>();

        recyclerViewD = findViewById(R.id.recyclerViewDocente);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button = findViewById(R.id.addBut);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarDocenteActivity.this, CrearDocenteActivity.class);
                startActivity(intent);
            }
        });

        consultarDocente();

        DocenteAdaptadorActivity = new DocenteAdaptadorActivity(ConsultarDocenteActivity.this,this, dui_docente,nombres_docentes,apellidos_docentes,email_docente,telefono_docente);
        recyclerViewD.setAdapter(DocenteAdaptadorActivity);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(ConsultarDocenteActivity.this));

    }


    public void consultarDocente(){

        Cursor cursor = helper.leerTodoDocente();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
        helper.abrir();
        ArrayList<Docente> registros = helper.consultarDocente();
        helper.cerrar();

        Docente docente;
        Iterator<Docente> it = registros.iterator();
        while(it.hasNext()) {
            docente = it.next();
            dui_docente.add(String.valueOf(docente.getDui_docente()));
            nombres_docentes.add(docente.getNombres_docente());
            apellidos_docentes.add(docente.getApellidos_docente());
            email_docente.add(docente.getEmail_docente());
            telefono_docente.add(docente.getTelefono_docente());
            }
        }
    }
}