package com.example.serviciosocial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AgregarUsuarioActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerRolUsuario;
    EditText editTextNombreUsuario,editTextContra,editTextContra2;
    Button buttonAgregarUsuario;
    int rolUsuario;
    ControlLogin controlLogin = new ControlLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        spinnerRolUsuario =  findViewById(R.id.spinnerRolUsuario);
        editTextNombreUsuario = findViewById(R.id.editTextNombreUsuario);
        editTextContra = findViewById(R.id.editTextContra);
        editTextContra2 = findViewById(R.id.editTextContra2);
        buttonAgregarUsuario = findViewById(R.id.buttonAgregarUsuario);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.roles, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerRolUsuario.setAdapter(adapter);
        spinnerRolUsuario.setOnItemSelectedListener(this);

        buttonAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarAInsertarusuario();
            }
        });

    }

    private boolean contraEsIgual(String contra1,String contra2){
        return contra1.equals(contra2);
    }

    private void mandarAInsertarusuario() {

        if(contraEsIgual(editTextContra.getText().toString(),editTextContra2.getText().toString())){
            Usuario usuario = new Usuario();
            usuario.setNomUsuario(editTextNombreUsuario.getText().toString());
            usuario.setClave(editTextContra.getText().toString());

            controlLogin.abrir();
            controlLogin.insertar(usuario);
            controlLogin.insertar(rolUsuario);

        }else{
            Toast.makeText(this,"La contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            editTextContra2.setText("");
            editTextContra.setText("");
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        rolUsuario = i;

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}