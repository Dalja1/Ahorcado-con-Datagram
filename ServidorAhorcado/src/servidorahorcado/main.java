/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorahorcado;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala de software
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // ServerSocket ss;
        DatagramSocket socket;
        Servidor server = new Servidor();
        System.out.print("Inicializando servidor... ");

        try {
            socket = new DatagramSocket(6000);
            System.out.print("OK");
            byte[] mensaje_bytes = new byte[256];
            String mensaje = "";
            mensaje = new String(mensaje_bytes);
            String mensajeComp = "";

            DatagramPacket paquete = new DatagramPacket(mensaje_bytes, 256);
            DatagramPacket envpaquete = new DatagramPacket(mensaje_bytes, 256);
            int puerto;
            InetAddress address;
            byte[] mensaje2_bytes = new byte[256];
           
            while(true){
                try {
                    // Recibimos el paquete
                    socket.receive(paquete);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Lo formateamos
                mensaje = new String(mensaje_bytes).trim();
                
                //System.out.println(i+mensaje);
                //Obtenemos IP Y PUERTO
                puerto = paquete.getPort();
                address = paquete.getAddress();

                if (mensaje.startsWith("fin")) {
                    mensajeComp = "chauuuuuuu cliente";
                }

                if (mensaje.startsWith("palabra")) {
                    mensajeComp = server.obtenerPalabra();
                }

                //formateamos el mensaje de salida
                mensaje2_bytes = mensajeComp.getBytes();

//Preparamos el paquete que queremos enviar
                envpaquete = new DatagramPacket(mensaje2_bytes, mensajeComp.length(), address, puerto);

                try {
                    // realizamos el envio
                    socket.send(envpaquete);
                } catch (IOException ex) {
                    Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                }

            } 

        } catch (SocketException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
