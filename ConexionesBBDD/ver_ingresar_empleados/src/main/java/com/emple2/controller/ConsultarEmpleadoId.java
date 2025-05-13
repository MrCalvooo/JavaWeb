package com.emple2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.emple2.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ConsultarEmpleadoId", value = "/ConsultarEmpleadoId")
public class ConsultarEmpleadoId extends HttpServlet {

    private final String url = "jdbc:sqlite:D:/Usuarios/calvo/Desktop/DAM/JavaWeb/ConexionesBBDD/scriptsBBDD/empleados.db";
    String mensaje;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // Cargamos el driver
            Class.forName("org:sqlite.JDBC");

            // Conectamos a la BBDD
            try (Connection con = DriverManager.getConnection(url)) {
                try (PreparedStatement consulta = con.prepareStatement("select * from empleados where nombre = ? and apellido1 = ? and apellido2 = ?")) {
                    String nombre = req.getParameter("nombre");
                    String apellido1 = req.getParameter("apellido1");
                    String apellido2 = req.getParameter("apellido2");

                    consulta.setString(1, nombre);
                    consulta.setString(2, apellido1);
                    consulta.setString(3, apellido2);

                    try (ResultSet rs = consulta.executeQuery()) {
                        if (rs.next()) {
                            int codigo = rs.getInt("codigo");
                            String nif = rs.getString("nif");
                            nombre = rs.getString("nombre");
                            apellido1 = rs.getString("apellido1");
                            apellido2 = rs.getString("apellido2");
                            int codigo_departamento = rs.getInt("codigo_departamento");

                            Empleado empleado = new Empleado(nif, nombre, apellido1, apellido2, codigo_departamento);

                            req.setAttribute("empleado", empleado);
                            req.getRequestDispatcher("/WEB-INF/views/empleados.jsp").forward(req, resp);
                        }
                    } catch (SQLException e) {
                        req.setAttribute(mensaje, "Empleado no encontrado");
                        req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    req.setAttribute(mensaje, "Error al consultar el empleado");
                    req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                req.setAttribute(mensaje, "Error al conectar a la base de datos");
                req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            req.setAttribute(mensaje, "Error al cargar el driver");
            req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
        }
    }

}
