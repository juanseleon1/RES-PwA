/*
 * @(#)Container.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class keeps the general configuration information of BESA container.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since JDK1.0
 */
@XmlRootElement()
public class Container {

    /**
     * Alias container.
     */
    @XmlAttribute
    protected String alias;
    /**
     * Password container.
     */
    @XmlAttribute
    protected double password;
    /**
     * IP Address container.
     */
    @XmlAttribute
    protected String ipaddress;
    /**
     * Tag where will keep the environment information.
     */
    @XmlElement
    protected Environment environment;

    /**
     * Gets the alias container.
     *
     * @return Alias container.
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Gets the tag environment.
     *
     * @return Tag environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * Gets the IP address.
     * 
     * @return IP address.
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * Gets the password.
     * 
     * @return Password.
     */
    public double getPassword() {
        return password;
    }
}
