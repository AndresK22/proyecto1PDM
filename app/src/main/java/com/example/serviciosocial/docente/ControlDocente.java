package com.example.serviciosocial.docente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlDocente {
    private static final String[] camposDocente = new String[]{"dui_docente","nombres_docente","apellidos_docente","email_docente","telefono_docente"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDocente(Context context) {
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

    public String insertar(Docente docente){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("dui_docente", docente.getDui_docente());
        doc.put("nombres_docente", docente.getNombres_docente());
        doc.put("apellidos_docente", docente.getApellidos_docente());
        doc.put("email_docente", docente.getEmail_docente());
        doc.put("telefono_docente", docente.getTelefono_docente());

        contador = db.insert("docente", null, doc);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Docente docente, String[] id_p){
        try{
            ContentValues doc = new ContentValues();
            doc.put("dui_docente", docente.getDui_docente());
            doc.put("nombres_docente", docente.getNombres_docente());
            doc.put("apellidos_docente", docente.getApellidos_docente());
            doc.put("email_docente", docente.getEmail_docente());
            doc.put("telefono_docente", docente.getTelefono_docente());

            db.update("docente", doc, "dui_docente = ?", id_p);

            return "Docente actualizado correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Cursor leerTodoDocente(){
        String query = "SELECT * FROM docente";
        SQLiteDatabase db;
        db = DBHelper.getReadableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
    }


    public String eliminar(Docente docente){
        String regAfectados="filas afectadas = ";
        int contador=0;

        String[] id = {String.valueOf(docente.getDui_docente())};
        try{
            contador += db.delete("docente", "dui_docente = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Docente> consultarDocente(){
        try {
            ArrayList<Docente> lisDoc= new ArrayList<Docente>();
            Cursor cursor = db.query("docente", camposDocente, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Docente docente = new Docente();
                    docente.setDui_docente(cursor.getString(0));
                    docente.setNombres_docente(cursor.getString(1));
                    docente.setApellidos_docente(cursor.getString(2));
                    docente.setEmail_docente(cursor.getString(3));
                    docente.setTelefono_docente(cursor.getString(4));

                    lisDoc.add(docente);
                } while (cursor.moveToNext());

                return lisDoc;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Docente consultarDocente(String[] id){
        try {
            Cursor cursor = db.query("docente", camposDocente, "dui_docente = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Docente docente = new Docente();
                docente.setDui_docente(cursor.getString(0));
                docente.setNombres_docente(cursor.getString(1));
                docente.setApellidos_docente(cursor.getString(2));
                docente.setEmail_docente(cursor.getString(3));
                docente.setTelefono_docente(cursor.getString(4));

                return docente;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
