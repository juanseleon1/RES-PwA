package com.leon.sasepwaperfil;

import java.util.HashMap;
import java.util.List;

public class ActCuenteria extends ActividadPepper  {
    private List<Cuento> cuentos;
    private HashMap<String,Float> gustoGenero;

    public List<Cuento> getCuentos() {
        return cuentos;
    }

    public void setCuentos(List<Cuento> cuentos) {
        this.cuentos = cuentos;
    }

    public HashMap<String, Float> getGustoGenero() {
        return gustoGenero;
    }

    public void setGustoGenero(HashMap<String, Float> gustoGenero) {
        this.gustoGenero = gustoGenero;
    }
}
