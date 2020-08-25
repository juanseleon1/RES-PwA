package com.leon.sasepwa;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class  ConexManager {
    private static final String MY_IP="127.0.0.1";
    private static final int PORT= 1025;
    private static InetSocketAddress address=null;
    private static  ConexManager manager;
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

    public void connecTest()
    {
        Socket s= new Socket();
        try {
            s.connect(address);
            JSONObject json = new JSONObject();
            json.put("Imprimir", "Hola Mundo");
            OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8);
            out.write(json.toString());
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



}
