/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rational.data;

import rational.mapping.Plan;

/**
 *
 * @author fjroldan
 */
public class CheckExecutionData extends InfoData {

    private Plan plan;

    public CheckExecutionData() {
        super("");
    }

    public CheckExecutionData(Plan plan) {
        super("");
        this.plan = plan;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

}
