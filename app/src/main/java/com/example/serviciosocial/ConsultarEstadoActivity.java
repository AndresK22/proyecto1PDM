package com.example.serviciosocial;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConsultarEstadoActivity extends AppCompatActivity {

    ControlBD helper;
    TableLayout tblEstados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_estado);

        helper = new ControlBD(this);
        tblEstados = (TableLayout) findViewById(R.id.tblEstados);
        //consultarEstado();
    }

    public void consultarEstado(){
        helper.abrir();
        View registros = helper.consultarEstados(this);
        helper.cerrar();

        if(registros == null){
            Toast.makeText(this, "No se han encontrado estados", Toast.LENGTH_SHORT).show();
        } else{
            tblEstados.addView(registros);
        }
    }
}