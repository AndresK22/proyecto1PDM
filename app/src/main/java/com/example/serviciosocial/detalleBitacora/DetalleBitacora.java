package com.example.serviciosocial.detalleBitacora;

public class DetalleBitacora {
    private long id_detalle_bitacora;
    private long id_bitacora;
    private String actividad;
    private String fecha_bitacora;

    public DetalleBitacora() {
    }

    public DetalleBitacora(long id_detalle_bitacora, long id_bitacora, String actividad, String fecha_bitacora) {
        this.id_detalle_bitacora = id_detalle_bitacora;
        this.id_bitacora = id_bitacora;
        this.actividad = actividad;
        this.fecha_bitacora = fecha_bitacora;
    }

    public long getId_detalle_bitacora() {
        return id_detalle_bitacora;
    }

    public void setId_detalle_bitacora(long id_detalle_bitacora) {
        this.id_detalle_bitacora = id_detalle_bitacora;
    }

    public long getId_bitacora() {
        return id_bitacora;
    }

    public void setId_bitacora(long id_bitacora) {
        this.id_bitacora = id_bitacora;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getFecha_bitacora() {
        return fecha_bitacora;
    }

    public void setFecha_bitacora(String fecha_bitacora) {
        this.fecha_bitacora = fecha_bitacora;
    }
}
