/*
 * @(#)ProtocolBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.protocol;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.StructBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
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
public class ProtocolBESA {

    /**
     *
     */
    private ArrayList<Class> stackStages;
    /**
     * 
     */
    private String lastStage;

    /**
     *
     */
    public ProtocolBESA(Class initProto) {
        this.stackStages = new ArrayList<Class>();
        this.stackStages.add(initProto);
        this.lastStage = initProto.getClass().getName();
    }

    /**
     * @return  Returns the stackStages.
     * @uml.property  name="stackStages"
     */
    public ArrayList<Class> getStackStages() {
        return stackStages;
    }

    /**
     * 
     * @param stage
     */
    public void addStage(Class stage) {
        /*Class[] partypes = new Class[1]; // 2
        partypes[0] = stage.getClass();
        Constructor ct = null;
        try {
        ct = stage.getConstructor(partypes);
        } catch (NoSuchMethodException e) {
        partypes[0] = partypes[0].getSuperclass();
        }*/
        //TODO: verificar si es instanciable...
        this.stackStages.add(stage);
        this.lastStage = stage.getName();
    }

    /**
     * @return  Returns the lastStage.
     * @uml.property  name="lastStage"
     */
    public String getLastStage() {
        return lastStage;
    }

    /**
     *  Sirve para agregar protocolos en tiempo real una vez se ha crado la estructura
     * @param proto El protocolo a se a�adido
     * @return valor que indica si fue o no, exitosa la operaci�n
     */
    public boolean addProtocol(StructBESA structAgent) {
        if (structAgent != null) {
            try {
                int stages = this.getStackStages().size();
                structAgent.bindGuard("ProtocolBehavior",this.getStackStages().get(0));
                for (int i = 1; i < stages; i++) {
                    structAgent.bindGuard("ProtocolBehavior",ProtocolStackBESA.class, this.getStackStages().get(i));
                }
                //structAgent.bindGuard( GuardBESA.class, ProtocolBehavior.class );
                return true;
            } catch (ExceptionBESA ex) {
                ReportBESA.error(ex);
                return false;
            }
        } else {
            return false;
        }
    }
}
