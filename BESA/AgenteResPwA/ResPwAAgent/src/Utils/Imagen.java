/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author juans
 */
import java.util.Date;
import java.util.List;

public class Imagen {
    private List<String> tags;
    private Date fecha;
    private float gusto;
    private String nombre;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setGusto(float gusto) {
        this.gusto = gusto;
    }

    public List<String> getTags() {
        return tags;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getGusto() {
        return gusto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

}
