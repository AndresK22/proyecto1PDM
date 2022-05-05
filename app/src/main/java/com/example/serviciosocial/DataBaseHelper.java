package com.example.serviciosocial;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



public class DataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "BookLibrary.db";
    private  static final int DATABASE_VERSION = 1;


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL("create table USUARIO (" +
                    "ID_USUARIO          INTEGER              not null," +
                    "NOMBRE_USUARIO       VARCHAR(30)          not null," +
                    "CLAVE                CHAR(5)              not null," +
                    "primary key (ID_USUARIO));"
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
                    "      references OPCION_CRUD (ID_OPCION));"
            );

            db.execSQL("create unique index ACCESO_USUARIO_PK on ACCESO_USUARIO (" +
                    "ID_OPCION ASC," +
                    "ID_USUARIO ASC);"
            );

            db.execSQL("create  index ACCESO_USUARIO2_FK on ACCESO_USUARIO (" +
                    "ID_USUARIO ASC);");



            
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
