/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PepperPackage.Utils;

import Adapter.ResPwaAdapter;
import BESA.Kernel.Social.ServiceProvider.agent.SPServiceDataRequest;
import ServiceAgentResPwA.Guard.ServiceDataRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juans
 */
public class PepperAdapter extends ResPwaAdapter{
    
    protected Thread recvThread;
    public PepperAdapter() throws Exception {
        super();
        receiver=new PepperAdapterReceiver();
        serviceMapper=new PepperServiceMapper();
        recvThread= new Thread((PepperAdapterReceiver)receiver);
        this.rpa=null;
        //System.out.println("PepperAdapter Created");
        startPepperRcv();
    }
    
    private void startPepperRcv()
    {
        recvThread.start();
    }
    @Override
    public void ActivityServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR = (ServiceDataRequest)data;
        //System.out.println("solicitarInfoActividadAsync Solicitado");
        sendRequest(dataR);
    }

    @Override
    public void AutonomyServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("setAutonomyAsync Solicitado");
        sendRequest(dataR);
    }
    @Override
    public void EnergyServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarInfoBatteryAsync Solicitado");
        sendRequest(dataR);
    }
    @Override
    public void HumanServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarInfoHumanAsync Solicitado");
        sendRequest(dataR);
    }
    @Override
    public void LocationServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarInfoLocationAsync Solicitado");
        sendRequest(dataR);

    }
    @Override
    public void RobotStateServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarInfoStateAsync Solicitado");
        sendRequest(dataR);

    }
    @Override
    public void VoiceServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarVoiceAsync Solicitado");
        sendRequest(dataR);

    }
    @Override
    public void MovementServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarMovementAsync Solicitado");
        sendRequest(dataR);

    }
    @Override
    public void TabletServiceReqAsync(SPServiceDataRequest data) {
        ServiceDataRequest dataR=(ServiceDataRequest)data;
        //System.out.println("solicitarTabletAsync Solicitado");
        sendRequest(dataR);

    }

   public void sendRequest(ServiceDataRequest data)
   {
        try {
            String JSON=convertServiceRequest(data);
            try (Socket s = new Socket(IP, robotPort); DataOutputStream oos = new DataOutputStream (s.getOutputStream())) {
                System.out.println("ENVIANDO \n"+JSON);
                oos.writeUTF(JSON+"\n\r");
                oos.flush();
//                oos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(PepperAdapter.class.getName()).log(Level.SEVERE, null, ex);
        } 
   }
   
   private String convertServiceRequest(ServiceDataRequest data) throws JsonProcessingException
   {
       PepperServiceMapper mapper=(PepperServiceMapper) serviceMapper;
       PepperSendable s= new PepperSendable(sendNewSendable(),data.getSubservice(),data.getSubservice(),data.getParams());
       return new ObjectMapper().writeValueAsString(s);
   }
}
