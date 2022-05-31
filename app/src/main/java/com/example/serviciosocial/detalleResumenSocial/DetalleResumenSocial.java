package com.example.serviciosocial.detalleResumenSocial;

public class DetalleResumenSocial {
    private int id_det_res;
    private int id_resumen;
    private int id_proyecto;
    private String fecha_inicio;
    private String fecha_final;
    private float horas_asignadas;
    private float monto;
    private int benef_indir;
    private int benef_dir;
    private String estado_det;

    public DetalleResumenSocial() {
    }

    public DetalleResumenSocial(int id_det_res, int id_resumen, int id_proyecto, String fecha_inicio, String fecha_final, float horas_asignadas, float monto, int benef_indir, int benef_dir, String estado_det) {
        this.id_det_res = id_det_res;
        this.id_resumen = id_resumen;
        this.id_proyecto = id_proyecto;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;
        this.horas_asignadas = horas_asignadas;
        this.monto = monto;
        this.benef_indir = benef_indir;
        this.benef_dir = benef_dir;
        this.estado_det = estado_det;
    }

    public int getId_det_res() {
        return id_det_res;
    }

    public void setId_det_res(int id_det_res) {
        this.id_det_res = id_det_res;
    }

    public int getId_resumen() {
        return id_resumen;
    }

    public void setId_resumen(int id_resumen) {
        this.id_resumen = id_resumen;
    }

    public int getId_proyecto() {
        return id_proyecto;
    }

    public void setId_proyecto(int id_proyecto) {
        this.id_proyecto = id_proyecto;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public float getHoras_asignadas() {
        return horas_asignadas;
    }

    public void setHoras_asignadas(float horas_asignadas) {
        this.horas_asignadas = horas_asignadas;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getBenef_indir() {
        return benef_indir;
    }

    public void setBenef_indir(int benef_indir) {
        this.benef_indir = benef_indir;
    }

    public int getBenef_dir() {
        return benef_dir;
    }

    public void setBenef_dir(int benef_dir) {
        this.benef_dir = benef_dir;
    }

    public String getEstado_det() {
        return estado_det;
    }

    public void setEstado_det(String estado_det) {
        this.estado_det = estado_det;
    }
}
