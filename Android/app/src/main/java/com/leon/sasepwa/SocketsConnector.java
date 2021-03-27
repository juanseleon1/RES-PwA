package com.leon.sasepwa;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;

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
    int data4QiContext;

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
            data4QiContext = Integer.parseInt(infoSocket);

            Animation myAnimation = AnimationBuilder.with(qiContext).withResources(R.raw.hh).build();

            Animate animate = AnimateBuilder.with(qiContext).withAnimation(myAnimation).build();
            animate.addOnLabelReachedListener((label, time) -> {
                // Called when a label is reached.
            });

            animate.async().run();

            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        };


    }

}
