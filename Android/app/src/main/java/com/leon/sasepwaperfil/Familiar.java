package com.leon.sasepwaperfil;

import java.util.Date;
import java.util.List;

public class Familiar {
    private String nombre;
    private String parentesco;
    private Date nacimiento;
    private float interes;
    private List<Imagen> relacionadas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public List<Imagen> getRelacionadas() {
        return relacionadas;
    }

    public void setRelacionadas(List<Imagen> relacionadas) {
        this.relacionadas = relacionadas;
    }
}
