package RobotAgentBDI.Believes.PerfilPwA;

public class PerfilPreferencias {
    private ActCuenteria cuenteria;
    private ActMemorama memorama;
    private ActMusicoterapia musicoterapia;
    private String nombrePreferido;
    private boolean gustoKaraoke;
    private boolean gustoBaile;
    private boolean gustoMusica;
    private boolean volumenPreferido;



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

    public boolean isVolumenPreferido() {
        return volumenPreferido;
    }

    public void setVolumenPreferido(boolean volumenPreferido) {
        this.volumenPreferido = volumenPreferido;
    }

    public void setCuenteria(ActCuenteria cuenteria) {
        this.cuenteria = cuenteria;
    }

    public void setMemorama(ActMemorama memorama) {
        this.memorama = memorama;
    }

    public void setMusicoterapia(ActMusicoterapia musicoterapia) {
        this.musicoterapia = musicoterapia;
    }

    public void setGustoMusica(boolean gustoMusica) {
        this.gustoMusica = gustoMusica;
    }

    public ActCuenteria getCuenteria() {
        return cuenteria;
    }

    public ActMemorama getMemorama() {
        return memorama;
    }

    public ActMusicoterapia getMusicoterapia() {
        return musicoterapia;
    }
    
}
