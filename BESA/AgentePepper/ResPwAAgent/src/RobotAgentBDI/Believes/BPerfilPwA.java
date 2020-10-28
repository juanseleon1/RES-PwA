package RobotAgentBDI.Believes;

import RobotAgentBDI.Believes.PerfilPwA.Perfilpwa;
import rational.data.InfoData;
import rational.mapping.Believes;

public class BPerfilPwA implements Believes{
    private Perfilpwa perfil;

    public Perfilpwa getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfilpwa perfil) {
        this.perfil = perfil;
    }

    @Override
    public boolean update(InfoData si) {
        return true;
        
    }
    
}
