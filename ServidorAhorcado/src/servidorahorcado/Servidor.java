/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorahorcado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala de software
 */
public class Servidor extends Thread{
       
    private DatagramSocket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
   
    private String[] diccionario = {"ABEJA", "AEROPUERTO", "COMPUTADOR", "OSO",
        "JAVA", "NEVERA", "PROGRAMA", "INFORMATICA", "COMPUTACION", "COMPUTADOR","CORAZON","BANANO","PLATANO",
        "AUTOMOVIL", "PERRO", "COLOMBIA", "LONDRES", "CEPILLO", "BRAZO", "CABEZA", "CUERPO","DEPORTE","SALUD",
        "ANONYMOUS", "CUADERNO", "PANTALLA", "UBUNTU", "SEMAFORO", "LINUX", "LOBO","AMOR","MOSCA","ZANAHORIA",
        "PINGUINO", "HACKER", "SISTEMA", "ELEFANTE", "CASCADA", "JUEGOS","LARGO","BONITO","IMPOSIBLE","UNIDOS","ZOMBIE",
        "MATEMATICAS", "CALCULO", "ALGEBRA", "DICCIONARIO", "BIBLIOTECA", "COCINA","PELICULA","COMERCIAL","GRANDE","PEQUEÑO",
        "GATO", "SAPO", "JIRAFA", "FERROCARRIL", "FACEBOOK", "PERSONA","BICICLETA","CONTROL","PANTALON","AEROSOL",
        "ERROR", "COPA", "COPA", "PROGRAMADOR", "LICENCIA", "NUEVE", "PROCESADOR","GARAJE","MODERNO","TABLA","DISCOTECA",
        "LENGUAJE", "PROGRAMACION", "HERRAMIENTAS", "INTERNET", "EJECUTAR", "PROYECTO","PERIODICO","COCODRILO","TORTUGA","CABALLO",
        "APLICACION", "PERA", "SOFTWARE", "ADMINISTRACION", "VENTANA", "MANTENIMIENTO","INFORMACION","PRESIDENTE","PERSONA","GENTE",
        "NARANJA", "PRUEBA", "MANZANA", "JARRA", "CELULAR", "TELEFONO","CONTAMINACION","COLOR","ROMANO","ADIVINAR","MARCADOR",
        "INSTRUCCION", "CUADERNO", "CASA", "PALA", "ARBOL", "PUENTE", "PAPEL", "HOJA","HELICOPTERO","BARCO","GOLF","CARRERA",
        "TUBERIA", "PLOMERO", "FUTBOL", "BALONCESTO", "ESTADIO", "JEAN", "FUENTE", "LEOPARDO","REGLA","PRIMERO","SEGUNDO",
        "BLUSA", "CAMISA", "AGUA", "FUEGO", "INDUSTRIA", "AIRE","TIERRA","NATURALEZA","MIERCOLES","FOTOGRAFIA","LEON",
        "TIGRE"}; //90
    private char[] palabra_secreta;
    
    
    
    
    
    public String obtenerPalabra()  {
        this.socket = socket;
        return  Random();
        
        //dos = new DataOutputStream(socket.getOutputStream());
        // dis = new DataInputStream(socket.getInputStream());
       //System.out.println(palabra_secreta);
        // dos.writeUTF(String.valueOf(palabra_secreta));
    }
  
    
    
    
    private String Random(){   
        int num = (int)(Math.random()*(diccionario.length));   
        return diccionario[num];
    }
    
    
   
    
    
 
}
