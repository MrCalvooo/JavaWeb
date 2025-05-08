<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.HashSet, java.util.Set" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Nombres de usuarios</title>
        </head>

        <body>
            <% Set<String> nombres = (Set<String>) request.getAttribute("nombres");
                    for (String nombre : nombres){ %>
                    <p>Hola <%=nombre%>
                    </p>
                    <% } %>

            <a href="NombreUsu">Volver a insertar nombre</a>
        </body>

        </html>