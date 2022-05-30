package com.example.serviciosocial.estado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlEstado {
    private static final String[] camposEstado = new String[] {"id_estado", "estado"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlEstado(Context context) {
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
    public String insertar(Estado estado){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues est = new ContentValues();
        est.put("estado", estado.getEstado());
        contador = db.insert("estado", null, est);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //UPDATE
    public String actualizar(Estado estado){
        try{
            String[] id = {String.valueOf(estado.getId_estado())};
            ContentValues cv = new ContentValues();
            cv.put("estado", estado.getEstado());
            db.update("estado", cv, "id_estado = ?", id);

            return "Estado actualizado correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    //DELETE
    public String eliminar(Estado estado){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(estado.getId_estado())};
        try{
            contador += db.delete("estado", "id_estado = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //SELECT
    public ArrayList<Estado> consultarEstados(){
        try {
            ArrayList<Estado> lisEstados = new ArrayList<Estado>();
            Cursor cursor = db.query("estado", camposEstado, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Estado estado = new Estado();
                    estado.setId_estado(cursor.getInt(0));
                    estado.setEstado(cursor.getString(1));
                    lisEstados.add(estado);
                } while (cursor.moveToNext());

                return lisEstados;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Estado consultarEstados(String[] id){
        try {
            Cursor cursor = db.query("estado", camposEstado, "id_estado = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                Estado estado = new Estado();
                estado.setId_estado(cursor.getInt(0));
                estado.setEstado(cursor.getString(1));
                return estado;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

