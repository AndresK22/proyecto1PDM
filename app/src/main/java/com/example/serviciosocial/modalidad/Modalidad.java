package com.example.serviciosocial.modalidad;

public class Modalidad {

    private int id_modalidad;
    private String nombre_modalidad;

    public Modalidad() {
    }

    public Modalidad(int id_modalidad, String nombre_modalidad) {
        this.id_modalidad = id_modalidad;
        this.nombre_modalidad = nombre_modalidad;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public String getNombre_modalidad() {
        return nombre_modalidad;
    }

    public void setNombre_modalidad(String nombre_modalidad) {
        this.nombre_modalidad = nombre_modalidad;
    }
}
