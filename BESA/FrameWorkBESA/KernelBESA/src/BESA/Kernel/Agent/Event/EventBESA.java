/*
 * @(#)EventBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent.Event;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.GuardBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Log.ReportBESA;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains the necessary information and methods for the treatment
 * of an event BESA.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class EventBESA extends ACLBESA implements Serializable {

    /**
     *
     */
    static final long serialVersionUID = "EventBESA".hashCode();
    /**
     * 
     */
    private String type;
    /**
     * 
     */
    private byte source;
    /**
     * 
     */
    private byte priority;
    /**
     * 
     */
    private String senderAgId;
    /**
     * 
     */
    private transient GuardBESA guard;
    /**
     * 
     */
    private int stamp;
    // Constantes para definir prioridad de un evento
    /**
     * 
     */
    public final static byte EV_PRIORITY_MAX = (byte) 127;
    /**
     * 
     */
    public final static byte EV_PRIORITY_NORMAL = (byte) 0;
    /**
     * 
     */
    public final static byte EV_SOURCE_BESA = (byte) -127;
    /**
     * 
     */
    public final static byte EV_PRIORITY_MIN = (byte) - 127;
    // Constantes para definir fuente que genera un evento
    /**
     * 
     */
    public final static byte EV_SOURCE_INTUSER = (byte) 64;
    /**
     * 
     */
    public final static byte EV_SOURCE_EXTUSER = (byte) 127;

    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type. 
     * @param data The event data.
     * @param priority A byre that represents the priority of the event.
     */
    public EventBESA(String evType, DataBESA data, byte priority) {
        super();
        this.stamp = EventStampBESA.nextStamp();
        this.source = EV_SOURCE_INTUSER;
        this.type = evType;
        setData(data);
        this.priority = priority;
        this.guard = null;
        this.senderAgId = null;
    }

    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type. 
     * @param data The event data.
     */
    public EventBESA(String evType) {
        super();
        this.stamp = EventStampBESA.nextStamp();
        this.source = EV_SOURCE_INTUSER;
        this.type = evType;
        setData(null);
        this.priority = EV_PRIORITY_NORMAL;
        this.guard = null;
        this.senderAgId = null;
    }
    
    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type. 
     * @param data The event data.
     */
    public EventBESA(String evType, DataBESA data) {
        super();
        this.stamp = EventStampBESA.nextStamp();
        this.source = EV_SOURCE_INTUSER;
        this.type = evType;
        setData(data);
        this.priority = EV_PRIORITY_NORMAL;
        this.guard = null;
        this.senderAgId = null;
    }

    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type.
     * @param passwd A password to be validated.
     * @throws Exception The password isn't valid.
     */
    public EventBESA(String evType, double passwd) throws ExceptionBESA {
        super();        
        //if (AdmBESA.getInstance().verifyPasswd(passwd)) {
            this.stamp = EventStampBESA.nextStamp();
            this.source = EV_SOURCE_BESA;
            this.type = evType;
            this.priority = EV_PRIORITY_MAX;
            this.guard = null;
            this.senderAgId = null;
        //} else {
          //  ReportBESA.error("Invalid Password.");
          //  throw new KernellAgentEventExceptionBESA("Invalid Password.");
        //}
    }

    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type.
     * @param senderAgId The identifier of the agent that sends the message.
     * @param data The event data.
     * @param priority A byre that represents the priority of the event. 
     */
    public EventBESA(String evType, String senderAgId, DataBESA data, byte priority) {
        super();
        this.stamp = EventStampBESA.nextStamp();
        this.source = EV_SOURCE_INTUSER;
        this.type = evType;
        setData(data);
        this.priority = priority;
        this.guard = null;
        this.senderAgId = senderAgId;
    }

    /**
     * Constructs a newly created EventBESA object.
     * 
     * @param evType A String that represents the event type.
     * @param senderAgId The identifier of the agent that sends the message.
     * @param data The event data.
     */
    public EventBESA(String evType, String senderAgId, DataBESA data) {
        super();
        this.stamp = EventStampBESA.nextStamp();
        this.source = EV_SOURCE_INTUSER;
        this.type = evType;
        setData(data);
        this.priority = EV_PRIORITY_NORMAL;
        this.guard = null;
        this.senderAgId = senderAgId;
    }

    /**
     * Obtains the type of event.
     * 
     * @return The event type. 
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the source of the event.
     * 
     * @return The event source.
     */
    public byte getSource() {
        return source;
    }

    /**
     * Sets the source of the event.
     * 
     * @param source The source to be stored. 
     */
    public void setSource(byte source) {
        this.source = source;
    }

    /**
     * Obtains the value of the priority field.
     * 
     * @return The event priority. 
     */
    public byte getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority field.
     * 
     * @param priority The priority to be assigned.
     */
    public void setPriority(byte priority) {
        this.priority = priority;
    }

    /**
     * The identifier of the sender agent.
     * 
     * @return A string that represents the identifier. 
     */
    public String getSenderAgId() {
        return senderAgId;
    }

    /**
     * Sets the identifier of the agent that sends the message.
     * 
     * @param senderAgId The sender agent identifier.
     */
    public void setSenderAgId(String senderAgId) {
        this.senderAgId = senderAgId;
    }

    /**
     * Returns the guard associated to the event.
     * 
     * @return The GuardBESA object. 
     */
    public GuardBESA getGuard() {
        return guard;
    }

    /**
     * Sets the guard associated to an event.
     * 
     * @param guard The guard to be associated.
     */
    public void setGuard(GuardBESA guard) {
        this.guard = guard;
    }

    /**
     * Obtains the stamp field value.
     * 
     * @return The stamp value. 
     */
    public int getStamp() {
        return stamp;
    }

    /**
     * Returns a state of the event, represented by the stamp field value.
     * 
     * @return A String with information of the EventBESA object. 
     */
    public String toString() {
        return Integer.toString(this.getStamp());
    }
}
