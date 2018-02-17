package core;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Fabian
 */
public class Cliente {

    public void enviarTexto(String mensaje) {
        try {
            Socket clientesocket = new Socket("localhost", 9999);
            try (DataOutputStream flujo_salida = new DataOutputStream(clientesocket.getOutputStream())) {
                flujo_salida.writeUTF(mensaje);
                flujo_salida.close();
            }
        } catch (IOException ex) {
            System.out.println("Error " + ex);
        }
    }
}
