package com.buscar_emple_codigo.model;

public class Departamento {

    private int codigo;
    private String nombre;
    private double presupuesto;
    private double gastos;

    public Departamento(int codigo, String nombre, double presupuesto, double gastos) {
        this.codigo = codigo;
        this.gastos = gastos;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public double getGastos() {
        return gastos;
    }

}
