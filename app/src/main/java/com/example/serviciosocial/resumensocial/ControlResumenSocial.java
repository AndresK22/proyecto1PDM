package com.example.serviciosocial.resumensocial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlResumenSocial {

    private static final String[] camposResumen = new String[]{"id_resumen", "dui_docente", "carnet", "fecha_apertura_expediente", "fecha_emision_certificado", "observaciones"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlResumenSocial(Context context){
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

    public String insertar(Resumensocial resumen){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues res = new ContentValues();
        res.put("id_resumen", resumen.getId_resumen());
        res.put("dui_docente", resumen.getDui_docente());
        res.put("carnet", resumen.getCarnet());
        res.put("fecha_apertura_expediente", resumen.getFecha_apertura_expediente());
        res.put("fecha_emision_certificado", resumen.getFecha_emision_certificado());
        res.put("observaciones", resumen.getObservaciones());
        contador = db.insert("resumen_servicio_social", null, res);

        if (contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro";
        }else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    public Cursor leerTodoResumen(){
        String query = "SELECT * FROM resumen_servicio_social";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public String actualizar(Resumensocial resumen){
        try{
            String[] id = {String.valueOf(resumen.getId_resumen())};
            ContentValues r = new ContentValues();
            r.put("dui_docente", resumen.getId_resumen());
            r.put("carnet", resumen.getCarnet());
            r.put("fecha_apertura_expediente", resumen.getFecha_apertura_expediente());
            r.put("fecha_emision_certificado", resumen.getFecha_emision_certificado());
            r.put("observaciones", resumen.getObservaciones());
            db.update("resumen_servicio_social", r, "id_resumen = ?", id);
            return "Resumen social actualizado correctamente";
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public String eliminar(Resumensocial resumen){
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String[] id = {String.valueOf(resumen.getId_resumen())};
        try{
            contador += db.delete("resumen_servicio_social", "id_resumen = ?", id);
            regAfectados += contador;
            return regAfectados;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Resumensocial> consultarResumen(){
        try{
            ArrayList<Resumensocial> listResumen = new ArrayList<>();
            Cursor cursor = db.query("resumen_servicio_social", camposResumen, null, null, null, null, null);
            if(cursor.moveToFirst()){
                do{
                    Resumensocial res = new Resumensocial();
                    res.setId_resumen(cursor.getInt(0));
                    res.setDui_docente(cursor.getString(1));
                    res.setCarnet(cursor.getString(2));
                    res.setFecha_apertura_expediente(cursor.getString(3));
                    res.setFecha_emision_certificado(cursor.getString(4));
                    res.setObservaciones(cursor.getString(5));
                    listResumen.add(res);
                }while(cursor.moveToNext());
                return listResumen;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
