/*
 * @(#)AdmBESA.java 3.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.System;

import BESA.Config.ConfigBESA;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.System.Directory.AdmHandlerBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.UUID;


/**
 * This class represents a BESA local administrator.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class AdmBESA {

    /**
     * A singleton instance.
     */
    protected static AdmBESA INSTANCE = null;
    /**
     * Host data structure in which local administrator launches.
     */
    protected AdmHandlerBESA admHandler;
    /**
     * Password that controls access to critical methods of administrator.
     */
    protected double passwd;
    /**
     * Activity Indicator administrator, affects the main loop of multicast
     * threads.
     */
    protected Boolean alive;
    /**
     * Flag to indicate whether a centralized or distributed.
     */
    protected boolean centralized;
    /**
     * System configuration class.
     */
    protected ConfigBESA config;
    /**
     * Counter that can generate unique numbers for the agents in each local
     * administrator.
     */
    protected int counterAgent = 0;
    
    /**
     * Creates a new instance.
     */
    protected AdmBESA() {
    }

    /**
     * Used to obtain a single instance of the container BESA.
     *
     * @return The reference to local container, or null if not initialized.
     * @throws ExceptionBESA BESA Exception.
     */
    public static AdmBESA getInstance() {
        if (INSTANCE == null) {
            try {
                createSingletonInstance();
            } catch (ExceptionBESA ex) {
                ReportBESA.error(ex);
            }
        }
        return INSTANCE;
    }

    /**
     * 
     * @param configBESAPATH
     * @return 
     */
    public static AdmBESA getInstance(String configBESAPATH) {
        if (INSTANCE == null) {
            try {
                ReportBESA.setLocationFile(configBESAPATH);
                createSingletonInstance(configBESAPATH);               
            } catch (ExceptionBESA ex) {
                ReportBESA.error(ex);
            }
        }
        return INSTANCE;
    }

    /**
     * Creates a new instance of the container administrator.
     *  
     * @throws ExceptionBESA BESA Exception.
     */
    private synchronized static void createSingletonInstance() throws ExceptionBESA {
        if (INSTANCE == null) {
            //----------------------------------------------------------------//
            // Creates the instance and loads the BESA container              //
            // configuration.                                                 //
            //----------------------------------------------------------------//
            ConfigBESA configBESA = new ConfigBESA();
            //----------------------------------------------------------------//
            // Gets the environment case from the configuration parameters    //
            // and builds the container administrator.                        //
            //----------------------------------------------------------------//
            environmentCase(configBESA);
        }
    }

    /**
     * Creates a new instance of the container administrator.
     *  
     * @throws ExceptionBESA BESA Exception.
     */
    private synchronized static void createSingletonInstance(String configBESAPATH) throws ExceptionBESA {
        if (INSTANCE == null) {
            //----------------------------------------------------------------//
            // Creates the instance and loads the BESA container              //
            // configuration.                                                 //
            //----------------------------------------------------------------//
            ConfigBESA configBESA = new ConfigBESA(configBESAPATH);
            //----------------------------------------------------------------//
            // Gets the environment case from the configuration parameters    //
            // and builds the container administrator.                        //
            //----------------------------------------------------------------//
            environmentCase(configBESA);
        }
    }

    /**
     * 
     * @param configBESA
     * @throws SystemExceptionBESA 
     */
    private static void environmentCase(ConfigBESA configBESA) throws SystemExceptionBESA {
        SystemFactoryBESA systemFactoryBESA = new SystemFactoryBESA();
        switch (configBESA.getEnvironmentCase()) {            
            case REMOTE:
                INSTANCE = systemFactoryBESA.createRemoteAdmBESA(configBESA);
                break;
            case INTEROP:
                INSTANCE = systemFactoryBESA.createExternAdmBESA(configBESA);
                break;
            case MOBILE:
                INSTANCE = systemFactoryBESA.createMobileAdmBESA(configBESA);
                break;
            case CE:
                INSTANCE = systemFactoryBESA.createCEAdmBESA(configBESA);
                break;    
            default:
                INSTANCE = systemFactoryBESA.createLocalAdmBESA(configBESA);
        }
    }

    /**
     * Override for keep the singleton instance.
     *
     * @return Clone.
     * @throws CloneNotSupportedException Clone not supportedException.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * Creates a unique instance for the local administrator in a JVM.
     *
     * @param admAlias Alias/name of the admnistrador.
     * @param passwd Security key, it is necessary to be able to create or
     * destroy the administrator.
     * @param ipAddress The IP address of the machine that contains the
     * administrator to be registered.
     * @param rmiPort The port of the machine that contains the administrator
     * to be registered.
     * @param multicastAddr Multicast address used by the administrators in the
     * same SMA.
     * @param multicastPort Multicast port used by the administrators in the
     * same SMA.
     * @param centralized Indicates if the container is going to be executed
     * in a single machine or if it is going to be used in a distributed way.
     * @return Reference to the unique/singleton instance of the local
     * administrator.
     * @throws java.lang.Exception If happens an error in the administrator
     * initialization.
     * @deprecated
     */
//***********************
    /*
    public static AdmBESA createInstance(String admAlias, double passwd, String ipAddress, int rmiPort, String multicastAddr, int multicastPort, boolean centralized) throws Exception {
        if (INSTANCE == null) {                                              //If there is no administrator.
            if (!centralized) {                                                 //If it is not centralized.
                INSTANCE = new RemoteAdmBESA(admAlias, passwd, ipAddress, rmiPort, multicastAddr, multicastPort, centralized); //Creates an instance for a distributed administrator.
            } else { //Is centralized.
                INSTANCE = new LocalAdmBESA(admAlias, passwd, ipAddress, rmiPort, centralized); //Creates an instance for a centralized administrator.
            }// End if.
        }// End if.
        return INSTANCE;
    }*/

    /**
     * Used to obtain a single instance of local container.
     *
     * @return The reference to local container, or null if not initialized.
     * @throws Exception
     * @deprecated
     */
    public static AdmBESA createInstance() throws Exception {
        return INSTANCE;
    }

    /**
     * This method returns the id of the container.
     *
     * @return Container ID.
     */
    public String getIdAdm() {
        return this.admHandler.getAdmId();
    }

    /**
     * Generates Agent ID.
     *
     * @param alias Agent alias.
     * @return Agent ID.
     */
    protected synchronized String generateAgId(String alias) {
        return UUID.randomUUID().toString();
        //return this.admHandler.getAdmId() + RemoteAdmHandlerBESA.ID_DELIMITER + Integer.toString(++counterAgent);// Format: aid -> 1.alias 2.ipAddress 3.rmiPort 4.counterLocal.
    }

    /**
     * Deletes of the regirters the agent ID for kill the agent.
     *
     * @param agId Agent ID.
     */
    protected abstract void unregisterAgent(String agId) throws SystemExceptionBESA;

    /**
     * searchAidByAlias buscar agentes que responden a un alias - paginas blancas
     *
     * @return un iterator con los aids de los agentes encontrados
     * OJO: por ahora retorna solo el aid de un agente, falta hacer lo del iterator
     * @param alias nombre con el que se referencian el/los agentes buscados
     * @throws BESA.Exception.ExceptionBESA
     */
    public abstract String searchAidByAlias(String alias) throws SystemExceptionBESA;

    /**     
     * searchAidByService buscar agentes que prestan un servicio - paginas amarillas
     * @param servId nombre/identificador del servicio que prestan el/los agentes buscados
     * @return un iterator con los ids de los agentes encontrados
     **/
    public abstract Iterator searchAidByService(String servId);

    /**
     * searchAliasByAid obtener el alias de un agente a partir del aid
     * @param agId id del agente
     * @return alias del agente
     **/
    public abstract String getAliasByAid(String agId);

    /**
     * 
     * @return 
     */
    public abstract Enumeration getIdList();

    /**
     *
     * getHandlerByAid obtener el aidHandler de un agente a partir del aid
     * @param agId id del agente
     * @return aidHandler del agente
     */
    public abstract AgHandlerBESA getHandlerByAid(String agId) throws ExceptionBESA;

    /**
     * 
     * @param agAlias
     * @return
     * @throws SystemExceptionBESA
     */
    public abstract AgHandlerBESA getHandlerByAlias(String agAlias) throws ExceptionBESA;

    /**
     * 
     * @param agAlias
     * @return
     * @throws ExceptionBESA 
     */
    public abstract Enumeration<String> getAdmAliasList();
    /**
     *
     * @param aid
     * @return
     */
    public abstract String erase(AgHandlerBESA agH);

    /**
     * addService crear un servicio en las paginas amarillas
     *
     * @param servId nombre/identificador unico del servicio
     */
    public abstract void addService(String servId);

    /**
     * addService crear un servicio en las paginas amarillas
     * @param servId nombre/identificador unico del servicio
     * @param descriptors vector con nombres alternativos/descriptivos del servicio
     */
    public abstract void addService(String servId, ArrayList<String> descriptors);

    /**
     * removeService eliminar un servicio en las paginas amarillas
     * @param servId nombre/identificador unico del servicio
     **/
    public abstract void removeService(String servId);

    /**
     * bindService asociar un agente a un servicio de las paginas amarillas
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    public abstract boolean bindService(String agId, String servId);

    /**
     * unbindService eliminar asociacion de un agente a un servicio de las paginas amarillas
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    public abstract void unbindService(String agId, String servId);

    /**
     * 
     * @param containerPassword
     * @throws ExceptionBESA 
     */
    public abstract void kill(double containerPassword) throws ExceptionBESA;

    /**
     * 
     * @param agId
     * @param agh
     * @param agAlias 
     */
    public abstract void registerAgent(String agId, AgHandlerBESA agh, String agAlias) throws ExceptionBESA;

    /**
     *
     * @return
     */
    public AdmHandlerBESA getAdmHandler() {
        return admHandler;
    }

    /**
     *
     * @param admHandler
     */
    public void setAdmHandler(AdmHandlerBESA admHandler) {
        this.admHandler = admHandler;
    }

    /**
     *
     * @return
     */
    final public synchronized Boolean isAlive() {
        return alive;
    }

    /**
     *
     * @param alive
     */
    protected synchronized void setAlive(Boolean alive) {
        this.alive = alive;
    }

    /**
     *
     * @return
     */
    public double getPasswd() {
        return this.passwd;
    }

    /**
     *
     * @param passwd
     * @return
     */
    public boolean verifyPasswd(double passwd) {
        if (Math.abs(this.passwd - passwd) < 0.0001) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodo que retorna el flag de centralizado o distribuido (19 de Enero de 2006)
     * @return
     */
    public boolean isCentralized() {
        return this.centralized;
    }

    /**
     * Metodo que retorna el configBESA (19 de Enero de 2006)
     * @return
     */
    public ConfigBESA getConfigBESA() {
        return this.config;
    }

    /**
     * Method that kill the agent that was indicated. Is modified the agent
     * state and disable the chanel.
     *
     * @param agId Agent ID.
     * @param agentPassword Agent password.
     */
    public abstract void killAgent(String agId, double agentPassword) throws ExceptionBESA;

    /**
     *
     * @param agentId
     * @param directoryServiceName
     */
    public abstract void bindSPServiceInDirectory(String agentId, String directoryServiceName);

    /**
     *
     * @param directoryServiceName
     * @return
     */
    public abstract String lookupSPServiceInDirectory(String directoryServiceName);

    /**
     * 
     * @param config 
     */
    public void setConfig(ConfigBESA config) {
        this.config = config;
    }

    /**
     * 
     * @param alias
     * @return 
     */
    public abstract boolean doesAgentExist(String alias);

    /**
     * 
     * @param alias
     * @param agId
     * @throws SystemExceptionBESA 
     */
    public abstract void publicagent(String alias, String agId) throws SystemExceptionBESA;

    /**
     * 
     * @param ag
     * @param id
     * @param alias
     * @return 
     */
    public abstract String registerAgent(AgentBESA ag, String id, String alias);
    
    /**
     * 
     * @param alias
     * @param aliasDestinationAdmBESA
     * @param passwdAgent
     * @throws ExceptionBESA 
     */
    public abstract void moveAgent(String alias, String aliasDestinationAdmBESA, double passwdAgent) throws ExceptionBESA; 
}
