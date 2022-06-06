package com.example.serviciosocial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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

    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

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

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper = new DataBaseHelper(this);

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
                Class<?> clase=Class.forName("com.example.serviciosocial.carrera.ConsultarCarreraActivity");
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
                Class<?> clase=Class.forName("com.example.serviciosocial.materia.ConsultarMateriaActivity");
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
                Class<?> clase=Class.forName("com.example.serviciosocial.estudiante.ConsultarEstudianteActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==5){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.categoria.ConsultarCategoriaActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==6){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.modalidad.ConsultarModalidadActivity");
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
                Class<?> clase=Class.forName("com.example.serviciosocial.estado.ConsultarEstadoActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==9){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.nota.ConsultarNotaActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==10){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.docenteWS.ConsultarDocenteActivityWS");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==11){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.estudianteWS.ConsultarEstudianteActivityWS");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==12){
            try{
                db = DBHelper.getWritableDatabase();

                db.execSQL("DELETE FROM materia;");
                db.execSQL("DELETE FROM area_carrera;");
                db.execSQL("DELETE FROM estudiante;");
                db.execSQL("DELETE FROM docente;");
                db.execSQL("DELETE FROM categoria;");
                db.execSQL("DELETE FROM modalidad;");
                db.execSQL("DELETE FROM carrera;");
                db.execSQL("DELETE FROM estado;");

                db.execSQL("INSERT INTO estado (id_estado,estado) VALUES (1,'Asignado');");
                db.execSQL("INSERT INTO estado (id_estado,estado) VALUES (2,'No asignado');");

                db.execSQL("INSERT INTO carrera (id_carrera, nombre_carrera, total_materias) VALUES ('I10515', 'Sistemas informaticos', 48);");
                db.execSQL("INSERT INTO carrera (id_carrera, nombre_carrera, total_materias) VALUES ('I10501', 'Ingenieria Civil', 47);");
                db.execSQL("INSERT INTO carrera (id_carrera, nombre_carrera, total_materias) VALUES ('I10503', 'Ingenieria Mecanica', 48);");

                db.execSQL("INSERT INTO modalidad (id_modalidad,nombre_modalidad) VALUES (1,'Presencial');");
                db.execSQL("INSERT INTO modalidad (id_modalidad,nombre_modalidad) VALUES (2,'En linea');");

                db.execSQL("INSERT INTO categoria(id_categoria,nombre_categoria) VALUES (1,'Pasantia');");
                db.execSQL("INSERT INTO categoria(id_categoria,nombre_categoria) VALUES (2,'Desarrollo de proyecto');");
                db.execSQL("INSERT INTO categoria(id_categoria,nombre_categoria) VALUES (3,'Soporte tecnico');");
                db.execSQL("INSERT INTO categoria(id_categoria,nombre_categoria) VALUES (4,'Ayudantia');");

                db.execSQL("INSERT INTO docente(dui_docente,nombres_docente, apellidos_docente, email_docente,telefono_docente) VALUES ('1234567890','Jose Gustavo', 'Perez Martinez','joseperez@ues.edu.sv','2202-2425');");
                db.execSQL("INSERT INTO docente(dui_docente,nombres_docente, apellidos_docente, email_docente,telefono_docente) VALUES ('9876543210','Juan Francisco', 'Hernandez Martinez','juanhernandez@ues.edu.sv','2222-2425');");
                db.execSQL("INSERT INTO docente(dui_docente,nombres_docente, apellidos_docente, email_docente,telefono_docente) VALUES ('1234567891','Bryan Vladimir', 'Araniva Gomez','bryanaraniva@ues.edu.sv','2223-2423');");
                db.execSQL("INSERT INTO docente(dui_docente,nombres_docente, apellidos_docente, email_docente,telefono_docente) VALUES ('1234567892','Kevin Alexis', 'Hernandez Guevara','kevinhernandez@ues.edu.sv','2245-5425');");

                db.execSQL("INSERT INTO estudiante(carnet,nombres_estudiante, apellidos_estudiante, email_estudiante,telefono_estudiante, domicilio, dui) VALUES ('aa19012','Fatima Mercedes', 'Aguilar Aguirre','aa19012@ues.edu.sv','2245-5425','casa','1237894567');");
                db.execSQL("INSERT INTO estudiante(carnet,nombres_estudiante, apellidos_estudiante, email_estudiante,telefono_estudiante, domicilio, dui) VALUES ('cc19114','Josue Ernesto', 'Cruz Cuellar','cc19114@ues.edu.sv','2245-4425','casa Josue','5227894567');");
                db.execSQL("INSERT INTO estudiante(carnet,nombres_estudiante, apellidos_estudiante, email_estudiante,telefono_estudiante, domicilio, dui) VALUES ('hg19010','Andres Oswaldo', 'Henriquez Gomez','hg19010@ues.edu.sv','2245-5325','casa Andres','7437894567');");
                db.execSQL("INSERT INTO estudiante(carnet,nombres_estudiante, apellidos_estudiante, email_estudiante,telefono_estudiante, domicilio, dui) VALUES ('rg19041','Jorge Eduardo', 'Romero Garcia','rg19041@ues.edu.sv','2245-5725','casa Jorge','7537894567');");


                //LLenado de tablas hijas
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('01', 'I10515', 'Programación');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('02', 'I10515', 'Datos');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('03', 'I10515', 'Administración');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('04', 'I10501', 'Construccion');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('05', 'I10501', 'Diseno');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('06', 'I10501', 'Mapas');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('07', 'I10503', 'Torno');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('08', 'I10503', 'Fresadora');");
                db.execSQL("INSERT INTO area_carrera (id_area, id_carrera, descrip_area) VALUES ('09', 'I10503', 'Herramientas');");


                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('iai115', '01', 'Introduccion');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('prn115', '01', 'Programacion i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('prn215', '01', 'Programacion ii');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('esd115', '02', 'Estructuras');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('hdp115', '02', 'Herramientas');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('arc115', '02', 'Arquitectura');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('msm115', '03', 'Manejo');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('fde115', '03', 'Fundamentos');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mop115', '03', 'Metodos');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('cgr115', '04', 'Comunicacion i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('cgr215', '04', 'Comunicacion ii');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mso115', '04', 'Solidos i');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mso215', '05', 'Solidos ii');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('gea115', '05', 'Geologia i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mso315', '05', 'Solidos iii');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('top115', '06', 'Topografia');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('inm115', '06', 'Materiales');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mce115', '06', 'Estructural');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('dit115', '07', 'Dibujo tecnico');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('ter115', '07', 'Termodinamica i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('ace115', '07', 'Circuitos');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('ter215', '08', 'Termodinamica ii');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('dem115', '08', 'Maquinas i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('pdf115', '08', 'Fabricacion');");

                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('tfc115', '09', 'Transferencia');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mid115', '09', 'Hidraulicas');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('dem215', '09', 'Maquinas ii');");


                db.close();

                Toast.makeText(MainActivity.this,"Base llenada",Toast.LENGTH_SHORT).show();
            } catch (SQLException e){
                e.printStackTrace();
            }



        }
    }
    private void setMenuDocente(int i) {
        if (i==0){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.proyecto.ConsultarProyectoActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==1){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.proyecto_estudiante.MainEstudiantesProyectoActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==2){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.recordAcademico.ConsultarRecordActivity");
                Intent intent = new Intent(MainActivity.this,clase);
                MainActivity.this.startActivity(intent);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
        }
        if (i==3){
            //Toast.makeText(MainActivity.this,"Posicion "+i,Toast.LENGTH_SHORT).show();
            try{
                Class<?> clase=Class.forName("com.example.serviciosocial.resumensocial.ConsultarResumenActivity");
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
                Class<?> clase=Class.forName("com.example.serviciosocial.resumensocial.ConsultarResumenActivity");
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
        if(this.preferences.getString("rol","").equals("Administrador")){
            menuLista.add("WS Docente");
            menuLista.add("WS Estudiante");
            menuLista.add("Llenar base");
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