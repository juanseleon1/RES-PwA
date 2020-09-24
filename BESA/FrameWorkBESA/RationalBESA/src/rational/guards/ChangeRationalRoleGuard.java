package rational.guards;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.RationalRole;
import rational.RationalState;
import rational.data.RoleListData;

/**
 *
 * @author Andres
 */
public class ChangeRationalRoleGuard extends GuardBESA {

    @Override
    public synchronized void funcExecGuard(EventBESA ebesa) {
        RationalState state = (RationalState) this.getAgent().getState();
        RoleListData roleListData = (RoleListData) ebesa.getData();

        // Checks the viability of roles.
        List<RationalRole> activeRoleList = getValidRoleList(roleListData.getRoleList());

        // Update active removing the goals not valid.
        for (int i = 0; i < state.getRoleList().size(); i++) {
            RationalRole role = state.getRoleList().get(i);
            if (!role.getRolePlan().inExecution()) {
                state.getRoleList().remove(role);
            }
        }
                
        // Add active roles.
        state.updateRoleList(activeRoleList);
        
        try {
            AgHandlerBESA handler = AdmBESA.getInstance().getHandlerByAlias(this.agent.getAlias());
            handler.sendEvent(new EventBESA(GoalExecutionGuard.class.getName(), null));
        } catch (ExceptionBESA ex) {
            Logger.getLogger(ChangeRationalRoleGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<RationalRole> getValidRoleList(List<RationalRole> roleList) {        
        if (roleList.size() > 1) {
            for (int i = 0; i < roleList.size(); i++) {
                if (!roleList.get(i).getRolePlan().inExecution()) {
                    List<String> a = roleList.get(i).getRolePlan().getResources();
                    for (int j = 0; j < roleList.size(); j++) {
                        if(i != j) {
                            List<String> b = roleList.get(j).getRolePlan().getResources();
                            if (isShareResources(a, b)) {
                                if (roleList.get(i).getRolePlan().getPriority() == roleList.get(j).getRolePlan().getPriority()) {
                                    roleList.remove(j);
                                } else {
                                    if (roleList.get(i).getRolePlan().getPriority() > roleList.get(j).getRolePlan().getPriority()) {
                                        roleList.remove(j);
                                    } else {
                                        roleList.remove(i);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return roleList;
    }

    public boolean isShareResources(List<String> a, List<String> b) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.get(i).equals(b.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

}
