package com.example.serviciosocial.categoria;

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

public class ConsultarCategoriaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ControlCategoria myDB;
    ArrayList<String> id_categoria,nombre_categoria;
    CategoriaAdpatador categoriaAdpatador;
    ImageView empty_imageview;
    TextView no_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_categoria);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_dataTextView);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultarCategoriaActivity.this, CrearCategoriaActivity.class);
                startActivity(intent);
            }
        });

        myDB = new ControlCategoria(ConsultarCategoriaActivity.this);
        id_categoria = new ArrayList<>();
        nombre_categoria = new ArrayList<>();

        storeDataInArrays();

        categoriaAdpatador = new CategoriaAdpatador(ConsultarCategoriaActivity.this,this,id_categoria,nombre_categoria);
        recyclerView.setAdapter(categoriaAdpatador);recyclerView.setLayoutManager(new LinearLayoutManager(ConsultarCategoriaActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.leerTodoCategoria();
        if (cursor.getCount()==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                id_categoria.add(cursor.getString(0));
                nombre_categoria.add(cursor.getString(1));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    }
