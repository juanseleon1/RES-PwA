/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Local.Directory;

import BESA.Kernel.System.Directory.AdmHandlerBESA;
import java.util.UUID;

/**
 *
 * @author fabianjose
 */
public class LocalAdmHandlerBESA extends AdmHandlerBESA {
    
    /**
     *
     * @param aliasAdm
     * @param ipRmiRegistry
     * @param portRmiRegistry
     */
    public LocalAdmHandlerBESA(String aliasAdm) {
        this.alias = aliasAdm;
        this.admId = UUID.randomUUID().toString();
    }
}
