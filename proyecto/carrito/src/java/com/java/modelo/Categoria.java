 
package com.java.modelo;

 
public class Categoria {
    
    private int idCategoria;
    private String nombre;
    private int estado;

    
    
    
    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
    

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria() {
    }

    @Override
    public String toString() {
        return "Categoria{" + "idCategoria=" + idCategoria + ", nombre=" + nombre + '}';
    }
    
    
}
