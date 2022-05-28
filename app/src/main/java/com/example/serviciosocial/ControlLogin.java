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

import java.net.IDN;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class ControlLogin {

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

    public void insertar(int rol) {

        AccesoUsuario acceso = new AccesoUsuario();
        int id = 0;

        Cursor cursor = db.rawQuery("SELECT MAX(ID_USUARIO) FROM USUARIO", null);
        if(cursor.moveToFirst()){
            id = cursor.getInt(0);
        }

       switch (rol){
            case 0:
                insertarAccesoAdmin(id);
                break;
            case 1:
                insertarAccesoDocente(id);
                break;
            case 2:
                insertarAccesoAlumno(id);
                break;
        }

    }

    private void insertarAccesoAlumno(int id) {
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('017',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('018',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('019',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('020',"+id+")");
    }

    private void insertarAccesoDocente(int id) {
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('013',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('014',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('015',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('016',"+id+")");
    }

    private void insertarAccesoAdmin(int id) {
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('001',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('002',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('003',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('004',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('005',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('006',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('007',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('008',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('009',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('010',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('011',"+id+")");
        db.execSQL("INSERT OR IGNORE INTO ACCESO_USUARIO VALUES('012',"+id+")");
    }

    //UPDATES
    public void actualizarUsuario(String id_usuario, String nombre_usuario, int rol_actualizado) {
        int id = Integer.parseInt(id_usuario);
        db = DBHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOMBRE_USUARIO",nombre_usuario);

        db.execSQL("DELETE FROM ACCESO_USUARIO WHERE ID_USUARIO="+id_usuario);

        switch (rol_actualizado){
            case 0:
                insertarAccesoAdmin(id);
                break;
            case 1:
                insertarAccesoDocente(id);
                break;
            case 2:
                insertarAccesoAlumno(id);
                break;
        }

        long result = db.update("USUARIO",cv,"ID_USUARIO=?",new String[] {id_usuario});

        if(result == -1){
            Toast.makeText(context,"Fallo al actualizar",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Actualizado satisfactoriamente!",Toast.LENGTH_SHORT).show();
        }

    }


    //DELETES
    public void eliminarUsuario(String id_usuario) {

        db = DBHelper.getWritableDatabase();
        long result = db.delete("USUARIO","ID_USUARIO=?",new String[]{id_usuario});
        long result1 = db.delete("ACCESO_USUARIO","ID_USUARIO=?",new String[]{id_usuario});
        if(result == -1 && result1 == -1){
            Toast.makeText(context,"Fallo al eliminar",Toast.LENGTH_SHORT).show();
        }
        if (result != -1 && result1 != -1) {
            Toast.makeText(context,"Eliminado Satisfactoriamente",Toast.LENGTH_SHORT).show();
        }
    }


    //SELECTS
    public boolean consultarUsuarioExiste(String nombre){
        String[] nombreusuario = {nombre};
        Cursor cursor = db.query("USUARIO", camposUsuario, "NOMBRE_USUARIO = ?", nombreusuario, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public boolean consultarUsuarioExiste(int id){
        String[] idusuario = {Integer.toString(id)};
        Cursor cursor = db.query("USUARIO", camposUsuario, "ID_USUARIO = ?", idusuario, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }else {
            return false;
        }
    }

    public Cursor consultarUsuarios(){
        String query = "SELECT * FROM USUARIO";
        db = DBHelper.getWritableDatabase();
        Cursor cursor = null;

        if(db != null){
            cursor = db.rawQuery(query,null);
        }

        return cursor;
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

    public  String getRolusuario(int id) {
        String rol ="";
        //String[] id_usuario = {Integer.toString(id)};
        Cursor cursor = db.rawQuery("SELECT * FROM ACCESO_USUARIO WHERE ID_USUARIO="+id+" AND ID_OPCION='001'", null);
        if(cursor.moveToFirst()){
            rol = "Administrador";
        }

        Cursor cursor1 = db.rawQuery("SELECT * FROM ACCESO_USUARIO WHERE ID_USUARIO="+id+" AND ID_OPCION='013'", null);
        if(cursor1.moveToFirst()){
            rol = "Docente";
        }

        Cursor cursor2 = db.rawQuery("SELECT * FROM ACCESO_USUARIO WHERE ID_USUARIO="+id+" AND ID_OPCION='017'", null);
        if(cursor2.moveToFirst()){
            rol = "Alumno";
        }
        return  rol;
    }

    //LLENAR BASE USUARIOS
    public void llenarUsuarios(){

        //Llenando usuarios
        Usuario Docente = new Usuario("Docente","Docente");
        Usuario Alumno = new Usuario("Alumno","Alumno");
        Usuario Admin = new Usuario("Admin","Admin");

        if(!consultarUsuarioExiste(1)){
            insertar(Admin);
        }
        if(!consultarUsuarioExiste(2)){
            insertar(Docente);
        }
        if(!consultarUsuarioExiste(3)){
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
        insertarAccesoAdmin(1);
        insertarAccesoDocente(2);
        insertarAccesoAlumno(3);


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

