package com.leon.sasepwaperfil;

import java.util.Date;
import java.util.List;

public class Usuario {
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private int edad;
    private EstadoCivil estadoCivil;
    private NivelEducativo nivelEd;
    private String profesion;
    private List<Imagen> fotosFamiliares;
    private PerfilPreferencias preferencias;
    private PerfilMedico medico;
    private List<Familiar> familiares;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public NivelEducativo getNivelEd() {
        return nivelEd;
    }

    public void setNivelEd(NivelEducativo nivelEd) {
        this.nivelEd = nivelEd;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public List<Imagen> getFotosFamiliares() {
        return fotosFamiliares;
    }

    public void setFotosFamiliares(List<Imagen> fotosFamiliares) {
        this.fotosFamiliares = fotosFamiliares;
    }

    public PerfilPreferencias getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(PerfilPreferencias preferencias) {
        this.preferencias = preferencias;
    }

    public PerfilMedico getMedico() {
        return medico;
    }

    public void setMedico(PerfilMedico medico) {
        this.medico = medico;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }
}
