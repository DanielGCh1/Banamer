/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor.comunicacion;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Usuario;
import banamerservidor.logica.Service;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author dgcha
 */
public class Server {
    ServerSocket ss;
    
    public Server() throws IOException {
        ss = new ServerSocket(Protocolo.PORT);
        System.out.println("Servidor iniciado...");
    }
    
    public void run(){
        boolean continuar = true;
        Usuario usuario=null;
        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;
        while (continuar) {
            try {
                s = ss.accept();
                out = new ObjectOutputStream(s.getOutputStream() );
                in = new ObjectInputStream(s.getInputStream());                
                try {
                    usuario=(Usuario)in.readObject();
                    usuario=Service.instance().login(usuario);
                    out.writeObject(usuario);
                    out.flush();
                    System.out.println("Conexion Establecida...");
                    Worker worker = new Worker(s,in,out,usuario); 
                    worker.start();                    
                } catch (Exception ex) {   
                    out.writeObject(new Usuario(0,"Error","ERROR",0));
                    out.flush();
                    s.close();
                }                

            } catch (IOException ex) { }
        }
    }
}
