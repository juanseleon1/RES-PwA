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
    private SortedSet<GoalBDI> attentionCycleGoalsList;
    private List<SortedSet<GoalBDI>> generalHerarchyList;
    private GoalBDI currentIntentionGoal;

    public DesireHierarchyPyramid() {
        comparator = new ContributionComparator();
        survivalGoalsList = (new TreeSet<GoalBDI>(comparator));
        dutyGoalsList = (new TreeSet<GoalBDI>(comparator));
        oportunityGoalsList = (new TreeSet<GoalBDI>(comparator));
        requirementGoalsList = (new TreeSet<GoalBDI>(comparator));
        needGoalsList = (new TreeSet<GoalBDI>(comparator));
        attentionCycleGoalsList = (new TreeSet<GoalBDI>(comparator));
        generalHerarchyList = Collections.synchronizedList(new ArrayList<SortedSet<GoalBDI>>());
        generalHerarchyList.add(survivalGoalsList);
        generalHerarchyList.add(dutyGoalsList);
        generalHerarchyList.add(oportunityGoalsList);
        generalHerarchyList.add(requirementGoalsList);
        generalHerarchyList.add(needGoalsList);
        generalHerarchyList.add(attentionCycleGoalsList);
        
    }

    public DesireHierarchyPyramid(ContributionComparator comparator, SortedSet<GoalBDI> survivalGoalsList, SortedSet<GoalBDI> dutyGoalsList, SortedSet<GoalBDI> oportunityGoalsList, SortedSet<GoalBDI> requirementGoalsList, SortedSet<GoalBDI> needGoalsList, SortedSet<GoalBDI> attentionCycleGoalsList, GoalBDI currentIntentionGoal) {
        this.comparator = comparator;
        this.survivalGoalsList = survivalGoalsList;
        this.dutyGoalsList = dutyGoalsList;
        this.oportunityGoalsList = oportunityGoalsList;
        this.requirementGoalsList = requirementGoalsList;
        this.needGoalsList = needGoalsList;
        this.attentionCycleGoalsList= attentionCycleGoalsList;
        this.currentIntentionGoal = currentIntentionGoal;
    }

    public ContributionComparator getComparator() {
        return comparator;
    }

    public void setComparator(ContributionComparator comparator) {
        this.comparator = comparator;
    }

    public void setCurrentIntentionGoal(GoalBDI currentIntentionGoal) {
        this.currentIntentionGoal = currentIntentionGoal;
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
    
    public synchronized List<SortedSet<GoalBDI>> getGeneralHerarchyList() {
        return generalHerarchyList;
    }

    public synchronized void setGeneralHerarchyList(List<SortedSet<GoalBDI>> generalHerarchyList) {
        this.generalHerarchyList = generalHerarchyList;
    }

    public void clear(){
        this.dutyGoalsList.clear();
        this.needGoalsList.clear();
        this.oportunityGoalsList.clear();
        this.requirementGoalsList.clear();
        this.survivalGoalsList.clear();
        this.attentionCycleGoalsList.clear();
      
    }
    /**
     * <p> method to get the currentIntentionGoal </p>
     * @return 
     */
    public GoalBDI getCurrentIntentionGoal() {
        synchronized (this) {
            for (SortedSet<GoalBDI> setGoal : this.getGeneralHerarchyList()) {
                if (!setGoal.isEmpty()) {
                    this.setCurrentIntentionGoal(setGoal.first());
                    break;
                }
            }
            return currentIntentionGoal;
        }
    }

    /**
     * <p>method that dismiss the non feasible or finished goals </p>
     * @param believes
     * @param machineParamsBDI 
     */
    public void callGarbageCollector(Believes believes, BDIMachineParams machineParamsBDI) throws KernellAgentEventExceptionBESA {
        synchronized(this){
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
                        case ATTENTION_CYCLE:
                            if (currentElement.evaluateViability(believes) <= machineParamsBDI.getAttentionCycleThreshold() || currentElement.goalSucceeded(believes)) {
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
