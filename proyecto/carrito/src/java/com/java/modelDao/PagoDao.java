 
package com.java.modelDao;

import com.java.config.Conexion;
import com.java.modelo.Cliente;
import com.java.modelo.Pago;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

 
public class PagoDao {
    
        Connection con;
    Conexion conexion = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
     /////// LISTAR
    public List<Pago> listar() {
        List<Pago> lista = new ArrayList();
        Pago pago = null;
        String sql = " select *\n"
                + "from pago" ;

        try {
            con = conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                
                pago=new Pago(); 
                pago.setId(rs.getInt(1));
                //pago.setMonto(rs.getInt(2)); 
                lista.add(pago);
            }

        } catch (SQLException ex) {

        }
        return lista;
    }
    
}
