/*
 * @(#)ContributionComparator.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.AgentStructuralModel.Functions;

import BESA.BDI.AgentStructuralModel.GoalBDI;
import java.util.Comparator;

/**
 * <p>Comparator for the goals using the contribution value</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class ContributionComparator implements Comparator<GoalBDI> {

    /**
     * <p> 
     * method that doesnt allow repeatd values and compare using ths contribution value
     * </p> 
     * @param goal1
     * @param goal2
     * @return 
     */
    @Override
    public int compare(GoalBDI goal1, GoalBDI goal2) {
        if (goal1.getId() == goal2.getId()) {
            return 0;
        } else {
            return goal2.getContributionValue() < goal1.getContributionValue() ? -1 : 1;
        }
    }
}
