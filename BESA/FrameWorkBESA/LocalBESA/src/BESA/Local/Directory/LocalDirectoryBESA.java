/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Local.Directory;

import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Kernel.System.Directory.DirectoryServiceBESA;
import BESA.Kernel.System.SystemExceptionBESA;
import BESA.Log.ReportBESA;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 *
 * @author fabianjose
 */
public class LocalDirectoryBESA {

    /**
     * Table of agents/aidHandlers accessible for agent id.
     */
    protected Hashtable<String, AgHandlerBESA> agIdTable;
    /**
     * Table of agents/aidHandlers accessible for agent alias.
     */
    protected Hashtable<String, AgHandlerBESA> agAliasTable;
    /**
     * Service table accessible by the service ID.
     */
    protected Hashtable<String, DirectoryServiceBESA> servIdTable;

    public LocalDirectoryBESA() {
        this.agIdTable = new Hashtable<String, AgHandlerBESA>();
        this.agAliasTable = new Hashtable<String, AgHandlerBESA>();
        this.servIdTable = new Hashtable<String, DirectoryServiceBESA>();
    }

    /**
     * searchAidByAlias buscar agentes que responden a un alias - paginas
     * blancas
     *
     * @return un iterator con los aids de los agentes encontrados OJO: por
     * ahora retorna solo el aid de un agente, falta hacer lo del iterator
     * @param alias nombre con el que se referencian el/los agentes buscados
     * @throws BESA.Exception.ExceptionBESA
     */
    public synchronized String searchAidByAlias(String alias) throws SystemExceptionBESA {
        // Buscar el aid del agente usando el alias
        String id = new String();
        int i;
        for (i = 0; i < 10; i++) { //10 sg. m�ximo de espera para el registro
            try {
                ReportBESA.trace("AdmBESA.searchAidByAlias()" + agAliasTable);
                AgHandlerBESA agh = (AgHandlerBESA) agAliasTable.get(alias);
                if (agh != null) {
                    id = agh.getAgId();
                    i = 11;
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(3000);
                } catch (Exception err) {
                    ReportBESA.error("Couldn't find the agent ID by the alias " + alias + ": " + err.toString());
                    throw new SystemExceptionBESA("Couldn't find the agent ID by the alias " + alias + ": " + err.toString());
                }
            }
        }
        if (i == 11) {
            ReportBESA.error("Couldn't find the agent ID by the alias " + alias + ".");
            throw new SystemExceptionBESA("Couldn't find the agent ID by the alias " + alias + ".");
        }
        return id;
        //return ((AgHandler)agAliasTable.get(alias)).getAgId();
    }

    /**
     * *
     * searchAidByService buscar agentes que prestan un servicio - paginas
     * amarillas
     *
     * @param servId nombre/identificador del servicio que prestan el/los
     * agentes buscados
     * @return un iterator con los ids de los agentes encontrados
     *
     */
    public synchronized Iterator searchAidByService(String servId) {
        // Tratar de obtener referencia al servicio a partir del nombre
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv == null) {
            // Retornar null para indicar que el servicio no existe
            return null;
        } else {
            // Sino, hacer copia del vector de agentes y retornar el iterator
            ArrayList servCopy = (ArrayList) serv.getAgIds().clone();
            return servCopy.iterator();
        }
    }

    /**
     * Obtiene el alias de un agente a partir del aid.
     *
     * @param agId id del agente
     * @return alias del agente
     *
     */
    public String getAliasByAid(String agId) {
        AgHandlerBESA ahbesa = agIdTable.get(agId);
        if (ahbesa != null) {
            return ahbesa.getAlias();
        } else {
            return null;
        }
    }

    public AgHandlerBESA getAgHandlerBESAByAlias(String agAlias) {
        return agAliasTable.get(agAlias);
    }

    public AgHandlerBESA getAgHandlerBESAByID(String agId) {
        return agIdTable.get(agId);
    }

    /**
     * addService crear un servicio en las paginas amarillas
     *
     * @param servId nombre/identificador unico del servicio
     */
    public synchronized void addService(String servId) {
        // Tratar de obtener referencia al servicio a partir del nombre
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv == null) {
            // Crear servicio y meterlo a la tabla
            serv = new DirectoryServiceBESA(servId);
            servIdTable.put(servId, serv);
        }
    }

    /**
     * addService crear un servicio en las paginas amarillas
     *
     * @param servId nombre/identificador unico del servicio
     * @param descriptors vector con nombres alternativos/descriptivos del
     * servicio
     */
    public synchronized void addService(String servId, ArrayList<String> descriptors) {
        // Tratar de obtener referencia al servicio a partir del nombre
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv == null) {
            // Crear servicio y meterlo a la tabla
            serv = new DirectoryServiceBESA(servId, descriptors);
            servIdTable.put(servId, serv);
        } else {
            // Sino, agregar solo los descriptores
            for (int i = 0; i < descriptors.size(); i++) {
                serv.addDescriptor((String) descriptors.get(i));
            }
        }
    }

    /**
     * removeService eliminar un servicio en las paginas amarillas
     *
     * @param servId nombre/identificador unico del servicio
     *
     */
    public void removeService(String servId) {
        // Tratar de obtener referencia al servicio a partir del id
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv != null) {
            // Retirar servicio de la tabla
            servIdTable.remove(servId);
        }
    }

    /**
     * bindService asociar un agente a un servicio de las paginas amarillas
     *
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    public synchronized boolean bindService(String agId, String servId) {
        // Tratar de obtener referencia al servicio a partir del nombre
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv == null) {
            // Retornar falso
            return false;
        } else {
            // Sino, incluir agente en la lista de agentes asociados al servicio
            serv.addAgent(agId);
            return true;
        }
    }

    /**
     * unbindService eliminar asociacion de un agente a un servicio de las
     * paginas amarillas
     *
     * @param agId id del agente
     * @param servId nombre/identificador unico del servicio
     * @return true si todo bien, false si el servicio no ha sido creado
     */
    public void unbindService(String agId, String servId) {
        // Tratar de obtener referencia al servicio a partir del nombre
        DirectoryServiceBESA serv = (DirectoryServiceBESA) servIdTable.get(servId);
        // Verificar si el servicio no ha sido creado
        if (serv != null) {
            // Eliminar agente en la lista de agentes asociados al servicio
            serv.removeAgent(agId);
        }
    }

    public void unbindAgent(AgHandlerBESA agH) {
        agAliasTable.remove(agH.getAlias());
        agIdTable.remove(agH.getAgId());
    }

    public void registerAgent(String agId, AgHandlerBESA agh, String agAlias) {
        this.agIdTable.put(agId, agh);                                          //Binds the agent ID with the handler.
        this.agAliasTable.put(agAlias, agh);                                    //Binds the agent alias with handler.
    }

    public Enumeration getIDs() {
        return this.agIdTable.keys();
    }

    public Enumeration getServIds() {
        return servIdTable.keys();
    }

    public void unbindAgent(String agId) {
        String alias = getAliasByAid(agId);
        if (alias != null) {
            agAliasTable.remove(alias);                               //Deletes the agent of the agent table.
        }
        agIdTable.remove(agId);
    }

    public DirectoryServiceBESA getService(String id) {
        return servIdTable.get(id);
    }

    public AgHandlerBESA getHandlerByAid(String agId) {
        return agIdTable.get(agId);
    }

    public AgHandlerBESA getHandlerByAlias(String alias) {
        return agAliasTable.get(alias);
    }

    public boolean thereAreAgent(String alias) {
        return (agAliasTable.get(alias) == null) ? false : true;

    }
}
