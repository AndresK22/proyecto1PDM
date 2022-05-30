package com.example.serviciosocial.estudiante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlEstudiante {
    private static final String[] camposEstudiante = new String[] {"carnet", "nombres_estudiante", "apellidos_estudiante", "email_estudiante", "telefono_estudiante", "domicilio", "dui"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;
     public ControlEstudiante(Context context){
         this.context = context;
         DBHelper = new DataBaseHelper(this.context);
     }
     public void abrir() throws SQLException{
         db = DBHelper.getWritableDatabase();
         return;
     }
     public void abrirParaLeer() throws  SQLException {
         db = DBHelper.getReadableDatabase();
         return;
     }

     public void cerrar() { db.close();}

    //INSERT
    public String insertar(Estudiante estudiante){
         String regInsertados = "Registro Insertado No = ";
         long contador = 0;

        ContentValues est = new ContentValues();
        est.put("carnet", estudiante.getCarnet());
        est.put("nombres_estudiante", estudiante.getNombres_estudiante());
        est.put("apellidos_estudiante", estudiante.getApellidos_estudiante());
        est.put("email_estudiante", estudiante.getEmail_estudiante());
        est.put("telefono_estudiante", estudiante.getTelefono_estudiante());
        est.put("domicilio", estudiante.getDomicilio());
        est.put("dui", estudiante.getDui());
        contador = db.insert("estudiante", null, est);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro";
        }else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }
    //UPDATES
    public String actualizar(Estudiante e,String[] id_p){
        try{
            String[] id = {String.valueOf(e.getCarnet())};
            ContentValues cv = new ContentValues();
            cv.put("carnet", e.getCarnet());
            cv.put("nombres_estudiante", e.getNombres_estudiante());
            cv.put("apellidos_estudiante", e.getApellidos_estudiante());
            cv.put("email_estudiante", e.getEmail_estudiante());
            cv.put("telefono_estudiante", e.getTelefono_estudiante());
            cv.put("domicilio", e.getDomicilio());
            cv.put("dui", e.getDui());
            db.update("estudiante", cv, "carnet = ?", id_p);
            return "Registro Actualizado Correctamente ";
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    //DELETES
    public String eliminar(Estudiante estudiante){
         String regAfectados = "filas afectadas = ";
         int contador = 0;
         String [] id = {String.valueOf(estudiante.getCarnet())};
         try{
             contador += db.delete("estudiante", "carnet = ?", id);
             regAfectados += contador;
             return regAfectados;
         }catch(SQLException e){
             e.printStackTrace();
         }
         return null;
    }
    //SELECTS
    public Cursor leerTodoEstudiante(){
        String query = "SELECT * FROM estudiante";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }
    public ArrayList<Estudiante> consultarEstudiante(){
         try{
             ArrayList<Estudiante> lisEstudiante = new ArrayList<Estudiante>();
             Cursor cursor = db.query("estudiante", camposEstudiante, null, null, null, null, null);

             if (cursor.moveToFirst()){
                 do{
                     Estudiante est = new Estudiante();
                     est.setCarnet(cursor.getString(0));
                     est.setNombres_estudiante(cursor.getString(1));
                     est.setApellidos_estudiante(cursor.getString(2));
                     est.setEmail_estudiante(cursor.getString(3));
                     est.setTelefono_estudiante(cursor.getString(4));
                     est.setDomicilio(cursor.getString(5));
                     est.setDui(cursor.getString(6));
                     lisEstudiante.add(est);
                 }while(cursor.moveToNext());
                 return lisEstudiante;
             }else{
                 return null;
             }
         }catch (SQLException e){
             e.printStackTrace();
         }
         return  null;
    }
}
