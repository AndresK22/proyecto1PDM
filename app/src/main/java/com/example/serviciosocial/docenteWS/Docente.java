package com.example.serviciosocial.docenteWS;

public class Docente {
    private String dui_docente;
    private String nombres_docente;
    private String apellidos_docente;
    private String email_docente;
    private String telefono_docente;

    public Docente() {
    }

    public Docente(String dui_docente, String nombres_docente, String apellidos_docente, String email_docente, String telefono_docente) {
        this.dui_docente = dui_docente;
        this.nombres_docente = nombres_docente;
        this.apellidos_docente = apellidos_docente;
        this.email_docente = email_docente;
        this.telefono_docente = telefono_docente;
    }

    public String getDui_docente() {
        return dui_docente;
    }

    public void setDui_docente(String dui_docente) {
        this.dui_docente = dui_docente;
    }

    public String getNombres_docente() {
        return nombres_docente;
    }

    public void setNombres_docente(String nombres_docente) {
        this.nombres_docente = nombres_docente;
    }

    public String getApellidos_docente() {
        return apellidos_docente;
    }

    public void setApellidos_docente(String apellidos_docente) {
        this.apellidos_docente = apellidos_docente;
    }

    public String getEmail_docente() {
        return email_docente;
    }

    public void setEmail_docente(String email_docente) {
        this.email_docente = email_docente;
    }

    public String getTelefono_docente() {
        return telefono_docente;
    }

    public void setTelefono_docente(String telefono_docente) {
        this.telefono_docente = telefono_docente;
    }
}
