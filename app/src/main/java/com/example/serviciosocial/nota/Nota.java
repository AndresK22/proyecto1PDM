package com.example.serviciosocial.nota;

public class Nota {
    private String cod_materia;
    private String carnet;
    private double calificacion;

    public Nota(){

    }

    public Nota(String cod_materia, String carnet, double calificacion) {
        this.cod_materia = cod_materia;
        this.carnet = carnet;
        this.calificacion = calificacion;
    }

    public String getCod_materia() {
        return cod_materia;
    }

    public void setCod_materia(String cod_materia) {
        this.cod_materia = cod_materia;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }
}
