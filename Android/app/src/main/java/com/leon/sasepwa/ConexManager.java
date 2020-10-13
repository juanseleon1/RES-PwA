package com.leon.sasepwa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.kaazing.gateway.jms.client.ConnectionDisconnectedException;
import com.kaazing.gateway.jms.client.JmsConnectionFactory;
import com.kaazing.net.ws.WebSocketFactory;

public class  ConexManager{
    private static final String MY_IP="127.0.0.1";
    private static final int PORT= 1025;
    private InetSocketAddress address=null;
    private static  ConexManager manager;


    private DispatchQueue dispatchQueue;



    private ConexManager(){
        address= new InetSocketAddress(MY_IP,PORT);
    }

    public static ConexManager getManager(){
        if(manager==null)
        {
            manager= new ConexManager();
        }

        return manager;

    }

    public  InetSocketAddress getAddress() {
        return address;
    }

    public void connecTest()
    {

        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                Socket s= new Socket();
                try {
                    s.connect(manager.getAddress());
                    JSONObject json = new JSONObject();
                    json.put("Imprimir", "Hola Mundo");
                    DataOutputStream out = new DataOutputStream(s.getOutputStream());
                    out.writeBytes(json.toString());
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

}
