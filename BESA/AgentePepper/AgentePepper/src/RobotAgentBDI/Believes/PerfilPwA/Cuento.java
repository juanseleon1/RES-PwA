package RobotAgentBDI.Believes.PerfilPwA;



import java.util.List;

public class Cuento {
    private String autor;
    private String genero;
    private float gusto;
    private List<Imagen> imagenes;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getGusto() {
        return gusto;
    }

    public void setGusto(float gusto) {
        this.gusto = gusto;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<Imagen> imagenes) {
        this.imagenes = imagenes;
    }
}
