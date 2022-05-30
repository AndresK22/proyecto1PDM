package com.example.serviciosocial.recordAcademico;

public class RecordAcademico {
    private int id_record;
    private String carnet;
    private String id_area;
    private int materias_aprobadas;
    private double progreso;
    private double promedio;

    public RecordAcademico() {
    }

    public RecordAcademico(int id_record, String carnet, String id_area, int materias_aprobadas, double progreso, double promedio) {
        this.id_record = id_record;
        this.carnet = carnet;
        this.id_area = id_area;
        this.materias_aprobadas = materias_aprobadas;
        this.progreso = progreso;
        this.promedio = promedio;
    }

    public int getId_record() {
        return id_record;
    }

    public void setId_record(int id_record) {
        this.id_record = id_record;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getId_area() {
        return id_area;
    }

    public void setId_area(String id_area) {
        this.id_area = id_area;
    }

    public int getMaterias_aprobadas() {
        return materias_aprobadas;
    }

    public void setMaterias_aprobadas(int materias_aprobadas) {
        this.materias_aprobadas = materias_aprobadas;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
}
