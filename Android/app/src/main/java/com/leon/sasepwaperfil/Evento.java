package com.leon.sasepwaperfil;

import java.util.Date;
import java.util.List;

public class Evento {
    private String nombre;
    private List<Imagen> imagenes;
    private List<String> tags;
    private Date fecha;
    private float gusto;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getGusto() {
        return gusto;
    }

    public void setGusto(float gusto) {
        this.gusto = gusto;
    }
}
