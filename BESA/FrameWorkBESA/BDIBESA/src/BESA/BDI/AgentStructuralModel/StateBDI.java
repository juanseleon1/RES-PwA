 package BESA.BDI.AgentStructuralModel;

import rational.RationalState;
import java.io.Serializable;


public class StateBDI extends RationalState implements Serializable {
     
    private BDIMachineParams machineBDIParams;  
    

    public StateBDI() {
        super();
        machineBDIParams = new BDIMachineParams();
    }

    public BDIMachineParams getMachineBDIParams() {
        return machineBDIParams;
    }
   
}