 
package com.java.modelo;
 
public class DetalleCompra {
    
    private int idDetalle;
    private int idProducto;
    private int idCompras;
    private int cantidad;
    private double precioCompra;
    private Producto producto;

    
    
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
     
    
    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCompras() {
        return idCompras;
    }

    public void setIdCompras(int idCompras) {
        this.idCompras = idCompras;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public DetalleCompra() {
    }

    public DetalleCompra(int idProducto, int idCompras, int cantidad, double precioCompra) {
        this.idProducto = idProducto;
        this.idCompras = idCompras;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "idDetalle=" + idDetalle + ", idProducto=" + idProducto + ", idCompras=" + idCompras + ", cantidad=" + cantidad + ", precioCompra=" + precioCompra + '}';
    }
    
    
    
    
    
}
