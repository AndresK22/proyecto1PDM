package com.example.serviciosocial.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serviciosocial.MainActivity;
import com.example.serviciosocial.R;

public class LoginActivity extends AppCompatActivity {

    Button botonLogin;
    CheckBox checkBox;
    EditText editTextUsuario, editTextContra;
    ControlLogin controlLogin = new ControlLogin(this);
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String llave = "sesion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        controlLogin.abrir();
        controlLogin.llenarUsuarios();
        controlLogin.cerrar();

        preferences = this.getSharedPreferences("sesion",Context.MODE_PRIVATE);
        editor = preferences.edit();
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextContra = findViewById(R.id.editTextContra);
        botonLogin = findViewById(R.id.buttonLogin);
        checkBox = findViewById(R.id.checkBox);

        if(consultarSesion()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlLogin.abrir();
                boolean iniciar = controlLogin.iniciarSesion(editTextUsuario.getText().toString(),editTextContra.getText().toString());
               // controlLogin.cerrar();

                if(iniciar){
                    guardarSesion(checkBox.isChecked());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    editTextUsuario.setText("");
                    editTextContra.setText("");
                    checkBox.setChecked(false);
                }
                else{
                    Toast.makeText(LoginActivity.this,"Usuario o contra invalidos",Toast.LENGTH_SHORT).show();
                    editTextUsuario.setText("");
                    editTextContra.setText("");
                    checkBox.setChecked(false);
                }
            }
        });
    }

    private void guardarSesion(boolean checked){
        editor.putBoolean(llave,checked);

        controlLogin.abrir();
        Usuario usuario = controlLogin.consultarUsuario(editTextUsuario.getText().toString());

        editor.putInt("id",usuario.getIdUsuario());
        editor.putString("rol",controlLogin.getRolusuario(usuario.getIdUsuario()));
        editor.putString("usuario",editTextUsuario.getText().toString());
        editor.putString("contra",editTextContra.getText().toString());
        editor.apply();
    }
    public boolean consultarSesion(){
        boolean flag = false;
        String usuario = this.preferences.getString("usuario","");
        String contra = this.preferences.getString("contra","");

        controlLogin.abrir();
        boolean sesionvalida = controlLogin.iniciarSesion(usuario,contra);
        //controlLogin.cerrar();

        if(sesionvalida && this.preferences.getBoolean(llave,false)){
            flag = true;
        }

        return flag;
    }

}