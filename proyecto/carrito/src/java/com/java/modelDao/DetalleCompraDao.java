package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.DetalleCompra;
import com.java.modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleCompraDao {

    Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    Producto producto=new Producto();
    ProductoDao productoDao=new ProductoDao();
    
    /////// LISTAR
    public List<DetalleCompra> listar(int idCompra) {
        List<DetalleCompra> lista = new ArrayList();
        DetalleCompra detalleCompra = null;
        String sql = "select *\n"
                + "from detalle_compras\n"
                + "where 1=1\n"
                + "and idcompras=" + idCompra;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                detalleCompra = new DetalleCompra();
                detalleCompra.setIdDetalle(rs.getInt(1));
                detalleCompra.setIdProducto(rs.getInt(2));
                detalleCompra.setIdCompras(rs.getInt(3));
                detalleCompra.setCantidad(rs.getInt(4));
                detalleCompra.setPrecioCompra(rs.getDouble(5));
                detalleCompra.setProducto(productoDao.buscar(rs.getInt(2)));
                lista.add(detalleCompra);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }
}
