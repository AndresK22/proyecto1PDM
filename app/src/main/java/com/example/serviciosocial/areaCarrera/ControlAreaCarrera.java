package com.example.serviciosocial.areaCarrera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlAreaCarrera {

    private static final String[] camposAreaCarrera = new String[]{"id_area","id_carrera","descrip_area"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlAreaCarrera(Context context) {
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

    public String insertar(AreaCarrera areaCarrera){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues area = new ContentValues();
        area.put("id_area", areaCarrera.getId_area());
        area.put("id_carrera", areaCarrera.getId_carrera());
        area.put("descrip_area", areaCarrera.getDescrip_area());
        contador = db.insert("area_carrera", null, area);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public Cursor leerTodoAreaCarrera(){
        String query = "SELECT * FROM area_carrera";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    public String actualizar(AreaCarrera areaCarrera,String[] id_p){
        try{
            ContentValues a = new ContentValues();
            a.put("id_area",areaCarrera.getId_area());
            a.put("id_carrera", areaCarrera.getId_carrera());
            a.put("descrip_area", areaCarrera.getDescrip_area());
            db.update("area_carrera", a, "id_area = ?", id_p);

            return "Area Actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String eliminar(AreaCarrera areaCarrera){
        String regAfectados="filas afectadas = ";
        int contador=0;
        String[] id = {String.valueOf(areaCarrera.getId_area())};
        try{
            contador += db.delete("area_carrera", "id_area = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<AreaCarrera> consultarArea(){
        try {
            ArrayList<AreaCarrera> lisArea= new ArrayList<AreaCarrera>();
            Cursor cursor = db.query("area_carrera", camposAreaCarrera, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    AreaCarrera areaCarrera = new AreaCarrera();
                    areaCarrera.setId_area(cursor.getString(0));
                    areaCarrera.setId_carrera(cursor.getString(1));
                    areaCarrera.setDescrip_area(cursor.getString(2));
                    lisArea.add(areaCarrera);
                } while (cursor.moveToNext());

                return lisArea;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<AreaCarrera> consultarAreaCarrera(String[] id){
        try {
            ArrayList<AreaCarrera> lisArea= new ArrayList<AreaCarrera>();
            Cursor cursor = db.query("area_carrera", camposAreaCarrera, "id_carrera = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    AreaCarrera areaCarrera = new AreaCarrera();
                    areaCarrera.setId_area(cursor.getString(0));
                    areaCarrera.setId_carrera(cursor.getString(1));
                    areaCarrera.setDescrip_area(cursor.getString(2));
                    lisArea.add(areaCarrera);
                } while (cursor.moveToNext());

                return lisArea;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public AreaCarrera consultarArea(String[] id){
        try {
            Cursor cursor = db.query("area_carrera", camposAreaCarrera, "id_area = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                AreaCarrera areaCarrera = new AreaCarrera();
                areaCarrera.setId_area(cursor.getString(0));
                areaCarrera.setId_carrera(cursor.getString(1));
                areaCarrera.setDescrip_area(cursor.getString(2));
                return areaCarrera;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public AreaCarrera consultarAreaPorCarrera(String[] id){
        try {
            Cursor cursor = db.query("area_carrera", camposAreaCarrera, "id_carrera = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                AreaCarrera areaCarrera = new AreaCarrera();
                areaCarrera.setId_area(cursor.getString(0));
                areaCarrera.setId_carrera(cursor.getString(1));
                areaCarrera.setDescrip_area(cursor.getString(2));
                return areaCarrera;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
