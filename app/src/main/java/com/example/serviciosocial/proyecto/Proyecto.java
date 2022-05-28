package com.example.serviciosocial.proyecto;

public class Proyecto {

    private int id_proyecto;
    private int id_categoria;
    private int id_modalidad;
    private String dui_docente;
    private int id_estado;
    private int id_carrera;
    private String id_area;
    private String nombre_proyecto;
    private String descripcion_proyecto;
    private String lugar;
    private double requisito_nota;

    public Proyecto() {
    }

    public Proyecto(int id_proyecto, int id_categoria, int id_modalidad, String dui_docente, int id_estado, int id_carrera, String id_area, String nombre_proyecto, String descripcion_proyecto, String lugar, double requisito_nota) {
        this.id_proyecto = id_proyecto;
        this.id_categoria = id_categoria;
        this.id_modalidad = id_modalidad;
        this.dui_docente = dui_docente;
        this.id_estado = id_estado;
        this.id_carrera = id_carrera;
        this.id_area = id_area;
        this.nombre_proyecto = nombre_proyecto;
        this.descripcion_proyecto = descripcion_proyecto;
        this.lugar = lugar;
        this.requisito_nota = requisito_nota;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_modalidad() {
        return id_modalidad;
    }

    public void setId_modalidad(int id_modalidad) {
        this.id_modalidad = id_modalidad;
    }

    public String getDui_docente() {
        return dui_docente;
    }

    public void setDui_docente(String dui_docente) {
        this.dui_docente = dui_docente;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getId_area() {
        return id_area;
    }

    public void setId_area(String id_area) {
        this.id_area = id_area;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getDescripcion_proyecto() {
        return descripcion_proyecto;
    }

    public void setDescripcion_proyecto(String descripcion_proyecto) {
        this.descripcion_proyecto = descripcion_proyecto;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getRequisito_nota() {
        return requisito_nota;
    }

    public void setRequisito_nota(double requisito_nota) {
        this.requisito_nota = requisito_nota;
    }
}
