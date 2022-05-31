package com.example.serviciosocial.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.example.serviciosocial.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class GestionUsuarioActivity extends AppCompatActivity {

    FloatingActionButton buttonAgregarUsuario;
    ControlLogin controlLogin;
    ArrayList<String> id_usuario,nombre_usuario,contra_usuario,rol_usuario;
    CustomAdapterUsuario customAdapterUsuario;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_usuario);

        recyclerView = findViewById(R.id.recyclerViewUsuario);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);

        controlLogin = new ControlLogin(this);
        id_usuario = new ArrayList<>();
        nombre_usuario = new ArrayList<>();
        contra_usuario = new ArrayList<>();
        rol_usuario = new ArrayList<>();

        Cursor cursor = controlLogin.consultarUsuarios();

        while (cursor.moveToNext()){
            id_usuario.add(Integer.toString(cursor.getInt(0)));
            nombre_usuario.add(cursor.getString(1));
            contra_usuario.add(cursor.getString(2));
            rol_usuario.add(controlLogin.getRolusuario(cursor.getInt(0)));
        }

        customAdapterUsuario = new CustomAdapterUsuario(GestionUsuarioActivity.this,this,id_usuario,nombre_usuario,contra_usuario,rol_usuario);
        recyclerView.setAdapter(customAdapterUsuario);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GestionUsuarioActivity.this.startActivityForResult(new Intent(GestionUsuarioActivity.this,AgregarUsuarioActivity.class),1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            finish();
            recreate();
        }
    }
}