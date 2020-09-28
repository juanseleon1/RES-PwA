/*
 * @(#)StructBESA.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Agent;

import BESA.ExceptionBESA;
import BESA.Log.ReportBESA;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * This class is used to define the agent BESA internal structure.
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class StructBESA implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = "StructBESA".hashCode();
    /**
     *
     */
    Hashtable<String, Hashtable> behaviorsList;

    /**
     * Constructs a newly created StructBESA object to be used in the 
     * creation of an agent.
     */
    public StructBESA() {
        behaviorsList = new Hashtable<String, Hashtable>();
    }

    /**
     * Adds a new behavior to the structure from a string that represents
     * the class name.
     * 
     * @param nameBehavior The behavior's name to be added.
     */
    public void addBehavior(String nameBehavior) {
        Enumeration<String> keys = behaviorsList.keys();
        while (keys.hasMoreElements()) {
            if (keys.nextElement().equalsIgnoreCase(nameBehavior)) {
                return;
            }
        }
        Hashtable guardsList = new Hashtable();
        behaviorsList.put(nameBehavior, guardsList);
    }

    /**
     * Indicates that the guard must be including by the behavior.
     *
     * @param nameGuard The guard to add.
     *	@param nameBehavior The behavior that adds the guard.
     */
    public void bindGuard(Class nameGuard) {
        addBehavior("DefaultBehavior");
        Hashtable guardList = (Hashtable) behaviorsList.remove("DefaultBehavior");
        ArrayList ackList = null;
        ackList = (ArrayList) guardList.remove(nameGuard.getName());
        if (ackList == null) {
            ackList = new ArrayList();
        }
        ackList.add(nameGuard.getName());
        guardList.put(nameGuard.getName(), ackList);
        behaviorsList.put("DefaultBehavior", guardList);
    }

    /**
     * Indicates that the guard must be including by the behavior.
     * 
     * @param behaviorName
     * @param guardClass
     * @throws ExceptionBESA
     */
    public void bindGuard(String behaviorName, Class... guardClass) throws ExceptionBESA{
         for (Class guard : guardClass) {
             bindGuard(behaviorName, guard);
         }
     }

    /**
     * Indicates that the guard must be including by the behavior.
     *
     * @param guardClass The guard to add.
     *	@param nameBehavior The behavior that adds the guard.
     */
    public void bindGuard(String behaviorName, Class guardClass) throws ExceptionBESA {
        Hashtable guardList = (Hashtable) behaviorsList.remove(behaviorName);
        if(guardList == null) {
            throw new KernelAgentExceptionBESA("Behavior didn't find. Before, you should define the behavior.");
        }
        ArrayList ackList = null;
        ackList = (ArrayList) guardList.remove(guardClass.getName());
        if (ackList == null) {
            ackList = new ArrayList();
        }
        ackList.add(guardClass.getName());
        guardList.put(guardClass.getName(), ackList);
        behaviorsList.put(behaviorName, guardList);
    }

    /**
     * Indicates that the guard must be including by the behavior.
     * 
     * @param nameGuard The guard to add.
     * @param nameBehavior The behavior that adds the guard.
     * @param nameAck Ack associated that must be added.
     */
    public void bindGuard(String nameBehavior, Class nameGuard, Class nameAck) throws ExceptionBESA {
        Hashtable guardList = (Hashtable) behaviorsList.remove(nameBehavior);
        if(guardList == null) {
            throw new KernelAgentExceptionBESA("Behavior didn't find. Before, you should define the behavior.");
        }
        ArrayList ackList = null;
        ackList = (ArrayList) guardList.remove(nameGuard.getName());
        if (ackList == null) {
            ackList = new ArrayList();
        }
        ackList.add(nameAck.getName());
        guardList.put(nameGuard.getName(), ackList);
        behaviorsList.put(nameBehavior, guardList);
    }

    /**
     * Builds the agent structure. 
     * 
     * @param agent The associated agent to the structure to be built.
     * @throws Exception The class doesn't exists in this BESA container. 
     */
    protected void buildAgentStruct(AgentBESA agent) throws KernelAgentExceptionBESA {
        for (Enumeration behaviors = behaviorsList.keys(); behaviors.hasMoreElements();) {
            //Para cada comportamiento
            String behaviorName = (String) behaviors.nextElement();

            //Obtengo sus guardas
            Hashtable guardsList = (Hashtable) this.behaviorsList.get(behaviorName);

            //BehaviorBESA beh = (BehaviorBESA) this.buildAgentComponent(agent, behaviorName);
            BehaviorBESA beh = new BehaviorBESA(agent, behaviorName);


            //Miro si las guardas existen
            for (Enumeration guards = guardsList.keys(); guards.hasMoreElements();) {
                String guardName = (String) guards.nextElement();
                //Mira si en mi locaci�n puedo ubicar la clase
                try {
                    //Miro si puedo crear la guarda
                    Class c = Class.forName(guardName);
                    if (GuardBESA.class.isAssignableFrom(c)) {
                        if (MultiGuardBESA.class.isAssignableFrom(c)) {
                            //Es una multiguarda
                            //Primero creo la guarda que lo recibe
                            MultiGuardBESA mgbesa;
                            if (agent.getChannel().findPort(guardName) == null) {
                                //Crear guarda sino existe
                                mgbesa = (MultiGuardBESA) this.buildAgentComponent(agent, guardName);
                            } else {
                                mgbesa = (MultiGuardBESA) agent.getChannel().
                                        findPort(guardName).getGuard();
                            }
                            beh.registerGuard(guardName);

                            //Ahora creo las multiguardas y las asocio a la
                            //guarda
                            ArrayList guardsList1 = (ArrayList) guardsList.get(guardName);
                            for (Iterator i = guardsList1.iterator(); i.hasNext();) {
                                String ackName = (String) i.next();
                                this.buildAgentMultiGuard(mgbesa, ackName);
                            }
                        } else {
                            //Es una guarda normalita
                            ArrayList guardsList1 = (ArrayList) guardsList.get(guardName);
                            for (Iterator i = guardsList1.iterator(); i.hasNext();) {
                                String guardName1 = (String) i.next();
                                if (agent.getChannel().findPort(guardName1) == null) {
                                    //Crear guarda sino existe
                                    this.buildAgentComponent(agent, guardName1);
                                }
                                beh.registerGuard(guardName1);
                            }
                        }
                    }
                } catch (ClassNotFoundException e) {
                    ReportBESA.error(e.toString());
                }
            }
        }
    }

    /**
     * Allows the creation of components related to an agent.
     * 
     * @param agent The agent that contains the component.
     * @param name The agent component.
     * @throws Exception Doesn't exist a constructor method.
     * @return The agent component.
     */
    private Object buildAgentComponent(AgentBESA agent, String name) throws KernelAgentExceptionBESA {
        try {
            Class cls = Class.forName(name);
            //Class[] partypes = new Class[1]; //2
            //partypes[0] = agent.getClass();
            //String n = new String();
            //partypes[1] = n.getClass();
            Constructor ct = null;
            //while (partypes[0] != null) {
            try {
                ct = cls.getConstructor();
                //      break; //Termina el while
            } catch (NoSuchMethodException e) {
                //    partypes[0] = partypes[0].getSuperclass();
            }
            //}
            Object[] arglist = new Object[1]; //2
            arglist[0] = agent;
            //arglist[1] = name;
            //Object component = ct.newInstance(arglist);

            Object component = cls.newInstance();


            /*    super();                                                                //???
            this.agent = ag;                                                        //Set agent.
            this.evType = this.getClass().getName();                                //???
            if (ag.getState() != null) {                                            //Starts if.
            ag.getState().bindGuard(this);                                      //Does bind to the port of agent.
            }                                                                       //End if.
            this.port = ag.bindGuard(this);
             */

            GuardBESA guardBESA = ((GuardBESA) component);

            guardBESA.agent = agent;
            guardBESA.evType = name;

            if (guardBESA.agent.state != null) {                                            //Starts if.
                guardBESA.agent.state.bindGuard(guardBESA);                                      //Does bind to the port of agent.
            }
            guardBESA.port = agent.bindGuard(guardBESA);


            if (guardBESA instanceof TimeOutGuardBESA) {
                ((TimeOutGuardBESA) guardBESA).getAgent().getChannel().findPort(guardBESA).createTimeOutThread();
            }

            return component;
        } catch (Exception ex) {
            ReportBESA.error("Couldn't build agent component: " + ex.toString());
            throw new KernelAgentExceptionBESA("Couldn't build agent component: " + ex.toString());
        }
    }

    /**
     * Creates a group of multiguards that will be associated to a guard.
     * 
     * @param guarda The multiguard.
     * @param name The guard's name.
     * @throws Exception Doesn't exist a constructor method.
     * @return The multiguards associated. 
     */
    private Object buildAgentMultiGuard(MultiGuardBESA guarda, String name) throws KernelAgentExceptionBESA {
        try {
            Class cls = Class.forName(name);
            Class[] partypes = new Class[1]; //2
            partypes[0] = guarda.getClass();
            //String n = new String();
            //partypes[1] = n.getClass();

            Constructor ct = null;
            while (partypes[0] != null) {
                try {
                    ct = cls.getConstructor(partypes);
                    break;  //Termina el while
                } catch (NoSuchMethodException e) {
                    partypes[0] = partypes[0].getSuperclass();
                }
            }

            Object[] arglist = new Object[1]; //2
            arglist[0] = guarda;
            //arglist[1] = name;
            Object component = ct.newInstance(arglist);
            return component;
        } catch (Exception ex) {
            ReportBESA.error("Couldn't build agent multi-guardt: " + ex.toString());
            throw new KernelAgentExceptionBESA("Couldn't build agent multi-guard: " + ex.toString());
        }
    }
}
