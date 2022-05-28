package com.example.serviciosocial.login;

public class Usuario {
    int idUsuario;
    String NomUsuario;
    String Clave;

    public Usuario(String nomUsuario, String clave){
        NomUsuario = nomUsuario;
        Clave = clave;
    }

    public Usuario(){

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomUsuario() {
        return NomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        NomUsuario = nomUsuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }
}
