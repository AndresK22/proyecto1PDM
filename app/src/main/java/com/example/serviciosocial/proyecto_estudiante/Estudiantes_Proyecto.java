package com.example.serviciosocial.proyecto_estudiante;

public class Estudiantes_Proyecto {

    private int id_proyecto;
    private String carnet;

    public Estudiantes_Proyecto() {
    }

    public Estudiantes_Proyecto(int id_proyecto, String carnet) {
        this.id_proyecto = id_proyecto;
        this.carnet = carnet;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }
}
