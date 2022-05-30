package com.example.serviciosocial.resumensocial;

public class Resumensocial {
    private int id_resumen;
    private String dui_docente;
    private String carnet;
    private String fecha_apertura_expediente;
    private String fecha_emision_certificado;
    private String observaciones;

    public Resumensocial() {
    }

    public Resumensocial(int id_resumen, String dui_docente, String carnet, String fecha_apertura_expediente, String fecha_emision_certificado, String observaciones) {
        this.id_resumen = id_resumen;
        this.dui_docente = dui_docente;
        this.carnet = carnet;
        this.fecha_apertura_expediente = fecha_apertura_expediente;
        this.fecha_emision_certificado = fecha_emision_certificado;
        this.observaciones = observaciones;
    }

    public int getId_resumen() {
        return id_resumen;
    }

    public void setId_resumen(int id_resumen) {
        this.id_resumen = id_resumen;
    }

    public String getDui_docente() {
        return dui_docente;
    }

    public void setDui_docente(String dui_docente) {
        this.dui_docente = dui_docente;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getFecha_apertura_expediente() {
        return fecha_apertura_expediente;
    }

    public void setFecha_apertura_expediente(String fecha_apertura_expediente) {
        this.fecha_apertura_expediente = fecha_apertura_expediente;
    }

    public String getFecha_emision_certificado() {
        return fecha_emision_certificado;
    }

    public void setFecha_emision_certificado(String fecha_emision_certificado) {
        this.fecha_emision_certificado = fecha_emision_certificado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
