package com.empleados.model;

public class Empleado {

    private final int codigo;
    private final String nif;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final int codigo_departamento;

    public Empleado(int codigo, String nif, String nombre, String apellido1, String apellido2, int codigo_departamento) {
        this.codigo = codigo;
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.codigo_departamento = codigo_departamento;
    }

    // Getters para acceder a los atributos
    public int getCodigo() {
        return codigo;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getCodigo_departamento() {
        return codigo_departamento;
    }
}
