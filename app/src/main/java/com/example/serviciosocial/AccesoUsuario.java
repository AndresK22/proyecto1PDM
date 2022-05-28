package com.example.serviciosocial;

public class AccesoUsuario {
    String idOpcion;
    int idUsuario;

    public AccesoUsuario(){

    }

    public AccesoUsuario(String idOpcion, int idUsuario) {
        this.idOpcion = idOpcion;
        this.idUsuario = idUsuario;
    }

    public String getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(String idOpcion) {
        this.idOpcion = idOpcion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
