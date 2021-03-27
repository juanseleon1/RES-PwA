/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BESA.Log;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@XmlRootElement(name = "config")
public class XMLLogConfig {

    /**
     * Represents the log tag.
     */
    @XmlElement
    protected LogConfig log;

    /**
     * Gets the tag log.
     *
     * @return Tag log.
     */
    public LogConfig getLog() {
        return log;
    }
}
