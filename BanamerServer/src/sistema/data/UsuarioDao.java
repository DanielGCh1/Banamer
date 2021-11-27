/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.data;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Respuesta;
import banamerprotocolo.logica.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dgcha
 */
public class UsuarioDao {

    Database db;

    public UsuarioDao() {
        db = Database.instance();
    }

    public Respuesta login(Usuario usuario) throws Exception {
        String sql = "select * from usuario u where id=? and clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, usuario.getId());
        stm.setString(2, usuario.getClave());
        Usuario u;
        Respuesta respuesta = new Respuesta(Protocolo.error, new Usuario());
        try {
            ResultSet rs = db.executeQuery(stm);

            if (rs.next()) {
                u = from(rs, "u");
                respuesta = new Respuesta(Protocolo.ok, u);
            }
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error, new Usuario());
        }
        return respuesta;
    }

    public Usuario getUsuario(Usuario usuario) throws Exception {
        String sql = "select * from usuario u where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, usuario.getId());

        Usuario u;
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            u = from(rs, "u");
            return u;
        } else {
            throw new Exception("Cliente no Existe");
        }
    }

    public Respuesta retiro(Usuario u, Integer monto) throws SQLException, Exception {
        String sql = "update usuario set saldo = saldo-? where id = ? and saldo >=? ";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, monto);
        stm.setInt(2, u.getId());
        stm.setInt(3, monto);

        Respuesta respuesta = new Respuesta(Protocolo.error);
        try {
            int count = db.executeUpdate(stm);
            if (count == 0) {
                respuesta = new Respuesta(Protocolo.saldoInsuficiente);
            } else {
                respuesta = new Respuesta(Protocolo.ok,(consultarSaldo(u).getSaldo() ));
            }
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
        }
        return respuesta;
    }
    public Respuesta cambiarClave(Usuario u, String claveActual, String claveNueva) throws SQLException, Exception {
        String sql = "update usuario set clave = ? where id = ? and clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, claveNueva);
        stm.setInt(2, u.getId());
        stm.setString(3, claveActual);

        Respuesta respuesta = new Respuesta(Protocolo.error);
        try {
            int count = db.executeUpdate(stm);
            if (count == 0) {
                respuesta = new Respuesta(Protocolo.error);
            } else {
                respuesta = new Respuesta(Protocolo.ok);
            }
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
        }
        return respuesta;
    }

    public Integer depositar(Usuario u, Integer monto) throws SQLException, Exception {
        String sql = "update usuario set saldo = saldo+? where id = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, monto);
        stm.setInt(2, u.getId());

        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("Saldo insuficiente");
        } else {
            return 1;
        }
    }

    public Respuesta consultarSaldo(Usuario usuario) throws Exception {
        String sql = "select * from usuario u where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, usuario.getId());

        Usuario u;
        Respuesta respuesta = new Respuesta(Protocolo.error);
        try {
            ResultSet rs = db.executeQuery(stm);
            if (rs.next()) {
                u = from(rs, "u");
                respuesta = new Respuesta(Protocolo.ok, u.getSaldo());
            }
        } catch (Exception e) {
            respuesta = new Respuesta(Protocolo.error);
        }
        return respuesta;
    }

    public List<Usuario> findAll() {
        List<Usuario> resultado = new ArrayList<>();
        try {
            String sql = "select * from usuario u";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs = db.executeQuery(stm);
            Usuario c;
            while (rs.next()) {
                c = from(rs, "u");
                resultado.add(c);
            }
        } catch (SQLException ex) {
        }
        return resultado;
    }

    public Usuario from(ResultSet rs, String alias) {
        try {
            Usuario u = new Usuario();
            u.setId(rs.getInt(alias + ".id"));
            u.setNombre(rs.getString(alias + ".nombre"));
            u.setSaldo(rs.getInt(alias + ".saldo"));
            u.setClave(rs.getString(alias + ".clave"));
            return u;
        } catch (SQLException ex) {
            return null;
        }
    }
}
