package com.example.serviciosocial.detalleResumenSocial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;
import com.example.serviciosocial.resumensocial.Resumensocial;

import java.util.ArrayList;

public class ControlDetalleResumen {
    private static final String[] camposDetalleResumen = new String[]{"id_det_res", "id_resumen", "id_proyecto", "fecha_inicio", "fecha_final", "horas_asignadas", "monto", "benef_indir", "benef_dir", "estado_det"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    ControlDetalleResumen(Context context){
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

    public String insertar(DetalleResumenSocial detalle) {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues res = new ContentValues();
        res.put("id_det_res", detalle.getId_det_res());
        res.put("id_resumen", detalle.getId_resumen());
        res.put("id_proyecto", detalle.getId_proyecto());
        res.put("fecha_inicio", detalle.getFecha_inicio());
        res.put("fecha_final", detalle.getFecha_final());
        res.put("horas_asignadas", detalle.getHoras_asignadas());
        res.put("monto", detalle.getMonto());
        res.put("benef_indir", detalle.getBenef_indir());
        res.put("benef_dir", detalle.getBenef_dir());
        res.put("estado_det", detalle.getEstado_det());
        contador = db.insert("det_res_ser_soc", null, res);

        if (contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro";
        }else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    public Cursor leerTodoDetalle(){
        String query = "SELECT * FROM det_res_ser_soc";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public String actualizar(DetalleResumenSocial detalle){
        try{
            String[] id = {String.valueOf(detalle.getId_det_res())};
            ContentValues res = new ContentValues();
            res.put("id_resumen", detalle.getId_resumen());
            res.put("id_proyecto", detalle.getId_proyecto());
            res.put("fecha_inicio", detalle.getFecha_inicio());
            res.put("fecha_final", detalle.getFecha_final());
            res.put("horas_asignadas", detalle.getHoras_asignadas());
            res.put("monto", detalle.getMonto());
            res.put("benef_indir", detalle.getBenef_indir());
            res.put("benef_dir", detalle.getBenef_dir());
            res.put("estado_det", detalle.getEstado_det());
            db.update("det_res_ser_soc", res, "id_det_res = ?", id);
            return "Detalle de resumen social actualizado correctamente";
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String eliminar(DetalleResumenSocial detalle){
        String regAfectados = "filas afectadas = ";
        int contador = 0;
        String[] id = {String.valueOf(detalle.getId_det_res())};
        try{
            contador += db.delete("det_res_ser_soc", "id_det_res", id);
            regAfectados +=contador;
            return regAfectados;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<DetalleResumenSocial> consultarDetalleResumen(){
        try{
            ArrayList<DetalleResumenSocial> listDetalle = new ArrayList<>();
            Cursor cursor = db.query("det_res_ser_soc", camposDetalleResumen, null, null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    DetalleResumenSocial res = new DetalleResumenSocial();
                    res.setId_det_res(cursor.getInt(0));
                    res.setId_resumen(cursor.getInt(1));
                    res.setId_proyecto(cursor.getInt(2));
                    res.setFecha_inicio(cursor.getString(3));
                    res.setFecha_final(cursor.getString(4));
                    res.setHoras_asignadas(cursor.getFloat(5));
                    res.setMonto(cursor.getFloat(6));
                    res.setBenef_indir(cursor.getInt(7));
                    res.setBenef_dir(cursor.getInt(8));
                    res.setEstado_det(cursor.getString(9));
                    listDetalle.add(res);
                }while(cursor.moveToNext());
                return listDetalle;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
