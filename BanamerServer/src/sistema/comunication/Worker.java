package sistema.comunication;

import banamerprotocolo.logica.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import sistema.logic.Service;

public class Worker {

    Socket s;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Usuario usuario;

    public Worker(Socket s, ObjectInputStream in, ObjectOutputStream out, Usuario usuario) {
        this.s = s;
        this.inputStream = in;
        this.outputStream = out;
        this.usuario = usuario;
    }

    private boolean condition = false;

    public void start() {

        System.out.println("Worker " + usuario.getId() + " Nombre: " + usuario.getNombre() + " atendiendo peticiones...");
        Runnable tarea = new Runnable() {
            public void run() {
                while (condition) {
                    listen();
                }
                System.out.println("Worker " + usuario.getId() + " Nombre: " + usuario.getNombre() + " finalizo...");
            }
        };
        Thread hilo = new Thread(tarea);
        condition = true;
        hilo.start();
    }

    public void listen() {
        try {
            String parameter = "";
            try {
                parameter = (String) inputStream.readObject(); // aqui se queda esperando que el cliente
            } catch (ClassNotFoundException ex) {              // diga algo
            }
            // Aqui es donde tengo que meter un switch para saber la petición del cliente
            String result = Service.instance().echo(usuario, parameter);
            outputStream.writeObject(result);
        } catch (IOException ex) {
            condition = false;
        }
    }
}
