package RobotAgentBDI.Believes.PerfilPwA;





import java.util.Date;
import java.util.List;

public class Imagen {
    private List<String> tags;
    private Date fecha;
    private float gusto;

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
    
    

}
