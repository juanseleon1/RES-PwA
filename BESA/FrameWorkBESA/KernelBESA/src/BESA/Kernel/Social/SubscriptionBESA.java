/*
 * @(#)SubscriptionBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Kernel.Social.ServiceProvider.sensorbesa.RuleBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class SubscriptionBESA {

    /**
     * 
     */
    private AgHandlerBESA agh;
    /**
     *
     */
    private String evType;
    /**
     * 
     */
    private RuleBESA rule;

    /**
     *
     * @param aid
     * @param evType
     * @param filterRule
     * @throws Exception
     */
    public SubscriptionBESA(String aid, String evType, RuleBESA filterRule) throws Exception {
        this.evType = evType;
        rule = filterRule;
//        agh=(AgLocalHandler)AdmBESA.createInstance().getHandlerByAid(aid);
    }
    /*
    public SubscriptionBESA(String aid, String evType) throws Exception {
    this.evType=evType;
    rule=new RuleBESA();
    agh=(AgLocalHandler)AdmBESA.createInstance().getHandlerByAid(aid);
    }*/
    /*se quita
    public String getAgId(){
    return this.agh.getAgId();
    }*/

    /**
     *
     * @return
     */
    public AgHandlerBESA getAgLocalHandler() {
        return agh;
    }

    /**
     *
     * @return
     */
    public String getEvType() {
        return evType;
    }

    /**
     *
     * @return
     */
    public RuleBESA getRule() {
        return rule;
    }
}
