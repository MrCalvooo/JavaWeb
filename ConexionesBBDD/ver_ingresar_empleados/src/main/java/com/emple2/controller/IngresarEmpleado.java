package com.emple2.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "IngresarEmpleado", value = "/IngresarEmpleado")
public class IngresarEmpleado extends HttpServlet {

    // Direccion de la base de datos
    private final String url = "jdbc:sqlite:D:/Usuarios/calvo/Desktop/DAM/JavaWeb/ConexionesBBDD/scriptsBBDD/empleados.db";
    String mensaje;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Registramos el driver
            Class.forName("org.sqlite.JDBC");

            // Conectamos a la BBDD
            try (Connection con = DriverManager.getConnection(url)) {
                // Hacemos la consulta
                try (PreparedStatement consultarCodigoDepartamento = con.prepareStatement("select codigo from departamentos where nombre = ?")) {
                    String nif = req.getParameter("nif");
                    String nombre = req.getParameter("nombre");
                    String apellido1 = req.getParameter("apellido1");
                    String apellido2 = req.getParameter("apellido2");

                    String nombre_departamento = req.getParameter("departamento");
                    consultarCodigoDepartamento.setString(1, nombre_departamento);

                    // Ejecutamos la consulta
                    try (ResultSet rs = consultarCodigoDepartamento.executeQuery()) {
                        // Si retorna datos
                        if (rs.next()) {

                            int cod_departamento = rs.getInt("codigo");

                            // Insertamos el empleado
                            try (PreparedStatement ingreso = con.prepareStatement("insert into empleados(nif, nombre, apellido1, apellido2, codigo_departamento) values(?, ?, ?, ?, ?)")) {
                                ingreso.setString(1, nif);
                                ingreso.setString(2, nombre);
                                ingreso.setString(3, apellido1);
                                ingreso.setString(4, apellido2);
                                ingreso.setInt(5, cod_departamento);

                                // Insertamos la insercion y obtenemos las filas ingresadas
                                int filasInsertadas = ingreso.executeUpdate();
                                if (filasInsertadas > 0) {
                                    mensaje = "Empleado insertado correctamente";
                                } else {
                                    mensaje = "Error al insertar el empleado";
                                }
                                req.setAttribute("mensaje", mensaje);
                                req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);

                            } catch (Exception e) {
                                mensaje = "Error al insertar empleado";
                                req.setAttribute("mensaje", mensaje);
                                req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                            }
                        } else {
                            mensaje = "Departamento no encontrado";
                            req.setAttribute("mensaje", mensaje);
                            req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                        }
                    } catch (Exception e) {
                        mensaje = "Error al ejecutar la consulta del departamento";
                        req.setAttribute("mensaje", mensaje);
                        req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                    }
                } catch (Exception e) {
                    mensaje = "Error al preparar la consulta del departamento";
                    req.setAttribute("mensaje", mensaje);
                    req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
                }

            } catch (Exception e) {
                mensaje = "Error al conectar a la base de datos";
                req.setAttribute("mensaje", mensaje);
                req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            mensaje = "Driver no encontrado";
            req.setAttribute("mensaje", mensaje);
            req.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(req, resp);
        }
    }

}
