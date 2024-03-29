/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor.logica;

import banamerprotocolo.logica.Usuario;
import banamerservidor.data.UsuarioDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dgcha
 */
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
    public Usuario login(Usuario u) throws Exception {
        //return usuarioDao.login(u);
        return new Usuario(0, "nulo", "nulo", 0);
    }

    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    public String echo(Usuario u, String parameter) {
        return parameter + " " + u.getId();
    }

    public Service() {
        try {
            usuarioDao = new UsuarioDao();

        } catch (Exception e) {

        }
    }
}
