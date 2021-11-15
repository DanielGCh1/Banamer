/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamercliente.logica;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Usuario;
import java.io.BufferedReader;
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
        try {
            input = new BufferedReader(new InputStreamReader(System.in));
            socket = new Socket(Protocolo.SERVER, Protocolo.PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {

        }
    }
    
    public Usuario login(Usuario u) throws Exception {
        try {
            
        } catch (Exception e) {
        }
    }
}
