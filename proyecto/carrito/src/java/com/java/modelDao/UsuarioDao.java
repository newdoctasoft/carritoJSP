package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.Cliente;
import com.java.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

     Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public Usuario buscarUsuario(String email, String password) {

        String sql = "SELECT * FROM usuario WHERE email = ? and password = ? and estado=1";
        Usuario usuario = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setEmail(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setEstado(rs.getInt(5));
                usuario.setTipo(rs.getString(6));
            }
            rs.close();
            ps.close();
            conexion.Cerrar();
        } catch (SQLException ex) {
            conexion.Cerrar();
        }
        return usuario;
    }
    
    
    
    //BUSCAR USUARIO VINCULANDOLO AL CLIENTE
    
    
    
    public Usuario buscarUsuario(int idCliente) {

            String sql = "SELECT * FROM usuario WHERE id= ?";
        Usuario usuario = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente); 
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setEmail(rs.getString(3));
                usuario.setPassword(rs.getString(4));
                usuario.setEstado(rs.getInt(5));
            }
            rs.close();
            ps.close();
            conexion.Cerrar();
        } catch (SQLException ex) {
            conexion.Cerrar();
        }
        return usuario;
    }
    
    ///INSERTAR USUARIO
     public void insertar(Usuario usuario) {
         
        String sql = "insert into usuario(nombre,email,password,estado,tipo,id)\n"
                + "values(?,?,?,?,?,?)";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setInt(4, usuario.getEstado());
            ps.setString(5, usuario.getTipo());
            ps.setInt(6, usuario.getId());
            
            
        
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     // ELIMINAR USUARIO 
        
     public void eliminar(Usuario usuario) {
         
        String sql = "update usuario\n" +
                        "set estado=0\n" +
                        "where 1=1\n" +
                        "and id=?";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setInt(1, usuario.getId()); 
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
}
