/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.data;

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
    
    public Usuario login(Usuario usuario) throws Exception{
        String sql="select * from usuario u where id=? and clave=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, usuario.getId());
        stm.setString(2, usuario.getClave());
        Usuario u;
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            u = from(rs, "u"); 
            return u;
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
    public Usuario getUsuario(Usuario usuario) throws Exception{
        String sql="select * from usuario u where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, usuario.getId());
        
        Usuario u;
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            u = from(rs, "u"); 
            return u;
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
    public void retiro(Usuario u, Integer monto) throws SQLException, Exception{
        String sql="update usuario set saldo = saldo-? where id = ? and saldo >=? ";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, monto);
        stm.setInt(2, u.getId());
        stm.setInt(3, monto);
        
        int count = db.executeUpdate(stm);
        if(count==0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public Integer depositar(Usuario u, Integer monto) throws SQLException, Exception{
        String sql="update usuario set saldo = saldo+? where id = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, monto);
        stm.setInt(2, u.getId());
        
        int count = db.executeUpdate(stm);
        if(count==0){
            throw new Exception("Saldo insuficiente");
        }
        else{
            return 1;
        }
    }
    public void cambiarClave(Usuario u, String clave) throws SQLException, Exception{
        String sql="update usuario set clave =? where id = ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, clave);
        stm.setInt(2, u.getId());
        int count = db.executeUpdate(stm);
        if(count==0){
            throw new Exception("Saldo insuficiente");
        }
    }
    public Usuario consultarSaldo(Integer id) throws Exception{
        String sql="select * from usuario u where id=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setInt(1, id);

        Usuario u;
        ResultSet rs =  db.executeQuery(stm);
        if (rs.next()) {
            u = from(rs, "u"); 
            return u;
        }
        else{
            throw new Exception ("Cliente no Existe");
        }
    }
    
    
    public List<Usuario> findAll(){
        List<Usuario> resultado=new ArrayList<>();
        try {
            String sql="select * from usuario u";
            PreparedStatement stm = db.prepareStatement(sql);
            ResultSet rs =  db.executeQuery(stm);
            Usuario c;
            while (rs.next()) {
                c = from(rs, "u"); 
                resultado.add(c);
            }
        } catch (SQLException ex) { }
        return resultado;        
    }
    
    public Usuario from(ResultSet rs, String alias){
        try {
            Usuario u = new Usuario();
            u.setId(rs.getInt(alias+".id"));
            u.setNombre(rs.getString(alias+".nombre"));
            u.setSaldo(rs.getInt(alias+".saldo"));
            u.setClave(rs.getString(alias+".clave"));
            return u;
        } catch (SQLException ex) {
            return null;
        }
    }
}
