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
package es.damut12.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private final String url = "jdbc:sqlite:D:\\Usuarios\\calvo\\Desktop\\DAM\\JavaWeb\\ConexionesBBDD\\PracticaEvaluable\\Recursos\\tareas.db";
    private String mensaje = "";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try {
            // Intentamos cargar los driver para conectar a la BBDD
            Class.forName("org.sqlite.JDBC");

            // Intentamos conectar a la BBDD
            try (Connection connection = DriverManager.getConnection(url)) {
                System.out.println("Conectado a la BBDD");
                // Preparamos la consulta
                try (PreparedStatement consulta = connection
                        .prepareStatement(
                                "select nombre_usuario, password from usuarios where nombre_usuario = ? and password = ?")) {

                    String user = request.getParameter("usuario");
                    String pass = request.getParameter("pass");

                    consulta.setString(1, user);
                    consulta.setString(2, pass);

                    System.out.println("Ps preparado");
                    // Ejecutamos la query
                    try (ResultSet rs = consulta.executeQuery()) {
                        if (rs.next()) {
                            user = rs.getString("nombre_usuario");
                            pass = rs.getString("password");

                            System.out.println("Consulta realizada");

                            request.setAttribute("user", user);
                            request.setAttribute("pass", pass);

                            System.out.println("User: " + user);
                            System.out.println("Password: " + pass);

                            request.getRequestDispatcher("/WEB-INF/views/opciones.jsp").forward(request, response);
                        } else {
                            // No hay resultados: usuario o contraseña incorrectos
                            System.out.println("DATOS INCORRECTOS (no hay resultados)");
                            mensaje = "USUARIO O CONTRASEÑA INCORRECTOS";
                            request.setAttribute("mensaje", mensaje);
                            request.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(request, response);
                        }

                    } catch (Exception e) {
                        System.out.println("No retorna datos");
                        mensaje = "USUARIO NO ENCONTRADO";
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(request, response);
                    }

                } catch (SQLException e) {
                    System.out.println("No realiza consulta");
                    mensaje = "No se pudo preparar la consulta";
                    request.setAttribute("mensaje", mensaje);
                    request.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(request, response);
                }

            } catch (SQLException e) {
                System.out.println("No conecta BBDD");
                mensaje = "Error al conectar a la BBDD";
                request.setAttribute("mensaje", mensaje);
                request.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("No carga driver");
            mensaje = "Error al cargar el driver de la base de datos";
            request.setAttribute("mensaje", mensaje);
            request.getRequestDispatcher("/WEB-INF/views/mensaje.jsp").forward(request, response);
        }

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // los .jsp incluidos en /WEB-INF/views no son visibles desde el navegador por
        // cuestiones de seguridad
        // el servidor Tomcat no permite el acceso directo a ellos
        // por eso, si queremos redirigir desde esos.jsp, tendremos que hacerlo pasando
        // por el Servlet
        // esas redirecciones el Servlet las gestiona desde el método doGet
        // debes controlar aquí si quieres enviar algo a otro .jsp
        // y el .forward al .jsp que corresponda

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

}
