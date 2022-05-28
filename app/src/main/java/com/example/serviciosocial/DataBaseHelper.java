package com.example.serviciosocial;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String NOMBRE_BASEDATOS = "proyectoServicioSocial.s3db";
    private  static final int VERSION_BASEDATOS = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, NOMBRE_BASEDATOS, null,VERSION_BASEDATOS);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            //TABLAS PARA INICIO DE SESION
            db.execSQL("create table USUARIO (" +
                    "ID_USUARIO           INTEGER primary key autoincrement," +
                    "NOMBRE_USUARIO       VARCHAR(30)          not null," +
                    "CLAVE                CHAR(5)              not null);"
            );

            db.execSQL("create table OPCION_CRUD (" +
                    "ID_OPCION            CHAR(3)              not null," +
                    "DESC_OPCION          VARCHAR(30)          not null," +
                    "NUM_CRUD             INTEGER              not null," +
                    "primary key (ID_OPCION));"
            );

            db.execSQL("create table ACCESO_USUARIO (" +
                    "ID_OPCION            CHAR(3)              not null," +
                    "ID_USUARIO           INTEGER              not null," +
                    "primary key (ID_OPCION, ID_USUARIO)," +
                    "foreign key (ID_USUARIO)" +
                    "      references USUARIO (ID_USUARIO)," +
                    "foreign key (ID_OPCION)" +
                    "      references OPCION_CRUD (ID_OPCION)" +
                    ");"
            );

            db.execSQL("create unique index ACCESO_USUARIO_PK on ACCESO_USUARIO (" +
                    "ID_OPCION ASC," +
                    "ID_USUARIO ASC);"
            );

            db.execSQL("create  index ACCESO_USUARIO2_FK on ACCESO_USUARIO (" +
                    "ID_USUARIO ASC);"
            );

            db.execSQL("create  index ACCESO_USUARIO_FK on ACCESO_USUARIO (" +
                    "ID_OPCION ASC);"
            );
            db.execSQL("CREATE TABLE area_carrera(id_area CHAR(2) NOT NULL,id_carrera CHAR(6) NOT NULL,descrip_area VARCHAR(255) NOT NULL, PRIMARY KEY (id_area,id_carrera));");
            db.execSQL("CREATE TABLE docente(dui_docente CHAR(10) NOT NULL PRIMARY KEY,nombres_docente VARCHAR(100) NOT NULL,apellidos_docente VARCHAR(100) NOT NULL, email_docente VARCHAR(50), telefono_docente VARCHAR(9));");
            db.execSQL("CREATE TABLE bitacora(id_bitacora INTEGER NOT NULL,id_proyecto INTEGER NOT NULL,carnet CHAR(8) NOT NULL, mes VARCHAR(12),total_horas_realizadas REAL(4,1) ,PRIMARY KEY (id_bitacora,id_proyecto,carnet));");
            db.execSQL("CREATE TABLE detalle_bitacora(id_detalle_bitacora INTEGER NOT NULL,id_bitacora INTEGER NOT NULL,actividad VARCHAR(255) NOT NULL,fecha_bitacora VARCHAR(10),PRIMARY KEY (id_detalle_bitacora,id_bitacora));");
            db.execSQL("CREATE TABLE categoria (id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, nombre_categoria CHAR(25) NOT NULL);");
            db.execSQL("CREATE TABLE modalidad (id_modalidad INTEGER PRIMARY KEY AUTOINCREMENT, nombre_modalidad CHAR(25) NOT NULL);");
            db.execSQL("CREATE TABLE proyecto (id_proyecto INTEGER PRIMARY KEY AUTOINCREMENT, id_categoria INTEGER NOT NULL,id_modalidad INTEGER NOT NULL,dui_docente CHAR(10) NOT NULL, id_estado INTEGER NOT NULL, id_carrera INTEGER NOT NULL,id_area CHAR(2) NOT NULL,  nombre_proyecto VARCHAR(100) NOT NULL,descripcion_proyecto VARCHAR(100),lugar VARCHAR(100),requisito_nota real(4,2));");
            db.execSQL("CREATE TABLE materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2) NOT NULL, nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
            db.execSQL("CREATE TABLE estado (id_estado INTEGER NOT NULL, estado CHAR(25) NOT NULL, PRIMARY KEY (id_estado));");
            db.execSQL("CREATE TABLE record_academico (id_record INTEGER NOT NULL, carnet CHAR(7), id_area CHAR(2), materias_aprobadas INTEGER NOT NULL, progreso DECIMAL(5,2), promedio DECIMAL(4,2) NOT NULL, PRIMARY KEY (id_record));");
            db.execSQL("CREATE TABLE nota (cod_materia CHAR(6) NOT NULL, carnet CHAR(7) NOT NULL, calificacion DECIMAL(4,2), PRIMARY KEY (cod_materia, carnet));");
            db.execSQL("CREATE TABLE carrera (id_carrera char(6) primary key not null, nombre_carrera varchar(50) not null, total_materias  integer not null);");
            db.execSQL("CREATE TABLE estudiante (carnet char(7) primary key not null, nombres_estudiante varchar(100), apellidos_estudiante varchar(100), email_estudiante varchar(50), telefono_estudiante varchar(9), domicilio VARCHAR(200), dui CHAR(10));");
            db.execSQL("CREATE TABLE resumen_servicio_social ( id_resumen INTEGER  PRIMARY KEY NOT NULL, dui_docente CHAR(10), carnet CHAR(7), fecha_apertura_expediente VARCHAR(10), fecha_emision_certificado VARCHAR(10), observaciones VARCHAR(255));");
            db.execSQL("CREATE TABLE det_res_ser_soc ( id_det_res INTEGER NOT NULL, id_resumen INTEGER, id_proyecto INTEGER, fecha_inicio VARCHAR(10), fecha_final VARCHAR(10), horas_asignadas DECIMAL(4,1), monto DECIMAL(8,2), benef_indir INTEGER, benef_dir INTEGER, estado_det VARCHAR(30));");
            db.execSQL("CREATE TABLE estudiantes_proyecto (id_proyecto INTEGER NOT NULL, carnet CHAR(7) NOT NULL ,PRIMARY KEY (id_proyecto,carnet));");

            //Agregar los triggers
            //db.execSQL("CREATE TRIGGER materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2), nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
