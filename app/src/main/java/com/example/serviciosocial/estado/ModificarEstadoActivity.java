package com.example.serviciosocial.estado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serviciosocial.ControlBD;
import com.example.serviciosocial.R;

public class ModificarEstadoActivity extends AppCompatActivity {

    ControlBD helper;
    EditText txtEstado;
    Button modificar;
    Button eliminar;
    String id_estado;
    String estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_estado);

        helper = new ControlBD(this);
        id_estado = getIntent().getExtras().getString("id_estado");
        estado = getIntent().getExtras().getString("estado");
        txtEstado = (EditText) findViewById(R.id.editTextEstado);
        modificar = findViewById(R.id.btnModificar);
        eliminar = findViewById(R.id.btnEliminar);
        txtEstado.setText(estado);
    }

    public void modificarEstado(View v) {
        Estado estado = new Estado();
        estado.setId_estado(Integer.valueOf(id_estado));
        estado.setEstado(txtEstado.getText().toString());

        helper.abrir();
        String estad = helper.actualizar(estado);
        helper.cerrar();

        Toast.makeText(this, estad, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ConsultarEstadoActivity.class);
        startActivity(intent);
    }

    public void eliminarEstado(View v) {
        String regEliminadas;
        Estado estado = new Estado();
        estado.setId_estado(Integer.valueOf(id_estado));

        helper.abrir();
        regEliminadas = helper.eliminar(estado);
        helper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ConsultarEstadoActivity.class);
        startActivity(intent);
    }
}