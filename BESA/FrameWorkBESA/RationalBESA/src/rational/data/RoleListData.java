/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.data;

import BESA.Kernel.Agent.Event.DataBESA;
import java.util.List;
import rational.RationalRole;

/**
 *
 * @author usuario
 */
public class RoleListData extends DataBESA {
    
    private List<RationalRole> roleList;

    public RoleListData() {
    }
    
    public RoleListData(List<RationalRole> roleList) {
        this.roleList = roleList;
    }

    public List<RationalRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RationalRole> roleList) {
        this.roleList = roleList;
    }
    
}
