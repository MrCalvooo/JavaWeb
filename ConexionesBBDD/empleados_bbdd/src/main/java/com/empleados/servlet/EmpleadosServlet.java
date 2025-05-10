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
package com.empleados.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.empleados.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EmpleadosServlet", value = "/EmpleadosServlet")
public class EmpleadosServlet extends HttpServlet {

    String url = "jdbc:sqlite:D:/Usuarios/calvo/Desktop/DAM/JavaWeb/ConexionesBBDD/scriptsBBDD/empleados.db";
    private String message;

    @Override
    public void init() {
        message = "Hello World from Servlet!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Empleado> listEmpleados = new ArrayList<>();
        try {
            // Registrar el controlador manualmente
            Class.forName("org.sqlite.JDBC");

            try (Connection conexion = DriverManager.getConnection(url)) {
                System.out.println("Conexión exitosa a la base de datos.");

                try (Statement st = conexion.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM EMPLEADOS")) {
                    int codigoEmple;
                    String nif;
                    String nombre, apellido1, apellido2;
                    int codigoDepart;

                    while (rs.next()) {
                        codigoEmple = rs.getInt("codigo");
                        nif = rs.getString("nif");
                        nombre = rs.getString("nombre");
                        apellido1 = rs.getString("apellido1");
                        apellido2 = rs.getString("apellido2");
                        codigoDepart = rs.getInt("codigo_departamento");

                        listEmpleados.add(new Empleado(codigoEmple, nif, nombre, apellido1, apellido2, codigoDepart));
                    }

                    System.out.println("Numero empleados: " + listEmpleados.size());
                    System.out.println("Consulta realizada y datos en lista");

                    req.setAttribute("listaEmpleados", listEmpleados);

                    req.getRequestDispatcher("/WEB-INF/views/verEmpleados.jsp").forward(req, resp);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el controlador JDBC de SQLite.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }
}
