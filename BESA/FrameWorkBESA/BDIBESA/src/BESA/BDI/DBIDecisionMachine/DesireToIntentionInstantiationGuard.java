/*
 * @(#)DesireToIntentionInstantiationGuard.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.DBIDecisionMachine;

import BESA.BDI.AgentStructuralModel.Agent.AgentBDI;
import BESA.BDI.AgentStructuralModel.BDIMachineParams;
import BESA.BDI.AgentStructuralModel.GoalBDI;
import BESA.BDI.AgentStructuralModel.PotencialGoalStructure;
import BESA.BDI.AgentStructuralModel.StateBDI;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.logging.Level;
import java.util.logging.Logger;
import rational.guards.ChangeRationalRoleGuard;
import rational.mapping.Believes;

/**
 * <p>Class that represents the second Thread BDI Guard</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class DesireToIntentionInstantiationGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        AgentBDI agentBDI = (AgentBDI) agent;
        StateBDI stateBDI = (StateBDI) agentBDI.getState();
        try {
            Believes believes = stateBDI.getBelieves().clone();
            BDIMachineParams paramsBDI = stateBDI.getMachineBDIParams();
            paramsBDI.getPyramidGoals().clear();
            PotencialGoalStructure potencialGoalStructure = paramsBDI.getPotencialGoals();
            /*
            if(paramsBDI.getIntention() !=null){
                paramsBDI.getPyramidGoals().callGarbageCollector(believes, paramsBDI);
            }
            */            
            /** detect each potencial goal*/
            /** duties*/
            for (GoalBDI dutyGoal : potencialGoalStructure.getDutyGoalsList()) {
                dutyGoal.setDetectionValue(dutyGoal.detectGoal(believes));
                if (dutyGoal.getDetectionValue() > paramsBDI.getDutyThreshold()) {
                    dutyGoal.setPlausibilityLevel(dutyGoal.evaluatePlausibility(believes));
                    if (dutyGoal.getPlausibilityLevel() > paramsBDI.getDutyThreshold()) {
                        dutyGoal.setViabilityValue(dutyGoal.evaluateViability(believes));
                        if (dutyGoal.getViabilityValue() > paramsBDI.getDutyThreshold()) {
                            dutyGoal.setContributionValue(dutyGoal.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getDutyGoalsList().add(dutyGoal);
                        }
                    }
                }

            }

            /** needs*/
            for (GoalBDI needGoal : potencialGoalStructure.getNeedGoalsList()) {
                needGoal.setDetectionValue(needGoal.detectGoal(believes));
                if (needGoal.getDetectionValue() > paramsBDI.getNeedThreshold()) {
                    needGoal.setPlausibilityLevel(needGoal.evaluatePlausibility(believes));
                    if (needGoal.getPlausibilityLevel() > paramsBDI.getNeedThreshold()) {
                        needGoal.setViabilityValue(needGoal.evaluateViability(believes));
                        if (needGoal.getViabilityValue() > paramsBDI.getNeedThreshold()) {
                            needGoal.setContributionValue(needGoal.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getNeedGoalsList().add(needGoal);
                        }
                    }
                }

            }

            /** oportunities*/
            for (GoalBDI oportunityGoal : potencialGoalStructure.getOportunityGoalsList()) {
                oportunityGoal.setDetectionValue(oportunityGoal.detectGoal(believes));
                if (oportunityGoal.getDetectionValue() > paramsBDI.getOportunityThreshold()) {
                    oportunityGoal.setPlausibilityLevel(oportunityGoal.evaluatePlausibility(believes));
                    if (oportunityGoal.getPlausibilityLevel() > paramsBDI.getOportunityThreshold()) {
                        oportunityGoal.setViabilityValue(oportunityGoal.evaluateViability(believes));
                        if (oportunityGoal.getViabilityValue() > paramsBDI.getOportunityThreshold()) {
                            oportunityGoal.setContributionValue(oportunityGoal.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getOportunityGoalsList().add(oportunityGoal);
                        }
                    }
                }

            }

            /** requiremets*/
            for (GoalBDI requirementGoal : potencialGoalStructure.getRequirementGoalsList()) {
                requirementGoal.setDetectionValue(requirementGoal.detectGoal(believes));
                if (requirementGoal.getDetectionValue() > paramsBDI.getRequirementThreshold()) {
                    requirementGoal.setPlausibilityLevel(requirementGoal.evaluatePlausibility(believes));
                    if (requirementGoal.getPlausibilityLevel() > paramsBDI.getRequirementThreshold()) {
                        requirementGoal.setViabilityValue(requirementGoal.evaluateViability(believes));
                        if (requirementGoal.getViabilityValue() > paramsBDI.getRequirementThreshold()) {
                            requirementGoal.setContributionValue(requirementGoal.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getRequirementGoalsList().add(requirementGoal);
                        }
                    }
                }

            }


            /** survival*/
            for (GoalBDI survivalGoal : potencialGoalStructure.getSurvivalGoalsList()) {
                survivalGoal.setDetectionValue(survivalGoal.detectGoal(believes));
                if (survivalGoal.getDetectionValue() > paramsBDI.getSurvivalThreshold()) {
                    survivalGoal.setPlausibilityLevel(survivalGoal.evaluatePlausibility(believes));
                    if (survivalGoal.getPlausibilityLevel() > paramsBDI.getSurvivalThreshold()) {
                        survivalGoal.setViabilityValue(survivalGoal.evaluateViability(believes));
                        if (survivalGoal.getViabilityValue() > paramsBDI.getSurvivalThreshold()) {
                            survivalGoal.setContributionValue(survivalGoal.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getSurvivalGoalsList().add(survivalGoal);
                        }
                    }
                }

            }
            
            /** AttentionCycle*/
            for (GoalBDI attentionCycle : potencialGoalStructure.getAttentionCycleGoalsList()) {
                attentionCycle.setDetectionValue(attentionCycle.detectGoal(believes));
                if (attentionCycle.getDetectionValue() > paramsBDI.getAttentionCycleThreshold()) {
                    attentionCycle.setPlausibilityLevel(attentionCycle.evaluatePlausibility(believes));
                    if (attentionCycle.getPlausibilityLevel() > paramsBDI.getAttentionCycleThreshold()) {
                        attentionCycle.setViabilityValue(attentionCycle.evaluateViability(believes));
                        if (attentionCycle.getViabilityValue() > paramsBDI.getAttentionCycleThreshold()) {
                            attentionCycle.setContributionValue(attentionCycle.evaluateContribution(stateBDI));
                            paramsBDI.getPyramidGoals().getAttentionCycleGoalsList().add(attentionCycle);
                        }
                    }
                }

            }
            
            paramsBDI.setIntention(paramsBDI.getPyramidGoals().getCurrentIntentionGoal());
            if (paramsBDI.getIntention() != null) {               
                if (paramsBDI.getIntention().evaluateMappingViability(paramsBDI, believes)) {
                    if (paramsBDI.getIntention().predictResultUnlegality(stateBDI)) {
                        EventBESA eventBesa = new EventBESA(ChangeRationalRoleGuard.class.getName(), paramsBDI.getIntention().getRole());
                        AgHandlerBESA agHandlerBESA = agentBDI.getAdmLocal().getHandlerByAlias(agentBDI.getAlias());
                        agHandlerBESA.sendEvent(eventBesa);
                    }
                }
            }
            
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(DesireToIntentionInstantiationGuard.class.getName()).log(Level.SEVERE, null, ex);
        }
        stateBDI.setEndedTheDesiresMachine(true);
        try {
            EventBESA eventBesa = new EventBESA(EndedTheDesiresMachineGuard.class.getName(), null);
            AgHandlerBESA agHandlerBESA;
            agHandlerBESA = agentBDI.getAdmLocal().getHandlerByAlias(agentBDI.getAlias());
            agHandlerBESA.sendEvent(eventBesa);
        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
            
    }
}