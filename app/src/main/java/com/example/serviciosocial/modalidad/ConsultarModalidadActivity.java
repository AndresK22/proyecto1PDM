package com.example.serviciosocial.modalidad;

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

public class ConsultarModalidadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlModalidad myDB;
    ArrayList<String> id_modalidad,nombre_modalidad;
    ModalidadAdaptador modalidadAdaptador;
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_modalidad);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarModalidadActivity.this, CrearModalidadActivity.class);
                startActivity(intent);
            }
        });

        myDB = new ControlModalidad(ConsultarModalidadActivity.this);
        id_modalidad = new ArrayList<>();
        nombre_modalidad = new ArrayList<>();

        storeDataInArrays();

        modalidadAdaptador = new ModalidadAdaptador(ConsultarModalidadActivity.this,this,id_modalidad,nombre_modalidad);
        recyclerView.setAdapter(modalidadAdaptador);recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarModalidadActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.leerTodoModalidad();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_modalidad.add(cursor.getString(0));
                nombre_modalidad.add(cursor.getString(1));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    }
