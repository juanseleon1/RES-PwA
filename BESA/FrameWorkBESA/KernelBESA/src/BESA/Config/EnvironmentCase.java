/*
 * @(#)EnvironmentCase.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

/**
 * This class represents the possible values set for the creation the BESA
 * container.
 *
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public enum EnvironmentCase {

    /**
     * Indicates that should create a local container.
     */
    LOCAL,
    /**
     * Indicates that should create a remote container.
     */
    REMOTE,
    /**
     * Indicates that should create a interop container.
     * TODO Cambiar por extern.
     */
    INTEROP,
    /**
     * Indicates that should create a mobile container.
     */
    MOBILE,
    /**
     * 
     */
    CE
}
