package com.example.serviciosocial.recordAcademico;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.serviciosocial.DataBaseHelper;

import java.util.ArrayList;

public class ControlRecord {
    private static final String[] camposRecord = new String[] {"id_record", "carnet", "id_area", "materias_aprobadas", "progreso", "promedio"};

    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlRecord(Context context) {
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

    //SELECTS
    public ArrayList<RecordAcademico> consultarRecords(){
        try {
            ArrayList<RecordAcademico> lisRecords = new ArrayList<RecordAcademico>();
            Cursor cursor = db.query("record_academico", camposRecord, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    RecordAcademico record = new RecordAcademico();
                    record.setId_record(cursor.getInt(0));
                    record.setCarnet(cursor.getString(1));
                    record.setId_area(cursor.getString(2));
                    record.setMaterias_aprobadas(cursor.getInt(3));
                    record.setProgreso(cursor.getDouble(4));
                    record.setPromedio(cursor.getDouble(5));
                    lisRecords.add(record);
                } while (cursor.moveToNext());

                return lisRecords;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<RecordAcademico> consultarRecordsPorCarrera(String[] idCarrera){
        try {
            ArrayList<RecordAcademico> lisRecords = new ArrayList<RecordAcademico>();
            Cursor cursor = db.rawQuery("SELECT rec.id_record, rec.carnet, rec.id_area, rec.materias_aprobadas, rec.progreso, rec.promedio FROM record_academico rec, area_carrera are, carrera car WHERE(rec.id_area = are.id_area AND are.id_carrera = car.id_carrera AND car.id_carrera = ?);", idCarrera);

            if (cursor.moveToFirst()) {
                do {
                    RecordAcademico record = new RecordAcademico();
                    record.setId_record(cursor.getInt(0));
                    record.setCarnet(cursor.getString(1));
                    record.setId_area(cursor.getString(2));
                    record.setMaterias_aprobadas(cursor.getInt(3));
                    record.setProgreso(cursor.getDouble(4));
                    record.setPromedio(cursor.getDouble(5));
                    lisRecords.add(record);
                } while (cursor.moveToNext());

                return lisRecords;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public RecordAcademico consultarRecords(String[] id){
        try {
            Cursor cursor = db.query("record_academico", camposRecord, "id_record = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                RecordAcademico record = new RecordAcademico();
                record.setId_record(cursor.getInt(0));
                record.setCarnet(cursor.getString(1));
                record.setId_area(cursor.getString(2));
                record.setMaterias_aprobadas(cursor.getInt(3));
                record.setProgreso(cursor.getDouble(4));
                record.setPromedio(cursor.getDouble(5));
                return record;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
