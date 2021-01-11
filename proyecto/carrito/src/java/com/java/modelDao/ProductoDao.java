package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.Categoria;
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

public class ProductoDao {

    Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    CategoriaDao categoriaDao = new CategoriaDao();
    Categoria categoria = new Categoria();

    /////// LISTAR
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList();
        Producto producto = null;
        String sql = "select *from producto where estado=1";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getFloat(5));
                producto.setStock(rs.getInt(6));
                producto.setEstado(rs.getInt(7));
                producto.setIdCategoria(rs.getInt(8));
                categoria = categoriaDao.buscarId(producto.getIdCategoria());
                producto.setCategoria(categoria);
                lista.add(producto);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }

    /////// LISTAR IMAGEN
    public void listarIMAGEN(int id, HttpServletResponse response) throws IOException {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        String sql = "  select * from producto where idProducto=" + id + " and estado=1";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            outputStream = response.getOutputStream();

            while (rs.next()) {
                inputStream = rs.getBinaryStream(3);
                //inputStream = rs.getBinaryStream("Foto");
            }

        } catch (SQLException ex) {

        }
        bufferedInputStream = new BufferedInputStream(inputStream);
        bufferedOutputStream = new BufferedOutputStream(outputStream);

        int i = 0;
        while ((i = bufferedInputStream.read()) != -1) {
            bufferedOutputStream.write(i);
        }

    }

    public Producto listarId(int id) {

        String sql = "select * from  producto where idProducto=" + id + " and estado=1";
        Producto producto = new Producto();

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));

            }
        } catch (SQLException ex) {

        }
        return producto;
    }

    //Buscar el producto por la categoria
    public List<Producto> buscarProductosCategoria(int idCategoria) {

        List<Producto> lista = new ArrayList<Producto>();
        String sql = "select * from  producto where idCategoria=?  and estado=1";
        Producto producto = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));
                producto.setEstado(rs.getInt(7));
                producto.setIdCategoria(rs.getInt(8));
                lista.add(producto);
            }
        } catch (SQLException ex) {

        }
        return lista;
    }

    //Buscar el producto por la categoria
    public List<Producto> buscarProducto(String nombre) {

        List<Producto> lista = new ArrayList<Producto>();
        String sql = "select *\n"
                + "from producto\n"
                + "where 1=1\n"
                + "and nombres like '%" + nombre + "%'"
                + "and estado=1";
        Producto producto = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));
               producto.setEstado(rs.getInt(7));
                producto.setIdCategoria(rs.getInt(8));
                categoria = categoriaDao.buscarId(producto.getIdCategoria());
                producto.setCategoria(categoria);
                lista.add(producto);
            }
        } catch (SQLException ex) {

        }
        return lista;
    }

    
    
    //Buscar el producto por la categoria
    public List<Producto> buscarProductoPrecio(int desde, int hasta) {

        List<Producto> lista = new ArrayList<Producto>();
        String sql = "select *\n" +
                "from producto\n" +
                "where 1=1\n" +
                "and estado=1\n" +
                "and precio between ? and ?";
        Producto producto = null;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,desde);
            ps.setInt(2, hasta);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));
               producto.setEstado(rs.getInt(7));
                producto.setIdCategoria(rs.getInt(8));
                categoria = categoriaDao.buscarId(producto.getIdCategoria());
                producto.setCategoria(categoria);
                lista.add(producto);
            }
        } catch (SQLException ex) {

        }
        return lista;
    }

    
    public void insertar(Producto producto) {
        String sql = "insert into producto(nombres,foto,descripcion,precio,stock,estado,idCategoria)\n"
                + "values(?,?,?,?,?,?,?)";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setBlob(2, producto.getFoto());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, 1);
            ps.setInt(7, producto.getIdCategoria());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void editar(Producto producto) {
        String sql = " update producto\n"
                + " set nombres=?,\n"
                + "   foto=?,\n"
                + "   descripcion=?,\n"
                + "   precio=?,\n"
                + "   stock=?\n"
                + "  where 1=1\n"
                + "  and idProducto=?;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setBlob(2, producto.getFoto());
            ps.setString(3, producto.getDescripcion());
            ps.setDouble(4, producto.getPrecio());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminar(int idProducto) {
        String sql = " update producto\n"
                + " set estado=0\n"
                + " where 1=1\n"
                + " and idProducto=?;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Producto buscar(int idProducto) {
        String sql = "  select *from producto \n"
                + " where 1=1\n"
                + " and estado=1\n"
                + " and idProducto=?;";

        Producto producto = null;
        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idProducto);
            rs = ps.executeQuery();
            while (rs.next()) {
                producto = new Producto();
                producto.setId(rs.getInt(1));
                producto.setNombre(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));
                producto.setEstado(rs.getInt(7));
                producto.setIdCategoria(rs.getInt(8));

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }

}//Clase
