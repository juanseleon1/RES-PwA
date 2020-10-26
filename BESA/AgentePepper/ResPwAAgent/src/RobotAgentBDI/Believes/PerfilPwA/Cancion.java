package RobotAgentBDI.Believes.PerfilPwA;

import java.util.List;



public class Cancion{
    private String nombre;
    private float gusto;
    private float duracion;
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

    public List<String> getTags() {
        return tags;
    }
    
    
}
