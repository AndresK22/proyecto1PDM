package com.example.serviciosocial.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.R;

public class ActualizarContraUsuario extends AppCompatActivity {

    EditText editTextContraActual,editTextContraNueva;
    Button buttonCambiarContra;
    String contraActual,id_usuario;
    ControlLogin controlLogin = new ControlLogin(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contra_usuario);

        contraActual = getIntent().getStringExtra("contraActual");
        id_usuario = getIntent().getStringExtra("id_usuario");

        editTextContraActual = findViewById(R.id.editTextCambiarContra);
        editTextContraNueva = findViewById(R.id.editTextCambiarContra1);
        buttonCambiarContra = findViewById(R.id.buttonCambiarContraInput);

        buttonCambiarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!contraActual.equals(editTextContraActual.getText().toString())){
                    Toast.makeText(ActualizarContraUsuario.this,"Contraseña actual incorrecta", Toast.LENGTH_SHORT).show();
                    editTextContraActual.setText("");
                    editTextContraNueva.setText("");
                }else{
                    if (editTextContraNueva.getText().toString().length()<6){
                        Toast.makeText(ActualizarContraUsuario.this,"Ingrese una contraña segura (6 digitos o más)",Toast.LENGTH_SHORT).show();
                        editTextContraNueva.setText("");
                    }
                    else{
                        controlLogin.actualizarContraUsuario(editTextContraNueva.getText().toString(),id_usuario);
                        finish();
                    }
                }
            }
        });



    }
}