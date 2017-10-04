/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Juan Benitez
 */
public class Ahorcado {

    private int id;

    JTextField campoPalabra;
    JLabel campoIntentos;
    JLabel campoErrores;
    JLabel largoPalabra;

    private boolean jugar = false;

    char[] palabra_secreta;
    private char[] palabra;

    int intentos = 0;
    boolean acerto = false;

    public Ahorcado() {
    }

    public Ahorcado(String palabra) {

        palabra_secreta = palabra.toCharArray();
    }

    public Ahorcado(JTextField campoPalabra, String palabra, JLabel errores, JLabel campoIntentos, JLabel largoPalabra) {

        //guardamos la palabra secreta
        this.campoIntentos = campoIntentos;
        this.campoPalabra = campoPalabra;
        this.campoErrores = errores;
        this.largoPalabra = largoPalabra;

        this.palabra_secreta = palabra.toCharArray();

        String linea = "";

        //colocamos lineas segun el tamaño de la palabra
        for (int i = 0; i <= palabra_secreta.length - 1; i++) {
            linea += "_";
        }

        //convertimos la linea en vector de char y guardamos en la variable palabra
        this.palabra = linea.toCharArray();

        //colocamos las lineas a adivinar en el JtextField
        this.campoPalabra.setText(linea);

        //colocamos el icono de los intentos restantes
        this.campoIntentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intentos/0.jpg")));

        //colocamos la imagen del ahorcado
        this.campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/0.jpg")));

        this.jugar = true;

        this.largoPalabra.setText("La palabra tiene " + this.palabra_secreta.length + " letras.");

    }

    public String conectarse() throws IOException {
        
        DatagramSocket socket;
        InetAddress address;
        byte[] mensaje_bytes = new byte[256];
        String mensaje = "";
        mensaje_bytes = mensaje.getBytes();

        //Paquete
        DatagramPacket paquete;
        String cadenaMensaje = "";
        DatagramPacket servPaquete;

        byte[] RecogerServidor_bytes = new byte[256];
        socket = new DatagramSocket(); // Creamos el socket del cliene
        address = InetAddress.getByName("localhost"); // Direccion del servidor

        mensaje = "palabra"; // Mensaje que se enviara al servidor
        mensaje_bytes = mensaje.getBytes(); //Pasamos el mensaje a bytes
        paquete = new DatagramPacket(mensaje_bytes, mensaje.length(), address, 6000); // Creamos el paquete
        socket.send(paquete); // Enviamso el paquete

        RecogerServidor_bytes = new byte[256]; // variable que recoge la respuesta del servidor

        //Esperamos a recibir un paquete
        servPaquete = new DatagramPacket(RecogerServidor_bytes, 256);
        socket.receive(servPaquete);

          //Convertimos el mensaje recibido en un string
        cadenaMensaje = new String(RecogerServidor_bytes).trim();

        //Imprimimos el paquete recibido
        System.out.println(cadenaMensaje);
       

        return cadenaMensaje;
    }

    public void validarPalabra(char letraIngresada) {
        if (jugar) {

            String letrasAcertadas = "";

            //validamos los intentos restantes
            if (this.intentos == 7) {

                this.campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/8.jpg")));

                String respuesta = "";

                for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
                    respuesta = respuesta + this.palabra_secreta[i];
                }

                JOptionPane.showMessageDialog(null, "Lo siento perdiste, la palabra era " + respuesta, "Perdiste!", JOptionPane.ERROR_MESSAGE);
            } else {
                //evaluamos si ha adivinado la palabra comparando cada letra
                for (int i = 0; i <= palabra_secreta.length - 1; i++) {

                    //validamos que la letra este en la palabra
                    if (this.palabra_secreta[i] == letraIngresada) {
                        this.palabra[i] = letraIngresada;
                        this.acerto = true;
                    }

                    letrasAcertadas += this.palabra[i]; //guardamos la palabra con las letras acertadas

                }
                //si no acerto, mostramos el intento fallido y el ahorcado

                if (this.acerto == false) {

                    intentos += 1; //Numero de errores

                    campoIntentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/intentos/" + this.intentos + ".jpg")));
                    campoErrores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/" + this.intentos + ".jpg")));

                    //Mostramos un mensaje avisando que erró la letra
                    if (intentos < 8) {
                        JOptionPane.showMessageDialog(null, "Fallaste! \n\n\t Te quedan " + ((8) - this.intentos) + " intentos.");
                    }

                } else {
                    this.acerto = false;
                }

                this.campoPalabra.setText(letrasAcertadas);
                //comprobamos el estado del juego

                validarGano();
            }
        }
    }

    private void validarGano() {

        boolean gano = false;

        for (int i = 0; i <= this.palabra_secreta.length - 1; i++) {
            if (this.palabra[i] == this.palabra_secreta[i]) {
                gano = true;
            } else {
                gano = false;
                break;
            }
        }

        if (gano) {
            JOptionPane.showMessageDialog(null, "!! Felicidades !! \n Adivinaste la palabra", "Ganaste", 0, new javax.swing.ImageIcon(getClass().getResource("/ahorcado/goals.png")));
        }
    }
}
