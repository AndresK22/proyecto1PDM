package com.example.serviciosocial.nota;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlNota {
    private static final String[] camposNota = new String[] {"cod_materia", "carnet", "calificacion"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlNota(Context context) {
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
    public String insertar(Nota nota){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues not = new ContentValues();
        not.put("cod_materia", nota.getCod_materia());
        not.put("carnet", nota.getCarnet());
        not.put("calificacion", nota.getCalificacion());
        contador = db.insert("nota", null, not);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //UPDATE
    public String actualizar(Nota nota){
        try{
            String[] id = {nota.getCod_materia(), nota.getCarnet()};
            ContentValues cv = new ContentValues();
            cv.put("calificacion", nota.getCalificacion());
            db.update("nota", cv, "cod_materia = ? AND carnet = ?", id);

            return "Nota actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //DELETE
    public String eliminar(Nota nota){
        String regAfectados="filas afectadas = ";
        int contador=0;

        String[] id = {nota.getCod_materia(), nota.getCarnet()};
        try{
            contador += db.delete("nota", "cod_materia = ? AND carnet = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //SELECTS
    public ArrayList<Nota> consultarNotas(){
        try {
            ArrayList<Nota> lisNotas = new ArrayList<Nota>();
            Cursor cursor = db.query("nota", camposNota, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Nota not = new Nota();
                    not.setCod_materia(cursor.getString(0));
                    not.setCarnet(cursor.getString(1));
                    not.setCalificacion(cursor.getDouble(2));
                    lisNotas.add(not);
                } while (cursor.moveToNext());

                return lisNotas;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Nota> consultarNotasPorCarrera(String[] idCarrera){
        try {
            ArrayList<Nota> lisNotas = new ArrayList<Nota>();
            Cursor cursor = db.rawQuery("SELECT nott.COD_MATERIA, nott.carnet, nott.CALIFICACION FROM nota nott, materia mat, area_carrera are, carrera car WHERE(nott.COD_MATERIA = mat.COD_MATERIA AND mat.ID_AREA = are.ID_AREA AND are.id_carrera = car.id_carrera AND car.id_carrera = ?);", idCarrera);

            if (cursor.moveToFirst()) {
                do {
                    Nota not = new Nota();
                    not.setCod_materia(cursor.getString(0));
                    not.setCarnet(cursor.getString(1));
                    not.setCalificacion(cursor.getDouble(2));
                    lisNotas.add(not);
                } while (cursor.moveToNext());

                return lisNotas;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Nota consultarNotas(String[] id){
        try {
            Cursor cursor = db.query("nota", camposNota, "cod_materia = ? AND carnet = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Nota not = new Nota();
                not.setCod_materia(cursor.getString(0));
                not.setCarnet(cursor.getString(1));
                not.setCalificacion(cursor.getDouble(2));
                return not;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
