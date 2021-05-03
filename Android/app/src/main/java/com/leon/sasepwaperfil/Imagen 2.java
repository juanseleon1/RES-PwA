package com.leon.sasepwaperfil;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

public class Imagen {
    private List<String> tags;
    private Date fecha;
    private Bitmap foto;
    private float gusto;
    private List<Familiar> relacionados;

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public void setGusto(float gusto) {
        this.gusto = gusto;
    }

    public void setRelacionados(List<Familiar> relacionados) {
        this.relacionados = relacionados;
    }
}
