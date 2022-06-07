package com.example.serviciosocial.proyecto_estudiante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;
import com.example.serviciosocial.categoria.Categoria;
import com.example.serviciosocial.materia.Materia;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;

public class ControlEstudianteProyecto {

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlEstudianteProyecto(Context context) {
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
    public String insertar(Estudiantes_Proyecto estudiantes_proyecto)
    {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues cate = new ContentValues();
        cate.put("id_proyecto",estudiantes_proyecto.getId_proyecto());
        cate.put("carnet", estudiantes_proyecto.getCarnet());
        contador = db.insert("estudiantes_proyecto", null, cate);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
            actualizarEstado(estudiantes_proyecto.getId_proyecto());
        }
        return regInsertados;
    }


    //UPDATE
    public String actualizar(Estudiantes_Proyecto estudiantes_proyecto){
        try
        {
            String[] id = {String.valueOf((estudiantes_proyecto.getId_proyecto()))};
            ContentValues cv = new ContentValues();
            cv.put("carnet", estudiantes_proyecto.getCarnet());
            db.update("estudiantes_proyecto", cv, "id_proyecto = ?",  id);

            return "Registro Actualizado Correctamente";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public String actualizarEstado(int id_proyecto){
        try{
            String[] id = {String.valueOf(id_proyecto)};
            ContentValues cv = new ContentValues();
            cv.put("id_estado", 1);
            db.update("proyecto", cv, "id_proyecto = ?", id);

            return "Estado actualizado";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    //DELETE
    public String eliminar(Estudiantes_Proyecto estudiantes_proyecto){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(estudiantes_proyecto.getId_proyecto()),String.valueOf(estudiantes_proyecto.getCarnet())};
        try{
            contador += db.delete("estudiantes_proyecto", "id_proyecto = ? AND carnet = ?",id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //SELECTS
    public Cursor leerEstudiantesProyecto(int id_proyecto){
        String query = "SELECT a.id_proyecto, a.carnet, b.nombres_estudiante, b.apellidos_estudiante,b.email_estudiante,b.telefono_estudiante FROM estudiantes_proyecto a INNER JOIN estudiante b ON a.carnet = b.carnet WHERE id_proyecto = "+ id_proyecto+";";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }






}
