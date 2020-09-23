package com.leon.sasepwaperfil;

import java.util.HashMap;
import java.util.List;

public class ActMusicoterapia extends ActividadPepper  {
    private HashMap<String,Float> gustoGenero;
    private List<Cancion> canciones;

    public HashMap<String, Float> getGustoGenero() {
        return gustoGenero;
    }

    public void setGustoGenero(HashMap<String, Float> gustoGenero) {
        this.gustoGenero = gustoGenero;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}
