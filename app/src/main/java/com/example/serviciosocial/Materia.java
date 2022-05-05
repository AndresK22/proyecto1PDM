package com.example.serviciosocial;

public class Materia {
    private String cod_materia;
    private String id_area;
    private String nombre_materia;

    public Materia() {
    }

    public Materia(String cod_materia, String id_area, String nombre_materia) {
        this.cod_materia = cod_materia;
        this.id_area = id_area;
        this.nombre_materia = nombre_materia;
    }

    public String getCod_materia() {
        return cod_materia;
    }

    public void setCod_materia(String cod_materia) {
        this.cod_materia = cod_materia;
    }

    public String getId_area() {
        return id_area;
    }

    public void setId_area(String id_area) {
        this.id_area = id_area;
    }

    public String getNombre_materia() {
        return nombre_materia;
    }

    public void setNombre_materia(String nombre_materia) {
        this.nombre_materia = nombre_materia;
    }

}
