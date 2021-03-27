/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BESA.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BLogServer extends Thread {

    private int PORT = 7777;
    private boolean active;
    private Socket client;
    private ServerSocket server;
    private Vector<Message> buffer;
    private ObjectInputStream input;

    public BLogServer() {
        try {
            active = false;
            buffer = new Vector<Message>();
            server = new ServerSocket(PORT);
        } catch (IOException ex) {
            Logger.getLogger(BLogServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean getConnection() {
        try {
            client = server.accept();
            input = new ObjectInputStream(client.getInputStream());
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void startSever() {
        active = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        if (getConnection()) {
            while (active) {
                try {
                    addBuffer((Message) input.readObject());
                } catch (ClassNotFoundException ex) {
                    active = false;
                    ex.printStackTrace();
                } catch (IOException ex) {
                    active = false;
                    ex.printStackTrace();
                }
            }
        }
    }

    private synchronized void addBuffer(Message message) {
        buffer.add(message);
        this.notify();
    }

    public synchronized Message readBuffer() {
        while (buffer.isEmpty()) {
            if (buffer.isEmpty()) {
                try {
                    this.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return buffer.remove(0);
    }
}
