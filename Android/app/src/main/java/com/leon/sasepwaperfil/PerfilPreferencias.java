package com.leon.sasepwaperfil;

import java.util.HashMap;

public class PerfilPreferencias {
    private HashMap<String,ActividadPepper> actividadesSis;
    private String nombrePreferido;

    public HashMap<String, ActividadPepper> getActividadesSis() {
        return actividadesSis;
    }

    public void setActividadesSis(HashMap<String, ActividadPepper> actividadesSis) {
        this.actividadesSis = actividadesSis;
    }

    public String getNombrePreferido() {
        return nombrePreferido;
    }

    public void setNombrePreferido(String nombrePreferido) {
        this.nombrePreferido = nombrePreferido;
    }
}
