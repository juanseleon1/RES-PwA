/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiceAgentPepper;

import BESA.Adapter.AdapterBESA;
import BESA.Kernel.Agent.Event.DataBESA;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import SensorHandlerAgent.SensorData;
import SensorHandlerAgent.SensorDataType;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperAdapter extends AdapterBESA{
    
    private RobotProviderAgent rpa;
    private int serverPort=7896;
    private String IP= "127.0.0.1"; 
    public PepperAdapter() {
        super(null,null);
        this.rpa=null;
    }

    public RobotProviderAgent getRpa() {
        return rpa;
    }

    public void setRpa(RobotProviderAgent rpa) {
        this.rpa = rpa;
    }
    
    
//AQUI VAN TODOS LOS SERVICIOS TANTO SYNC COMO ASYNC    

    public DataBESA solicitarInfoActividadAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoActividadAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.ACTIVIDAD);
        return sd;
    }

    public DataBESA setAutonomyAsync(SPServiceDataRequest data) {
        System.out.println("setAutonomyAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.AUTO);
        return sd;
    }

    public DataBESA solicitarInfoBatteryAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoBatteryAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.BATERIA);
        return sd;
    }

    public DataBESA solicitarInfoHumanAsync(SPServiceDataRequest data) {
        SensorData sd=null;
        try {
            System.out.println("solicitarInfoHumanAsync Iniciado");
            Socket s = new Socket(IP, serverPort);
            ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
            System.out.println("Enviando solicitud al Robot");
            oos.writeObject((Integer)0);
            sd= new SensorData(null);
            sd.setDataType(SensorDataType.EMOCIONES);
            
        } catch (IOException ex) {
            Logger.getLogger(PepperAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sd;
    }

    public DataBESA solicitarInfoLocationAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoLocationAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.LOCATION);
        return sd;
    }

    public DataBESA solicitarInfoStateAsync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoStateAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.INACTIVIDAD);
        return sd;
    }

    public DataBESA solicitarVoiceAsync(SPServiceDataRequest data) {
        System.out.println("solicitarVoiceAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.INTHABLA);
        return sd;
    }

    public DataBESA solicitarMovementAsync(SPServiceDataRequest data) {
        System.out.println("solicitarMovementAsync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.MOVIM);
        return sd;
    }
    
    public DataBESA solicitarInfoActividadSync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoActividadSync Iniciado");
        SensorData sd= new SensorData(null);
        sd.setDataType(SensorDataType.ACTIVIDAD);
        return sd;
    }

    public DataBESA solicitarInfoBatterySync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoBatterySync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoHumanSync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoHumanSync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoLocationSync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoLocationSync Iniciado");
        return null;
    }

    public DataBESA solicitarInfoStateSync(SPServiceDataRequest data) {
        System.out.println("solicitarInfoStateSync Iniciado");
        return null;
    }

    public DataBESA solicitarVoiceSync(SPServiceDataRequest data) {
        System.out.println("solicitarVoiceSync Iniciado");
        return null;
    }

    public DataBESA solicitarMovementSync(SPServiceDataRequest data) {
        System.out.println("solicitarMovementSync Iniciado");
        return null;
    }
    

}