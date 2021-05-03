/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement()
public class LogConfig {

    /**
     *
     */
    @XmlAttribute
    protected Boolean trace;
    /**
     *
     */
    @XmlAttribute
    protected Boolean debug;
    /**
     *
     */
    @XmlAttribute
    protected Boolean info;
    /**
     *
     */
    @XmlAttribute
    protected Boolean warn;
    /**
     *
     */
    @XmlAttribute
    protected Boolean error;
    /**
     *
     */
    @XmlAttribute
    protected Boolean fatal;
    /**
     *
     */
    @XmlAttribute
    protected Boolean logmanager;
    /**
     * 
     */
    @XmlAttribute
    protected String dateformat;

    public Boolean getDebug() {
        return debug;
    }

    public Boolean getError() {
        return error;
    }

    public Boolean getFatal() {
        return fatal;
    }

    public Boolean getInfo() {
        return info;
    }

    public Boolean getLogmanager() {
        return logmanager;
    }

    public Boolean getTrace() {
        return trace;
    }

    public Boolean getWarn() {
        return warn;
    }

    public String getDateformat() {
        return dateformat;
    }
}
