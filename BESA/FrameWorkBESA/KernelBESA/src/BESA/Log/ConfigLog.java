/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import BESA.Config.ConfigExceptionBESA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author User
 */
public class ConfigLog {
    //========================================================================//
    //                             DEFAULT VALUES                             //
    //========================================================================//

    /**
     * Configuration file.
     */
    private String CONFIG_FILE = "confbesa.xml";
    /**
     *
     */
    private boolean trace = false;
    /**
     *
     */
    private boolean debug = true;
    /**
     *
     */
    private boolean info = true;
    /**
     *
     */
    private boolean warn = true;
    /**
     *
     */
    private boolean error = true;
    /**
     *
     */
    private boolean fatal = true;
    /**
     *
     */
    private boolean logmanager = false;
    /**
     *
     */
    private String dateformat = "hh:mm:ss:SS";

    
    public ConfigLog() throws ConfigExceptionBESA {
        loadConfiFile();
    }
    
    public ConfigLog(String path) throws ConfigExceptionBESA {
        CONFIG_FILE = path;
        loadConfiFile();
    }
    
    /**
     *
     */
    public void loadConfiFile() throws ConfigExceptionBESA {
        XMLLogConfig xMLLogConfig = null;
        try {
            xMLLogConfig = getProperties();
        } catch (FileNotFoundException ex) {
            System.out.println("[WARN]: The \"confbesa.xml\" configuration file isn't into project root directory: " + ex.toString());
            return;
        } catch (JAXBException ex) {
            ReportBESA.error("Couldn't load the \"confbesa.xml\" configuration file: " + ex.toString());
            throw new ConfigExceptionBESA("Couldn't load the \"confbesa.xml\" configuration file: " + ex.toString());
        }
        //--------------------------------------------------------------------//
        // Gets the information of the container tag and checks if the data   //
        // are correct.                                                       //
        //--------------------------------------------------------------------//
        LogConfig xMLLog = xMLLogConfig.getLog();
        if (xMLLog != null) {
            Boolean aux = null;
            aux = xMLLog.getTrace();
            if(aux != null)
                trace = aux;
            aux = xMLLog.getDebug();
            if(aux != null)
                debug = aux;
            aux = xMLLog.getInfo();
            if(aux != null)
                info = aux;
            aux = xMLLog.getWarn();
            if(aux != null)
                warn = aux;
            aux = xMLLog.getError();
            if(aux != null)
                error = aux;
            aux = xMLLog.getFatal();
            if(aux != null)
                fatal = aux;
            String dateFAux = xMLLog.getDateformat();
            if(dateFAux != null)
                dateformat = dateFAux;
        }
    }

    /**
     * Loads the configuration parameters from the configbesa.xml file.
     *
     * @return XML config object.
     * @throws FileNotFoundException File not found exception.
     * @throws JAXBException JAXB Exception.
     */
    private XMLLogConfig getProperties() throws FileNotFoundException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XMLLogConfig.class);
        Unmarshaller u = jaxbContext.createUnmarshaller();
        return (XMLLogConfig) u.unmarshal(new FileInputStream(CONFIG_FILE));
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isFatal() {
        return fatal;
    }

    public void setFatal(boolean fatal) {
        this.fatal = fatal;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }

    public boolean isLogmanager() {
        return logmanager;
    }

    public void setLogmanager(boolean logmanager) {
        this.logmanager = logmanager;
    }

    public boolean isTrace() {
        return trace;
    }

    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    public boolean isWarn() {
        return warn;
    }

    public void setWarn(boolean warn) {
        this.warn = warn;
    }

    public String getDateFormat() {
        return dateformat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateformat = dateFormat;
    }
    
}
