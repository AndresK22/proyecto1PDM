package com.example.serviciosocial.estudianteWS;

public class Estudiante {
    private String carnet;
    private String nombres_estudiante;
    private String apellidos_estudiante;
    private String email_estudiante;
    private String telefono_estudiante;
    private String domicilio;
    private String dui;

    public Estudiante(String carnet, String nombres_estudiante, String apellidos_estudiante, String email_estudiante, String telefono_estudiante, String domicilio, String dui) {
        this.carnet = carnet;
        this.nombres_estudiante = nombres_estudiante;
        this.apellidos_estudiante = apellidos_estudiante;
        this.email_estudiante = email_estudiante;
        this.telefono_estudiante = telefono_estudiante;
        this.domicilio = domicilio;
        this.dui = dui;
    }

    public Estudiante() {
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres_estudiante() {
        return nombres_estudiante;
    }

    public void setNombres_estudiante(String nombres_estudiante) {
        this.nombres_estudiante = nombres_estudiante;
    }

    public String getApellidos_estudiante() {
        return apellidos_estudiante;
    }

    public void setApellidos_estudiante(String apellidos_estudiante) {
        this.apellidos_estudiante = apellidos_estudiante;
    }

    public String getEmail_estudiante() {
        return email_estudiante;
    }

    public void setEmail_estudiante(String email_estudiante) {
        this.email_estudiante = email_estudiante;
    }

    public String getTelefono_estudiante() {
        return telefono_estudiante;
    }

    public void setTelefono_estudiante(String telefono_estudiante) {
        this.telefono_estudiante = telefono_estudiante;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
}