package com.example.serviciosocial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.serviciosocial.estado.Estado;
import com.example.serviciosocial.materia.Materia;
import com.example.serviciosocial.nota.Nota;
import com.example.serviciosocial.recordAcademico.RecordAcademico;

import java.util.ArrayList;

public class ControlBD {
    private static final String[] camposMateria = new String[] {"cod_materia", "id_area", "nombre_materia"};
    private static final String[] camposEstado = new String[] {"id_estado", "estado"};
    private static final String[] camposRecord = new String[] {"id_record", "carnet", "id_area", "materias_aprobadas", "progreso", "promedio"};
    private static final String[] camposNota = new String[] {"cod_materia", "carnet", "calificacion"};
    //Aqui van agregando los de las demas tablas

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlBD(Context ctx){
        this.context = ctx;
        this.DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String BASE_DATOS = "proyecto.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context){
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            try{
                //Agregar los CREATE TABLE
                db.execSQL("CREATE TABLE materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2) NOT NULL, nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('iai115', 'pr', 'introduccion');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('mat115', 'cb', 'matematica i');");
                db.execSQL("INSERT INTO materia (cod_materia, id_area, nombre_materia) VALUES ('abc115', 'pt', 'avver');");

                db.execSQL("CREATE TABLE estado (id_estado INTEGER NOT NULL, estado CHAR(25) NOT NULL, PRIMARY KEY (id_estado));");
                db.execSQL("INSERT INTO estado (estado) VALUES ('Finalizado');");
                db.execSQL("INSERT INTO estado (estado) VALUES ('En proceso');");
                db.execSQL("INSERT INTO estado (estado) VALUES ('Asignado');");

                db.execSQL("CREATE TABLE record_academico (id_record INTEGER NOT NULL, carnet CHAR(8), id_area CHAR(2), materias_aprobadas INTEGER NOT NULL, progreso DECIMAL(5,2), promedio DECIMAL(4,2) NOT NULL, PRIMARY KEY (id_record));");
                db.execSQL("INSERT INTO record_academico (carnet, id_area, materias_aprobadas, progreso, promedio) VALUES ('hg19010', 'pr', 0, 25, 6.5);");
                db.execSQL("INSERT INTO record_academico (carnet, id_area, materias_aprobadas, progreso, promedio) VALUES ('aa19012', 'cb', 0, 25, 6.5);");
                db.execSQL("INSERT INTO record_academico (carnet, id_area, materias_aprobadas, progreso, promedio) VALUES ('cc19114', 'pt', 0, 25, 6.5);");

                db.execSQL("CREATE TABLE nota (cod_materia CHAR(6) NOT NULL, carnet CHAR(8) NOT NULL, calificacion DECIMAL(4,2), PRIMARY KEY (cod_materia, carnet));");
                db.execSQL("INSERT INTO nota (cod_materia, carnet, calificacion) VALUES ('iai115', 'hg19010', 2.5);");
                db.execSQL("INSERT INTO nota (cod_materia, carnet, calificacion) VALUES ('mat115', 'aa19012', 9);");
                db.execSQL("INSERT INTO nota (cod_materia, carnet, calificacion) VALUES ('abc115', 'cc19114', 7.5);");

                //Agregar los triggers
                //db.execSQL("CREATE TRIGGER materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2), nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            // TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
    }

    public void cerrar(){
        DBHelper.close();
    }


    //INSERTS
    public String insertar(Materia materia){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues mate = new ContentValues();
        mate.put("cod_materia", materia.getCod_materia());
        mate.put("id_area", materia.getId_area());
        mate.put("nombre_materia", materia.getNombre_materia());
        contador = db.insert("materia", null, mate);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Estado estado){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues est = new ContentValues();
        est.put("estado", estado.getEstado());
        contador = db.insert("estado", null, est);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String insertar(Nota nota){
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;

        ContentValues not = new ContentValues();
        not.put("cod_materia", nota.getCod_materia());
        not.put("carnet", nota.getCarnet());
        not.put("calificacion", nota.getCalificacion());
        contador = db.insert("nota", null, not);

        if(contador == -1 || contador == 0){
            regInsertados = "Error al insertar el registro. Registro duplicado. Verificar insercion";
        } else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }


    //UPDATES
    public String actualizar(Materia materia){
        try{
            String[] id = {materia.getCod_materia()};
            ContentValues cv = new ContentValues();
            cv.put("id_area", materia.getId_area());
            cv.put("nombre_materia", materia.getNombre_materia());
            db.update("materia", cv, "cod_materia = ?", id);

            return "Materia actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String actualizar(Estado estado){
        try{
            String[] id = {String.valueOf(estado.getId_estado())};
            ContentValues cv = new ContentValues();
            cv.put("estado", estado.getEstado());
            db.update("estado", cv, "id_estado = ?", id);

            return "Estado actualizado correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String actualizar(Nota nota){
        try{
            String[] id = {nota.getCod_materia(), nota.getCarnet()};
            ContentValues cv = new ContentValues();
            cv.put("calificacion", nota.getCalificacion());
            db.update("nota", cv, "cod_materia = ? AND carnet = ?", id);

            return "Nota actualizada correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    //DELETES
    public String eliminar(Materia materia){
        String regAfectados="filas afectadas = ";
        int contador=0;
        //Borrar la nota con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar la nota

        String[] id = {String.valueOf(materia.getCod_materia())};
        try{
            contador += db.delete("materia", "cod_materia = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String eliminar(Estado estado){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {String.valueOf(estado.getId_estado())};
        try{
            contador += db.delete("estado", "id_estado = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String eliminar(Nota nota){
        String regAfectados="filas afectadas = ";
        int contador=0;

        //Borrar los registros de las tablas relacionadas con un trigger/ o validar trigger que aparezca mensaje que antes tiene que borrar esos registros

        String[] id = {nota.getCod_materia(), nota.getCarnet()};
        try{
            contador += db.delete("nota", "cod_materia = ? AND carnet = ?", id);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //SELECTS
    public ArrayList<Materia> consultarMateria(){
        try {
            ArrayList<Materia> lisMaterias = new ArrayList<Materia>();
            Cursor cursor = db.query("materia", camposMateria, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Materia materia = new Materia();
                    materia.setCod_materia(cursor.getString(0));
                    materia.setId_area(cursor.getString(1));
                    materia.setNombre_materia(cursor.getString(2));
                    lisMaterias.add(materia);
                } while (cursor.moveToNext());

                return lisMaterias;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Materia consultarMateria(String[] id){
        try {
            Cursor cursor = db.query("materia", camposMateria, "cod_materia = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Materia materia = new Materia();
                materia.setCod_materia(cursor.getString(0));
                materia.setId_area(cursor.getString(1));
                materia.setNombre_materia(cursor.getString(2));
                return materia;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Estado> consultarEstados(){
        try {
            ArrayList<Estado> lisEstados = new ArrayList<Estado>();
            Cursor cursor = db.query("estado", camposEstado, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Estado estado = new Estado();
                    estado.setId_estado(cursor.getInt(0));
                    estado.setEstado(cursor.getString(1));
                    lisEstados.add(estado);
                } while (cursor.moveToNext());

                return lisEstados;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Estado consultarEstados(String[] id){
        try {
            Cursor cursor = db.query("estado", camposEstado, "id_estado = ?", id, null, null, null);
            if (cursor.moveToFirst()) {
                Estado estado = new Estado();
                estado.setId_estado(cursor.getInt(0));
                estado.setEstado(cursor.getString(1));
                return estado;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

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

    public ArrayList<Nota> consultarNotas(){
        try {
            ArrayList<Nota> lisNotas = new ArrayList<Nota>();
            Cursor cursor = db.query("nota", camposNota, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    Nota not = new Nota();
                    not.setCod_materia(cursor.getString(0));
                    not.setCarnet(cursor.getString(1));
                    not.setCalificacion(cursor.getDouble(2));
                    lisNotas.add(not);
                } while (cursor.moveToNext());

                return lisNotas;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ArrayList<Nota> consultarNotasPorCarrera(String[] idCarrera){
        try {
            ArrayList<Nota> lisNotas = new ArrayList<Nota>();
            Cursor cursor = db.rawQuery("SELECT nott.COD_MATERIA, nott.carnet, nott.CALIFICACION FROM nota nott, materia mat, area_carrera are, carrera car WHERE(nott.COD_MATERIA = mat.COD_MATERIA AND mat.ID_AREA = are.ID_AREA AND are.id_carrera = car.id_carrera AND car.id_carrera = ?);", idCarrera);

            if (cursor.moveToFirst()) {
                do {
                    Nota not = new Nota();
                    not.setCod_materia(cursor.getString(0));
                    not.setCarnet(cursor.getString(1));
                    not.setCalificacion(cursor.getDouble(2));
                    lisNotas.add(not);
                } while (cursor.moveToNext());

                return lisNotas;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public Nota consultarNotas(String[] id){
        try {
            Cursor cursor = db.query("nota", camposRecord, "cod_materia = ? AND carnet = ?", id, null, null, null);

            if (cursor.moveToFirst()) {
                Nota not = new Nota();
                not.setCod_materia(cursor.getString(0));
                not.setCarnet(cursor.getString(1));
                not.setCalificacion(cursor.getDouble(2));
                return not;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    //Llenado de la base inicial
    /*
    public String llenarBDCarnet(){
        final String[] VAcarnet = {"OO12035", "OF12044", "GG11098", "CC12021"};
        final String[] VAnombre = {"Carlos", "Pedro", "Sara", "Gabriela"};
        final String[] VAapellido = {"Orantes", "Ortiz", "Gonzales", "Coto"};
        final String[] VAsexo = {"M", "M", "F", "F"};

        final String[] VMcodmateria = {"MAT115", "PRN115", "IEC115", "TSI115"};
        final String[] VMnommateria = {"Matematica I", "Programacion I", "Ingenieria Economica", "Teoria de Sistemas"};
        final String[] VMunidadesval = {"4", "4", "4", "4"};

        final String[] VNcarnet = {"OO12035", "OF12044", "GG11098", "CC12021", "OO12035", "GG11098", "OF12044"};
        final String[] VNcodmateria = {"MAT115", "PRN115", "IEC115", "TSI115", "IEC115", "MAT115", "PRN115"};
        final String[] VNciclo = {"12016", "12016", "22016", "22016", "22016", "12016", "22016"};
        final float[] VNnotafinal = {7, 5, 8, 7, 6, 10, 7};

        abrir();
        db.execSQL("DELETE FROM alumno");
        db.execSQL("DELETE FROM materia");
        db.execSQL("DELETE FROM nota");

        Alumno alumno = new Alumno();
        for(int i=0; i<4; i++){
            alumno.setCarnet(VAcarnet[i]);
            alumno.setNombre(VAnombre[i]);
            alumno.setApellido(VAapellido[i]);
            alumno.setSexo(VAsexo[i]);
            alumno.setMatganadas(0);
            insertar(alumno);
        }

        Materia materia = new Materia();
        for(int i=0; i<4; i++){
            materia.setCodmateria(VMcodmateria[i]);
            materia.setNommateria(VMnommateria[i]);
            materia.setUnidadesval(VMunidadesval[i]);
            insertar(materia);
        }

        Nota nota = new Nota();
        for(int i=0; i<7; i++){
            nota.setCarnet(VNcarnet[i]);
            nota.setCodmateria(VNcodmateria[i]);
            nota.setCiclo(VNciclo[i]);
            nota.setNotafinal(VNnotafinal[i]);
            insertar(nota);
        }

        cerrar();
        return "Guardo correctamente";
    }
    */
}
