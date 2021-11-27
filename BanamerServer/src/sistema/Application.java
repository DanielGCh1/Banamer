package sistema;

import banamerprotocolo.logica.Usuario;
import java.io.IOException;
import java.util.List;
import sistema.comunication.Server;
import sistema.data.UsuarioDao;
import sistema.logic.Service;

public class Application {
    
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.run();
        
       /* try {
            List<Usuario> resultado = Service.instance().findAll();
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println("Usuario: "+ resultado.get(i).toString());
            }
            /*
            
            Usuario usuario = new Usuario(1, "5Mu$kr24");
            Service.instance().retiro(usuario, 100);
            if(result==1){
                System.out.println("Retiro exitoso");
            }
            
            
            usuario = Service.instance().login(usuario);
            //UsuarioDao ud = new UsuarioDao();
            //usuario = ud.login(usuario);
            
            
            
            System.out.println("Usuario: "+ usuario.toString());
            
            System.out.println("Usuario tiene un saldo de: "+ Service.instance().consultarSaldo(usuario.getId()));
            
            //Service.instance().cambiarClave(usuario, "5Mu$kr24");
            
            usuario = Service.instance().getUsuario(usuario);
            
            System.out.println("Usuario: "+ usuario.toString());
        } catch (Exception e) {
            System.out.println("Error");
        }*/
    }
}
