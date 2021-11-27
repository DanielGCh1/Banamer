package sistema.comunication;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Consulta;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import sistema.logic.Service;

public class Server {

    ServerSocket ss;

    public Server() throws IOException {
        ss = new ServerSocket(Protocolo.PORT);
        System.out.println("Servidor iniciado...");
    }

    public void loginUsuario(Consulta consulta) throws Exception {
        Usuario usuario = null;
        Respuesta respuesta;

    }

    public void run() {
        boolean continuar = true;
        Usuario usuario = null;
        Respuesta respuesta;
        Consulta consulta;

        Socket s;
        ObjectInputStream in;
        ObjectOutputStream out;
        while (continuar) {
            try {
                s = ss.accept();
                out = new ObjectOutputStream(s.getOutputStream());
                in = new ObjectInputStream(s.getInputStream());
                try {
                    consulta = (Consulta) in.readObject(); // LLega la consulta del cliente

                    if (consulta.getAccion() == Protocolo.login) {
                        respuesta = Service.instance().login(consulta.getUsuario());
                        if (respuesta.getAccion() == Protocolo.ok) {
                            out.writeObject(respuesta);
                            out.flush();
                            System.out.println("Conexion Establecida...");
                            Worker worker = new Worker(s, in, out, respuesta.getUsuario());
                            worker.start();
                        } else {
                            out.writeObject(new Respuesta(Protocolo.error));
                            out.flush();
                            s.close();
                        }
                    }
                } catch (Exception ex) {
                    out.writeObject(new Respuesta(Protocolo.error));
                    out.flush();
                    s.close();
                }

            } catch (IOException ex) {
                
            }
        }
    }

}
