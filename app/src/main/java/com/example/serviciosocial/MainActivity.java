package com.example.serviciosocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

import com.example.serviciosocial.login.ControlLogin;


public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ListView mListView;
     private List<String> menuLista = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    ControlLogin controlLogin = new ControlLogin(this);
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String[] menu={
            //Administrador
            "Gestion Usuario","Gestion de Carreras","Area por carreras","Gestion de materias","Gestion de estudiantes",
            "Gestion de categorias","Gestion de modalidad","Gestion de docentes","Estado de proyectos","Gestion de notas",
            //Docente
            "Gestion de proyectos","Grupos de proyectos","Record academico","Resumen Social",
            //Alumno
            "Gestion de Bitacoras","Resumen Social", "Record academico","Mis Proyectos"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos valores de sesion
        preferences = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editor = preferences.edit();

        //inicializamos toolbar
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        //inicializamos el menu
        mListView = findViewById(R.id.ListViewMenu);
        cargarAcceso();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,menuLista);
        mListView.setAdapter(mAdapter);

        //cargamos los clicks del menu
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String rol =    MainActivity.this.preferences.getString("rol","");
                if(rol.equals("Administrador")){
                    setMenuAdministrador(i);
                }
                if(rol.equals("Docente")){
                    setMenuDocente(i);
                }
                if (rol.equals("Alumno")){
                    setMenuAlumno(i);
                }


            }
        });
    }

    private void setMenuAdministrador(int i) {
        if (i==0){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==1){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==2){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.areaCarrera.ConsultarAreaActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==3){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }        if (i==0){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==4){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==5){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==6){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==7){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.docente.ConsultarDocenteActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==8){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==9){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }
    private void setMenuDocente(int i) {
        if (i==0){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==1){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==2){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==3){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    }
    private void setMenuAlumno(int i) {
        if (i==0){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.bitacora.ConsultaBitacoraActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==1){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==2){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==3){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.login.GestionUsuarioActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void cargarAcceso() {
        controlLogin.abrir();
        Cursor cursor = controlLogin.getAccesoUsuario(this.preferences.getInt("id",0));

        while (cursor.moveToNext()) {
            String idopcionstr = cursor.getString(0);
            int idopcion = Integer.parseInt(idopcionstr);
            idopcion = idopcion-1;
            menuLista.add(menu[idopcion]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_logout:
                editor.putBoolean("sesion",false);
                editor.apply();
                this.finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finishAffinity();
        }
        return super.onKeyDown(keyCode, event);
    }



}