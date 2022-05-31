package com.example.serviciosocial.bitacora;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlBitacora {
    private static final String[] camposBitacora = new String[]{"id_bitacora","id_proyecto","carnet","mes","total_horas_realizadas"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBitacora(Context context) {
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


    public String insertar(Bitacora bitacora){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues bita = new ContentValues();
        bita.put("id_bitacora", bitacora.getId_bitacora());
        bita.put("id_proyecto", bitacora.getId_proyecto());
        bita.put("carnet", bitacora.getCarnet());
        bita.put("mes", bitacora.getMes());
        bita.put("total_horas_realizadas", bitacora.getTotal_horas_realizadas());

        contador = db.insert("bitacora", null, bita);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    public String actualizar(Bitacora bitacora,String[] id_p){
        try{
            ContentValues bi = new ContentValues();
            bi.put("id_bitacora",bitacora.getId_bitacora());
            bi.put("id_proyecto", bitacora.getId_proyecto());
            bi.put("carnet", bitacora.getCarnet());
            bi.put("mes", bitacora.getMes());
            bi.put("total_horas_realizadas", bitacora.getTotal_horas_realizadas());


            db.update("bitacora", bi, "id_bitacora = ?", id_p);

            return "Bitacora Actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String eliminar(Bitacora bitacora){
        String regAfectados="filas afectadas = ";
        int contador=0;

        if(verificarIntegridadBitacora(bitacora,1)){
            regAfectados="La bitacora no se puede eliminar, contiene registros";
            return regAfectados;

        }else{
            String[] id = {String.valueOf(bitacora.getId_bitacora())};
            try{
                contador += db.delete("bitacora", "id_bitacora = ?", id);
                regAfectados += contador;

                return regAfectados;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;

        }
    }

    public ArrayList<Bitacora> consultarBitacora(){
        try {
            ArrayList<Bitacora> lisBita= new ArrayList<Bitacora>();
            Cursor cursor = db.query("bitacora", camposBitacora, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Bitacora bitacora = new Bitacora();
                    bitacora.setId_bitacora(cursor.getLong(0));
                    bitacora.setId_proyecto(cursor.getLong(1));
                    bitacora.setCarnet(cursor.getString(2));
                    bitacora.setMes(cursor.getString(3));
                    bitacora.setTotal_horas_realizadas(cursor.getFloat(4));

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
    public Bitacora consultarBitacora(String[] id){
        try {
            Cursor cursor = db.query("bitacora", camposBitacora, "id_bitacora = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Bitacora bitacora = new Bitacora();
                bitacora.setId_bitacora(cursor.getLong(0));
                bitacora.setId_proyecto(cursor.getLong(1));
                bitacora.setCarnet(cursor.getString(2));
                bitacora.setMes(cursor.getString(3));
                bitacora.setTotal_horas_realizadas(cursor.getFloat(4));
                return bitacora;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Cursor leerTodoBitacora(){
        String query = "SELECT * FROM bitacora";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }


    private boolean verificarIntegridadBitacora(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:
            {
                Bitacora bitacora= (Bitacora)dato;
                Cursor cursor1= db.query(true,"detalle_bitacora", new String[]{"id_detalle_bitacora"},"id_bitacora='"+bitacora.getId_bitacora()+"'",null,
                        null, null, null, null);

                if(cursor1.moveToFirst()){
                    return true;
                }
                return false;
            }
            default:return false;
        }}


}
