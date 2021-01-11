package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.Categoria;
import com.java.modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    /////// LISTAR
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList();
        Categoria categoria = null;
        String sql = " select *\n"
                + "from categoria\n"
                + "where 1=1\n"
                + "and estado=1;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNombre(rs.getString(2));
                categoria.setEstado(rs.getInt(3));
                lista.add(categoria);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }

    public Categoria buscarId(int id) {

        String sql = "select *\n"
                + "from categoria\n"
                + "where 1=1\n"
                + "and idCategoria=?\n"
                + "and estado=1;";

        Categoria categoria = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {

                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNombre(rs.getString(2));
                categoria.setEstado(rs.getInt(3));

            }
        } catch (SQLException ex) {

        }
        return categoria;
    }

    public void insertar(Categoria categoria) {
        String sql = "insert into categoria(nombre) values(?);";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editar(Categoria categoria) {
        String sql = " update categoria\n"
                + "set nombre=?\n"
                + "where 1=1 \n"
                + "and estado=1\n"
                + "and idCategoria=?;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setInt(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminar(int idCategoria) {
        String sql = " update categoria \n"
                + "set estado=0\n"
                + "where idCategoria=?;";
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
