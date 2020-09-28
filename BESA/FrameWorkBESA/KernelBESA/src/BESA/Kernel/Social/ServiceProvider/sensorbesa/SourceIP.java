/*
 * @(#)SourceIP.java 2.0	11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Kernel.Social.ServiceProvider.sensorbesa;

import BESA.Log.ReportBESA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class represents 
 * 
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public abstract class SourceIP extends SourceInfo {

    /**
     *
     */
    private BufferedReader input;
    /**
     *
     */
    private Socket socket;
    /**
     *
     */
    private int portIP;
    /**
     *
     */
    private String nameIP;

    /**
     * @description Agregar descripci�n del elemento
     *
     * @param alias
     * @param type
     * @param headerSize
     * @param portIP
     * @param nameIP
     */
    public SourceIP(String alias, int type, int headerSize, int portIP, String nameIP) throws ServiceProviderSensorExceptionBESA {
        super(alias, SourceInfo.TYPE_IP, headerSize);
        this.portIP = portIP;
        this.nameIP = nameIP;
        try {
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socket = new Socket(InetAddress.getByName(this.nameIP), this.portIP);
        } catch (Exception e) {
            ReportBESA.error("Couldn't start the socket on " + this.nameIP + ":" + this.portIP + ": " + e.toString());
            throw new ServiceProviderSensorExceptionBESA("Couldn't start the socket on " + this.nameIP + ":" + this.portIP + ": " + e.toString());
        }
    }

    /** OJO: para evitar la espera ocupada, este m�todo deber�a ser bloqueante,
     *  de forma tal que s�lo cuando ocurra un evento se desbloquee el read
     *  
     *  @return	retorna el n�mero de datos leidos del body
     *  
     **/
    public int read(char[] headerDataAcquired, char[] bodyDataAcquired) throws ServiceProviderSensorExceptionBESA {
        int dataSizeToAcquire = -1;
        try {
            char headerDataToAcquire[] = new char[this.getHeaderSize()];
            input.read(headerDataToAcquire, 0, this.getHeaderSize());  //AB-lee el header de los datos
            dataSizeToAcquire = this.getDataSizeToAcquire(headerDataToAcquire);
            if (dataSizeToAcquire != 0) {
                char bodyDataToAcquire[] = new char[dataSizeToAcquire];
                input.read(bodyDataToAcquire, 0, dataSizeToAcquire);
            }
        } catch (IOException e) {
            ReportBESA.error("Couldn't read data: " + e.toString());
            throw new ServiceProviderSensorExceptionBESA("Couldn't read data: " + e.toString());
        }
        return dataSizeToAcquire;
    }
}
