 
package com.java.modelo;

import java.io.InputStream;

 
public class Producto {
    
    
    private int id;
    private String nombre;
    private InputStream foto;
    private double precio;
    private String descripcion;
    private int stock;
    private int estado;
    private int idCategoria;
    private Categoria categoria;
    
    
    
    

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    
    
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    } 
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    } 
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

  

    
    public Producto() {
    }
 

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", foto=" + foto + ", precio=" + precio + ", descripcion=" + descripcion + ", stock=" + stock + ", estado=" + estado + '}';
    }

   
    
    
    
}//Clase
