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
import java.util.ArrayList;
import java.util.List;
import rational.RationalAgent;
import rational.RationalRole;
import rational.data.CheckExecutionData;
import rational.data.GoalFinalizeData;
import rational.data.RoleListData;
import rational.guards.ChangeRationalRoleGuard;
import rational.guards.EnableTaskExecutionGuard;
import rational.mapping.Believes;
import rational.mapping.Plan;

/**
 * <p>
 * Class that represents the second Thread BDI Guard</p>
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
public class DesireToIntentionInstantiationGuard extends GuardBESA {

    @Override
    public void funcExecGuard(EventBESA event) {
        try {
            AgentBDI agentBDI = (AgentBDI) agent;
            StateBDI stateBDI = (StateBDI) agentBDI.getState();
            Believes believes = stateBDI.getBelieves();
            BDIMachineParams paramsBDI = stateBDI.getMachineBDIParams();
            PotencialGoalStructure potencialGoalStructure = paramsBDI.getPotencialGoals();

            if (event.getData() instanceof CheckExecutionData) {
                CheckExecutionData data = (CheckExecutionData) event.getData();
                Plan plan = data.getPlan();
                List<GoalBDI> goals = paramsBDI.getIntentionList();
                for (int i = 0; i < goals.size(); i++) {
                    GoalBDI goal = goals.get(i);
                    if (goal.getRole().getRolePlan().getPlanID() == plan.getPlanID()) {
                        if (goal.evaluateViability(believes) == 1.0f) {
                            if (!goal.isExpropriated()) {
                                ((RationalAgent)this.agent).notifyEnableTaskExecution(goal.getRole().getRoleName());
                                return;
                            }
                        } else {                            
                            plan.setInExecution(false);
                            goal.setSucceed(true);
                            GoalFinalizeData response = new GoalFinalizeData(plan.getCommand()); 
                            believes.update(response);
                            break;
                        }
                    }
                }
            }

            if (paramsBDI.getIntentionList() != null) {
                paramsBDI.getPyramidGoals().callGarbageCollector(believes, paramsBDI);
            }

            /**
             * detect each potencial goal
             */
            /**
             * duties
             */
            for (GoalBDI dutyGoal : potencialGoalStructure.getDutyGoalsList()) {
                dutyGoal.setDetectionValue(dutyGoal.detectGoal(believes));
                if (dutyGoal.getDetectionValue() > paramsBDI.getDutyThreshold()) {
                    dutyGoal.setPlausibilityLevel(dutyGoal.evaluatePlausibility(believes));
                    if (dutyGoal.getPlausibilityLevel() > paramsBDI.getDutyThreshold()) {
                        dutyGoal.setViabilityValue(dutyGoal.evaluateViability(believes));
                        if (dutyGoal.getViabilityValue() > paramsBDI.getDutyThreshold()) {
                            dutyGoal.setContributionValue(dutyGoal.evaluateContribution(stateBDI));
                            if (!paramsBDI.getPyramidGoals().getDutyGoalsList().contains(dutyGoal)) {
                                paramsBDI.getPyramidGoals().getDutyGoalsList().add(dutyGoal);
                            }
                        }
                    }
                }

            }

            /**
             * needs
             */
            for (GoalBDI needGoal : potencialGoalStructure.getNeedGoalsList()) {
                needGoal.setDetectionValue(needGoal.detectGoal(believes));
                if (needGoal.getDetectionValue() > paramsBDI.getNeedThreshold()) {
                    needGoal.setPlausibilityLevel(needGoal.evaluatePlausibility(believes));
                    if (needGoal.getPlausibilityLevel() > paramsBDI.getNeedThreshold()) {
                        needGoal.setViabilityValue(needGoal.evaluateViability(believes));
                        if (needGoal.getViabilityValue() > paramsBDI.getNeedThreshold()) {
                            needGoal.setContributionValue(needGoal.evaluateContribution(stateBDI));
                            if (!paramsBDI.getPyramidGoals().getNeedGoalsList().contains(needGoal)) {
                                paramsBDI.getPyramidGoals().getNeedGoalsList().add(needGoal);
                            }
                        }
                    }
                }

            }

            /**
             * oportunities
             */
            for (GoalBDI oportunityGoal : potencialGoalStructure.getOportunityGoalsList()) {
                oportunityGoal.setDetectionValue(oportunityGoal.detectGoal(believes));
                if (oportunityGoal.getDetectionValue() > paramsBDI.getOportunityThreshold()) {
                    oportunityGoal.setPlausibilityLevel(oportunityGoal.evaluatePlausibility(believes));
                    if (oportunityGoal.getPlausibilityLevel() > paramsBDI.getOportunityThreshold()) {
                        oportunityGoal.setViabilityValue(oportunityGoal.evaluateViability(believes));
                        if (oportunityGoal.getViabilityValue() > paramsBDI.getOportunityThreshold()) {
                            oportunityGoal.setContributionValue(oportunityGoal.evaluateContribution(stateBDI));
                            if (!paramsBDI.getPyramidGoals().getOportunityGoalsList().contains(oportunityGoal)) {
                                paramsBDI.getPyramidGoals().getOportunityGoalsList().add(oportunityGoal);
                            }
                        }
                    }
                }

            }

            /**
             * requiremets
             */
            for (GoalBDI requirementGoal : potencialGoalStructure.getRequirementGoalsList()) {
                requirementGoal.setDetectionValue(requirementGoal.detectGoal(believes));
                if (requirementGoal.getDetectionValue() > paramsBDI.getRequirementThreshold()) {
                    requirementGoal.setPlausibilityLevel(requirementGoal.evaluatePlausibility(believes));
                    if (requirementGoal.getPlausibilityLevel() > paramsBDI.getRequirementThreshold()) {
                        requirementGoal.setViabilityValue(requirementGoal.evaluateViability(believes));
                        if (requirementGoal.getViabilityValue() > paramsBDI.getRequirementThreshold()) {
                            requirementGoal.setContributionValue(requirementGoal.evaluateContribution(stateBDI));
                            if (!paramsBDI.getPyramidGoals().getRequirementGoalsList().contains(requirementGoal)) {
                                paramsBDI.getPyramidGoals().getRequirementGoalsList().add(requirementGoal);
                            }
                        }
                    }
                }

            }

            /**
             * survival
             */
            for (GoalBDI survivalGoal : potencialGoalStructure.getSurvivalGoalsList()) {
                survivalGoal.setDetectionValue(survivalGoal.detectGoal(believes));
                if (survivalGoal.getDetectionValue() > paramsBDI.getSurvivalThreshold()) {
                    survivalGoal.setPlausibilityLevel(survivalGoal.evaluatePlausibility(believes));
                    if (survivalGoal.getPlausibilityLevel() > paramsBDI.getSurvivalThreshold()) {
                        survivalGoal.setViabilityValue(survivalGoal.evaluateViability(believes));
                        if (survivalGoal.getViabilityValue() > paramsBDI.getSurvivalThreshold()) {
                            survivalGoal.setContributionValue(survivalGoal.evaluateContribution(stateBDI));
                            if (!paramsBDI.getPyramidGoals().getSurvivalGoalsList().contains(survivalGoal)) {
                                paramsBDI.getPyramidGoals().getSurvivalGoalsList().add(survivalGoal);
                            }
                        }
                    }
                }
            }

            paramsBDI.setIntentionList(paramsBDI.getPyramidGoals().getCurrentIntentionGoalList());

            List<RationalRole> roleList = new ArrayList<>();
            for (GoalBDI goal : paramsBDI.getIntentionList()) {
                if (goal.evaluateMappingViability(paramsBDI, believes)) {
                    if (goal.predictResultUnlegality(stateBDI)) {
                        roleList.add(goal.getRole());
                    }
                }
            }
            if (!paramsBDI.getIntentionList().isEmpty()) {
                EventBESA eventBesa = new EventBESA(ChangeRationalRoleGuard.class.getName(), new RoleListData(roleList));
                AgHandlerBESA agHandlerBESA = agentBDI.getAdmLocal().getHandlerByAlias(agentBDI.getAlias());
                agHandlerBESA.sendEvent(eventBesa);
            }

        } catch (ExceptionBESA e) {
            ReportBESA.error(e.getMessage());
        }
    }
}
