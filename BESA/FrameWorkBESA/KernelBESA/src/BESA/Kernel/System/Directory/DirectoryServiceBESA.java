/*
 * @(#)DirectoryService.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.System.Directory;

import java.util.ArrayList;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class DirectoryServiceBESA {

    /**
     *
     */
    private String servId;
    /**
     *
     */
    private ArrayList<String> agIds;
    /**
     *
     */
    private ArrayList<String> descriptors;

    /**
     *
     * @param servId
     */
    public DirectoryServiceBESA(String servId) {
        this.servId = servId;
        this.descriptors = new ArrayList<String>();
        agIds = new ArrayList<String>();
    }

    /**
     *
     * @param servId
     * @param descriptors
     */
    public DirectoryServiceBESA(String servId, ArrayList<String> descriptors) {
        this.servId = servId;
        this.descriptors = descriptors;
        agIds = new ArrayList<String>();
    }

    /**
     *
     * @param agId
     */
    public void addAgent(String agId) {
        // Agregar sin repetir
        if (!agIds.contains(agId)) {
            agIds.add(agId);
        }
    }

    /**
     *
     * @param agId
     */
    public void removeAgent(String agId) {
        // Eliminar agente de la tabla
        String tmp;
        for (int i = 0; i < agIds.size(); i++) {
            tmp = (String) agIds.get(i);
            if (tmp.equals(agId)) {
                agIds.remove(i);
            }
        }
    }

    /**
     *
     * @return
     */
    public String getServId() {
        return servId;
    }

    /**
     *
     * @return
     */
    public ArrayList getAgIds() {
        return agIds;
    }

    /**
     *
     * @param desc
     */
    public void addDescriptor(String desc) {
        // Agregar sin repetir
        if (!descriptors.contains(desc)) {
            descriptors.add(desc);
        }
    }

    /**
     *
     * @param desc
     * @return
     */
    public boolean searchDescriptor(String desc) {
        // Verificar si existe el descriptor
        return descriptors.contains(desc);
    }
}
