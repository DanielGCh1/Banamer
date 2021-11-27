package sistema.comunication;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Consulta;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.logic.Service;

public class Worker {

    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    Usuario usuario;

    public Worker(Socket s, ObjectInputStream in, ObjectOutputStream out, Usuario usuario) {
        this.socket = s;
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
                    try {
                        listen();
                    } catch (Exception ex) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.out.println("Worker " + usuario.getId() + " Nombre: " + usuario.getNombre() + " finalizo...");
            }
        };
        Thread hilo = new Thread(tarea);
        condition = true;
        hilo.start();
    }

    public void listen() throws Exception {
        try {
            Consulta consulta = null;
            Respuesta respuesta = null;
            try {
                consulta = (Consulta) inputStream.readObject(); // aqui se queda esperando que el cliente
            } catch (ClassNotFoundException ex) {              // diga algo
            }
            /* String result = Service.instance().echo(usuario, parameter);
            outputStream.writeObject(result);
             */
            // Aqui es donde tengo que meter un switch para saber la petición del cliente
            switch (consulta.getAccion()) {
                case Protocolo.salir:
                    condition = false;
                    respuesta = new Respuesta(Protocolo.ok);
                    outputStream.writeObject(respuesta);
                    outputStream.flush();
                    socket.close();
                    break;
                case Protocolo.retirarSaldo:
                    respuesta = Service.instance().retiro(usuario, consulta.getSaldo());
                    outputStream.writeObject(respuesta);
                    outputStream.flush();
                    break;
                case Protocolo.consultarSaldo:
                    respuesta = Service.instance().consultarSaldo(usuario);
                    outputStream.writeObject(respuesta);
                    outputStream.flush();
                    break;
                case Protocolo.cambiarClave:
                    respuesta = Service.instance().cambiarClave(usuario, consulta.getClave(), consulta.getClaveNueva());
                    outputStream.writeObject(respuesta);
                    outputStream.flush();
                    break;
                default:
                    break;
            }

        } catch (IOException ex) {
            condition = false;
        }
    }
}
