/*
 * @(#)SPService.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.agent;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;

/**
 * This class represents
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class SPService {

    /**
     *
     */
    private String Name;

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }

    /**
     * @description	metodo encargado de ejecutar el servicio es mandatorio sobreescribirlo
     * @param adapter TODO
     * @param SPServiceDataMoveRobot data	los datos necesarios para ejecutar el servicio
     *
     * @return	SPServiceDataRequest	los datos posteriores a haber ejecutado el servicio
     */
    public abstract DataBESA executeService(SPServiceDataRequest data, AdapterBESA adapter);
}
