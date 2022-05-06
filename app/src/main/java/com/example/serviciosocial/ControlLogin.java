package com.example.serviciosocial;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class ControlLogin {

    private static final String[] camposUsuario = new String[] {"ID_USUARIO", "NOMBRE_USUARIO", "CLAVE"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlLogin(Context ctx){
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String BASE_DATOS = "proyecto.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context){
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                //TABLAS PARA INICIO DE SESION
                db.execSQL("create table USUARIO (" +
                        "ID_USUARIO           INTEGER primary key autoincrement," +
                        "NOMBRE_USUARIO       VARCHAR(30)          not null," +
                        "CLAVE                CHAR(5)              not null);"
                );

                db.execSQL("create table OPCION_CRUD (" +
                        "ID_OPCION            CHAR(3)              not null," +
                        "DESC_OPCION          VARCHAR(30)          not null," +
                        "NUM_CRUD             INTEGER              not null," +
                        "primary key (ID_OPCION));"
                );

                db.execSQL("create table ACCESO_USUARIO (" +
                        "ID_OPCION            CHAR(3)              not null," +
                        "ID_USUARIO           INTEGER              not null," +
                        "primary key (ID_OPCION, ID_USUARIO)," +
                        "foreign key (ID_USUARIO)" +
                        "      references USUARIO (ID_USUARIO)," +
                        "foreign key (ID_OPCION)" +
                        "      references OPCION_CRUD (ID_OPCION)" +
                        ");"
                );

                db.execSQL("create unique index ACCESO_USUARIO_PK on ACCESO_USUARIO (" +
                        "ID_OPCION ASC," +
                        "ID_USUARIO ASC);"
                );

                db.execSQL("create  index ACCESO_USUARIO2_FK on ACCESO_USUARIO (" +
                        "ID_USUARIO ASC);"
                );

                db.execSQL("create  index ACCESO_USUARIO_FK on ACCESO_USUARIO (" +
                        "ID_OPCION ASC);"
                );

                //Agregar los triggers
                //db.execSQL("CREATE TRIGGER materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2), nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            // TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
    }

    public void cerrar(){
        DBHelper.close();
    }


    //INSERTS
    public void insertar(Usuario usuario){
        long result = 0;
        ContentValues u = new ContentValues();
        u.put("NOMBRE_USUARIO",usuario.getNomUsuario());
        u.put("CLAVE",usuario.getClave());
        result = db.insert("USUARIO",null,u);

        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
    }

    //UPDATES



    //DELETES



    //SELECTS
    public boolean consultarUsuarioExiste(String nombre){
        String[] nombreUsuario = {nombre};
        Cursor cursor = db.query("USUARIO", camposUsuario, "NOMBRE_USUARIO = ?", nombreUsuario, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public Usuario consultarUsuario(String nombre){
        String[] nombreUsuario = {nombre};
        Cursor cursor = db.query("USUARIO", camposUsuario, "NOMBRE_USUARIO = ?", nombreUsuario, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNomUsuario(cursor.getString(1));
            usuario.setClave(cursor.getString(2));
            return usuario;
        }else {
            return null;
        }
    }

    //LLENAR BASE USUARIOS
    public void llenarUsuarios(){

        //Llenando usuarios
        Usuario Tutor = new Usuario("Tutor","Tutor123");
        Usuario Alumno = new Usuario("Alumno","Alumno123");
        Usuario Admin = new Usuario("Admin","Admin123");

        if(!consultarUsuarioExiste(Tutor.getNomUsuario())){
            insertar(Tutor);
        }
        if(!consultarUsuarioExiste(Alumno.getNomUsuario())){
            insertar(Alumno);
        }
        if(!consultarUsuarioExiste(Admin.getNomUsuario())){
            insertar(Admin);
        }

    }

    //OTROS METODOS

    public boolean iniciarSesion(String usuario,String contra){

        if (consultarUsuarioExiste(usuario)){

            Usuario usuarioConsulta = new Usuario();
            usuarioConsulta = consultarUsuario(usuario);

            if (usuario.equals(usuarioConsulta.getNomUsuario()) && contra.equals(usuarioConsulta.getClave())){
                return true;
            }
            return  false;
        }
        else{
            return false;
        }
    }




}

