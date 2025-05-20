<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.emple2.model.Empleado" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta de TODOS los empleados</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/empleados.css">
</head>
<body>
    <h1>EMPLEADOS: </h1>
    <table>
        <tr>
            <th>NIF</th>
            <th>Nombre</th>
            <th>Primer apellido</th>
            <th>Segundo apellido</th>
            <th>Codigo del departamento</th>
        </tr>
        <% 
            List<Empleado> listaEmpleados = (List<Empleado>) request.getAttribute("listaEmpleados");
            if (listaEmpleados != null && !listaEmpleados.isEmpty()) {
                for (Empleado emple : listaEmpleados) {
        %>
        <tr>
            <td><%= emple.getNif() %></td>
            <td><%= emple.getNombre() %></td>
            <td><%= emple.getApellido1() %></td>
            <td><%= emple.getApellido2() %></td>
            <td><%= emple.getCod_departamento() %></td>
        </tr>
        <%      }
            } else { %>
        <tr>
            <td colspan="5">No hay empleados para mostrar</td>
        </tr>
        <% } %>
    </table>
    <a href="<%= request.getContextPath() %>/index.jsp">Volver al menu</a>
</body>
</html>