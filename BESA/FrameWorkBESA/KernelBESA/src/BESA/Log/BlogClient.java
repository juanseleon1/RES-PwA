package BESA.Log;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class BlogClient {

    private String IP = "localhost";
    private int PORT = 7777;
    private Socket client;
    private ObjectOutputStream output;

    public BlogClient() {
        try {
            client = new Socket(IP, PORT);
            output = new ObjectOutputStream(client.getOutputStream());
        } catch (UnknownHostException ex) {
            Logger.getLogger(BlogClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BlogClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean send(Message message) {
        try {
            output.writeObject(message);
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
