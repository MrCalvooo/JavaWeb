package com.emple2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emple2.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultarTodosEmpleados", value = "ConsultarTodosEmpleados")
public class ConsultarTodosEmpleados extends HttpServlet {

    private final String url = "jdbc:sqlite:D:/Usuarios/calvo/Desktop/DAM/JavaWeb/ConexionesBBDD/scriptsBBDD/empleados.db";
    private String mensaje = "";
    private final List<Empleado> listaEmpleados = new ArrayList<>();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(url)) {

                try (Statement consulta = connection.createStatement()) {

                    try (ResultSet resultSet = consulta.executeQuery("SELECT * FROM EMPLEADOS")) {
                        String nif, nombre, apellido1, apellido2;
                        int cod_departamento;

                        while (resultSet.next()) {
                            nif = resultSet.getString("nif");
                            nombre = resultSet.getString("nombre");
                            apellido1 = resultSet.getString("apellido1");
                            apellido2 = resultSet.getString("apellido2");
                            cod_departamento = resultSet.getInt("codigo_departamento");

                            listaEmpleados.add(new Empleado(nif, nombre, apellido1, apellido2, cod_departamento));
                        }

                        req.setAttribute("listaEmpleados", listaEmpleados);

                        System.out.println("Todos los empleados han sido consultados");

                        req.getRequestDispatcher("/WEB-INF/views/consultarEmpleados.jsp").forward(req, resp);
                    } catch (Exception e) {
                        mensaje = "Error al ejecutar la consulta";
                        req.setAttribute("mensaje", mensaje);
                        req.getRequestDispatcher(mensaje).forward(req, resp);
                    }
                } catch (Exception e) {
                    mensaje = "Error al conectar al consultar la BBDD";
                    req.setAttribute("mensaje", mensaje);
                    req.getRequestDispatcher(mensaje).forward(req, resp);
                }
            } catch (Exception e) {
                mensaje = "Error al conectar a la BBDD";
                req.setAttribute("mensaje", mensaje);
                req.getRequestDispatcher(mensaje).forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            mensaje = "Error al cargar el driver de la BBDD";
            req.setAttribute("mensaje", mensaje);
            req.getRequestDispatcher(mensaje).forward(req, resp);
        }
    }

}
