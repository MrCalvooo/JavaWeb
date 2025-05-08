package com.productos.model;

public class Producto implements Comparable<Producto> {

    private String nombre;
    private double precio;
    private String categoria;
    private int cantidad;

    public Producto(String nombre, double precio, String categoria, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int compareTo(Producto otro) {
        int resultado = this.nombre.compareToIgnoreCase(otro.nombre);
        return resultado == 0 ? this.categoria.compareToIgnoreCase(otro.categoria) : resultado;
    }

}
