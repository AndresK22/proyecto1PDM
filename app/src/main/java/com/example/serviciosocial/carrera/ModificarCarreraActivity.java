package com.example.serviciosocial.carrera;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.serviciosocial.R;

public class ModificarCarreraActivity extends AppCompatActivity {

    ControlCarrera myBD;
    EditText txtNombreCarrera, txtTotalMaterias, txtIdCarrera;
    String id_carrera, nombre_carrera, total_materias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_carrera);

        myBD = new ControlCarrera(this);
        id_carrera = getIntent().getExtras().getString("id_carrera");
        nombre_carrera = getIntent().getExtras().getString("nombre_carrera");
        total_materias = getIntent().getExtras().getString("total_materias");
        txtIdCarrera = findViewById(R.id.editTextIdCarrera);
        txtNombreCarrera = findViewById(R.id.editTextNombreCarrera);
        txtTotalMaterias = findViewById(R.id.editTextTotalMaterias);

        txtIdCarrera.setText(id_carrera);
        txtNombreCarrera.setText(nombre_carrera);
        txtTotalMaterias.setText(total_materias);
    }
    public void modificarCarrera(View v){
        if(verificarCamposLLenos()){
            String i = id_carrera;
            String[] ie = {i};
            Carrera carre = new Carrera();
            carre.setId_carrera(txtIdCarrera.getText().toString());
            carre.setNombre_carrera(txtNombreCarrera.getText().toString());
            carre.setTotal_materias(Integer.valueOf(txtTotalMaterias.getText().toString()));

            myBD.abrir();
            String car = myBD.actualizar(carre, ie);
            myBD.cerrar();

            Toast.makeText(this, car, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ConsultarCarreraActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminarCarrera(View v){
        AlertDialog dialogo = new AlertDialog.Builder(ModificarCarreraActivity.this)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String regEliminados;
                        Carrera carrera = new Carrera();
                        carrera.setId_carrera(String.valueOf(id_carrera));
                        myBD.abrir();
                        regEliminados = myBD.eliminar(carrera);
                        myBD.cerrar();
                        Toast.makeText(ModificarCarreraActivity.this, regEliminados, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ModificarCarreraActivity.this, ConsultarCarreraActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Confirmar")
                .setMessage("¿Deseas eliminar la carrera '" + nombre_carrera + "'?")
                .create();
        dialogo.show();
    }
    public boolean verificarCamposLLenos(){
        if(txtNombreCarrera.getText().toString().isEmpty() || txtNombreCarrera.getText().toString() == null){
            return false;
        }else if(txtTotalMaterias.getText().toString().isEmpty() || txtTotalMaterias.getText().toString() == null){
            return false;
        }else{
            return true;
        }
    }
}