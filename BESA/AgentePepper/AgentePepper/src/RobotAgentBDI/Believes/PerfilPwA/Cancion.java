package RobotAgentBDI.Believes.PerfilPwA;

import java.util.List;



public class Cancion {
    private String nombre;
    private float gusto;
    private float duracion;
    private String linkVideo;
    private Evento eventoRel;
    private List<String> tags;

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

    public List<String> getTags() {
        return tags;
    }
    
    public void buscarCancion(String n){
        
    }
}
