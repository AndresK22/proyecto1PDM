package com.example.serviciosocial.proyecto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;
import com.example.serviciosocial.carrera.Carrera;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;

public class ControlProyecto {

    private static final String[] camposProyecto = new String[] {"id_proyecto", "id_categoria", "id_modalidad", "dui_docente",
    "id_estado","id_carrera","id_area","nombre_proyecto","descripcion_proyecto","lugar","requisito_nota"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlProyecto(Context context) {
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
    public String insertar(Proyecto proyecto)
    {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues mod = new ContentValues();
        mod.put("id_proyecto", (Integer) null);
        mod.put("id_categoria", proyecto.getId_categoria());
        mod.put("id_categoria", proyecto.getId_categoria());
        mod.put("id_modalidad", proyecto.getId_modalidad());
        mod.put("dui_docente", proyecto.getDui_docente());
        mod.put("id_estado", proyecto.getId_estado());
        mod.put("id_carrera", proyecto.getId_carrera());
        mod.put("id_area", proyecto.getId_area());
        mod.put("nombre_proyecto", proyecto.getNombre_proyecto());
        mod.put("descripcion_proyecto", proyecto.getDescripcion_proyecto());
        mod.put("lugar", proyecto.getLugar());
        mod.put("requisito_nota", proyecto.getRequisito_nota());

        contador = db.insert("proyecto", null, mod);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    //UPDATES
    public String actualizar(Proyecto proyecto){
        try
        {
            String[] id = {String.valueOf((proyecto.getId_proyecto()))};
            ContentValues cv = new ContentValues();
            cv.put("id_categoria", proyecto.getId_categoria());
            cv.put("id_modalidad", proyecto.getId_modalidad());
            cv.put("dui_docente", proyecto.getDui_docente());
            cv.put("id_estado", proyecto.getId_estado());
            cv.put("id_carrera", proyecto.getId_carrera());
            cv.put("id_area", proyecto.getId_area());
            cv.put("nombre_proyecto", proyecto.getNombre_proyecto());
            cv.put("descripcion_proyecto", proyecto.getDescripcion_proyecto());
            cv.put("lugar", proyecto.getLugar());
            cv.put("requisito_nota", proyecto.getRequisito_nota());
            db.update("proyecto", cv, "id_proyecto = ?",  id);

            return "Registro Actualizado Correctamente";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    //DELETES
    public String eliminar(Proyecto proyecto){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(proyecto.getId_proyecto())};
        try{
            contador += db.delete("proyecto", "id_proyecto = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //SELECTS

    public Cursor leerTodoProyecto(){
        String query = "SELECT * FROM proyecto";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }

    //Dropdown

    public Cursor leerProyectosAsignados(){
        String query = "SELECT * FROM proyecto where id_estado=1";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }
    public Cursor leerProyectosNoAsignados(){
        String query = "SELECT * FROM proyecto where id_estado=2";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }


    public ArrayList<Proyecto> consultarProyecto(){
        try{
            ArrayList<Proyecto> lisPro = new ArrayList<>();
            Cursor cursor = db.query("proyecto", camposProyecto, null, null, null, null, null);

            if(cursor.moveToFirst()){
                do{
                    Proyecto proyecto= new Proyecto();
                    proyecto.setId_proyecto(cursor.getInt(0));
                    proyecto.setId_categoria(cursor.getInt(1));
                    proyecto.setId_modalidad(cursor.getInt(2));
                    proyecto.setDui_docente(cursor.getString(3));
                    proyecto.setId_estado(cursor.getInt(4));
                    proyecto.setId_carrera(cursor.getString(5));
                    proyecto.setId_area(cursor.getString(6));
                    proyecto.setNombre_proyecto(cursor.getString(7));
                    proyecto.setDescripcion_proyecto(cursor.getString(8));
                    proyecto.setLugar(cursor.getString(9));
                    proyecto.setRequisito_nota(cursor.getInt(10));

                    lisPro.add(proyecto);
                }while(cursor.moveToNext());

                return lisPro;
            }else{
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
