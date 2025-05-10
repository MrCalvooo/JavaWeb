<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.empleados.model.Empleado" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/empleados.css">
    <title>Empleados</title>
</head>

<body>
    <h1>EMPLEADOS: </h1>
    <table border="3" align="center">
        <tr>
            <th>Codigo</th>
            <th>NIF</th>
            <th>Nombre</th>
            <th>Apellido 1</th>
            <th>Apellido 2</th>
            <th>Codigo departamento</th>
        </tr>
        <%List<Empleado> listaEmpleados = (List<Empleado>) request.getAttribute("listaEmpleados");

                if (listaEmpleados != null && !listaEmpleados.isEmpty()){
                for (Empleado emple : listaEmpleados){%>
                <tr>
                    <td>
                        <%= emple.getCodigo()%>
                    </td>
                    <td>
                        <%= emple.getNif()%>
                    </td>
                    <td>
                        <%= emple.getNombre()%>
                    </td>
                    <td>
                        <%= emple.getApellido1()%>
                    </td>
                    <td>
                        <%= emple.getApellido2()%>
                    </td>
                    <td>
                        <%= emple.getCodigo_departamento()%>
                    </td>
                </tr>
                <%} } else { %>
                    <p>No hay empleados</p>
                    <%}%>
    </table>
</body>

</html>