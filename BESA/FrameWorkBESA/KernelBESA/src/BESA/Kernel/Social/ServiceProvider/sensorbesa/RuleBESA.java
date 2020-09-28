/*
 * @(#)RuleBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.sensorbesa;

import BESA.Kernel.Agent.Event.DataBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class RuleBESA {

    /**
     *
     */
    public RuleBESA() {
    }

    /**
     * 
     * @param state
     * @param header
     * @param data
     * @return
     */
    public boolean verify(SensorState state, char[] header, DataBESA data) {
        return true;
    }
}
