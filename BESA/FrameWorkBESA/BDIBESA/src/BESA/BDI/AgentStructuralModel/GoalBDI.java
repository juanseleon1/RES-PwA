/*
 * @(#)GoalBDI.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel;

import BESA.BDI.AgentStructuralModel.Functions.ContributionComparator;
import BESA.Kernel.Agent.Event.KernellAgentEventExceptionBESA;
import rational.RationalRole;
import rational.mapping.Believes;
import java.io.Serializable;

/**
 * <p>Class that represents the goals in the BDI flow</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class GoalBDI implements BDIEvaluable, Serializable, Comparable<GoalBDI> {

    private int id;
    private double plausibilityLevel;
    private double viabilityValue;
    private double contributionValue;
    private double detectionValue;
    private RationalRole role;
    private String description;
    private GoalBDITypes type;    
    private boolean succeed;
    private boolean expropriated;
    
    public GoalBDI(int id, RationalRole role, String description, GoalBDITypes type) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.type = type;        
        this.role.getRolePlan().setPlanID(id);
        this.role.getRolePlan().setPriority(calcPriority(type));
        expropriated = false;
    }
    
    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succed) {
        this.succeed = succed;
    }

    public double getDetectionValue() {
        return detectionValue;
    }

    public void setDetectionValue(double detectionValue) {
        this.detectionValue = detectionValue;
    }

    public double getContributionValue() {
        return contributionValue;
    }

    public void setContributionValue(double contributionValue) {
        this.contributionValue = contributionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPlausibilityLevel() {
        return plausibilityLevel;
    }

    public void setPlausibilityLevel(double plausibilityLevel) {
        this.plausibilityLevel = plausibilityLevel;
    }

    public GoalBDITypes getType() {
        return type;
    }

    public void setType(GoalBDITypes type) {
        this.type = type;
    }

    public RationalRole getRole() {
        return role;
    }

    public void setRole(RationalRole role) {
        this.role = role;
    }

    public double getViabilityValue() {
        return viabilityValue;
    }

    public void setViabilityValue(double viabilityValue) {
        this.viabilityValue = viabilityValue;
    }   

    public boolean isExpropriated() {
        return expropriated;
    }

    public void setExpropriated(boolean expropriated) {
        this.expropriated = expropriated;
    }
    
    /**
     * <p>evaluate viability for a mapping proccess using the believes</p>
     * @param machineParams
     * @param believes
     * @return 
     */
    public boolean evaluateMappingViability(BDIMachineParams machineParams, Believes believes) throws KernellAgentEventExceptionBESA {
        boolean returnValue = false;
        double viabilityEvaluation = this.evaluateViability(believes);
        switch (this.getType()) {
            case DUTY:
                if (viabilityEvaluation > machineParams.getDutyThreshold() ) {
                    returnValue = true;
                }

            case NEED:
                if (viabilityEvaluation > machineParams.getNeedThreshold() ) {
                    returnValue = true;
                }
            case OPORTUNITY:
                if (viabilityEvaluation > machineParams.getOportunityThreshold()) {
                    returnValue = true;
                }
            case REQUIREMENT:
                if (viabilityEvaluation > machineParams.getRequirementThreshold()) {
                    returnValue = true;
                }
            case SURVIVAL:
                if (viabilityEvaluation > machineParams.getSurvivalThreshold()) {
                    returnValue = true;
                }
        }

        return returnValue;
    }

    @Override
    public int compareTo(GoalBDI o) {
        ContributionComparator contributionComparator = new ContributionComparator();
        return contributionComparator.compare(this, o);
    }    

    @Override
    public boolean equals(Object goal) {
        if(goal == null){
            return false;
        }else if(!(goal instanceof GoalBDI)){
            return false;
        }else{
            GoalBDI g = (GoalBDI) goal;
            return this.getId() == g.getId();
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }
    
    public static int calcPriority(GoalBDITypes type) {
        switch (type) {
            case DUTY:
                return 1;
            case NEED:
                return 2;
            case OPORTUNITY:
                return 3;
            case REQUIREMENT:
                return 4;
            case SURVIVAL:
                return 5;
        }
        return 0;
    }
    
}