package com.leon.sasepwa;
import android.se.omapi.Session;
import android.util.Log;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.builder.AnimateBuilder;
import com.aldebaran.qi.sdk.builder.AnimationBuilder;
import com.aldebaran.qi.sdk.object.actuation.Animate;
import com.aldebaran.qi.sdk.object.actuation.Animation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.ArrayDeque;
import java.util.HashMap;

import com.kaazing.gateway.jms.client.ConnectionDisconnectedException;
import com.kaazing.gateway.jms.client.JmsConnectionFactory;
import com.kaazing.net.ws.WebSocketFactory;
import javax.jms.MessageConsumer;

import java.util.concurrent.atomic.AtomicBoolean;

public class  ConexManager extends Thread {

    private static final String MY_IP = "127.0.0.1";
    private static final int PORT = 7896;
    private ServerSocket serverSocket;
    private Socket socket;
    private static ConexManager manager;
    private QiContext qiContext;
    private Object received;
    private AtomicBoolean funcionando;

    public ConexManager(QiContext qiContext) {
        // address= new InetSocketAddress(MY_IP,PORT);
        this.qiContext = qiContext;
        funcionando = new AtomicBoolean(true);
    }

    @Override
    public void run() {
        super.run();
        ObjectInputStream in = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (funcionando.get()) {
                Log.i("Crack", "Escuchandooooooooooooooooooooo!!!!!!!!!!!!");
                socket = serverSocket.accept();
                in = new ObjectInputStream(socket.getInputStream());
                received = in.readObject();
                JSONObject.class.cast(received);
                switch ( (int) received) {
                    case 0:
                        Animation myAnimation = AnimationBuilder.with(qiContext)
                                .withResources(R.raw.macarena)
                                .build();

                        Animate animate = AnimateBuilder.with(qiContext)
                                .withAnimation(myAnimation)
                                .build();
                        animate.addOnLabelReachedListener((label, time) -> {
                            // Called when a label is reached.
                        });

                        animate.async().run();
                        break;
                }
            }
        }catch(IOException | ClassNotFoundException e){
                e.printStackTrace();
            }
        }

    //    public void connecTest()
//    {
//
//        Thread t1 = new Thread(new Runnable(){
//
//            @Override
//            public void run() {
//                Socket s= new Socket();
//                try {
//                    s.connect(manager.getAddress());
//                    JSONObject json = new JSONObject();
//                    json.put("Imprimir", "Hola Mundo");
//                    DataOutputStream out = new DataOutputStream(s.getOutputStream());
//                    out.writeBytes(json.toString());
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                }
//                finally {
//                    try {
//                        s.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        t1.start();
//    }

}
