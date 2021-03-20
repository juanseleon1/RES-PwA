/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Local.Directory;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.AGENTSTATE;
import BESA.Kernel.Agent.AgentBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Config.ConfigBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import BESA.Kernel.System.Directory.SystemDirectoryExceptionBESA;
import BESA.Log.ReportBESA;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class AgLocalHandlerBESA extends AgHandlerBESA {

    /**
     *
     */
    private ConfigBESA config;

    /**
     * Creates a new instance local agent handler.
     *
     * @param alias Agent alias.
     * @param agId Agent ID.
     * @param ag Agent BESA.
     * @param location The location.
     */
    public AgLocalHandlerBESA(String alias, String agId, AgentBESA ag) throws ExceptionBESA {
        super(agId, alias, ag);
        this.config = AdmBESA.getInstance().getConfigBESA();
    }

    @Override
    public void sendEvent(EventBESA ev) throws ExceptionBESA {
        int i = 0;
        for (i = 0; i < this.config.getSendEventAttemps(); i++) {
            try {

                if (state == AGENTSTATE.KILL) {
                    ReportBESA.error("Couldn't send the event because the agent state is \"kill\"");
                    throw new SystemDirectoryExceptionBESA("Couldn't send the event because the agent state is \"kill\"");
                } else if (state == AGENTSTATE.MOVE) {
                    waitTransition();
                    AgHandlerBESA agh = AdmBESA.getInstance().getHandlerByAlias(alias);
                    agh.sendEvent(ev);
                }
                ReportBESA.trace("Llamo send event del local");
                this.ag.sendEvent(ev);
                i = 10;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    this.wait(this.config.getSendEventTimeout());
                } catch (Exception e1) {
                    ReportBESA.error("Happened an error on time wait of the event send: " + e1);
                    throw new SystemDirectoryExceptionBESA("Happened an error on time wait of the event send: " + e1);
                }
            }
            if (i == this.config.getSendEventAttemps() - 1) {
                ReportBESA.error("Couldn't send the event.");
                throw new SystemDirectoryExceptionBESA("Couldn't send the event.");
            }
        }
    }

    /**
     * 
     */
    public synchronized void waitTransition() {
        try {
            this.wait();
        } catch (InterruptedException ex) {
            ReportBESA.error("Happened an error on time wait of the event send: " + ex);
        }
    }
}
