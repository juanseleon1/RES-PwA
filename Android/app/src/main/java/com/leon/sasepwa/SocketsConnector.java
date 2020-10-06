package com.leon.sasepwa;
import com.aldebaran.qi.sdk.QiContext;

import java.net.*;
import java.io.*;

public class SocketsConnector {
    ServerSocket server;
    Socket socket;
    int port = 9080;
    String data;
    DataOutputStream out;
    BufferedReader in;
    QiContext qiContext;
    String infoSocket;

    public SocketsConnector(QiContext qiContext) {
        this.qiContext = qiContext;
    }

    public void iniciarSocket(){
        try {
            server = new ServerSocket(port);
            socket = new Socket();
            socket = server.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
            infoSocket = in.readLine();

            

            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        };


    }

}
