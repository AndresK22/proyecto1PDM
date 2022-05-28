package com.example.serviciosocial.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.serviciosocial.DataBaseHelper;

public class ControlLogin {

    private static final String[] camposUsuario = new String[] {"ID_USUARIO", "NOMBRE_USUARIO", "CLAVE"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlLogin(Context context) {
        this.context = context;
        DBHelper = new DataBaseHelper(this.context);
    }

    public void abrir() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void abrirParaLeer() throws SQLException {
        db = DBHelper.getReadableDatabase();
        return;
    }

    public void cerrar(){
        db.close();
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
