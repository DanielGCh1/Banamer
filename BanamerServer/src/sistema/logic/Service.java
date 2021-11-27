package sistema.logic;

import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import sistema.data.UsuarioDao;

public class Service {
    
    // Singleton implementation
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    // Service data
    UsuarioDao usuarioDao;

    // Service methods
    public Respuesta login(Usuario u) throws Exception {
        return usuarioDao.login(u);
    }
    public Usuario getUsuario(Usuario usuario) throws Exception{
        return usuarioDao.getUsuario(usuario);
    }

    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    public Respuesta retiro(Usuario u, Integer monto) throws Exception{
        return usuarioDao.retiro(u, monto);
    }
    public String echo(Usuario u, String parameter) {
        return parameter + " " + u.getId();
    }

    public void depositar(Usuario u, Integer monto) throws Exception{
         usuarioDao.retiro(u, monto);
    }
    public Respuesta cambiarClave(Usuario u, String claveActual, String claveNueva) throws Exception{
        return usuarioDao.cambiarClave(u, claveActual, claveNueva);
    }
    public Respuesta consultarSaldo(Usuario usuario) throws Exception{
        return usuarioDao.consultarSaldo(usuario);
    }
    public Service() {
        try {
            usuarioDao = new UsuarioDao();

        } catch (Exception e) {

        }
    }
    
}
