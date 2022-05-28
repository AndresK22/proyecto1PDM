package com.example.serviciosocial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActualizarUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerRolUsuario;
    EditText editTextNombreUsuario,editTextRolUsuario;
    Button buttonActualizar,buttonEliminar;
    String id_usuario,nombre_usuario,clave,rol;
    int rol_actualizado;
    ControlLogin controlLogin = new ControlLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuario);

        spinnerRolUsuario =  findViewById(R.id.spinnerRolUsuarioA);
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuarioA);
        buttonActualizar = findViewById(R.id.buttonActualizarUsuario);
        buttonEliminar = findViewById(R.id.buttonEliminarUsuario);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerRolUsuario.setAdapter(adapter);
        spinnerRolUsuario.setOnItemSelectedListener(this);

        asignarInfo();

        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlLogin.actualizarUsuario(id_usuario,editTextNombreUsuario.getText().toString().trim(),rol_actualizado);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarDialog();
            }
        });

    }

    public void asignarInfo(){
        id_usuario = getIntent().getStringExtra("id_usuario");
        nombre_usuario = getIntent().getStringExtra("nombre_usuario");
        rol = getIntent().getStringExtra("rol_usuario");
        clave = getIntent().getStringExtra("clave");

        editTextNombreUsuario.setText(nombre_usuario);
        if(rol.equals("Administrador")){
            spinnerRolUsuario.setSelection(0);
        }
        if(rol.equals("Docente")){
            spinnerRolUsuario.setSelection(1);
        }
        if(rol.equals("Alumno")){
            spinnerRolUsuario.setSelection(2);
        }
    }

    void confirmarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar Usuario");
        builder.setMessage("Estas seguro de eliminar a " + nombre_usuario + "?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controlLogin.eliminarUsuario(id_usuario);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        rol_actualizado = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}