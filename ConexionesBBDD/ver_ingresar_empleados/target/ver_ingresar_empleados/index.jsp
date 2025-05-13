<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Menu de inicio</title>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
    </head>

    <body>
        <h1>OPCIONES</h1>

        <form action="Direccionamiento">
            <input type="submit" name="opcion" value="1">Ingresar Empleado</input>
            <input type="submit" name="opcion" value="2">Modificar Empleado</input>
            <input type="submit" name="opcion" value="3">Eliminar Empleado</input>
            <input type="submit" name="opcion" value="4">Consultar Empleado</input>
            <input type="submit" name="opcion" value="5">Consultar Todos los Empleados</input>
        </form>
    </body>

    </html>