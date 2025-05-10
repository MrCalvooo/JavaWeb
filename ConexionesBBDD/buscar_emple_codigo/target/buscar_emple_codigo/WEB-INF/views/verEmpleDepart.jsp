<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="com.buscar_emple_codigo.model.Departamento, com.buscar_emple_codigo.model.Empleado" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/empleDepart.css">
            <title>Datos empleado y departamento</title>
        </head>

        <body>
            <%Empleado emple=(Empleado) request.getAttribute("empleado"); Departamento depart=(Departamento)
                request.getAttribute("departamento");%>
                <h1>EMPLEADO</h1>
                <table border="3" align="center">
                    <tr>
                        <th>Codigo</th>
                        <th>NIF</th>
                        <th>Nombre</th>
                        <th>Apellido 1</th>
                        <th>Apellido 2</th>
                        <th>Codigo departamento</th>
                    </tr>

                    <tr>
                        <% if (emple != null) { %>
                            <th><%=emple.getCodigo()%></th>
                        <% } else { %>
                            <th>No hay datos del empleado</th>
                        <% } %>
                        <th>
                            <%=emple.getNif()%>
                        </th>
                        <th>
                            <%=emple.getNombre()%>
                        </th>
                        <th>
                            <%=emple.getApellido1()%>
                        </th>
                        <th>
                            <%=emple.getApellido2()%>
                        </th>
                        <th>
                            <%=emple.getCodigo_departamento()%>
                        </th>
                    </tr>
                </table>

                <h1>DEPARTAMENTO</h1>
                <table border="3" align="center">
                    <tr>
                        <th>Codigo</th>
                        <th>Nombre</th>
                        <th>Presupuesto</th>
                        <th>Gastos</th>
                    </tr>

                    <tr>
                        <th>
                            <%=depart.getCodigo()%>
                        </th>
                        <th>
                            <%=depart.getNombre()%>
                        </th>
                        <th>
                            <%=depart.getPresupuesto()%>
                        </th>
                        <th>
                            <%=depart.getGastos()%>
                        </th>
                    </tr>
                </table>
        </body>

        </html>