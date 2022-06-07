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
            db.execSQL("CREATE TABLE bitacora(id_bitacora INTEGER NOT NULL,id_proyecto INTEGER NOT NULL,carnet CHAR(8) NOT NULL, mes VARCHAR(12),total_horas_realizadas REAL(4,1) ,PRIMARY KEY (id_bitacora));");
            db.execSQL("CREATE TABLE detalle_bitacora(id_detalle_bitacora INTEGER NOT NULL,id_bitacora INTEGER NOT NULL,actividad VARCHAR(255) NOT NULL,fecha_bitacora VARCHAR(10),PRIMARY KEY (id_detalle_bitacora));");
            db.execSQL("CREATE TABLE categoria (id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, nombre_categoria CHAR(25) NOT NULL);");
            db.execSQL("CREATE TABLE modalidad (id_modalidad INTEGER PRIMARY KEY AUTOINCREMENT, nombre_modalidad CHAR(25) NOT NULL);");
            db.execSQL("CREATE TABLE proyecto (id_proyecto INTEGER PRIMARY KEY AUTOINCREMENT, id_categoria INTEGER NOT NULL,id_modalidad INTEGER NOT NULL,dui_docente CHAR(10) NOT NULL, id_estado INTEGER NOT NULL, id_carrera INTEGER NOT NULL,id_area CHAR(2) NOT NULL,  nombre_proyecto VARCHAR(100) NOT NULL,descripcion_proyecto VARCHAR(100),lugar VARCHAR(100),requisito_nota real(4,2));");
            db.execSQL("CREATE TABLE materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2) NOT NULL, nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");
            db.execSQL("CREATE TABLE estado (id_estado INTEGER NOT NULL, estado CHAR(25) NOT NULL, PRIMARY KEY (id_estado));");
            db.execSQL("CREATE TABLE record_academico (id_record INTEGER NOT NULL, carnet CHAR(7), id_area CHAR(2), materias_aprobadas INTEGER NOT NULL, progreso DECIMAL(5,2), promedio DECIMAL(4,2) NOT NULL, PRIMARY KEY (id_record));");
            db.execSQL("CREATE TABLE nota (cod_materia CHAR(6) NOT NULL, carnet CHAR(7) NOT NULL, calificacion DECIMAL(4,2), PRIMARY KEY (cod_materia, carnet));");
            db.execSQL("CREATE TABLE carrera (id_carrera char(6) primary key not null, nombre_carrera varchar(50) not null, total_materias  integer not null);");
            db.execSQL("CREATE TABLE estudiante (carnet varchar(7) primary key not null, nombres_estudiante varchar(100), apellidos_estudiante varchar(100), email_estudiante varchar(50), telefono_estudiante varchar(9), domicilio VARCHAR(200), dui varchar(10));");
            db.execSQL("CREATE TABLE resumen_servicio_social ( id_resumen INTEGER  PRIMARY KEY NOT NULL, dui_docente CHAR(10), carnet CHAR(7), fecha_apertura_expediente VARCHAR(10), fecha_emision_certificado VARCHAR(10), observaciones VARCHAR(255));");
            db.execSQL("CREATE TABLE det_res_ser_soc ( id_det_res INTEGER PRIMARY KEY NOT NULL, id_resumen INTEGER, id_proyecto INTEGER, fecha_inicio VARCHAR(10), fecha_final VARCHAR(10), horas_asignadas DECIMAL(4,1), monto DECIMAL(8,2), benef_indir INTEGER, benef_dir INTEGER, estado_det VARCHAR(30));");
            db.execSQL("CREATE TABLE estudiantes_proyecto (id_proyecto INTEGER NOT NULL, carnet CHAR(7) NOT NULL ,PRIMARY KEY (id_proyecto,carnet));");

            db.execSQL("create unique index AREA_CARRERA_PK on AREA_CARRERA ( " +
                    "ID_AREA ASC " +
                    ");");

            db.execSQL("create  index TIENE4_FK on AREA_CARRERA ( " +
                    "ID_CARRERA ASC " +
                    ");");

            db.execSQL("create unique index BITACORA_PK on BITACORA ( " +
                    "ID_BITACORA ASC " +
                    ");");

            db.execSQL("create  index CONTIENE_FK on BITACORA ( " +
                    "ID_PROYECTO ASC " +
                    ");");

            db.execSQL("create  index ESCRIBE_FK on BITACORA ( " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create unique index CARRERA_PK on CARRERA ( " +
                    "ID_CARRERA ASC " +
                    ");");

            db.execSQL("create unique index CATEGORIA_PK on CATEGORIA ( " +
                    "ID_CATEGORIA ASC " +
                    ");");

            db.execSQL("create unique index DETALLE_BITACORA_PK on DETALLE_BITACORA ( " +
                    "ID_DETALLE_BITACORA ASC " +
                    ");");

            db.execSQL("create  index POSEE3_FK on DETALLE_BITACORA ( " +
                    "ID_BITACORA ASC " +
                    ");");

            db.execSQL("create unique index DET_RES_SER_SOC_PK on DET_RES_SER_SOC ( " +
                    "ID_DET_RES ASC " +
                    ");");

            db.execSQL("create  index POSEE_FK on DET_RES_SER_SOC ( " +
                    "ID_RESUMEN ASC " +
                    ");");

            db.execSQL("create  index CUENTA_CON_FK on DET_RES_SER_SOC ( " +
                    "ID_PROYECTO ASC " +
                    ");");

            db.execSQL("create unique index DOCENTE_PK on DOCENTE ( " +
                    "DUI_DOCENTE ASC " +
                    ");");

            db.execSQL("create unique index ESTADO_PK on ESTADO ( " +
                    "ID_ESTADO ASC " +
                    ");");

            db.execSQL("create unique index ESTUDIANTE_PK on ESTUDIANTE ( " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create unique index ESTUDIANTES_PROYECTO_PK on ESTUDIANTES_PROYECTO ( " +
                    "ID_PROYECTO ASC, " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create  index ESTUDIANTES_PROYECTO2_FK on ESTUDIANTES_PROYECTO ( " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create  index ESTUDIANTES_PROYECTO_FK on ESTUDIANTES_PROYECTO ( " +
                    "ID_PROYECTO ASC " +
                    ");");

            db.execSQL("create unique index MATERIA_PK on MATERIA ( " +
                    "COD_MATERIA ASC " +
                    ");");

            db.execSQL("create  index TIENE3_FK on MATERIA ( " +
                    "ID_AREA ASC " +
                    ");");

            db.execSQL("create unique index MODALIDAD_PK on MODALIDAD ( " +
                    "ID_MODALIDAD ASC " +
                    ");");

            db.execSQL("create unique index NOTA_PK on NOTA ( " +
                    "COD_MATERIA ASC, " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create  index NOTA2_FK on NOTA ( " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create  index NOTA_FK on NOTA ( " +
                    "COD_MATERIA ASC " +
                    ");");

            db.execSQL("create unique index OPCION_CRUD_PK on OPCION_CRUD ( " +
                    "ID_OPCION ASC " +
                    ");");

            db.execSQL("create unique index PROYECTO_PK on PROYECTO ( " +
                    "ID_PROYECTO ASC " +
                    ");");

            db.execSQL("create  index TIENE_FK on PROYECTO ( " +
                    "ID_CATEGORIA ASC " +
                    ");");

            db.execSQL("create  index TIENE2_FK on PROYECTO ( " +
                    "ID_MODALIDAD ASC " +
                    ");");

            db.execSQL("create  index ES_ASIGNADO_FK on PROYECTO ( " +
                    "DUI_DOCENTE ASC " +
                    ");");

            db.execSQL("create  index POSEE2_FK on PROYECTO ( " +
                    "ID_ESTADO ASC " +
                    ");");

            db.execSQL("create  index TIENE5_FK on PROYECTO ( " +
                    "ID_AREA ASC " +
                    ");");

            db.execSQL("create unique index RECORD_ACADEMICO_PK on RECORD_ACADEMICO ( " +
                    "ID_RECORD ASC " +
                    ");");

            db.execSQL("create  index DISPONE_DE_FK on RECORD_ACADEMICO ( " +
                    "CARNET ASC " +
                    ");");

            db.execSQL("create  index POSEE4_FK on RECORD_ACADEMICO ( " +
                    "ID_AREA ASC " +
                    ");");

            db.execSQL("create unique index RESUMEN_SERVICIO_SOCIAL_PK on RESUMEN_SERVICIO_SOCIAL ( " +
                    "ID_RESUMEN ASC " +
                    ");");

            db.execSQL("create  index VALIDA_FK on RESUMEN_SERVICIO_SOCIAL ( " +
                    "DUI_DOCENTE ASC " +
                    ");");

            db.execSQL("create  index CUENTA_CON2_FK on RESUMEN_SERVICIO_SOCIAL ( " +
                    "CARNET ASC " +
                    ");");
            
            db.execSQL("create unique index USUARIO_PK on USUARIO ( " +
                    "ID_USUARIO ASC " +
                    ");");


            //Agregar los triggers
            db.execSQL("CREATE TRIGGER fk_proyecto_modalidad BEFORE INSERT ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_modalidad FROM modalidad WHERE id_modalidad = NEW.id_modalidad) IS NULL) THEN RAISE(ABORT, 'No existe la modalidad') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_categoria BEFORE INSERT ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_categoria FROM categoria WHERE id_categoria = NEW.id_categoria) IS NULL) THEN RAISE(ABORT, 'No existe la categoria') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_docente BEFORE INSERT ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT dui_docente FROM docente WHERE dui_docente = NEW.dui_docente) IS NULL) THEN RAISE(ABORT, 'No existe el docente') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_estado BEFORE INSERT ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_estado FROM estado WHERE id_estado = NEW.id_estado) IS NULL) THEN RAISE(ABORT, 'No existe el estado') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_area BEFORE INSERT ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_resumen_docente BEFORE INSERT ON resumen_servicio_social " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT dui_docente FROM docente WHERE dui_docente = NEW.dui_docente) IS NULL) THEN RAISE(ABORT, 'No existe el docente') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_resumen_estudiante BEFORE INSERT ON resumen_servicio_social " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_detalle_resumen BEFORE INSERT ON det_res_ser_soc " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_resumen FROM resumen_servicio_social WHERE id_resumen = NEW.id_resumen) IS NULL) THEN RAISE(ABORT, 'No existe el resumen de servicio social') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_detalle_proyecto BEFORE INSERT ON det_res_ser_soc " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_detalleBit_bitacora BEFORE INSERT ON detalle_bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_bitacora FROM bitacora WHERE id_bitacora = NEW.id_bitacora) IS NULL) THEN RAISE(ABORT, 'No existe la bitacora') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_bitacora_proyecto BEFORE INSERT ON bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_bitacora_estudiante BEFORE INSERT ON bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_estProy_proyecto BEFORE INSERT ON estudiantes_proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_estProy_estudiante BEFORE INSERT ON estudiantes_proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_area_carrera BEFORE INSERT ON area_carrera " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_carrera FROM carrera WHERE id_carrera = NEW.id_carrera) IS NULL) THEN RAISE(ABORT, 'No existe la carrera') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_materia_area BEFORE INSERT ON materia " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_record_estudiante BEFORE INSERT ON record_academico " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_record_area BEFORE INSERT ON record_academico " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_nota_materia BEFORE INSERT ON nota " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT cod_materia FROM materia WHERE cod_materia = NEW.cod_materia) IS NULL) THEN RAISE(ABORT, 'No existe la materia') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_nota_estudiante BEFORE INSERT ON nota " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_acceso_usuario BEFORE INSERT ON acceso_usuario " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_usuario FROM usuario WHERE id_usuario = NEW.id_usuario) IS NULL) THEN RAISE(ABORT, 'No existe el usuario') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_acceso_opcion BEFORE INSERT ON acceso_usuario " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_opcion FROM opcion_crud WHERE id_opcion = NEW.id_opcion) IS NULL) THEN RAISE(ABORT, 'No existe la opcion') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_proyecto_modalidad_Update BEFORE UPDATE ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_modalidad FROM modalidad WHERE id_modalidad = NEW.id_modalidad) IS NULL) THEN RAISE(ABORT, 'No existe la modalidad') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_categoria_Update BEFORE UPDATE ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_categoria FROM categoria WHERE id_categoria = NEW.id_categoria) IS NULL) THEN RAISE(ABORT, 'No existe la categoria') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_docente_Update BEFORE UPDATE ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT dui_docente FROM docente WHERE dui_docente = NEW.dui_docente) IS NULL) THEN RAISE(ABORT, 'No existe el docente') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_estado_Update BEFORE UPDATE ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_estado FROM estado WHERE id_estado = NEW.id_estado) IS NULL) THEN RAISE(ABORT, 'No existe el estado') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_proyecto_area_Update BEFORE UPDATE ON proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_resumen_docente_Update BEFORE UPDATE ON resumen_servicio_social " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT dui_docente FROM docente WHERE dui_docente = NEW.dui_docente) IS NULL) THEN RAISE(ABORT, 'No existe el docente') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_resumen_estudiante_Update BEFORE UPDATE ON resumen_servicio_social " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_detalle_resumen_Update BEFORE UPDATE ON det_res_ser_soc " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_resumen FROM resumen_servicio_social WHERE id_resumen = NEW.id_resumen) IS NULL) THEN RAISE(ABORT, 'No existe el resumen de servicio social') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_detalle_proyecto_Update BEFORE UPDATE ON det_res_ser_soc " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_detalleBit_bitacora_Update BEFORE UPDATE ON detalle_bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_bitacora FROM bitacora WHERE id_bitacora = NEW.id_bitacora) IS NULL) THEN RAISE(ABORT, 'No existe la bitacora') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_bitacora_proyecto_Update BEFORE UPDATE ON bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_bitacora_estudiante_Update BEFORE UPDATE ON bitacora " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_estProy_proyecto_Update BEFORE UPDATE ON estudiantes_proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_proyecto FROM proyecto WHERE id_proyecto = NEW.id_proyecto) IS NULL) THEN RAISE(ABORT, 'No existe el proyecto') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_estProy_estudiante_Update BEFORE UPDATE ON estudiantes_proyecto " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_area_carrera_Update BEFORE UPDATE ON area_carrera " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_carrera FROM carrera WHERE id_carrera = NEW.id_carrera) IS NULL) THEN RAISE(ABORT, 'No existe la carrera') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_materia_area_Update BEFORE UPDATE ON materia " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_record_estudiante_Update BEFORE UPDATE ON record_academico " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_record_area_Update BEFORE UPDATE ON record_academico " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_area FROM area_carrera WHERE id_area = NEW.id_area) IS NULL) THEN RAISE(ABORT, 'No existe el area') " +
                    "END; END;");


            db.execSQL("CREATE TRIGGER fk_nota_materia_Update BEFORE UPDATE ON nota " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT cod_materia FROM materia WHERE cod_materia = NEW.cod_materia) IS NULL) THEN RAISE(ABORT, 'No existe la materia') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_nota_estudiante_Update BEFORE UPDATE ON nota " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT carnet FROM estudiante WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe el estudiante') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_acceso_usuario_Update BEFORE UPDATE ON acceso_usuario " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_usuario FROM usuario WHERE id_usuario = NEW.id_usuario) IS NULL) THEN RAISE(ABORT, 'No existe el usuario') " +
                    "END; END;");

            db.execSQL("CREATE TRIGGER fk_acceso_opcion_Update BEFORE UPDATE ON acceso_usuario " +
                    "FOR EACH ROW BEGIN " +
                    "SELECT CASE " +
                    "WHEN ((SELECT id_opcion FROM opcion_crud WHERE id_opcion = NEW.id_opcion) IS NULL) THEN RAISE(ABORT, 'No existe la opcion') " +
                    "END; END;");


            //Triggers de actualizacion en cascada

            //db.execSQL("CREATE TRIGGER area_promedio BEFORE INSERT ON estudiantes_proyecto FOR EACH ROW BEGIN SELECT CASE WHEN (((SELECT id_area FROM record_academico WHERE carnet = NEW.carnet)<>(SELECT id_area FROM proyecto WHERE id_proyecto = NEW.id_proyecto)) OR (SELECT promedio FROM record_academico WHERE carnet = NEW.carnet)< (SELECT requisito_nota FROM proyecto WHERE id_proyecto = NEW.id_proyecto)) THEN RAISE(ABORT, 'No cumple con requisito') END; END;");
            db.execSQL("CREATE TRIGGER area_promedio BEFORE INSERT ON estudiantes_proyecto BEGIN SELECT CASE WHEN NOT EXISTS (SELECT 1 FROM record_academico WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM proyecto WHERE id_proyecto = NEW.id_proyecto)) OR ((SELECT promedio FROM record_academico WHERE carnet = NEW.carnet AND id_area = (SELECT id_area FROM proyecto WHERE id_proyecto = NEW.id_proyecto) ) < (SELECT requisito_nota FROM proyecto WHERE id_proyecto = NEW.id_proyecto)) THEN RAISE(ABORT, 'No cumple con requisito') END; END;");

            db.execSQL("CREATE TRIGGER crear_record1 AFTER INSERT ON nota " +
                    "WHEN NEW.calificacion >= 6 AND NOT EXISTS ( SELECT 1 FROM record_academico WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) " +
                    "BEGIN " +
                    "INSERT INTO record_academico(carnet,id_area,materias_aprobadas,progreso,promedio) " +
                    "VALUES(NEW.carnet, " +
                    "(SELECT id_area FROM materia WHERE cod_materia = new.cod_materia), 1, " +
                    "(SELECT ROUND(1.00 *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) ),2 ) ) , " +
                    "(SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) ); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record2 AFTER INSERT ON nota " +
                    "WHEN NEW.calificacion >= 6 AND EXISTS ( SELECT 1 FROM record_academico WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) " +
                    "BEGIN " +
                    "UPDATE record_academico SET materias_aprobadas=materias_aprobadas+1, " +
                    "progreso= (SELECT ROUND((materias_aprobadas+1) *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) ),2 ) ) , " +
                    "promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record3 AFTER INSERT ON nota " +
                    "WHEN NEW.calificacion <6 AND NOT EXISTS ( SELECT 1 FROM record_academico WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) " +
                    "BEGIN " +
                    "INSERT INTO record_academico(carnet,id_area,materias_aprobadas,progreso,promedio) " +
                    "VALUES(NEW.carnet, " +
                    "(SELECT id_area FROM materia WHERE cod_materia = new.cod_materia), " +
                    "0, " +
                    "0.00, " +
                    "(SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) ); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record4 AFTER INSERT ON nota " +
                    "WHEN NEW.calificacion < 6 AND EXISTS ( SELECT 1 FROM record_academico WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) " +
                    "BEGIN " +
                    "UPDATE record_academico SET promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record5 AFTER UPDATE ON nota " +
                    "WHEN new.calificacion >= 6 AND old.calificacion < 6 " +
                    "BEGIN " +
                    "UPDATE record_academico SET materias_aprobadas=materias_aprobadas+1, " +
                    "progreso= (SELECT ROUND((materias_aprobadas+1) *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) ),2 ) ) , " +
                    "promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record6 AFTER UPDATE ON nota " +
                    "WHEN new.calificacion < 6 AND old.calificacion >= 6 " +
                    "BEGIN " +
                    "UPDATE record_academico SET materias_aprobadas=materias_aprobadas-1, " +
                    "progreso= (SELECT ROUND((materias_aprobadas-1) *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia) ) ),2 ) ) , " +
                    "promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record7 AFTER UPDATE ON nota " +
                    "WHEN new.calificacion >= 6 AND old.calificacion >= 6 " +
                    "BEGIN " +
                    "UPDATE record_academico SET promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record8 AFTER UPDATE ON nota " +
                    "WHEN new.calificacion < 6 AND old.calificacion < 6 " +
                    "BEGIN " +
                    "UPDATE record_academico SET promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = new.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = new.cod_materia))) , 2)) " +
                    "WHERE carnet = new.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = new.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record9 AFTER DELETE ON nota " +
                    "WHEN old.calificacion >= 6 AND EXISTS (SELECT 1 FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) " +
                    "BEGIN " +
                    "UPDATE record_academico SET materias_aprobadas=materias_aprobadas-1, " +
                    "progreso= (SELECT ROUND((materias_aprobadas-1) *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia) ) ),2 ) ) , " +
                    "promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) , 2)) " +
                    "WHERE carnet = old.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record10 AFTER DELETE ON nota " +
                    "WHEN old.calificacion < 6 AND EXISTS (SELECT 1 FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) " +
                    "BEGIN " +
                    "UPDATE record_academico SET promedio= (SELECT ROUND( (SELECT SUM(calificacion) FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) / (SELECT COUNT(carnet) FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) , 2)) " +
                    "WHERE carnet = old.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia); " +
                    "END;");

            db.execSQL("CREATE TRIGGER crear_record11 AFTER DELETE ON nota " +
                    "WHEN old.calificacion >= 6 AND NOT EXISTS (SELECT 1 FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) " +
                    "BEGIN " +
                    "UPDATE record_academico SET materias_aprobadas=materias_aprobadas-1, " +
                    "progreso= (SELECT ROUND((materias_aprobadas-1) *100.00/(SELECT total_materias FROM carrera WHERE id_carrera = (SELECT id_carrera FROM area_carrera WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia) ) ),2 ) ) , " +
                    "promedio= 0.00 " +
                    "WHERE carnet = old.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia); " +
                    "END;");
            
            db.execSQL("CREATE TRIGGER crear_record12 AFTER DELETE ON nota " +
                    "WHEN old.calificacion < 6 AND NOT EXISTS (SELECT 1 FROM nota WHERE carnet = old.carnet AND cod_materia IN (SELECT cod_materia FROM materia WHERE id_area = (SELECT id_area FROM materia WHERE cod_materia  = old.cod_materia))) " +
                    "BEGIN " +
                    "UPDATE record_academico SET promedio= 0.00 " +
                    "WHERE carnet = old.carnet AND id_area = (SELECT id_area FROM materia WHERE cod_materia = old.cod_materia); " +
                    "END;");
            
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
