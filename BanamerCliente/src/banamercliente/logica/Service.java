/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.logica;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Consulta;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author dgcha
 */
// Este es el proxy 
public class Service {

    private static Service theInstance;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    public Service() {
    }

    public void nuevaComunicacion() throws IOException {
        input = new BufferedReader(new InputStreamReader(System.in));
        socket = new Socket(Protocolo.SERVER, Protocolo.PORT);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public Respuesta login(Usuario u) throws Exception {
        Respuesta respuesta;
        try {
            nuevaComunicacion();
            Consulta consulta = new Consulta(Protocolo.login, u);
            outputStream.writeObject(consulta);
            outputStream.flush();
            respuesta = (Respuesta) inputStream.readObject();
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
            System.out.println("Login cliente error: " + e.getMessage());
        }
        return respuesta;
    }

    public Respuesta retiro(Integer monto) {
        Respuesta respuesta;
        try {
            Consulta consulta = new Consulta(Protocolo.retirarSaldo, monto);
            outputStream.writeObject(consulta);
            outputStream.flush();
            respuesta = (Respuesta) inputStream.readObject();
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
            System.out.println("Error retirando el saldo: " + e.getMessage());
        }
        return respuesta;
    }

    public Respuesta consultarSaldo(Usuario usuario) {
        Respuesta respuesta;
        try {
            Consulta consulta = new Consulta(Protocolo.consultarSaldo);
            outputStream.writeObject(consulta);
            outputStream.flush();
            respuesta = (Respuesta) inputStream.readObject();
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
            System.out.println("Error retirando el dinero: " + e.getMessage());
        }
        return respuesta;
    }

    public Respuesta cambiarClave(String claveActual, String claveNueva) {
        Respuesta respuesta;
        try {
            Consulta consulta = new Consulta(Protocolo.cambiarClave, claveActual, claveNueva);
            outputStream.writeObject(consulta);
            outputStream.flush();
            respuesta = (Respuesta) inputStream.readObject();
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
            System.out.println("Error al cambiar la contraseña: " + e.getMessage());
        }
        return respuesta;
    }

    public Respuesta salir() throws Exception {
        Respuesta respuesta;
        try {
            Consulta consulta = new Consulta(Protocolo.salir);
            outputStream.writeObject(consulta);
            outputStream.flush();
            respuesta = (Respuesta) inputStream.readObject();
            socket.close();
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
            System.out.println("Error al salir: " + e.getMessage());
            socket.close();
        }
        return respuesta;
    }
}
