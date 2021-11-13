/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banamerservidor.data;

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
       /* String sql="select * from usuario u where id=? and clave=?";
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
        }*/
       Usuario u = new Usuario(0, "nulo", "nulo", 0);
       return u;
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
