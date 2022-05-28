package com.example.serviciosocial.categoria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.serviciosocial.DataBaseHelper;
import com.example.serviciosocial.login.Usuario;
import com.example.serviciosocial.modalidad.Modalidad;

import java.util.ArrayList;

public class ControlCategoria {

    private static final String[] camposCategoria = new String[] {"id_categoria", "nombre_categoria"};
    private static final String[] camposModalidad = new String[] {"id_modalidad", "nombre_modalidad"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlCategoria(Context context) {
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
    public String insertar(Categoria categoria)
    {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues cate = new ContentValues();
        cate.put("id_categoria", (Integer) null);
        cate.put("nombre_categoria", categoria.getNombre_categoria());
        contador = db.insert("categoria", null, cate);
        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //UPDATE
    public String actualizar(Categoria categoria){
        try
        {
            String[] id = {String.valueOf((categoria.getId_categoria()))};
            ContentValues cv = new ContentValues();
            cv.put("nombre_categoria", categoria.getNombre_categoria());
            db.update("categoria", cv, "id_categoria = ?",  id);

            return "Registro Actualizado Correctamente";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //DELETE
    public String eliminar(Categoria categoria){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(categoria.getId_categoria())};
        try{
            contador += db.delete("categoria", "id_categoria = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //SELECTS
    public Cursor leerTodoCategoria(){
        String query = "SELECT * FROM categoria";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }
    //Dropdown
    public ArrayList<Categoria> consultarCategoria(){
        try {
            ArrayList<Categoria> lisCategoria = new ArrayList<Categoria>();
            Cursor cursor = db.query("categoria", camposCategoria, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Categoria categoria = new Categoria();
                    categoria.setId_categoria(cursor.getInt(0));
                    categoria.setNombre_categoria(cursor.getString(1));
                    lisCategoria.add(categoria);
                } while (cursor.moveToNext());

                return lisCategoria;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Modalidad> consultarModalidad(){
        try {
            ArrayList<Modalidad> lisModalidad = new ArrayList<Modalidad>();
            Cursor cursor = db.query("modalidad", camposModalidad, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Modalidad modalidad = new Modalidad();
                    modalidad.setId_modalidad(cursor.getInt(0));
                    modalidad.setNombre_modalidad(cursor.getString(1));
                    lisModalidad.add(modalidad);
                } while (cursor.moveToNext());

                return lisModalidad;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }




}
