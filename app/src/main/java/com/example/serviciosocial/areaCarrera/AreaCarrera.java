package com.example.serviciosocial.areaCarrera;

public class AreaCarrera {
    private String id_area;
    private String id_carrera;
    private String descrip_area;

    public AreaCarrera() {
    }

    public AreaCarrera(String id_area, String id_carrera, String descrip_area) {
        this.id_area = id_area;
        this.id_carrera = id_carrera;
        this.descrip_area = descrip_area;
    }

    public String getId_area() {
        return id_area;
    }

    public void setId_area(String id_area) {
        this.id_area = id_area;
    }

    public String getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(String id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getDescrip_area() {
        return descrip_area;
    }

    public void setDescrip_area(String descrip_area) {
        this.descrip_area = descrip_area;
    }
}
