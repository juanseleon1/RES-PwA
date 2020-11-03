/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage;

import Adapter.ResPwaAdapterReceiver;
import BESA.ExceptionBESA;
import SensorHandlerAgent.SensorData;
import SensorHandlerAgent.SensorDataType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperAdapterReceiver extends ResPwaAdapterReceiver<String> implements Runnable{

    public static final int revPort=7897;
    protected AtomicBoolean ready;
    protected ServerSocket ss;
    public PepperAdapterReceiver() throws IOException{
        ready= new AtomicBoolean(true);
        ss=new ServerSocket(revPort);
        System.out.println("PepperAdptRecvReady");
    }
    
    @Override
    public void run() {
        
//        while(ready.get())
//        {
//            try {
                //Socket s=ss.accept();
                //DataInputStream  in = new DataInputStream(s.getInputStream());
                //String json = in.readUTF();
                String json="{\"params\": {\"bodyLanguageState\": { \"ease\": { \"confidence\": 0.0, \"level\": 0.0}},\"smile\": {\"confidence\": 0.0,\"value\": 0.0},\"expressions\": {\"joy\": {\"confidence\": 0.0, \"value\": 0.0}, \"sorrow\": {\"confidence\": 0.0, \"value\": 0.0},\"excitement\": {\"confidence\": 0.0,\"value\": 0.0}, \"calm\": {\"confidence\": 0.0, \"value\": 0.0}, \"anger\": {\"confidence\": 0.0, \"value\": 0.0}, \"surprise\": {\"confidence\": 0.0, \"value\": 0.0}, \"laughter\": {\"confidence\": 0.0, \"value\": 0.0}},\"valence\": {\"confidence\": 0.0, \"value\": 0.0}, \"attention\": {\"confidence\": 0.0, \"value\": 0.0}}, \"id\": -1,\"respType\": \"emo\"}";
             Thread t1= new Thread(){
                @Override
                public void run()
                {
                    try {
                        SensorData sd=toSensorData(json);
                        updateBlvs(sd);
                    } catch (ExceptionBESA ex) {
                        Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                };
                t1.start();
//            } catch (IOException ex) {
//                Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
    }
    
    public void close()
    {
        ready.set(false);
    }

    @Override
    protected synchronized SensorData toSensorData(String json) {
        
        SensorData resp= new SensorData();
        try {
//            System.out.println("toSensorData "+json);
            Map<String, Object> map= new ObjectMapper().readValue(json, new TypeReference<Map<String,Object>>(){});
            System.out.println("MAP: "+map.toString());
            resp.setDataType(SensorDataType.getFromId((String)map.get("respType")));
            resp.setAck((int) map.get("id"));
            resp.setDataP((Map<String, Object>) map.get("params"));
            wait(2000);
        } catch (JsonProcessingException | InterruptedException ex) {
            Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resp;
    }
}
