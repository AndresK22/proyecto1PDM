package com.example.serviciosocial.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;

public class ControlProyecto {

    private static final String[] camposModalidad = new String[] {"id_modalidad", "nombre_modalidad"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlProyecto(Context context) {
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

    //INSERT
    public String insertar(Modalidad modalidad)
    {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues mod = new ContentValues();
        mod.put("id_modalidad", (Integer) null);
        mod.put("nombre_modalidad", modalidad.getNombre_modalidad());
        contador = db.insert("modalidad", null, mod);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //UPDATES
    public String actualizar(Modalidad modalidad){
        try
        {
            String[] id = {String.valueOf((modalidad.getId_modalidad()))};
            ContentValues cv = new ContentValues();
            cv.put("nombre_modalidad", modalidad.getNombre_modalidad());
            db.update("modalidad", cv, "id_modalidad = ?",  id);

            return "Registro Actualizado Correctamente";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    //DELETES
    public String eliminar(Modalidad modalidad){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(modalidad.getId_modalidad())};
        try{
            contador += db.delete("modalidad", "id_modalidad = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //SELECTS

    public Cursor leerTodoModalidad(){
        String query = "SELECT * FROM modalidad";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    //Dropdown

    public ArrayList<Modalidad> consultarModalidad(){
        try {
            ArrayList<Modalidad> lisModalidad = new ArrayList<Modalidad>();
            Cursor cursor = db.query("modalidad", camposModalidad, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Modalidad modalidad = new Modalidad();
                    modalidad.setId_modalidad(cursor.getInt(0));
                    modalidad.setNombre_modalidad(cursor.getString(1));
                    lisModalidad.add(modalidad);
                } while (cursor.moveToNext());

                return lisModalidad;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }




}
