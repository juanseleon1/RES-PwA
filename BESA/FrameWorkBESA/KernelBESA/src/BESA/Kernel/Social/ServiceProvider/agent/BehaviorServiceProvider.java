/*
 * TODO Borrar.
 * 
 * @(#)BehaviorServiceProvider.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.BehaviorBESA;
import BESA.Log.ReportBESA;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class BehaviorServiceProvider {//extends BehaviorBESA {

    /**
     * @param ag
     */
    public BehaviorServiceProvider(AgentBESA ag) {
        //super(ag);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     *
     * @see BESA.Agent.BehaviorBESA#setupBehavior()
     */
    //@Override
    protected void setupBehavior() {
        // Inicializaci�n del comportamiento
        // guardas y vars de estado propias del comportamiento
        // Registro de guardas asociadas al comportamiento
        // Se crean los links en ambas direcciones ag<->beh
        // OJO: no invocar estos metodos en el setup del agente
        // this.registerGuard(EventsMAD.SERVICE_REQUEST);
        // Init de componentes del estado que conciernen al comportamiento
    }

    /*
     * (non-Javadoc)
     *
     * @see BESA.Agent.BehaviorBESA#shutdownBehavior()
     */
    //@Override
    protected void shutdownBehavior() {
        // Rutina de cierre del coportamiento - cerrar y liberar recursos
        // Invocada autom�ticamente al salir de la bucla infinita

        // Cerrar registro del comportamiento con guardas
        // getAgent().unregisterGuard(this,EventsMAD.SERVICE_REQUEST);
        // guards.clear();

        // Cerrar y liberar recursos
        ReportBESA.trace("End Behavior Service Provider.");
    }
}
