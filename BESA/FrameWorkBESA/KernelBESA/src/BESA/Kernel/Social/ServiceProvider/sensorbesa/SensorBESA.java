/*
 * @(#)SensorBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.sensorbesa;

import BESA.Kernel.Social.SubscriptionBESA;
import java.util.ArrayList;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class SensorBESA {

    /**
     *
     */
    public final byte SENSOR_TYPE_CONTINUOS = (byte) 0;
    /**
     *
     */
    private byte sensorType;
    /**
     *
     */
    public final byte SENSOR_TYPE_CONTROLLED = (byte) 1;
    /**
     *
     */
    private String alias;
    /**
     *
     */
    private SensorThread sensorThread;
    /**
     *
     */
    private SourceInfo source;
    /**
     *
     */
    private ArrayList<SubscriptionBESA> subscriptionTable;
    /**
     *
     */
    private SensorState state;

    /**
     *
     * @param alias
     * @param sensorType
     * @param source
     * @param state
     */
    public SensorBESA(String alias, byte sensorType, SourceInfo source, SensorState state) {
        this.alias = alias;
        this.sensorType = sensorType;
        this.source = source;
        this.state = state;
        subscriptionTable = new ArrayList<SubscriptionBESA>();
        if (this.sensorType == this.SENSOR_TYPE_CONTINUOS) {
            // Crear hilo y arrancarlo
            this.sensorThread = new SensorThread(this);
            Thread thread = new Thread(this.sensorThread);
            thread.start();
        }
    }

    /**
     * 
     * @param subs
     */
    final public synchronized void subscribe(SubscriptionBESA subs) {
        if (this.sensorType != this.SENSOR_TYPE_CONTINUOS) {
            if (this.getSubscriptionTable().isEmpty()) {
                // Crear hilo y arrancarlo
                this.sensorThread = new SensorThread(this);
                Thread thread = new Thread(this.sensorThread);
                thread.start();
            }
        }
        this.getSubscriptionTable().add(subs);
    }

    //despues cuadrar el snsorbesa y el subs
    /**
     *
     * @param agId
     */
    final public synchronized void unsubscribe(String agId) {
        /*		SubscriptionBESA subs;
        for(int i=0;i<this.subscriptionTable.size();i++){
        subs=(SubscriptionBESA)this.subscriptionTable.get(i);
        if (subs.getAgId().equals(agId)){
        this.getSubscriptionTable().remove(subs);
        i=this.subscriptionTable.size()+1;//break
        }
        }
        if (this.subscriptionTable.isEmpty()){
        this.sensorThread.resetAlive();
        }
         */
    }

    /**
     *
     * @return
     */
    final public SourceInfo getSource() {
        return source;
    }

    /**
     *     afecta el estado dependiendo de la informaci�n recibida de la fuente de datos
     */
    abstract public void processInfo(int bodySize, char[] header, char[] body);

    /**
     *
     */
    abstract public void setup();

    /**
     *
     */
    abstract public void setdown();

    /**
     *
     * @return
     */
    final public ArrayList<SubscriptionBESA> getSubscriptionTable() {
        return subscriptionTable;
    }

    /**
     *
     * @return
     */
    final public SensorState getState() {
        return this.state;
    }

    /**
     *
     * @return
     */
    final public String getAlias() {
        return alias;
    }
}
