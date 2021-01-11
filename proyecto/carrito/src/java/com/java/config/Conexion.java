package com.java.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    Connection con;
    String url = "jdbc:mysql://localhost:3306/carritoCompras";
    String user = "root";
    String pass = "";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {

            System.out.println("Error en conexi�n ");
            System.out.println(ex.getMessage());
        }
        return con;
    }

    //CERRAR CONEXION 
    public void Cerrar() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Error al cerrar conexi�n");
            System.out.println(ex.getMessage());
        }
    }// cerar conexion

}
