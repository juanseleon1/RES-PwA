/*
 * @(#)ConfigBESA.java 3.0	11/09/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved. Takina
 * and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.Config;

import BESA.Log.ReportBESA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * This class represents the BESA container configuration.
 *
 * @author SIDRe - Pontificia Universidad Javeriana
 * @author Takina - Pontificia Universidad Javeriana
 * @version 3.0, 11/09/11
 * @since JDK1.4
 * @since JAXB2.0
 */
public class ConfigBESA {

    //========================================================================//
    //                             DEFAULT VALUES                             //
    //========================================================================//
    /**
     * Configuration file.
     */
    private String CONFIG_FILE = "confbesa.xml";
    /**
     * Default IP Adderss.
     */
    private final String DEFAULT_IP_ADDRESS = "127.0.0.1";
    /**
     * Default send event attemps.
     */
    private final int DEFAULT_SEND_EVENT_ATTEMPS = 10;
    /**
     * Default send event time-out.
     */
    private final long DEFAULT_SEND_EVENT_TIMEOUT = 1;
    /**
     * Default RMI time-out.
     */
    private final long DEFAULT_RMI_TIMEOUT = 1000;
    /**
     * The possible values set for the creation the BESA.
     */
    private EnvironmentCase environmentCase = EnvironmentCase.LOCAL;
    /**
     * Alias container.
     */
    private String aliasContainer = "MAS";
    /**
     * Password container.
     */
    private double passwordContainer = 0.91;
    /**
     * IP address.
     */
    private String ipaddress = "127.0.0.1";
    /**
     * RMI Port.
     */
    private int rmiPort = 1099;
    /**
     * MC Address.
     */
    private String mcaddress = "230.0.0.1";
    /**
     * MC Port.
     */
    private int mcport = 2222;
    /**
     * BAP Locator address.
     */
    private String baplocatoradd = "127.0.0.1";
    /**
     * BAP Port.
     */
    private int bapport = 7000;
    /**
     * Send event time-out.
     */
    private long sendEventTimeout = 1;
    /**
     * RMI Time-out.
     */
    private long rMITimeout = 1000;
    /**
     * Send event attemps.
     */
    private int sendEventAttemps = 10;
    /**
     * BPO port.
     */
    private int bloport = 8080;
    /**
     * BPO port.
     */
    private int bpoPort = 8000;

    /**
     * Creates a new instance of the configuration object through of default 
     * path configuration file.
     * 
     * @throws ConfigExceptionBESA ConfigBESAException Happens when: missings the property
     * \"alias\" into the X tag, missings the property \"password\"
     * into the X tag, missings the X tag, missings the property
     * \"rmiport\" into the X tag, Missings the property \"mcaddress\"
     * into the X tag, missings the property \"mcport\" into the X
     * tag, missings the property \"baplocatoradd\" into the X tag,
     * missings the property \"bapport\" into the X tag, the
     * \"confbesa.xml\" configuration file isn't into project root directory or
     * couldn't load the \"confbesa.xml\" configuration file.
     */
    public ConfigBESA() throws ConfigExceptionBESA {
        loadConfig();
    }

    /**
     * Creates a new instance of the configuration object through the path that 
     * was indicated.
     * 
     * @param configBESAPATH Configuration file path.
     * @throws ConfigExceptionBESA ConfigBESAException Happens when: missings the property
     * \"alias\" into the X tag, missings the property \"password\"
     * into the X tag, missings the X tag, missings the property
     * \"rmiport\" into the X tag, Missings the property \"mcaddress\"
     * into the X tag, missings the property \"mcport\" into the X
     * tag, missings the property \"baplocatoradd\" into the X tag,
     * missings the property \"bapport\" into the X tag, the
     * \"confbesa.xml\" configuration file isn't into project root directory or
     * couldn't load the \"confbesa.xml\" configuration file.
     */
    public ConfigBESA(String configBESAPATH) throws ConfigExceptionBESA {
        CONFIG_FILE = configBESAPATH;                                           //Sets a new path.
        loadConfig();
    }

    /**
     * Loads the configuration file.
     * 
     * @return true if was loaded or false in other case;
     * @throws ConfigExceptionBESA ConfigBESAException Happens when: missings the property
     * \"alias\" into the X tag, missings the property \"password\"
     * into the X tag, missings the X tag, missings the property
     * \"rmiport\" into the X tag, Missings the property \"mcaddress\"
     * into the X tag, missings the property \"mcport\" into the X
     * tag, missings the property \"baplocatoradd\" into the X tag,
     * missings the property \"bapport\" into the X tag, the
     * \"confbesa.xml\" configuration file isn't into project root directory or
     * couldn't load the \"confbesa.xml\" configuration file.
     */
    private boolean loadConfig() throws ConfigExceptionBESA {
        XMLConfig xMLConfig = null;
        try {
            xMLConfig = getProperties();                                        //Loads the configuration parameters from the configbesa.xml file.
        } catch (FileNotFoundException ex) {
            ReportBESA.warn(" The \"confbesa.xml\" configuration file isn't into project root directory: " + ex.toString());
            return false;
        } catch (JAXBException ex) {
            ReportBESA.error("Couldn't load the \"confbesa.xml\" configuration file: " + ex.toString());
            throw new ConfigExceptionBESA("Couldn't load the \"confbesa.xml\" configuration file: " + ex.toString());
        }
        //--------------------------------------------------------------------//
        // Gets the information of the container tag and checks if the data   //
        // are correct.                                                       //
        //--------------------------------------------------------------------//
        Container xMLcontainer = xMLConfig.getContainer();
        if (xMLcontainer != null) {
            aliasContainer = xMLcontainer.getAlias();
            passwordContainer = xMLcontainer.getPassword();
            ipaddress = xMLcontainer.getIpaddress();
            if (aliasContainer == null || aliasContainer.isEmpty()) {
                ReportBESA.error("Missings the property \"alias\" into the Container tag.");
                throw new ConfigExceptionBESA("Missings the property \"alias\" into the Container tag.");
            }
            if (passwordContainer == 0.0) {
                ReportBESA.error("Missings the property \"password\" into the Container tag.");
                throw new ConfigExceptionBESA("Missings the property \"password\" into the Container tag.");
            }
            //----------------------------------------------------------------//
            // If isn't the IP address specificated then set the default      //
            // value.                                                         //
            //----------------------------------------------------------------//
            if (ipaddress == null || ipaddress.isEmpty()) {
                try {
                    ipaddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException ex) {
                    ipaddress = DEFAULT_IP_ADDRESS;
                }
            }
            //----------------------------------------------------------------//
            // Gets the information of the environment tag and checks if the  //
            // data are correct. If the environment tag that isn't, then does //
            // create the local container.                                    //
            //----------------------------------------------------------------//
            Environment xMLEnvironment = xMLcontainer.getEnvironment();
            if (xMLEnvironment != null) {
                //------------------------------------------------------------//
                // Checks if the values of the environment tag are correct;   //
                // if the values are not, then takes the default values.      //
                //------------------------------------------------------------//
                rMITimeout = xMLEnvironment.getRmitimeout();
                if (rMITimeout == 0) {
                    rMITimeout = DEFAULT_RMI_TIMEOUT;
                }
                sendEventAttemps = xMLEnvironment.getSeneventattemps();
                if (sendEventAttemps == 0) {
                    sendEventAttemps = DEFAULT_SEND_EVENT_ATTEMPS;
                }
                sendEventTimeout = xMLEnvironment.getSendeventtimeout();
                if (sendEventTimeout == 0) {
                    sendEventTimeout = DEFAULT_SEND_EVENT_TIMEOUT;
                }
                //------------------------------------------------------------//
                // Gets the information of the mobile tag and checks if       //
                // the data are correct.                                      //
                //------------------------------------------------------------//
                Mobile xMLMobile = xMLEnvironment.getMobile();
                if (xMLMobile != null) {
                    rmiPort = xMLMobile.getRmiport();
                    if (rmiPort == 0) {
                        ReportBESA.error("Missings the property \"rmiport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"rmiport\" into the mobile tag.");
                    }
                    mcaddress = xMLMobile.getMcaddress();
                    if (mcaddress == null || mcaddress.isEmpty()) {
                        ReportBESA.error("Missings the property \"mcaddress\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcaddress\" into the mobile tag.");
                    }
                    mcport = xMLMobile.getMcport();
                    if (mcport == 0) {
                        ReportBESA.error("Missings the property \"mcport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcport\" into the mobile tag.");
                    }
                    baplocatoradd = xMLMobile.getBaplocatoradd();
                    if (baplocatoradd == null || baplocatoradd.isEmpty()) {
                        ReportBESA.error("Missings the property \"baplocatoradd\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"baplocatoradd\" into the mobile tag.");
                    }
                    bapport = xMLMobile.getBapport();
                    if (bapport == 0) {
                        ReportBESA.error("Missings the property \"bapport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bapport\" into the mobile tag.");
                    }
                    bpoPort = xMLMobile.bpoport;
                    if (bpoPort == 0) {
                        ReportBESA.error("Missings the property \"mobport\" into the Mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mobport\" into the Mobile tag.");
                    }
                    bloport = xMLMobile.bloport;
                    if (bloport == 0) {
                        ReportBESA.error("Missings the property \"bloport\" into the Mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bloport\" into the Mobile tag.");
                    }
                    //--------------------------------------------------------//
                    // Indicates the creation of the mobile container.        //
                    //--------------------------------------------------------//
                    environmentCase = EnvironmentCase.MOBILE;
                    return true;
                }

                //------------------------------------------------------------//
                // Gets the information of the mobile tag and checks if       //
                // the data are correct.                                      //
                //------------------------------------------------------------//
                CE xMLCE = xMLEnvironment.getCE();
                if (xMLCE != null) {
                    rmiPort = xMLCE.getRmiport();
                    if (rmiPort == 0) {
                        ReportBESA.error("Missings the property \"rmiport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"rmiport\" into the mobile tag.");
                    }
                    mcaddress = xMLCE.getMcaddress();
                    if (mcaddress == null || mcaddress.isEmpty()) {
                        ReportBESA.error("Missings the property \"mcaddress\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcaddress\" into the mobile tag.");
                    }
                    mcport = xMLCE.getMcport();
                    if (mcport == 0) {
                        ReportBESA.error("Missings the property \"mcport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcport\" into the mobile tag.");
                    }
                    baplocatoradd = xMLCE.getBaplocatoradd();
                    if (baplocatoradd == null || baplocatoradd.isEmpty()) {
                        ReportBESA.error("Missings the property \"baplocatoradd\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"baplocatoradd\" into the mobile tag.");
                    }
                    bapport = xMLCE.getBapport();
                    if (bapport == 0) {
                        ReportBESA.error("Missings the property \"bapport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bapport\" into the mobile tag.");
                    }
                    bpoPort = xMLCE.bpoport;
                    if (bpoPort == 0) {
                        ReportBESA.error("Missings the property \"mobport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mobport\" into the Mobile tag.");
                    }
                    bloport = xMLCE.bloport;
                    if (bloport == 0) {
                        ReportBESA.error("Missings the property \"bloport\" into the mobile tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bloport\" into the mobile tag.");
                    }
                    //--------------------------------------------------------//
                    // Indicates the creation of the mobile container.        //
                    //--------------------------------------------------------//
                    environmentCase = EnvironmentCase.CE;
                    return true;
                }


                //------------------------------------------------------------//
                // Gets the information of the interop tag and checks if      //
                // the data are correct.                                      //
                //------------------------------------------------------------//
                Interop xMLInterop = xMLEnvironment.getInterop();
                if (xMLInterop != null) {
                    rmiPort = xMLInterop.getRmiport();
                    if (rmiPort == 0) {
                        ReportBESA.error("Missings the property \"rmiport\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"rmiport\" into the Interop tag.");
                    }
                    mcaddress = xMLInterop.getMcaddress();
                    if (mcaddress == null || mcaddress.isEmpty()) {
                        ReportBESA.error("Missings the property \"mcaddress\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcaddress\" into the Interop tag.");
                    }
                    mcport = xMLInterop.getMcport();
                    if (mcport == 0) {
                        ReportBESA.error("Missings the property \"mcport\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcport\" into the Interop tag.");
                    }
                    baplocatoradd = xMLInterop.getBaplocatoradd();
                    if (baplocatoradd == null || baplocatoradd.isEmpty()) {
                        ReportBESA.error("Missings the property \"baplocatoradd\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"baplocatoradd\" into the Interop tag.");
                    }
                    bapport = xMLInterop.getBapport();
                    if (bapport == 0) {
                        ReportBESA.error("Missings the property \"bapport\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bapport\" into the Interop tag.");
                    }
                    bpoPort = xMLInterop.getBpoport();
                    if (bpoPort == 0) {
                        ReportBESA.error("Missings the property \"bpoport\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bpoport\" into the Interop tag.");
                    }
                    bloport = xMLInterop.bloport;
                    if (bloport == 0) {
                        ReportBESA.error("Missings the property \"bloport\" into the Interop tag.");
                        throw new ConfigExceptionBESA("Missings the property \"bloport\" into the Interop tag.");
                    }
                    //--------------------------------------------------------//
                    // Indicates the creation of the interop container.       //
                    //--------------------------------------------------------//
                    environmentCase = EnvironmentCase.INTEROP;
                    return true;
                }
                //------------------------------------------------------------//
                // Gets the information of the remote tag and checks if      //
                // the data are correct.                                      //
                //------------------------------------------------------------//
                Remote xMLRemote = xMLEnvironment.getRemote();
                if (xMLRemote != null) {
                    rmiPort = xMLRemote.getRmiport();
                    if (rmiPort == 0) {
                        ReportBESA.error("Missings the property \"rmiport\" into the remote tag.");
                        throw new ConfigExceptionBESA("Missings the property \"rmiport\" into the remote tag.");
                    }
                    mcaddress = xMLRemote.getMcaddress();
                    if (mcaddress == null || mcaddress.isEmpty()) {
                        ReportBESA.error("Missings the property \"mcaddress\" into the remote tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcaddress\" into the remote tag.");
                    }
                    mcport = xMLRemote.getMcport();
                    if (mcport == 0) {
                        ReportBESA.error("Missings the property \"mcport\" into the remote tag.");
                        throw new ConfigExceptionBESA("Missings the property \"mcport\" into the remote tag.");
                    }
                    //--------------------------------------------------------//
                    // Indicates the creation of the remote container.        //
                    //--------------------------------------------------------//
                    environmentCase = EnvironmentCase.REMOTE;
                    return true;
                }
                //------------------------------------------------------------//
                // Indicates the creation of the local container with default //
                // values.                                                    //
                //------------------------------------------------------------//
                environmentCase = EnvironmentCase.LOCAL;
            } else {                                                            //Creates the local container.
                //------------------------------------------------------------//
                // Indicates the creation of the local container with default //
                // values.                                                    //
                //------------------------------------------------------------//
                sendEventAttemps = DEFAULT_SEND_EVENT_ATTEMPS;
                sendEventTimeout = DEFAULT_SEND_EVENT_TIMEOUT;
                environmentCase = EnvironmentCase.LOCAL;
                return true;
            }
        } else {
            ReportBESA.error("Missings the Container tag.");
            throw new ConfigExceptionBESA("Missings the Container tag.");
        }
        return false;
    }

    /**
     * Loads the configuration parameters from the configbesa.xml file.
     *
     * @return XML config object.
     * @throws FileNotFoundException File not found exception.
     * @throws JAXBException JAXB Exception.
     */
    private XMLConfig getProperties() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLConfig.class);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        return (XMLConfig) u.unmarshal(new FileInputStream(CONFIG_FILE));
    }

    /**
     * Sets the send event time-out.
     *
     * @param t Send event time-out.
     */
    public void setSendEventTimeout(long t) {
        this.sendEventTimeout = t;
    }

    /**
     * Sets the RMI time-out.
     * 
     * @param t RMI time-out.
     */
    public void setRMITimeout(long t) {
        this.rMITimeout = t;
    }

    /**
     * Sets the send event attemps.
     *
     * @param t Send event attemps.
     */
    public void setSendEventAttemps(int t) {
        this.sendEventAttemps = t;
    }

    /**
     * Gets send event time-out.
     *
     * @return Send event time-out.
     */
    public long getSendEventTimeout() {
        return this.sendEventTimeout;
    }

    /**
     * Gets the RMI time out.
     * 
     * @return RMI time out.
     */
    public long getRMITimeout() {
        return this.rMITimeout;
    }

    /**
     * Gets the send event attemps.
     *
     * @return Send event attemps.
     */
    public int getSendEventAttemps() {
        return this.sendEventAttemps;
    }

    /**
     * Gets the alias container.
     *
     * @return Alias container.
     */
    public String getAliasContainer() {
        return aliasContainer;
    }

    /**
     * Sets alias container.
     *
     * @param aliasContainer Alias container.
     */
    public void setAliasContainer(String aliasContainer) {
        this.aliasContainer = aliasContainer;
    }

    /**
     * Gets the IP Address.
     *
     * @return IP Address.
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * Sets the IP Addess.
     *
     * @param ipaddress IP Address.
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * Gets the password container.
     *
     * @return Password container.
     */
    public double getPasswordContainer() {
        return passwordContainer;
    }

    /**
     * Sets the passsword container.
     *
     * @param passwordContainer
     */
    public void setPasswordContainer(double passwordContainer) {
        this.passwordContainer = passwordContainer;
    }

    /**
     * Gets the BAP locator address.
     *
     * @return BAP locator address.
     */
    public String getBaplocatoradd() {
        return baplocatoradd;
    }

    /**
     * Sets the BAP locator address.
     * 
     * @param baplocatoradd BAP locator address.
     */
    public void setBaplocatoradd(String baplocatoradd) {
        this.baplocatoradd = baplocatoradd;
    }

    /**
     * Gets the BAP port.
     *
     * @return BAP port.
     */
    public int getBapport() {
        return bapport;
    }

    /**
     * Sets the BAP port.
     *
     * @param bapport BAP port.
     */
    public void setBapport(int bapport) {
        this.bapport = bapport;
    }

    /**
     * Gets the MC Address.
     * 
     * @return MC Address.
     */
    public String getMcaddress() {
        return mcaddress;
    }

    /**
     * Sets the MC Address.
     *
     * @param mcaddress MC Address.
     */
    public void setMcaddress(String mcaddress) {
        this.mcaddress = mcaddress;
    }

    /**
     * Gets the MC port.
     *
     * @return MC port.
     */
    public int getMcport() {
        return mcport;
    }

    /**
     * Sets the MC port:
     *
     * @param mcport MC port.
     */
    public void setMcport(int mcport) {
        this.mcport = mcport;
    }

    /**
     * Gets the RMI port.
     *
     * @return RMI port.
     */
    public int getRmiPot() {
        return rmiPort;
    }

    /**
     * Sets the RMI port.
     * 
     * @param rmiPot RMI port.
     */
    public void setRmiPot(int rmiPot) {
        this.rmiPort = rmiPot;
    }

    /**
     * Gets the environment type.
     *
     * @return environment type.
     */
    public EnvironmentCase getEnvironmentCase() {
        return environmentCase;
    }

    /**
     * Gets the RMI time-out.
     *
     * @return RMI time-out.
     */
    public long getrMITimeout() {
        return rMITimeout;
    }

    /**
     * Gets RMI port.
     *
     * @return RMI port.
     */
    public int getRmiPort() {
        return rmiPort;
    }

    /**
     * Gets the BPO port.
     * 
     * @return BPO port.
     */
    public int getBpoPort() {
        return bpoPort;
    }

    /**
     * Sets the BPO Port.
     * 
     * @param bpoPort BPO Port.
     */
    public void setBpoPort(int bpoPort) {
        this.bpoPort = bpoPort;
    }

    /**
     *
     * @return
     */
    public int getBloport() {
        return bloport;
    }

    /**
     * 
     * @param bloport
     */
    public void setBloport(int bloport) {
        this.bloport = bloport;
    }
}
