package com.example.serviciosocial.bitacora;

public class Bitacora {
    private long id_bitacora;
    private long id_proyecto;
    private String carnet;
    private String mes;
    private float total_horas_realizadas;

    public Bitacora() {
    }

    public Bitacora(long id_bitacora, long id_proyecto, String carnet, String mes, float total_horas_realizadas) {
        this.id_bitacora = id_bitacora;
        this.id_proyecto = id_proyecto;
        this.carnet = carnet;
        this.mes = mes;
        this.total_horas_realizadas = total_horas_realizadas;
    }

    public long getId_bitacora() {
        return id_bitacora;
    }

    public void setId_bitacora(long id_bitacora) {
        this.id_bitacora = id_bitacora;
    }

    public long getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(long id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public float getTotal_horas_realizadas() {
        return total_horas_realizadas;
    }

    public void setTotal_horas_realizadas(float total_horas_realizadas) {
        this.total_horas_realizadas = total_horas_realizadas;
    }
}
