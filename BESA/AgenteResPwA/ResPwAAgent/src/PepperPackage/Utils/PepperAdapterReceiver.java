/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.Utils;

import Adapter.ResPwaAdapterReceiver;
import BESA.ExceptionBESA;
import SensorHandlerAgent.Guards.SensorData;
import SensorHandlerAgent.Guards.SensorDataType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class PepperAdapterReceiver extends ResPwaAdapterReceiver<String> implements Runnable {

    public static final int revPort = 7897;
    protected AtomicBoolean ready;
    protected ServerSocket ss;

    public PepperAdapterReceiver() throws IOException {
        ready = new AtomicBoolean(true);
        ss = new ServerSocket(revPort);
//        System.out.println("PepperAdptRecvReady");
    }

    @Override
    public void run() {

        while (ready.get()) {
            try {
                Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String json = in.readLine();
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            SensorData sd = toSensorData(json);
                            if (sd.getAck()!=-1){
                                System.out.println("Llego: " + json);
                        }
                            updateBlvs(sd);
                        } catch (ExceptionBESA ex) {
                            Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                };
                t1.start();
            } catch (IOException ex) {
                Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void close() {
        ready.set(false);
    }

    @Override
    protected synchronized SensorData toSensorData(String json) {

        SensorData resp = new SensorData();
        try {
//            System.out.println("toSensorData "+json);
            Map<String, Object> map = new ObjectMapper().readValue(json, new TypeReference<Map<String, Object>>() {
            });
//            System.out.println("-------To sensor data: "+map.toString()+ "-------------------");
            resp.setDataType(SensorDataType.getFromId((String) map.get("respType")));

//            System.out.println("MIRE:"+ (String)map.get("respType") + "\n"+SensorDataType.getFromId((String)map.get("respType")));
            resp.setAck((int) map.get("id"));
            resp.setDataP((Map<String, Object>) map.get("params"));
            wait(2000);
        } catch (JsonProcessingException | InterruptedException ex) {
            Logger.getLogger(PepperAdapterReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }
}
