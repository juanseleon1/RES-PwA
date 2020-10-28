package RobotAgentBDI.Believes;

import Init.InitRESPwA;
import RobotAgentBDI.Believes.PerfilPwA.Perfilpwa;
import RobotAgentBDI.Believes.PerfilPwA.handlers.PerfilpwaJpaController;
import javax.persistence.Persistence;
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

    void getFromDB(String cedula) {
        PerfilpwaJpaController pjc=new PerfilpwaJpaController(Persistence.createEntityManagerFactory(InitRESPwA.emf));
      perfil= pjc.findPerfilpwa(cedula);
    }

    void updateToDB() throws Exception {
    PerfilpwaJpaController pjc=new PerfilpwaJpaController(Persistence.createEntityManagerFactory(InitRESPwA.emf));
      pjc.edit(perfil);
    }
    
}
