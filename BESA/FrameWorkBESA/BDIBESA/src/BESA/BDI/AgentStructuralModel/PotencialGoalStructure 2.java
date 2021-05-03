/*
 * @(#)DesireHierarchyPyramid.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <p>Class that represents the Structure with the potencial Goals</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class PotencialGoalStructure implements Serializable {

    private SortedSet<GoalBDI> survivalGoalsList;
    private SortedSet<GoalBDI> dutyGoalsList;
    private SortedSet<GoalBDI> oportunityGoalsList;
    private SortedSet<GoalBDI> requirementGoalsList;
    private SortedSet<GoalBDI> needGoalsList;
    private SortedSet<GoalBDI> attentionCycleGoalsList;

    public PotencialGoalStructure() {

        survivalGoalsList = new TreeSet<GoalBDI>();
        dutyGoalsList = new TreeSet<GoalBDI>();
        oportunityGoalsList = new TreeSet<GoalBDI>();
        requirementGoalsList = new TreeSet<GoalBDI>();
        needGoalsList = new TreeSet<GoalBDI>();
        attentionCycleGoalsList = new TreeSet<GoalBDI>();
    }

    public SortedSet<GoalBDI> getDutyGoalsList() {
        return dutyGoalsList;
    }

    public void setDutyGoalsList(SortedSet<GoalBDI> dutyGoalsList) {
        this.dutyGoalsList = dutyGoalsList;
    }

    public SortedSet<GoalBDI> getNeedGoalsList() {
        return needGoalsList;
    }

    public void setNeedGoalsList(SortedSet<GoalBDI> needGoalsList) {
        this.needGoalsList = needGoalsList;
    }

    public SortedSet<GoalBDI> getOportunityGoalsList() {
        return oportunityGoalsList;
    }

    public void setOportunityGoalsList(SortedSet<GoalBDI> oportunityGoalsList) {
        this.oportunityGoalsList = oportunityGoalsList;
    }

    public SortedSet<GoalBDI> getRequirementGoalsList() {
        return requirementGoalsList;
    }

    public void setRequirementGoalsList(SortedSet<GoalBDI> requirementGoalsList) {
        this.requirementGoalsList = requirementGoalsList;
    }

    public SortedSet<GoalBDI> getSurvivalGoalsList() {
        return survivalGoalsList;
    }

    public void setSurvivalGoalsList(SortedSet<GoalBDI> survivalGoalsList) {
        this.survivalGoalsList = survivalGoalsList;
    }

    public SortedSet<GoalBDI> getAttentionCycleGoalsList() {
        return attentionCycleGoalsList;
    }

    public void setAttentionCycleGoalsList(SortedSet<GoalBDI> attentionCycleGoalsList) {
        this.attentionCycleGoalsList = attentionCycleGoalsList;
    }
    
    
}