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

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

class ControlLogin {

    private static final String[] camposUsuario = new String[] {"ID_USUARIO", "NOMBRE_USUARIO", "CLAVE"};
    private static final String[] camposAccesoUsuario = new String[] {"ID_OPCION", "ID_USUARIO"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlLogin(Context ctx){
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
    public void insertar(Usuario usuario){
        long result = 0;
        ContentValues u = new ContentValues();
        u.put("NOMBRE_USUARIO",usuario.getNomUsuario());
        u.put("CLAVE",usuario.getClave());
        result = db.insert("USUARIO",null,u);

        if(result == -1){
            Toast.makeText(context,"Fallo al insertar",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,"Insertado satisfactoriamente",Toast.LENGTH_SHORT).show();
        }
    }

    //UPDATES



    //DELETES



    //SELECTS
    public boolean consultarUsuarioExiste(String nombre){
        String[] nombreUsuario = {nombre};
        Cursor cursor = db.query("USUARIO", camposUsuario, "NOMBRE_USUARIO = ?", nombreUsuario, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public Usuario consultarUsuario(String nombre){
        String[] nombreUsuario = {nombre};
        Cursor cursor = db.query("USUARIO", camposUsuario, "NOMBRE_USUARIO = ?", nombreUsuario, null, null, null);
        if(cursor.moveToFirst()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(cursor.getInt(0));
            usuario.setNomUsuario(cursor.getString(1));
            usuario.setClave(cursor.getString(2));
            return usuario;
        }else {
            return null;
        }
    }

    //LLENAR BASE USUARIOS
    public void llenarUsuarios(){

        //Llenando usuarios
        Usuario Tutor = new Usuario("Tutor","Tutor123");
        Usuario Alumno = new Usuario("Alumno","Alumno123");
        Usuario Admin = new Usuario("Admin","Admin123");

        if(!consultarUsuarioExiste(Admin.getNomUsuario())){
            insertar(Admin);
        }
        if(!consultarUsuarioExiste(Tutor.getNomUsuario())){
            insertar(Tutor);
        }
        if(!consultarUsuarioExiste(Alumno.getNomUsuario())){
            insertar(Alumno);
        }

        //Llenando OPCION_CRUD --> ID_OPCION, DESC_OPCION, NUM_CRUD
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('001','Gestión Usuario',1)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('002','Gestión Opcion CRUD',2)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('003','Gestion acceso_usuario',3)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('004','Gestion de carrera',4)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('005','Gestion de areas por carreras',5)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('006','Gestion de materias',6)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('007','Gestion de estudiantes',7)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('008','Gestion de categorias',8)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('009','Gestión de modalidad',9)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('010','Gestion de docentes',10)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('011','Gestion de estados del proyecto',11)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('012','Gestion de notas',12)");

        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('013','Gestion de proyectos',13)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('014','Gestion de grupos asignados por proyecto',14)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('015','Record academico',15)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('016','Gestion del resumen social',16)");

        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('017','Gestion de bitacoras',17)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('018','Gestion del resumen social (alumno)',18)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('019','Consultar record academico',19)");
        db.execSQL("INSERT OR IGNORE INTO OPCION_CRUD VALUES('020','Consultar proyectos en los que participa',20)");

        //Llenando ACCESO_USUARIO --> ID_OPCION ID_USUARIO
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('001',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('002',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('003',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('004',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('005',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('006',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('007',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('008',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('009',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('010',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('011',1)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('012',1)");

        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('013',2)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('014',2)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('015',2)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('016',2)");

        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('017',3)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('018',3)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('019',3)");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('020',3)");



    }

    public boolean iniciarSesion(String usuario,String contra){

        if (consultarUsuarioExiste(usuario)){

            Usuario usuarioConsulta = consultarUsuario(usuario);

            if (usuario.equals(usuarioConsulta.getNomUsuario()) && contra.equals(usuarioConsulta.getClave())){
                return true;
            }
            return  false;
        }
        else{
            return false;
        }
    }

    public Cursor getAccesoUsuario(int id){

        String[] idUsuario = {Integer.toString(id)};
        Cursor cursor = db.query("ACCESO_USUARIO", camposAccesoUsuario, "ID_USUARIO = ?", idUsuario, null, null, null);
        return  cursor;

    }

}

