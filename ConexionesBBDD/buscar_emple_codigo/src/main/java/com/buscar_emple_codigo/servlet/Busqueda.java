/*
 * Copyright 2020 Diego Silva <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.buscar_emple_codigo.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.buscar_emple_codigo.model.Departamento;
import com.buscar_emple_codigo.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Busqueda", value = "/Busqueda")
public class Busqueda extends HttpServlet {

    private final String url = "jdbc:sqlite:D:/Usuarios/calvo/Desktop/DAM/JavaWeb/ConexionesBBDD/scriptsBBDD/empleados.db";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("org.sqlite.JDBC");

            // Hacemos la conexion, preparamos la consulta y ejecutamos
            try (Connection conexion = DriverManager.getConnection(url); PreparedStatement ps = conexion.prepareStatement(
                "SELECT E.CODIGO AS EMPLEADO_CODIGO, E.NIF AS EMPLEADO_NIF, E.NOMBRE AS EMPLEADO_NOMBRE, " +
                "E.APELLIDO1 AS EMPLEADO_APELLIDO1, E.APELLIDO2 AS EMPLEADO_APELLIDO2, E.CODIGO_DEPARTAMENTO AS EMPLEADO_CODIGO_DEPARTAMENTO, " +
                "D.CODIGO AS DEPARTAMENTO_CODIGO, D.NOMBRE AS DEPARTAMENTO_NOMBRE, D.PRESUPUESTO AS DEPARTAMENTO_PRESUPUESTO, D.GASTOS AS DEPARTAMENTO_GASTOS " +
                "FROM EMPLEADOS E JOIN DEPARTAMENTOS D ON E.CODIGO_DEPARTAMENTO = D.CODIGO WHERE E.CODIGO = ? AND D.CODIGO = ?")) {

                System.out.println("Conexion establecida");
                // Departamentos
                int codigoDepartamento = Integer.parseInt(req.getParameter("cod_depart"));
                int codigoEmpleado = Integer.parseInt(req.getParameter("cod_emple"));

                // Primera interrogacion es el codigo del empleado introducido por el usuario
                ps.setInt(1, codigoEmpleado);
                // Segunda interrogacion es el codigo del departamento introducido por el usuario
                ps.setInt(2, codigoDepartamento);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // Datos departamento
                        Departamento departamento = new Departamento(
                            rs.getInt("DEPARTAMENTO_CODIGO"),
                            rs.getString("DEPARTAMENTO_NOMBRE"),
                            rs.getDouble("DEPARTAMENTO_PRESUPUESTO"),
                            rs.getDouble("DEPARTAMENTO_GASTOS")
                        );

                        req.setAttribute("departamento", departamento);

                        // Datos empleado
                        Empleado empleado = new Empleado(
                            rs.getInt("EMPLEADO_CODIGO"),
                            rs.getString("EMPLEADO_NIF"),
                            rs.getString("EMPLEADO_NOMBRE"),
                            rs.getString("EMPLEADO_APELLIDO1"),
                            rs.getString("EMPLEADO_APELLIDO2"),
                            rs.getInt("EMPLEADO_CODIGO_DEPARTAMENTO")
                        );

                        req.setAttribute("empleado", empleado);
                    }
                }

                System.out.println("Datos almacenados correctamente");
                req.getRequestDispatcher("/WEB-INF/views/verEmpleDepart.jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al cargar el driver JDBC: " + ex.getMessage());
            throw new ServletException("Error al cargar el driver JDBC", ex);
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }
}
