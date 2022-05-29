package com.example.serviciosocial.detalleBitacora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlDetalleBitacora {


    private static final String[] camposDetalleBitacora = new String[]{"id_detalle_bitacora","id_bitacora","actividad","fecha_bitacora"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDetalleBitacora(Context context) {
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


    public String insertar(DetalleBitacora detalleBitacora){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues bita = new ContentValues();
        bita.put("id_detalle_bitacora", detalleBitacora.getId_detalle_bitacora());
        bita.put("id_bitacora", detalleBitacora.getId_bitacora());
        bita.put("actividad", detalleBitacora.getActividad());
        bita.put("fecha_bitacora", detalleBitacora.getFecha_bitacora());

        contador = db.insert("detalle_bitacora", null, bita);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    public String actualizar(DetalleBitacora detalleBitacora){
        try{
            String[] id = {String.valueOf(detalleBitacora.getId_detalle_bitacora())};

            ContentValues bi = new ContentValues();
            bi.put("id_bitacora", detalleBitacora.getId_bitacora());
            bi.put("actividad", detalleBitacora.getActividad());
            bi.put("fecha_bitacora",detalleBitacora.getFecha_bitacora());


            db.update("detalle_bitacora", bi, "id_detalle_bitacora = ?", id);

            return "Detalle Actualizado correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }



    public String eliminar(DetalleBitacora detalleBitacora){
        String regAfectados="filas afectadas = ";
        int contador=0;

        String[] id = {String.valueOf(detalleBitacora.getId_detalle_bitacora())};
        try{
            contador += db.delete("detalle_bitacora", "id_detalle_bitacora = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cursor leerTodoDetalleBitacora(){
        String query = "SELECT * FROM detalle_bitacora";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    public ArrayList<DetalleBitacora> consultarDetalleBitacora(String idd){
        try {
            String[] id = {String.valueOf(idd)};

            ArrayList<DetalleBitacora> lisBita= new ArrayList<DetalleBitacora>();

            Cursor cursor = db.query("detalle_bitacora", camposDetalleBitacora, "id_bitacora = ?", id, null, null, null);


            if (cursor.moveToFirst()) {
                do {
                    DetalleBitacora bitacora = new DetalleBitacora();
                    bitacora.setId_detalle_bitacora(cursor.getLong(0));
                    bitacora.setId_bitacora(cursor.getLong(1));
                    bitacora.setActividad(cursor.getString(2));
                    bitacora.setFecha_bitacora(cursor.getString(3));

                    lisBita.add(bitacora);
                } while (cursor.moveToNext());

                return lisBita;
            } else {
                return null;
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
