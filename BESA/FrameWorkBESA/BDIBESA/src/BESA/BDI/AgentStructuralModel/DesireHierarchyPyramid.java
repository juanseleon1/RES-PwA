/*
 * @(#)DesireHierarchyPyramid.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import BESA.BDI.AgentStructuralModel.Functions.ContributionComparator;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.mapping.Believes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <p>Class that represents the DesireHierarchyPyramid with the desire instanciated goals</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class DesireHierarchyPyramid implements Serializable {

    private ContributionComparator comparator;
    private SortedSet<GoalBDI> survivalGoalsList;
    private SortedSet<GoalBDI> dutyGoalsList;
    private SortedSet<GoalBDI> oportunityGoalsList;
    private SortedSet<GoalBDI> requirementGoalsList;
    private SortedSet<GoalBDI> needGoalsList;
    private List<SortedSet<GoalBDI>> generalHerarchyList;
    private List<GoalBDI> currentIntentionGoalList;

    public DesireHierarchyPyramid() {
        comparator = new ContributionComparator();
        survivalGoalsList = new TreeSet<GoalBDI>(comparator);
        dutyGoalsList = new TreeSet<GoalBDI>(comparator);
        oportunityGoalsList = new TreeSet<GoalBDI>(comparator);
        requirementGoalsList = new TreeSet<GoalBDI>(comparator);
        needGoalsList = new TreeSet<GoalBDI>(comparator);
        generalHerarchyList = Collections.synchronizedList(new ArrayList<SortedSet<GoalBDI>>());
        generalHerarchyList.add(survivalGoalsList);
        generalHerarchyList.add(dutyGoalsList);
        generalHerarchyList.add(oportunityGoalsList);
        generalHerarchyList.add(requirementGoalsList);
        generalHerarchyList.add(needGoalsList);
        currentIntentionGoalList = new ArrayList<>();
    }

    public DesireHierarchyPyramid(ContributionComparator comparator, SortedSet<GoalBDI> survivalGoalsList, SortedSet<GoalBDI> dutyGoalsList, SortedSet<GoalBDI> oportunityGoalsList, SortedSet<GoalBDI> requirementGoalsList, SortedSet<GoalBDI> needGoalsList, List<GoalBDI> currentIntentionGoalList) {
        this.comparator = comparator;
        this.survivalGoalsList = survivalGoalsList;
        this.dutyGoalsList = dutyGoalsList;
        this.oportunityGoalsList = oportunityGoalsList;
        this.requirementGoalsList = requirementGoalsList;
        this.needGoalsList = needGoalsList;
        this.currentIntentionGoalList = currentIntentionGoalList;
    }

    public ContributionComparator getComparator() {
        return comparator;
    }

    public void setComparator(ContributionComparator comparator) {
        this.comparator = comparator;
    }

    public void setCurrentIntentionGoal(List<GoalBDI> currentIntentionGoalList) {
        this.currentIntentionGoalList = currentIntentionGoalList;
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

    public List<SortedSet<GoalBDI>> getGeneralHerarchyList() {
        return generalHerarchyList;
    }

    public void setGeneralHerarchyList(List<SortedSet<GoalBDI>> generalHerarchyList) {
        this.generalHerarchyList = generalHerarchyList;
    }

    /**
     * <p> method to get the currentIntentionGoal </p>
     * @return 
     */
    public List<GoalBDI> getCurrentIntentionGoalList() {
        synchronized (this) {
            currentIntentionGoalList.clear();
            for (SortedSet<GoalBDI> setGoal : this.getGeneralHerarchyList()) {
                if (!setGoal.isEmpty()) {
                    Iterator<GoalBDI> it = setGoal.iterator();
                    while (it.hasNext()) {
                        GoalBDI goal = it.next();
                        if (!goal.getRole().getRolePlan().inExecution())
                            currentIntentionGoalList.add(goal);
                    }
                }
            }
            return currentIntentionGoalList;
        }
    }

    /**
     * <p>method that dismiss the non feasible or finished goals </p>
     * @param believes
     * @param machineParamsBDI 
     */
    public void callGarbageCollector(Believes believes, BDIMachineParams machineParamsBDI) throws KernellAgentEventExceptionBESA {

        synchronized(this.generalHerarchyList){
            for (Set<GoalBDI> set : this.getGeneralHerarchyList()) {
                Iterator<GoalBDI> setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    GoalBDI currentElement = setIterator.next();
                    GoalBDITypes type = currentElement.getType();
                    switch (type) {
                        case DUTY:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getDutyThreshold() || currentElement.goalSucceeded(believes)) {
                                setIterator.remove();
                            }
                            break;
                        case NEED:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getNeedThreshold() || currentElement.goalSucceeded(believes)) {
                                setIterator.remove();
                            }
                            break;
                        case OPORTUNITY:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getOportunityThreshold() || currentElement.goalSucceeded(believes)) {
                                setIterator.remove();
                            }
                            break;
                        case REQUIREMENT:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getRequirementThreshold() || currentElement.goalSucceeded(believes)) {
                                setIterator.remove();
                            }
                            break;
                        case SURVIVAL:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getSurvivalThreshold() || currentElement.goalSucceeded(believes)) {
                                setIterator.remove();
                            }
                            break;
                    }
                }
            }
        }
    }

    /**
     * <p> to String overrided to log using </p>
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder resultValue = new StringBuilder();
        for (Set<GoalBDI> set : this.getGeneralHerarchyList()) {
            Iterator<GoalBDI> setIterator = set.iterator();
            while (setIterator.hasNext()) {
                GoalBDI currentElement = setIterator.next();
                resultValue.append(currentElement.getDescription()).append(currentElement.getId()).append("   TIPO:   ").append(currentElement.getType().getName()).append("   CONTRIBUTION      ").append(currentElement.getContributionValue()).append("    DETECTION    ").append(currentElement.getDetectionValue()).append("   FEASIBLE   ").append(currentElement.getViabilityValue()).append("   PLAUSIBLE   ").append(currentElement.getPlausibilityLevel()).append("\n");
            }
        }
        return resultValue.toString();
    }
}
