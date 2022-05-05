package com.example.serviciosocial;

public class Usuario {
    String NomUsuario;
    String Clave;

    public Usuario(String nomUsuario, String clave){
        NomUsuario = nomUsuario;
        Clave = clave;
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
