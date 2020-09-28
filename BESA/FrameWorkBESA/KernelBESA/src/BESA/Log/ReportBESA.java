/*
 * @(#)ReportBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Log;

import BESA.Config.ConfigExceptionBESA;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents
 * Esta clase es el m�todo est�ndar para depurar aplicaciones BESA.
 * Con sus m�todos se pueden enviar mensajes que aparecen en la
 * consola de contenedor BESA, para hacer seguimiento o visualizacion
 * de datos.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class ReportBESA {

    /**
     *
     */
    protected static ReportBESA INSTANCE = null;
    /**
     *
     */
    protected ConfigLog configLog;

    /**
     *
     */
    protected ReportBESA() {
        try {
            configLog = new ConfigLog();
        } catch (ConfigExceptionBESA ex) {
            System.err.println(ex.toString());
        }
    }

    /**
     *
     */
    protected ReportBESA(String path) {
        try {
            configLog = new ConfigLog(path);
        } catch (ConfigExceptionBESA ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     *
     */
    private synchronized static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TerminalReportBESA();
        }
    }
    
    /**
     *
     */
    private synchronized static void createInstance(String path) {
        if (INSTANCE == null) {
            INSTANCE = new TerminalReportBESA(path);
        }
    }

    /**
     *
     * @return
     */
    protected static ReportBESA getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    public static void setLocationFile(String path) {
        if (INSTANCE == null) {
            createInstance(path);
        } else {
            System.err.println("[ERROR]: This method should be invoked before of any report.");
        }
    }
    
    /**
     *
     * @param message
     */
    public static void trace(Object message) {
        try {
            INSTANCE.traceImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().traceImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     * @param time
     */
    protected abstract void traceImp(Object message, long time);

    /**
     *
     * @param message
     */
    public static void debug(Object message) {
        try {
            INSTANCE.debugImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().debugImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     * @param time
     */
    protected abstract void debugImp(Object message, long time);

    /**
     *
     * @param message
     */
    public static void info(Object message) {
        try {
            INSTANCE.infoImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().infoImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     * @param time
     */
    protected abstract void infoImp(Object message, long time);

    /**
     *
     * @param message
     */
    public static void warn(Object message) {
        try {
            INSTANCE.warnImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().warnImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     * @param time
     */
    protected abstract void warnImp(Object message, long time);

    /**
     *
     * @param message
     */
    public static void error(Object message) {
        try {
            INSTANCE.errorImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().errorImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     */
    public static void error(Exception message) {
        try {
            INSTANCE.errorImp(message.toString(), System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().errorImp(message, System.currentTimeMillis());
        }
    }
    
    /**
     *
     * @param message
     * @param time
     */
    protected abstract void errorImp(Object message, long time);

    /**
     *
     * @param message
     */
    public static void fatal(Object message) {
        try {
            INSTANCE.fatalImp(message, System.currentTimeMillis());
        } catch (NullPointerException ex) {
            getInstance().fatalImp(message, System.currentTimeMillis());
        }
    }

    /**
     *
     * @param message
     * @param time
     */
    protected abstract void fatalImp(Object message, long time);    
}
