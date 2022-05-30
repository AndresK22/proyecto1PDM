package com.example.serviciosocial.carrera;

public class Carrera {
    private String id_carrera;
    private String nombre_carrera;
    private int total_materias;

    public Carrera(String id_carrera, String nombre_carrera, int total_materias) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.total_materias = total_materias;
    }

    public Carrera() {
    }

    public String getId_carrera(){
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public int getTotal_materias() {
        return total_materias;
    }

    public void setTotal_materias(int total_materias) {
        this.total_materias = total_materias;
    }
}
