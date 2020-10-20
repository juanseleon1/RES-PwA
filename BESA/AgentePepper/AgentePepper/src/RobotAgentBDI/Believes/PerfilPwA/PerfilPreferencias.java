package RobotAgentBDI.Believes.PerfilPwA;



import java.util.HashMap;

public class PerfilPreferencias {
    private HashMap<String,ActividadPepper> actividadesSis;
    private String nombrePreferido;
    private boolean gustoKaraoke;
    private boolean gustoBaile;
    private boolean gustoMusica;

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
    
    public boolean isGustoKaraoke() {
        return gustoKaraoke;
    }

    public void setGustoKaraoke(boolean gustoKaraoke) {
        this.gustoKaraoke = gustoKaraoke;
    }

    public boolean isGustoBaile() {
        return gustoBaile;
    }

    public void setGustoBaile(boolean gustoBaile) {
        this.gustoBaile = gustoBaile;
    }

    public boolean isGustoMusica() {
        return gustoMusica;
    }
    
}
