package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.Cliente;
import com.java.modelo.Producto;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class ClienteDao {

    Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    /////// LISTAR
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList();
        Cliente cliente = null;
        String sql = " select *\n"
                + "from cliente\n"
                + "where 1=1\n"
                + "and estado=1;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
                cliente.setPassword(rs.getString(6));
                cliente.setEstado(1);
                lista.add(cliente);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }

    public Cliente listarId(int id) {

        String sql = "select * from  cliente where idCliente=" + id + " and estado=1";
        Cliente cliente = new Cliente();

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setCorreo(rs.getString(4));
                cliente.setPassword(rs.getString(6));
                cliente.setEstado(1);

            }
        } catch (SQLException ex) {

        }
        return cliente;
    }

    
    
    // ULTIMO CLIENTE INSERTADO
    
    
     public int buscarUltimoCliente() {

        String sql = "select max(idCliente)\n" +
                    "from cliente;";
        int ultimo=0;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { 
               ultimo=rs.getInt(1); 

            }
        } catch (SQLException ex) {

        }
        return ultimo;
    }

     
     
     // INSERTAR CLIENTE
    public void insertar(Cliente cliente) {
        
        
        String sql = "insert into cliente(dni,nombres,direccion,email,password,estado)\n"
                + "values(?,?,?,?,?,?)";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getPassword());
            ps.setInt(6, cliente.getEstado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editar(Cliente cliente) {
        String sql = " update cliente \n"
                + "set dni=?,\n"
                + "nombres=?,\n"
                + "direccion=?,\n"
                + "email=?,\n"
                + "password=?,\n"
                + "estado=?\n"
                + "where idCliente=?;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getPassword());
            ps.setInt(6, cliente.getEstado());
            ps.setInt(7, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminar(int idCliente) {
        String sql = " update cliente \n"
                + "set estado=0\n"
                + "where idCliente=?;";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Cliente buscar(int idCliente) {
        String sql = "  select *\n"
                + "from cliente\n"
                + "where 1=1\n"
                + "and estado=1\n"
                + "and idCliente=?";

        Cliente cliente = new Cliente();
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            while (rs.next()) {

                cliente.setId(rs.getInt(1));
                cliente.setDni(rs.getString(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setCorreo(rs.getString(5));
                cliente.setPassword(rs.getString(6));
                cliente.setEstado(rs.getInt(7));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

}//Clase
