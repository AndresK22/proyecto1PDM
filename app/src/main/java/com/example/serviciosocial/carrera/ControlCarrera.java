package com.example.serviciosocial.carrera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlCarrera {

    private static final String[] camposCarrera = new String[] {"id_carrera", "nombre_carrera", "total_materias"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlCarrera(Context context){
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
    public String insertar(Carrera carrera){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues car = new ContentValues();
        car.put("id_carrera", carrera.getId_carrera());
        car.put("nombre_carrera", carrera.getNombre_carrera());
        car.put("total_materias", carrera.getTotal_materias());
        contador = db.insert("carrera", null, car);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //UPDATES
    public String actualizar(Carrera carrera){
        try{
            //String[] id = {carrera.getId_carrera()};
            //ContentValues cv = new ContentValues();
            //cv.put("id_carrera", carrera.getId_carrera());
            //cv.put("nombre_carrera", carrera.getNombre_carrera());
            //cv.put("total_materias", (Integer) carrera.getTotal_materias());
            //db.update("carrera", cv, "id_carrera = ?", id);
            String id = carrera.getId_carrera();
            String nombre = carrera.getNombre_carrera();
            int total = carrera.getTotal_materias();
            String sql = "update carrera set id_carrera = '"+id+"', nombre_carrera = '"+nombre+"', total_materias = "+total+" where id_carrera = '"+id+"'";
            db.execSQL(sql);
            return "Registro Actualizado Correctamente ";
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //DELETES
    public String eliminar(Carrera carrera){
        String regAfectados="filas afectadas = ";
        int contador = 0;

        String[] id = {String.valueOf(carrera.getId_carrera())};
        try{
            contador += db.delete("carrera", "id_carrera = ?", id);
            regAfectados += contador;

            return regAfectados;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //SELECTS
    public Cursor leerTodoCarrera(){
        String query = "SELECT * FROM carrera";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }
    public ArrayList<Carrera> consultarCarrera(){
        try{
            ArrayList<Carrera> lisCarrera = new ArrayList<>();
            Cursor cursor = db.query("carrera", camposCarrera, null, null, null, null, null);

            if(cursor.moveToFirst()){
                do{
                    Carrera carrera = new Carrera();
                    carrera.setId_carrera(cursor.getString(0));
                    carrera.setNombre_carrera(cursor.getString(1));
                    carrera.setTotal_materias(cursor.getInt(2));
                    lisCarrera.add(carrera);
                }while(cursor.moveToNext());

                return lisCarrera;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
