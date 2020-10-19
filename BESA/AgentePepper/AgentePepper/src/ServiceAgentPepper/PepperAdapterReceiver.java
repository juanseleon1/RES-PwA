/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.ExceptionBESA;
import BESA.Kernel.Agent.Event.EventBESA;
import BESA.Kernel.System.AdmBESA;
import BESA.Kernel.System.Directory.AgHandlerBESA;
import Init.RunAgentePepper;
import SensorHandlerAgent.GetInfoGuard;
import SensorHandlerAgent.SensorData;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperAdapterReceiver implements Runnable{

    private AtomicBoolean ready;
    private ServerSocket ss;
    public static final int revPort=7897 ;
    public PepperAdapterReceiver() throws IOException{
        ready= new AtomicBoolean(true);
        ss=new ServerSocket(revPort);
    }
    
    @Override
    public void run() {
        
        while(ready.get())
        {
            try {
                Socket s=ss.accept();
                ObjectInputStream  in = new ObjectInputStream(s.getInputStream());
                byte[] buf = null;
                in.readFully(buf);
                SensorData sd=byteToSensorData(buf);
                actualizarBelieves(sd);
            } catch (IOException | ExceptionBESA ex) {
                Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void close()
    {
        ready.set(false);
    }

    private SensorData byteToSensorData(byte[] buf) {
        return new SensorData("TEST");
    }

    private void actualizarBelieves(SensorData sd) throws ExceptionBESA {
        AgHandlerBESA agH = AdmBESA.getInstance().getHandlerByAlias(RunAgentePepper.aliasSHAAgent);
        EventBESA evSP = new EventBESA(GetInfoGuard.class.getName(),sd);
        agH.sendEvent(evSP);
    }
}
