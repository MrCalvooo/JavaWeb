package com.emple2.model;

public class Empleado {

    private String nif;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int cod_departamento;

    public Empleado(String nif, String nombre, String apellido1, String apellido2, int cod_departamento) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.cod_departamento = cod_departamento;
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

    public int getCod_departamento() {
        return cod_departamento;
    }

}
