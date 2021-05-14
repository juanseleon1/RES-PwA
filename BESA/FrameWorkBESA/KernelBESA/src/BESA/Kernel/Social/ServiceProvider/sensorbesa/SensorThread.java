/*
 * @(#)SensorThread.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.sensorbesa;

import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Social.SubscriptionBESA;
import BESA.Log.ReportBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
final public class SensorThread implements Runnable {

    /**
     *
     */
    private boolean alive = true;
    /**
     *
     */
    private SensorBESA sensor;

    /**
     * @description Agregar descripci�n del elemento
     *
     * @param sensor
     */
    public SensorThread(SensorBESA sensor) {
        this.sensor = sensor;
    }

    /**
     *
     */
    final public void run() {
        synchronized (this.sensor) {
            this.sensor.setup();
        }
        while (alive) {//Si esta activo el sensor
            //adquirir y procesar datos
            char[] header = null;
            char[] body = null;
            // leer datos de la fuente de datos


            int dataSize = 0;
            try {
                dataSize = this.sensor.getSource().read(header, body);
            } catch (ServiceProviderSensorExceptionBESA ex) {
                ex.printStackTrace();//TODO
            }


            // procesar informaci�n para modificar el estado del sensor
            this.sensor.processInfo(dataSize, header, body);
            // enviar mensajes a los agentes que hayan hecho una suscripcion y la regla se dispare
            for (int i = 0; i < sensor.getSubscriptionTable().size(); i++) {
                SubscriptionBESA subs = (SubscriptionBESA) sensor.getSubscriptionTable().get(i);
                DataBESA dataToSend = null;
                // verificar regla y generar datos que se deben enviar
                if (subs.getRule().verify(this.sensor.getState(), header, dataToSend)) {//@todo siempre el metodo verify debe hacer new DataBESA
                    EventBESA ev = new EventBESA(subs.getEvType(), dataToSend);
                    try {
                        //ojo toca arreglar esto en el sensor     subs.getAgLocalHandler().sendEvent(ev);
                    } catch (Exception e) {
                        try {
                            throw new ServiceProviderSensorExceptionBESA("Couldn't send the event.");
                        } catch (ServiceProviderSensorExceptionBESA ex) {
                            ReportBESA.error("Couldn't send the event: " + e.toString());
                            ex.printStackTrace();
                        }
                    }

                }
            }
        }
        synchronized (this.sensor) {
            this.sensor.setdown();
        }
    }

    /**
     * 
     * @return Si el sensor esta vivo
     */
    final public synchronized boolean isAlive() {
        return alive;
    }

    /**
     * sera necesario el comentario???
     */
    final public synchronized void resetAlive() {
        this.alive = false;
    }
}
