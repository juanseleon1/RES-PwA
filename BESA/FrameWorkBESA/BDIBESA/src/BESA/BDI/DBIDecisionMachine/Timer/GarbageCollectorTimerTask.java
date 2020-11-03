/*
 * @(#)GarbageCollectorTimerTask.java  2.0 11/01/11
 *
 * Copyright 2011, Pontificia Universidad Javeriana, All rights reserved.
 * Takina and SIDRe PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package BESA.BDI.DBIDecisionMachine.Timer;

import BESA.BDI.DBIDecisionMachine.Data.DataGarbageCollection;
import BESA.BDI.DBIDecisionMachine.GarbageCollectionGuard;
import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import java.util.TimerTask;

/**
 * <p>Timer to execute the GarbageCollector</p>
 * @author  SIDRe - Pontificia Universidad Javeriana
 * @author  Takina  - Pontificia Universidad Javeriana
 * @version 2.0, 11/01/11
 * @since   JDK1.0
 */
public class GarbageCollectorTimerTask extends TimerTask {

    private String aliasAgent;
    private AdmBESA admLocal;

    public GarbageCollectorTimerTask(String aliasAgent, AdmBESA admLocal) {
        this.aliasAgent = aliasAgent;
        this.admLocal = admLocal;
    }

    @Override
    public void run() {
        DataBESA dataBESA = new DataGarbageCollection();
        EventBESA eventBesa = new EventBESA(GarbageCollectionGuard.class.getName(), dataBESA);
        try {
            AgHandlerBESA agHandlerBESA = admLocal.getHandlerByAlias(aliasAgent);
            agHandlerBESA.sendEvent(eventBesa);
        } catch (ExceptionBESA e) {
            ReportBESA.info(e.getMessage());
        }
    }
}
