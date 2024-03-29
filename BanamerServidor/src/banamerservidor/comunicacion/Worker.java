/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor.comunicacion;

import banamerprotocolo.logica.Usuario;
import banamerservidor.logica.Service;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author dgcha
 */
public class Worker {
    Socket s;
    ObjectInputStream in;
    ObjectOutputStream out;
    Usuario usuario;
    
    public Worker(Socket s, ObjectInputStream in,  ObjectOutputStream out, Usuario usuario) {
        this.s = s;
        this.in=in;
        this.out=out;
        this.usuario=usuario;
    }
    
    private boolean condition=false;
    
    public void start(){
        
        System.out.println("Worker "+ usuario.getId()+ " atendiendo peticiones...");
        Runnable tarea = new Runnable(){
            public void run(){
                while(condition){ 
                    listen();
                }
                System.out.println("Worker "+ usuario.getId()+ " finalizo...");
            }
        };
        Thread hilo = new Thread(tarea);
        condition=true;
        hilo.start();
    }

    public void listen(){
        try {
            String parameter="";
            try { parameter = (String)in.readObject(); } catch (ClassNotFoundException ex) {}
                String result=Service.instance().echo(usuario,parameter);
                out.writeObject(result);
        } catch (IOException  ex) {
            condition = false;
        }                             
    }
}
