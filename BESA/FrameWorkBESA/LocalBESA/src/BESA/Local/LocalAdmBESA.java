/*
 * @(#)LocalAdmBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Local;

import BESA.Config.ConfigBESA;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AGENTSTATE;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.BehaviorBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.Agent.KernelAgentExceptionBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Kernel.System.Directory.DirectoryServiceBESA;
import BESA.Kernel.System.SystemExceptionBESA;
import BESA.Local.Directory.AgLocalHandlerBESA;
import BESA.Local.Directory.LocalAdmHandlerBESA;
import BESA.Local.Directory.LocalDirectoryBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * TODO
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since   JDK1.4
 */
public class LocalAdmBESA extends AdmBESA {

    /**
     * 
     */
    protected LocalDirectoryBESA localDirectory;
    
    public LocalAdmBESA() {
        localDirectory = new LocalDirectoryBESA();
    }

    /**
     * Creates a unique instance for the local administrator in a JVM.
     *
     * @param admAlias Alias/name of the admnistrador.
     * @param passwd Security key, it is necessary to be able to create or
     * destroy the administrator.
     * @param ipRmiRegistry The IP address of the machine that contains the
     * administrator to be registered.
     * @param portRmiRegistry The port of the machine that contains the administrator
     * to be registered.
     * @param centralized Indicates if the container is going to be executed
     * in a single machine or if it is going to be used in a distributed way.
     * @return Reference to the unique/singleton instance of the local
     * administrator.
     * @throws ExceptionBESA If happens an error in the administrator
     * initialization.
     */
    public LocalAdmBESA(String admAlias, double passwd, String ipRmiRegistry, int portRmiRegistry, boolean centralized) throws ExceptionBESA {
        localDirectory = new LocalDirectoryBESA();
        ConfigBESA configBESA = new ConfigBESA();
        setConfig(configBESA);
        try {
            this.centralized = centralized;                                     //Adds the centralized value.
            if (this.centralized) {                                             //Cheks if is centralized.
                //------------------------------------------------------------//
                // Initializes the data tables.                               //
                //------------------------------------------------------------//
                this.passwd = passwd;                                           //Adds the password value.
                this.alive = true;                                              //Indicates that container is alive.
                this.admHandler = new LocalAdmHandlerBESA(admAlias); //Initializes the administrator handler.
            } else { //Distributed BESA has been invoked.
                ReportBESA.error("Can't initialize Distributed BESA because centralized BESA has been invoked");
                throw new SystemExceptionBESA("Couldn't initialize Distributed BESA because centralized BESA has been invoked");
            } //End else.
        } catch (Exception e) { //Try else.
            ReportBESA.error("Couldn't create a new instance of the local container administrator: " + e.toString());
            throw new SystemExceptionBESA("Couldn't create a new instance of the local container administrator: " + e.toString());
        } //Try catch.
    }

    /**
     * Creates a unique instance for the local administrator in a JVM.
     *
     * @param admAlias Alias/name of the admnistrador.
     * @param passwd Security key, it is necessary to be able to create or
     * destroy the administrator.
     * @param ipRmiRegistry The IP address of the machine that contains the
     * administrator to be registered.
     * @param portRmiRegistry The port of the machine that contains the administrator
     * to be registered.
     * @param centralized Indicates if the container is going to be executed
     * in a single machine or if it is going to be used in a distributed way.
     * @return Reference to the unique/singleton instance of the local
     * administrator.
     * @throws ExceptionBESA If happens an error in the administrator
     * initialization.
     */
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public LocalAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        localDirectory = new LocalDirectoryBESA();
        this.config = configBESA;
        try {
            this.centralized = true;                                            //Adds the centralized value.
            if (this.centralized) {                                             //Cheks if is centralized.
                //------------------------------------------------------------//
                // Initializes the data tables.                               //
                //------------------------------------------------------------//
                this.passwd = configBESA.getPasswordContainer();                //Adds the password value.
                this.alive = true;                                              //Indicates that container is alive.
                this.admHandler = new LocalAdmHandlerBESA(configBESA.getAliasContainer());//Initializes the administrator handler.
            } else { //Distributed BESA has been invoked.
                ReportBESA.error("Can't initialize Distributed BESA because centralized BESA has been invoked");
                throw new SystemExceptionBESA("Couldn't initialize Distributed BESA because centralized BESA has been invoked");
            } //End else.
        } catch (Exception e) { //Try else.
            ReportBESA.error("Couldn't create a new instance of the local container administrator: " + e.toString());
            throw new SystemExceptionBESA("Couldn't create a new instance of the local container administrator: " + e.toString());
        } //Try catch.
    }

    @Override
    public String getAliasByAid(String agId) {
        return localDirectory.getAliasByAid(agId);
    }

    @Override
    public synchronized String registerAgent(AgentBESA ag, String agId, String alias) {
        if(agId == null){
            agId = this.generateAgId(alias);                               //Generates the agent ID. 
        }        
        AgLocalHandlerBESA aidHandler = null;
        try {
            aidHandler = new AgLocalHandlerBESA(alias, agId, ag);      //Creates agent handler.
        } catch (ExceptionBESA ex) {
            ReportBESA.error(ex.toString());
        }
        registerAgent(agId, aidHandler, alias);
        return agId;
    }

    @Override
    public void registerAgent(String agId, AgHandlerBESA agh, String agAlias) {
        localDirectory.registerAgent(agId, agh, agAlias);
    }

    @Override
    public void unregisterAgent(String agId) throws SystemExceptionBESA {
        //--------------------------------------------------------------------//
        // Deletes the agent of the service table.                            //
        //--------------------------------------------------------------------//
        for (Enumeration enum1 = localDirectory.getServIds(); enum1.hasMoreElements();) {
            DirectoryServiceBESA serv = localDirectory.getService((String) enum1.nextElement());//servIdTable.get(enum1.nextElement());
            serv.removeAgent(agId);
        } //End for.        
        //--------------------------------------------------------------------//
        // Unbind the agent of the log tables.                                //
        //--------------------------------------------------------------------//
        localDirectory.unbindAgent(agId);

        //Deletes the agent ID of the agent ID table.
        counterAgent--;
    }

    /*public synchronized void unregisterAgent(String agId) throws SystemExceptionBESA {
    
    }*/
    /**
     * Method that destroys the container with the threads and data objects.
     *
     * @param containerPassword Container authentication key.
     */
    @Override
    public void kill(double containerPassword) throws ExceptionBESA {
        if (Math.abs(this.passwd - containerPassword) < 0.0001) {               //Checks if the password is correct.
            ReportBESA.trace("Password Correcto - Iniciando cierre de Threads");
            synchronized (this.isAlive()) {                                     //Synchronized access to the variable alive.
                //------------------------------------------------------------//
                // Kills to all agents that are into container.               //
                //------------------------------------------------------------//
                Enumeration<String> agents = localDirectory.getIDs();
                while (agents.hasMoreElements()) {
                    killAgent(agents.nextElement());
                }
                //------------------------------------------------------------//
                // kills this localy the container.                           //
                //------------------------------------------------------------//
                this.setAlive(false);
                INSTANCE = null;
                passwd = 0.0;
                config = null;
                ReportBESA.trace("Password Correcto - Cierre de Threads Concluido");
            }
        }
    }

    @Override
    public void killAgent(String agId, double agentPassword) throws ExceptionBESA {
        AgHandlerBESA agh = (AgHandlerBESA) this.getHandlerByAid(agId);                 //Gets the agent handler.
        if (agh != null && agh.getAg().verifyPasswd(agentPassword)) {        //Checks if the passwod is correct.
            //----------------------------------------------------------------//
            // Kills the local agent.                                         //
            //----------------------------------------------------------------//
            killLocalAgent(agh);
        }
    }

    /**
     * Method that kill the agent that was indicated. Is modified the agent
     * state and disable the chanel.
     *
     * @param agId Agent ID.
     * @param passwdAgent Agent password.
     */
    private void killAgent(String agId) throws ExceptionBESA {
        killLocalAgent((AgHandlerBESA) this.getHandlerByAid(agId));                      //Gets the agent handler.
    }

    /**
     * @TODO ¿Un contenedor puede eliminar un agente remoto?
     * @param agh
     */
    private void killLocalAgent(AgHandlerBESA agh) throws ExceptionBESA {
        agh.setState(AGENTSTATE.KILL);                                          //Sets states as kill.
        agh.getAg().waitChannel();                                              //Disable the chanel.
        //--------------------------------------------------------------------//
        // Kills the agent.                                                   //
        //--------------------------------------------------------------------//
        killSCAgent(agh.getAg());
        //--------------------------------------------------------------------//
        // Unbind the agent of alias Table and ID table.                      //
        //--------------------------------------------------------------------//
        this.unregisterAgent(agh.getAgId());                                    //Unbind the agent of alias Table and ID table.
    }

    /**
     * 
     * @param agent
     */
    private void killSCAgent(AgentBESA agent) throws KernelAgentExceptionBESA {
        synchronized (agent.isAlive()) {                                        //Synchronized access to the variable alive.
            if (agent.isAlive()) {                                              //Checks if is alive.
                ArrayList<BehaviorBESA> behaviors = agent.getBehaviors();
                agent.getChannel().initBehBarrier(behaviors.size());            //Synchronization boot - Wait full channel setup.
                //------------------------------------------------------------//
                // Kills behavioors.                                          //
                //------------------------------------------------------------//
                for (int index = 0; index < behaviors.size(); index++) {
                    behaviors.get(index).kill();
                }
                while (agent.getChannel().getbarrierCounter() > 0) {
                    agent.getChannel().signalBehBarrier();
                } //End while.
                agent.getChannel().waitBehBarrier();                            //Blocks the behavior while the barrier counter indicates it.
                for (int i = 0; i < behaviors.size(); i++) {                    //Removes Agent's Behaviors.
                    behaviors.remove(i);                                        //Removes behavior.
                } //End for.               
                //------------------------------------------------------------//
                // Forces the finalization thread channel and behaviors.      //
                //------------------------------------------------------------//
                agent.resetAlive();
                String evType = "KILL_BESA_AGENT";                              //Event type for unlock the chanel.
                try {                                                           //Starts try.
                    EventBESA ev = new EventBESA(evType, passwd);               //Creates the event to send.
                    agent.sendEvent(ev);                                        //Sends the event to unlock the chanel.
                } catch (Exception e) { //End try.
                    ReportBESA.error("No send" + e.toString());
                } //End catch.
            } //End if.
        } //End synchronized.
    }

    //@Override
    /*public AgHandlerBESA getHandlerByAlias(String agAlias) throws ExceptionBESA {
    AgHandlerBESA ah = (AgHandlerBESA) agAliasTable.get(agAlias);
    if (ah != null) {
    return ah;
    } else {
    throw new SystemExceptionBESA("Agent with alias: " + agAlias + ", not found.");
    }
    }*/
    @Override
    public AgHandlerBESA getHandlerByAid(String agId) throws ExceptionBESA {
        AgHandlerBESA ah = localDirectory.getAgHandlerBESAByID(agId);
        if (ah != null) {
            return ah;
        } else {
            throw new SystemExceptionBESA("Agent with ID: " + agId + ", not found.");
        }
    }

    @Override
    public AgHandlerBESA getHandlerByAlias(String agAlias) throws ExceptionBESA {
        AgHandlerBESA ah = localDirectory.getHandlerByAlias(agAlias);
        if (ah != null) {
            return ah;
        } else {
            throw new SystemExceptionBESA("Agent with alias: " + agAlias + ", not found.");
        }
    }

    @Override
    public void publicagent(String alias, String agId) throws SystemExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public synchronized String searchAidByAlias(String alias) throws SystemExceptionBESA {
        return localDirectory.searchAidByAlias(alias);
    }

    @Override
    public boolean doesAgentExist(String alias) {
        return localDirectory.thereAreAgent(alias);
    }

    /**
     *
     * @param agentId
     * @param directoryServiceName
     */
    @Override
    public void bindSPServiceInDirectory(String agentId, String directoryServiceName) {
        localDirectory.addService(directoryServiceName);
        localDirectory.bindService(agentId, directoryServiceName);
    }

    /**
     *
     * @param directoryServiceName
     * @return
     */
    @Override
    public String lookupSPServiceInDirectory(String directoryServiceName) {
        Iterator it = localDirectory.searchAidByService(directoryServiceName);
        if (it.hasNext()) {
            return (String) it.next();
        }
        return null;

    }

    /**
     *
     * @param aid
     * @return
     */
    @Override
    public String erase(AgHandlerBESA agH) {
        localDirectory.unbindAgent(agH);
        return agH.getAgId();
    }

    /**
     * addService crear un servicio en las paginas amarillas
     *
     * @param servId nombre/identificador unico del servicio
     */
    @Override
    public synchronized void addService(String servId) {
        localDirectory.addService(servId);
    }

    /**
     * addService crear un servicio en las paginas amarillas
     * @param servId nombre/identificador unico del servicio
     * @param descriptors vector con nombres alternativos/descriptivos del servicio
     */
    @Override
    public synchronized void addService(String servId, ArrayList<String> descriptors) {
        localDirectory.addService(servId, descriptors);
    }

    /**
     * removeService eliminar un servicio en las paginas amarillas
     * @param servId nombre/identificador unico del servicio
     **/
    @Override
    public void removeService(String servId) {
        localDirectory.removeService(servId);
    }

    /**
     * bindService asociar un agente a un servicio de las paginas amarillas
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    @Override
    public synchronized boolean bindService(String agId, String servId) {
        return localDirectory.bindService(agId, servId);
    }

    /**
     * unbindService eliminar asociacion de un agente a un servicio de las paginas amarillas
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    @Override
    public void unbindService(String agId, String servId) {
        localDirectory.unbindService(agId, servId);
    }

    /**     
     * searchAidByService buscar agentes que prestan un servicio - paginas amarillas
     * @param servId nombre/identificador del servicio que prestan el/los agentes buscados
     * @return un iterator con los ids de los agentes encontrados
     **/
    @Override
    public synchronized Iterator searchAidByService(String servId) {
        return localDirectory.searchAidByService(servId);
    }

    @Override
    public Enumeration getIdList() {
        return localDirectory.getIDs();
    }

    @Override
    public void moveAgent(String agId, String aliasDestinationAdmBESA, double passwdAgent) throws ExceptionBESA {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Enumeration<String> getAdmAliasList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }    
}
