<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.emple2.model.Empleado" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/empleados.css">
            <title>Empleado</title>
        </head>

        <body>
            <% Empleado emple = (Empleado) request.getAttribute("empleado"); %>
                <table>
                    <tr>
                        <th>NIF</th>
                        <th>Nombre</th>
                        <th>Primer apellido</th>
                        <th>Segundo apellido</th>
                        <th>Codigo del departamento</th>
                    </tr>
                    <tr>
                        <% if (emple != null) { %>
                            <td><%= emple.getNif() %></td>
                            <td><%= emple.getNombre() %></td>
                            <td><%= emple.getApellido1() %></td>
                            <td><%= emple.getApellido2() %></td>
                            <td><%= emple.getCod_departamento() %></td>
                        <% } else { %>
                            <td colspan="5">No se encontró el empleado</td>
                        <% } %>
                    </tr>
                </table>

                <a href="<%= request.getContextPath() %>/index.jsp">Volver al menu</a>
        </body>

        </html>