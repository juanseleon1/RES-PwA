/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter.PepperAdapter;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import ServiceAgentResPwA.RobotSPAgent;
import ServiceAgentResPwA.ServiceDataRequest;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperAdapter extends AdapterBESA{
    private RobotSPAgent rpa;
    private final int robotPort=7896;
    private final String IP= "127.0.0.1"; 
    private PepperAdapterReceiver receiver;
    private Thread recvThread;
    private static int numPackage=0;
    public PepperAdapter() throws Exception {
        super(null,null);
        receiver=new PepperAdapterReceiver();
        recvThread= new Thread(receiver);
        recvThread.start();
        this.rpa=null;

    }

    public RobotSPAgent getRpa() {
        return rpa;
    }

    public void setRpa(RobotSPAgent rpa) {
        this.rpa = rpa;
    }
    
    
//AQUI VAN TODOS LOS SERVICIOS TANTO SYNC COMO ASYNC    

    public void ActivityServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoActividadAsync Iniciado");
        sendRequest(data);
    }

    public void AutonomyServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("setAutonomyAsync Iniciado");
        sendRequest(data);
    }

    public void EnergyServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoBatteryAsync Iniciado");
        sendRequest(data);
    }

    public void HumanServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoHumanAsync Iniciado");
        sendRequest(data);
    }

    public void LocationServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoLocationAsync Iniciado");
        sendRequest(data);

    }

    public void RobotStateServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoStateAsync Iniciado");
        sendRequest(data);

    }

    public void VoiceServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarVoiceAsync Iniciado");
        sendRequest(data);

    }

    public void MovementServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarMovementAsync Iniciado");
        sendRequest(data);

    }
    public void TabletServiceReqAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarTabletAsync Iniciado");
        sendRequest(data);

    }
   
   private void sendRequest(SPServiceDataRequest data)
   {
        try {
            Socket s = new Socket(IP, robotPort);
            ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
            System.out.println("Enviando solicitud al Robot");
            oos.writeObject((Integer)0);
        } catch (IOException ex) {
            Logger.getLogger(PepperAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }   
   }
}
