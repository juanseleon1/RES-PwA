/*
 * @(#)AdapterBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Adapter;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;

/**
 * This class represents a adapter for an BESA agent. The adapter allows to the 
 * agents interact wiht the work environment.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since   JDK1.4
 */
public class AdapterBESA {

    /**
     * The type of event to send.
     */
    private String eventToSendType;
    /**
     * Handler agent.
     */
    private AgHandlerBESA agh;

    /**
     * Method that creates the object.
     *
     * @param eventToSendType The type of event to send.
     * @param agh Handler agents.
     */
    public AdapterBESA(String eventToSendType, AgHandlerBESA agh) {
        this.eventToSendType = eventToSendType;
        this.agh = agh;
    }

    /**
     * Sends event with data.
     *
     * @param data Data to send.
     * @throws AdapterExceptionBESA Couldn't send the event.
     */
    public void sendEvent(DataBESA data) throws AdapterExceptionBESA {
        EventBESA eventToSend = new EventBESA(this.eventToSendType, data);
        try {
            this.agh.sendEvent(eventToSend);
        } catch (Exception e) {
            ReportBESA.error("Couldn't send the event: " + e.toString());
            throw new AdapterExceptionBESA("Couldn't send the event: " + e.toString());
        }
    }

    /**
     * Sends event.
     *
     * @throws AdapterExceptionBESA Couldn't send the event.
     */
    public void sendEvent() throws AdapterExceptionBESA {
        EventBESA eventToSend = new EventBESA(this.eventToSendType, null);
        try {
            this.agh.sendEvent(eventToSend);
        } catch (Exception e) {
            ReportBESA.error("Couldn't send the event: " + e.toString());
            throw new AdapterExceptionBESA("Couldn't send the event: " + e.toString());
        }
    }
}
