package com.leon.sasepwaperfil;

import java.util.HashMap;
import java.util.List;

public class PerfilMedico {
    private String nombreCuidador;
    private boolean gustaSocializar;
    private boolean tomaMedicamentos;
    private boolean discapacidadAuditiva;
    private boolean discapacidadVisual;
    private boolean discpacidadMotora;
    private int estadio;
    private double periodoVigilia;
    private CausaDemencia causa;
    private HashMap<String,Float> CDR;
    private List<ActividadRutinaria> actividades;
    private HashMap<String,Emocion> emociones;

    public String getNombreCuidador() {
        return nombreCuidador;
    }

    public void setNombreCuidador(String nombreCuidador) {
        this.nombreCuidador = nombreCuidador;
    }

    public boolean isGustaSocializar() {
        return gustaSocializar;
    }

    public void setGustaSocializar(boolean gustaSocializar) {
        this.gustaSocializar = gustaSocializar;
    }

    public boolean isTomaMedicamentos() {
        return tomaMedicamentos;
    }

    public void setTomaMedicamentos(boolean tomaMedicamentos) {
        this.tomaMedicamentos = tomaMedicamentos;
    }

    public boolean isDiscapacidadAuditiva() {
        return discapacidadAuditiva;
    }

    public void setDiscapacidadAuditiva(boolean discapacidadAuditiva) {
        this.discapacidadAuditiva = discapacidadAuditiva;
    }

    public boolean isDiscapacidadVisual() {
        return discapacidadVisual;
    }

    public void setDiscapacidadVisual(boolean discapacidadVisual) {
        this.discapacidadVisual = discapacidadVisual;
    }

    public boolean isDiscpacidadMotora() {
        return discpacidadMotora;
    }

    public void setDiscpacidadMotora(boolean discpacidadMotora) {
        this.discpacidadMotora = discpacidadMotora;
    }

    public int getEstadio() {
        return estadio;
    }

    public void setEstadio(int estadio) {
        this.estadio = estadio;
    }

    public double getPeriodoVigilia() {
        return periodoVigilia;
    }

    public void setPeriodoVigilia(double periodoVigilia) {
        this.periodoVigilia = periodoVigilia;
    }

    public CausaDemencia getCausa() {
        return causa;
    }

    public void setCausa(CausaDemencia causa) {
        this.causa = causa;
    }

    public HashMap<String, Float> getCDR() {
        return CDR;
    }

    public void setCDR(HashMap<String, Float> CDR) {
        this.CDR = CDR;
    }

    public List<ActividadRutinaria> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadRutinaria> actividades) {
        this.actividades = actividades;
    }

    public HashMap<String, Emocion> getEmociones() {
        return emociones;
    }

    public void setEmociones(HashMap<String, Emocion> emociones) {
        this.emociones = emociones;
    }
}
