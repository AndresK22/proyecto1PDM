package com.example.serviciosocial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ControlBD {
    private static final String[] camposMateria = new String[] {"cod_materia", "id_area", "nombre_materia"};
    private static final String[] camposEstado = new String[] {"id_estado", "estado"};
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
                db.execSQL("CREATE TABLE materia (cod_materia CHAR(6) NOT NULL, id_area CHAR(2), nombre_materia VARCHAR(25) NOT NULL, PRIMARY KEY (cod_materia));");

                db.execSQL("CREATE TABLE estado (id_estado INTEGER NOT NULL, estado CHAR(25), PRIMARY KEY (id_estado));");
                db.execSQL("INSERT INTO estado (id_estado, estado) VALUES (1, 'Finalizado');");

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

    public void insertar(Usuario usuario){
        long result = 0;
        ContentValues u = new ContentValues();
        u.put("NOMBRE_USUARIO",usuario.getNomUsuario());
        u.put("CLAVE",usuario.getClave());
        result = db.insert("USUARIO",null,u);

        if(result == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Added successfully",Toast.LENGTH_SHORT).show();
        }
    }


    //UPDATES
    public String actualizar(Materia materia){
        try{
            String[] id = {materia.getCod_materia()};
            ContentValues cv = new ContentValues();
            cv.put("id_area", materia.getId_area());
            cv.put("nombre_materia", materia.getNombre_materia());
            db.update("materia", cv, "cod_materia = ?", id);

            return "Registro Actualizado Correctamente";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    //DELETES
    public String eliminar(Materia materia){
        String regAfectados="filas afectadas= ";
        int contador=0;

        try {
            contador += db.delete("nota", "cod_materia='"+materia.getCod_materia()+"'", null);
        } catch (SQLException e){
            e.printStackTrace();
        }

        try{
            contador += db.delete("materia", "cod_materia='"+materia.getCod_materia()+"'", null);
            regAfectados += contador;

            return regAfectados;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //SELECTS
    public Materia consultarMateria(){
        Cursor cursor = db.query("materia", camposMateria, null, null, null, null, null);
        if(cursor.moveToFirst()){
            Materia materia = new Materia();
            materia.setCod_materia(cursor.getString(0));
            materia.setId_area(cursor.getString(1));
            materia.setNombre_materia(cursor.getString(2));
            return materia;
        }else {
            return null;
        }
    }

    public View consultarEstados(Context cont){
        View registro = LayoutInflater.from(cont).inflate(R.layout.item_table_layout_estado, null, false);
        TextView id_estado = (TextView) registro.findViewById(R.id.txtIdEstado);
        TextView txtEstado = (TextView) registro.findViewById(R.id.txtEstado);

        Cursor cursor = db.query("estado", camposEstado, null, null, null, null, null);
        if(cursor.moveToFirst()){
            do {
                Estado estado = new Estado();
                estado.setId_estado(cursor.getInt(0));
                estado.setEstado(cursor.getString(1));

                id_estado.setText(estado.getId_estado());
                txtEstado.setText(estado.getEstado());
            }while (cursor.moveToNext());

            return registro;
        }else {
            return null;
        }
    }


    //Llenado de la base inicial
    public void llenarBD(){

        //Llenando usuarios
        Usuario Tutor = new Usuario("Tutor","Tutor123");
        Usuario Alumno = new Usuario("Alumno","Alumno123");
        Usuario Admin = new Usuario("Admin","Admin123");

        insertar(Tutor);
        insertar(Alumno);
        insertar(Admin);

    }

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
