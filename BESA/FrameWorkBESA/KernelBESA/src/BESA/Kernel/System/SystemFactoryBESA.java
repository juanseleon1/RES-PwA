/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Kernel.System;

import BESA.Config.ConfigBESA;
import java.lang.reflect.Constructor;

/**
 *
 * @author User
 */
class SystemFactoryBESA {

    public AdmBESA createLocalAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        return build("BESA.Local.LocalAdmBESA", configBESA);
    }

    public AdmBESA createRemoteAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        return build("BESA.Remote.RemoteAdmBESA", configBESA);
    }

    public AdmBESA createExternAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        return build("BESA.Extern.ExternAdmBESA", configBESA);
    }

    public AdmBESA createMobileAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        return build("BESA.Master.PostOfficeAdmBESA", configBESA);
    }
    
    public AdmBESA createCEAdmBESA(ConfigBESA configBESA) throws SystemExceptionBESA {
        return build("BESA.Mobile.CompactAdmBESA", configBESA);
    }

    protected AdmBESA build(String name, ConfigBESA configBESA) throws SystemExceptionBESA {
        try {
            Class cls = Class.forName(name);
            Class[] partypes = new Class[1];
            partypes[0] = configBESA.getClass();
            Constructor ct = null;
            try {
                ct = cls.getConstructor(partypes);
            } catch (NoSuchMethodException e) {
                partypes[0] = partypes[0].getSuperclass();
                e.printStackTrace();
                throw new SystemExceptionBESA(e.getMessage());
            }
            Object[] arglist = new Object[1];
            arglist[0] = configBESA;
            return (AdmBESA) ct.newInstance(arglist);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new SystemExceptionBESA(ex.getMessage());
        }
    }
}
