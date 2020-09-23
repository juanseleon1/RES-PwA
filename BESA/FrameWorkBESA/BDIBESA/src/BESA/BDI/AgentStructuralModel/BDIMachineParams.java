/*
 * @(#)BDIMachineParams.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * <p>Class that contains some params value for the BDI machine</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class BDIMachineParams  implements Serializable {

    /** structure that represents the pyramid for the desire priority */
    private DesireHierarchyPyramid pyramidGoals;
    /** list of goals that the agent knows*/
    private PotencialGoalStructure potencialGoals;
    /** main goal for the BDI agent*/
    private GoalBDI mainGoal;
    /** intention goal for the BDI agent*/
    private List<GoalBDI> intentionList;

    
    /** Thresholds for the goal operations*/
    private double dutyThreshold;
    private double survivalThreshold;
    private double oportunityThreshold;
    private double requirementThreshold;
    private double needThreshold;
    private double mainGoalThreshold;

    public BDIMachineParams() {
        pyramidGoals = new DesireHierarchyPyramid();
        potencialGoals = new PotencialGoalStructure();
    }

    public BDIMachineParams(double dutyThreshold, double survivalThreshold, double oportunityThreshold, double requirementThreshold, double needThreshold) {
        this.dutyThreshold = dutyThreshold;
        this.survivalThreshold = survivalThreshold;
        this.oportunityThreshold = oportunityThreshold;
        this.requirementThreshold = requirementThreshold;
        this.needThreshold = needThreshold;
        this.pyramidGoals = new DesireHierarchyPyramid();
    }

    public PotencialGoalStructure getPotencialGoals() {
        return potencialGoals;
    }

    public void setPotencialGoals(PotencialGoalStructure potencialGoals) {
        this.potencialGoals = potencialGoals;
    }

    public DesireHierarchyPyramid getPyramidGoals() {
        return pyramidGoals;
    }

    public void setPyramidGoals(DesireHierarchyPyramid pyramidGoals) {
        this.pyramidGoals = pyramidGoals;
    }

    public double getDutyThreshold() {
        return dutyThreshold;
    }

    public void setDutyThreshold(double dutyThreshold) {
        this.dutyThreshold = dutyThreshold;
    }

    public double getNeedThreshold() {
        return needThreshold;
    }

    public void setNeedThreshold(double needThreshold) {
        this.needThreshold = needThreshold;
    }

    public double getOportunityThreshold() {
        return oportunityThreshold;
    }

    public void setOportunityThreshold(double oportunityThreshold) {
        this.oportunityThreshold = oportunityThreshold;
    }

    public double getRequirementThreshold() {
        return requirementThreshold;
    }

    public void setRequirementThreshold(double requirementThreshold) {
        this.requirementThreshold = requirementThreshold;
    }

    public double getSurvivalThreshold() {
        return survivalThreshold;
    }

    public void setSurvivalThreshold(double survivalThreshold) {
        this.survivalThreshold = survivalThreshold;
    }

    public GoalBDI getMainGoal() {
        return mainGoal;
    }

    public void setMainGoal(GoalBDI mainGoal) {
        this.mainGoal = mainGoal;
    }

    public double getMainGoalThreshold() {
        return mainGoalThreshold;
    }

    public void setMainGoalThreshold(double mainGoalThreshold) {
        this.mainGoalThreshold = mainGoalThreshold;
    }
    
    public List<GoalBDI> getIntentionList() {
        return intentionList;
    }

    public void setIntentionList(List<GoalBDI> intentionList) {
        this.intentionList = intentionList;
    }

    /**
     * <p>method that allows to delete an elmento from the pyramid using its id</p>
     * @param goalId 
     */
    public void deleteFromPyramid(long goalId) {
        
        for (Set<GoalBDI> set : this.getPyramidGoals().getGeneralHerarchyList()) {
            Iterator<GoalBDI> setIterator = set.iterator();
            while (setIterator.hasNext()) {
                GoalBDI currentElement = setIterator.next();
                if (currentElement.getId() == goalId) {                  
                    setIterator.remove();
                }
            }
        }
    }
    
    public void addPotentialGoal(GoalBDI goal){
        if(goal.getType() == GoalBDITypes.DUTY ){
            this.potencialGoals.getDutyGoalsList().add(goal);
        }else if(goal.getType() == GoalBDITypes.NEED ){
            this.potencialGoals.getNeedGoalsList().add(goal);
        }else if(goal.getType() == GoalBDITypes.OPORTUNITY ){
            this.potencialGoals.getOportunityGoalsList().add(goal);
        }else if(goal.getType() == GoalBDITypes.REQUIREMENT ){
            this.potencialGoals.getRequirementGoalsList().add(goal);
        }else if(goal.getType() == GoalBDITypes.SURVIVAL ){
            this.potencialGoals.getSurvivalGoalsList().add(goal);
        }
    }
}
