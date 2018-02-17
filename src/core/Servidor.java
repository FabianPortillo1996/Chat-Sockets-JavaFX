package core;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author Fabian
 */
public class Servidor implements Runnable {

    public Servidor() {
        Thread hilo = new Thread(this);
        hilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server_socket = new ServerSocket(9999);
            while (true) {
                Socket misocket = server_socket.accept();
                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                String mensaje_texto = flujo_entrada.readUTF();
                
                misocket.close();
            }
        } catch (IOException ex) {
            System.out.println("Error s " + ex);
        }
    }
    
    public static void main(String[] args) {
        Servidor ser = new Servidor();
    }
}
