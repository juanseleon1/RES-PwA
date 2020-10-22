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
    private Thread hiloReceiver;
    private static int numPackage=0;
    public PepperAdapter() throws Exception {
        super(null,null);
        receiver=new PepperAdapterReceiver();
        hiloReceiver= new Thread(receiver);
        hiloReceiver.start();
        this.rpa=null;

    }

    public RobotSPAgent getRpa() {
        return rpa;
    }

    public void setRpa(RobotSPAgent rpa) {
        this.rpa = rpa;
    }
    
    
//AQUI VAN TODOS LOS SERVICIOS TANTO SYNC COMO ASYNC    

    public void solicitarInfoActividadAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoActividadAsync Iniciado");
        enviarMensaje(data);
    }

    public void solicitarAutonomyAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("setAutonomyAsync Iniciado");
        enviarMensaje(data);
    }

    public void solicitarInfoBatteryAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoBatteryAsync Iniciado");
        enviarMensaje(data);
    }

    public void solicitarInfoHumanAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoHumanAsync Iniciado");
        enviarMensaje(data);
    }

    public void solicitarInfoLocationAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoLocationAsync Iniciado");
        enviarMensaje(data);

    }

    public void solicitarInfoStateAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarInfoStateAsync Iniciado");
        enviarMensaje(data);

    }

    public void solicitarVoiceAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarVoiceAsync Iniciado");
        enviarMensaje(data);

    }

    public void solicitarMovementAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarMovementAsync Iniciado");
        enviarMensaje(data);

    }
    public void solicitarTabletAsync(SPServiceDataRequest data) {
        data=(ServiceDataRequest)data;
        System.out.println("solicitarTabletAsync Iniciado");
        enviarMensaje(data);

    }
   
   private void enviarMensaje(SPServiceDataRequest data)
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
