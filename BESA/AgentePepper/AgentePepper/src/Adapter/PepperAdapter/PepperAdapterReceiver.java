/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter.PepperAdapter;

import Adapter.ResPwaAdapterReceiver;
import BESA.ExceptionBESA;
import SensorHandlerAgent.SensorData;
import java.io.IOException;
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
public class PepperAdapterReceiver extends ResPwaAdapterReceiver{

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
                updateBlvs(sd);
            } catch (IOException | ExceptionBESA ex) {
                Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void close()
    {
        ready.set(false);
    }

    @Override
    protected SensorData byteToSensorData(byte[] buf) {
        return new SensorData("TEST");
    }
}
