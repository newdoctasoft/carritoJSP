package com.java.modelDao;

import com.java.config.Conexion;
import com.java.config.Fecha;
import com.java.modelo.Carrito;
import com.java.modelo.Cliente;
import com.java.modelo.Compra;
import com.java.modelo.DetalleCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompraDao {

    Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;
    ClienteDao clienteDao = new ClienteDao();
    DetalleCompraDao detalleDao = new DetalleCompraDao();

    public int GenerarCompra(Compra compra) {

        try {
            String sql = "insert into compras(idCliente,idPago,FechaCompras,Monto,Estado) values(?,?,?,?,?)";
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, compra.getCliente().getId());
            ps.setInt(2, 1);
            ps.setString(3, compra.getFecha());
            ps.setDouble(4, compra.getMonto());
            ps.setString(5, compra.getEstado());
            ps.executeUpdate();

            sql = "select @@IDENTITY as idCompras";
            rs = ps.executeQuery(sql);
            rs.next();
            int idCompras = rs.getInt("idCompras");
            rs.close();

            for (Carrito detalle : compra.getDetalleCompras()) {
                sql = "insert into detalle_compras(idProducto,idCompras,cantidad,precioCompra) values(?,?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setInt(1, detalle.getIdProducto());
                ps.setInt(2, idCompras);
                ps.setInt(3, detalle.getCantidad());
                ps.setDouble(4, detalle.getPrecioCompra());
                r = ps.executeUpdate();

            }

        } catch (SQLException ex) {
            Logger.getLogger(CompraDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    // Listado de todas las compras
    public List<Compra> listar() {
        List<Compra> lista = new ArrayList();
        Compra compra = null;
        String sql = " select *\n"
                + "from compras;";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                compra = new Compra();

                compra.setId(rs.getInt(1));
                Cliente cliente = clienteDao.buscar(rs.getInt(2));
                compra.setCliente(cliente);
                compra.setIdpago(19);
                compra.setFecha(Fecha.fechaBDConvert(rs.getDate(4)));
                compra.setMonto(rs.getDouble(5));
                compra.setEstado(rs.getString(6));

                List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
                detalles = detalleDao.listar(compra.getId());
                compra.setDetalle(detalles);
                lista.add(compra);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }

    
    
    //Listar compra por producto
    
      // Listado de todas las compras
    public List<Compra> listarCompraProducto(int idProducto) {
        List<Compra> lista = new ArrayList();
        Compra compra = null;
        String sql = "   select *\n" +
                    " from compras c, detalle_compras dc \n" +
                    " where 1=1 \n" +
                    " and dc.idCompras=c.idCompras\n" +
                    " and dc.idProducto=?";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,idProducto);
            rs = ps.executeQuery();

            while (rs.next()) {

                compra = new Compra();

                compra.setId(rs.getInt(1));
                Cliente cliente = clienteDao.buscar(rs.getInt(2));
                compra.setCliente(cliente);
                compra.setIdpago(1);
                compra.setFecha(Fecha.fechaBDConvert(rs.getDate(4)));
                compra.setMonto(rs.getDouble(5));
                compra.setEstado(rs.getString(6));

                List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
                detalles = detalleDao.listar(compra.getId());
                compra.setDetalle(detalles);
                lista.add(compra);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }
    
    
    
    
    
    // Listado de todas las compras
    public List<Compra> listarCompraCliente(int idCliente) {
        List<Compra> lista = new ArrayList();
        Compra compra = null;
        String sql = "  select *\n"
                + " from compras\n"
                + " where 1=1 \n"
                + " and idCliente=" + idCliente;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                compra = new Compra();

                compra.setId(rs.getInt(1));
                Cliente cliente = clienteDao.buscar(rs.getInt(2));
                compra.setCliente(cliente);
                compra.setIdpago(19);
                compra.setFecha(Fecha.fechaBDConvert(rs.getDate(4)));
                compra.setMonto(rs.getDouble(5));
                compra.setEstado(rs.getString(6));

                List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
                detalles = detalleDao.listar(compra.getId());
                compra.setDetalle(detalles);
                lista.add(compra);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }

    // Listado de todas las compras
    public Compra buscarCompraId(int idCompra) {

        Compra compra = null;
        String sql = " select *\n"
                + "from compras\n"
                + "where 1=1 \n"
                + "and idCompras=" + idCompra;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                compra = new Compra();

                compra.setId(rs.getInt(1));
                compra.setCliente(clienteDao.buscar(rs.getInt(2)));
                compra.setIdpago(19);
                compra.setFecha(Fecha.fechaBDConvert(rs.getDate(4)));
                compra.setMonto(rs.getDouble(5));
                compra.setEstado(rs.getString(6));

                List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
                detalles = detalleDao.listar(compra.getId());
                compra.setDetalle(detalles);

            }

        } catch (SQLException ex) {

        }
        return compra;
    }

    // Listado de todas las compras
    public double calcularSubtotalCompra(int idCliente, int idCompra) {
        Compra compra ;
        double subtotal = 0;
        String sql = "   select *\n" +
                " from compras\n" +
                " where 1=1 \n" +
                " and idCliente=?\n" +
                " and idCompras=?";

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            ps.setInt(2,idCompra);
            rs = ps.executeQuery();

            while (rs.next()) {

                compra = new Compra();

                compra.setId(rs.getInt(1));
                Cliente cliente = clienteDao.buscar(rs.getInt(2));
                compra.setCliente(cliente);
                compra.setIdpago(19);
                compra.setFecha(Fecha.fechaBDConvert(rs.getDate(4)));
                compra.setMonto(rs.getDouble(5));
                compra.setEstado(rs.getString(6));

                List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
                detalles = detalleDao.listar(compra.getId());
                compra.setDetalle(detalles);
                for (DetalleCompra d : compra.getDetalle()) {
                    subtotal += subtotal + (d.getCantidad() * d.getPrecioCompra());
                }

            }

        } catch (SQLException ex) {

        }
        return subtotal;
    }

}
