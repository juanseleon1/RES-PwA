/*
 * @(#)SourceInfo.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.sensorbesa;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class SourceInfo {

    /**
     *
     */
    private String aliasSourceInfo;
//    private int typeSourceInfo;
    /** El tama�o del header DEBE ser FIJO para cada tipo de fuente de datos
     */
    /**
     *
     */
    private int headerSize;
    /**
     *
     */
    static public int TYPE_IP = 0;

    /**
     *
     * @param alias
     * @param type
     * @param headerSize
     */
    public SourceInfo(String alias, int type, int headerSize) {
        this.aliasSourceInfo = alias;
//		this.typeSourceInfo=type;
        this.headerSize = headerSize;
    }

    /**
     *
     * @param header
     * @return
     */
    abstract public int getDataSizeToAcquire(char[] header);

    /**
     * OJO: para evitar la espera ocupada, este m�todo deber�a ser bloqueante,
     * de forma tal que s�lo cuando ocurra un evento se desbloquee el read
     */
    abstract public int read(char[] header, char[] body) throws ServiceProviderSensorExceptionBESA;

    /**
     *
     * @return
     */
    public String getAliasSourceInfo() {
        return aliasSourceInfo;
    }

    /*    private void setAliasSourceInfo(String aliasSourceInfo){ this.aliasSourceInfo = aliasSourceInfo; }

    public int getTypeSourceInfo(){
    return typeSourceInfo;
    }
     */
    /*    private void setTypeSourceInfo(int typeSourceInfo){
    this.typeSourceInfo= typeSourceInfo;
    }
     */
    /**
     *
     * @return
     */
    public int getHeaderSize() {
        return headerSize;
    }

    /*    private void setHeaderSize(int headerSize){
    this.headerSize= headerSize;
    }
     */
}
