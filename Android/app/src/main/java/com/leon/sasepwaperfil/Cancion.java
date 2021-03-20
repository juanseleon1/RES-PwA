package com.leon.sasepwaperfil;

public class Cancion {
    private String nombre;
    private float gusto;
    private float duracion;
    private String linkVideo;
    private Evento eventoRel;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getGusto() {
        return gusto;
    }

    public void setGusto(float gusto) {
        this.gusto = gusto;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public Evento getEventoRel() {
        return eventoRel;
    }

    public void setEventoRel(Evento eventoRel) {
        this.eventoRel = eventoRel;
    }
}
