/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RobotAgentBDI.Metas;

/**
 *
 * @author maria.f.garces.cala
 */
public class ConfiguracionInicial extends GoalBDI{

    private static String descrip = "ConfiguracionInicial";

    public static ConfiguracionInicial buildGoal() {

        CreacionJSON confInicial = new CreacionJSON();
        List<String> resources= new ArrayList<>();
        List<Task> tarea= new ArrayList<>();

        Plan rolePlan= new Plan();
        rolePlan.addTask(confInicial);

        RationalRole animateRole = new RationalRole(descrip, rolePlan);
        ConfiguracionInicial b= new ConfiguracionInicial(InitRESPwA.getPlanID(), animateRole, descrip, GoalBDITypes.DUTY);
        return b;
    }
    
    public ConfiguracionInicial(int id, RationalRole role, String description, GoalBDITypes type) {
        super(id, role, description, type);
        //System.out.println("Meta ConfiguracionInicial created");
    }

    @Override
    public double evaluateViability(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial evaluateViability");
        return 1;
    }

    @Override
    public double detectGoal(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial detectGoal");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        if(!blvs.getbEstadoRobot().isRobotInicializado())
        {
            return 1;
        }
        return 0;
    }

    @Override
    public double evaluatePlausibility(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial evaluatePlausibility");
        return 1;
    }

    @Override
    public double evaluateContribution(StateBDI stateBDI) throws KernellAgentEventExceptionBESA {
        System.out.println("Meta ConfiguracionInicial evaluateContribution");
        return 1;
    }

    @Override
    public boolean predictResultUnlegality(StateBDI agentStatus) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial predictResultUnlegality");
        return true;
    }

    @Override
    public boolean goalSucceeded(Believes believes) throws KernellAgentEventExceptionBESA {
        //System.out.println("Meta ConfiguracionInicial goalSucceeded");
        RobotAgentBelieves blvs = (RobotAgentBelieves) believes;
        return blvs.getEstadoRobot().isRobotInicializado();
    }

}
