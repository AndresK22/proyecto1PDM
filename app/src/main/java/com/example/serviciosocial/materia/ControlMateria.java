package com.example.serviciosocial.materia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlMateria {
    private static final String[] camposMateria = new String[] {"cod_materia", "id_area", "nombre_materia"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlMateria(Context context) {
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
    public String insertar(Materia materia){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues mate = new ContentValues();
        mate.put("cod_materia", materia.getCod_materia());
        mate.put("id_area", materia.getId_area());
        mate.put("nombre_materia", materia.getNombre_materia());
        contador = db.insertOrThrow("materia", null, mate);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //UPDATE
    public String actualizar(Materia materia){
        try{
            String[] id = {materia.getCod_materia()};
            ContentValues cv = new ContentValues();
            cv.put("id_area", materia.getId_area());
            cv.put("nombre_materia", materia.getNombre_materia());
            db.update("materia", cv, "cod_materia = ?", id);

            return "Materia actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //DELETE
    public String eliminar(Materia materia){
        String regAfectados="filas afectadas = ";
        int contador=0;
        //Borrar la nota con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar la nota

        String[] id = {String.valueOf(materia.getCod_materia())};
        try{
            contador += db.delete("materia", "cod_materia = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //SELECTS
    public ArrayList<Materia> consultarMateria(){
        try {
            ArrayList<Materia> lisMaterias = new ArrayList<Materia>();
            Cursor cursor = db.query("materia", camposMateria, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Materia materia = new Materia();
                    materia.setCod_materia(cursor.getString(0));
                    materia.setId_area(cursor.getString(1));
                    materia.setNombre_materia(cursor.getString(2));
                    lisMaterias.add(materia);
                } while (cursor.moveToNext());

                return lisMaterias;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Materia consultarMateria(String[] id){
        try {
            Cursor cursor = db.query("materia", camposMateria, "cod_materia = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Materia materia = new Materia();
                materia.setCod_materia(cursor.getString(0));
                materia.setId_area(cursor.getString(1));
                materia.setNombre_materia(cursor.getString(2));
                return materia;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}