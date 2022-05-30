package com.example.serviciosocial.docenteWS;

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

public class ControladorServicioDocente {
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

    public static List<Docente> obtenerDocenteExterno(String json, Context ctx)
    {
        List<Docente> docente = new ArrayList<Docente>();
        if(json.equals("[]")){
            Toast.makeText(ctx, "No existe registro de este docente", Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONArray docenteJSON = new JSONArray(json);
                for (int i = 0; i < docenteJSON.length(); i++) {
                    JSONObject obj = docenteJSON.getJSONObject(i);
                    Docente d = new Docente();
                    d.setDui_docente(obj.getString("dui_docente"));
                    d.setNombres_docente(obj.getString("nombres_docente"));
                    d.setApellidos_docente(obj.getString("apellidos_docente"));
                    d.setEmail_docente(obj.getString("email_docente"));
                    d.setTelefono_docente(obj.getString("telefono_docente"));
                    docente.add(d);
                }
                return docente;
            } catch (Exception e) {
                Toast.makeText(ctx, "Error en parse de JSON", Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return null;
    }

    public static List<Docente> obtenerDocenteExternoAll(String json, Context ctx)
    {
        List<Docente> docente = new ArrayList<Docente>();
        if(json.equals("[]")){
            Toast.makeText(ctx, "No existe registro de docentes", Toast.LENGTH_LONG).show();
        }else {
            try {
                JSONArray docenteJSON = new JSONArray(json);
                for (int i = 0; i < docenteJSON.length(); i++) {
                    JSONObject obj = docenteJSON.getJSONObject(i);
                    Docente d = new Docente();
                    d.setDui_docente(obj.getString("dui_docente"));
                    d.setNombres_docente(obj.getString("nombres_docente"));
                    d.setApellidos_docente(obj.getString("apellidos_docente"));
                    d.setEmail_docente(obj.getString("email_docente"));
                    d.setTelefono_docente(obj.getString("telefono_docente"));
                    docente.add(d);
                }
                return docente;
            } catch (Exception e) {
                Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
                return null;
            }
        }
        return null;
    }

    public static int insertarDocente(String peticion, Context ctx) {
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






}
