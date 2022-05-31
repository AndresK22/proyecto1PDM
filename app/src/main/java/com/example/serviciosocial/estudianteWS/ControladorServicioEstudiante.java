package com.example.serviciosocial.estudianteWS;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControladorServicioEstudiante {
    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";
        // Estableciendo tiempo de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);HttpConnectionParams.setSoTimeout(parametros, 5000);
        // Creando objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
            // Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static List<Estudiante> obtenerEstudianteExterno(String json, Context ctx)
    {
        List<Estudiante> estudiante = new ArrayList<Estudiante>();
        if(json.equals("[]")){
            Toast.makeText(ctx, "No existe registro de este estudiante", Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONArray docenteJSON = new JSONArray(json);
                for (int i = 0; i < docenteJSON.length(); i++) {
                    JSONObject obj = docenteJSON.getJSONObject(i);
                    Estudiante d = new Estudiante();
                    d.setCarnet(obj.getString("carnet"));
                    d.setNombres_estudiante(obj.getString("nombres_estudiante"));
                    d.setApellidos_estudiante(obj.getString("apellidos_estudiante"));
                    d.setTelefono_estudiante(obj.getString("telefono_estudiante"));
                    d.setEmail_estudiante(obj.getString("email_estudiante"));
                    d.setDomicilio(obj.getString("domicilio"));
                    d.setDui(obj.getString("dui"));
                    estudiante.add(d);
                }
                return estudiante;
            } catch (Exception e) {
                Toast.makeText(ctx, "Error en parse de JSON", Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return null;
    }

    public static List<Estudiante> obtenerEstudianteExternoAll(String json, Context ctx)
    {
        List<Estudiante> estudiantes = new ArrayList<Estudiante>();
        if(json.equals("[]")){
            Toast.makeText(ctx, "No existe registros de estudiantes", Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONArray docenteJSON = new JSONArray(json);
                for (int i = 0; i < docenteJSON.length(); i++) {
                    JSONObject obj = docenteJSON.getJSONObject(i);
                    Estudiante d = new Estudiante();
                    d.setCarnet(obj.getString("carnet"));
                    d.setNombres_estudiante(obj.getString("nombres_estudiante"));
                    d.setApellidos_estudiante(obj.getString("apellidos_estudiante"));
                    d.setTelefono_estudiante(obj.getString("telefono_estudiante"));
                    d.setEmail_estudiante(obj.getString("email_estudiante"));
                    d.setDomicilio(obj.getString("domicilio"));
                    d.setDui(obj.getString("dui"));
                    estudiantes.add(d);
                }
                return estudiantes;
            } catch (Exception e) {
                Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return null;
    }

    public static int insertarEstudiante(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        int gv=0;
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1){
                Toast.makeText(ctx, "Registro ingresado puede consultar", Toast.LENGTH_LONG).show();
                gv=respuesta;
            }
            else{
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return gv;
    }

    public static int eliminarEstudiante(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        int gv=0;
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1){
                Toast.makeText(ctx, "Registro eliminado de la web", Toast.LENGTH_LONG).show();
                gv=respuesta;
            }
            else{
                Toast.makeText(ctx, "Error el registro no existe, no puede ser eliminado", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error el registro no existe, no puede ser eliminado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return gv;
    }

    public static int eliminarEstudianteAll(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        int gv=0;
        try {
            JSONObject resultado = new JSONObject(json);
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1){
                Toast.makeText(ctx, "Registro eliminado de la web", Toast.LENGTH_LONG).show();
                gv=respuesta;
            }
            else{
                Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return gv;
    }


}
