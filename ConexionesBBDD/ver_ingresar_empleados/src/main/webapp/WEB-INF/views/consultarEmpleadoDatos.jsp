<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/consultarEmpleado.css">
        <title>Consultar empleado por sus datos</title>
    </head>

    <body>
        <h1>Datos del empleado</h1>

        <form action="ConsultarEmpleadoId" method="post">

            <label for="Nombre">Nombre empleado: </label>
            <input type="text" name="nombre" required>
            <br>

            <label for="Nombre">Primer apellido del empleado: </label>
            <input type="text" name="apellido1" required>
            <br>

            <label for="Nombre">Segundo apellido del empleado: </label>
            <input type="text" name="apellido2" required>
            <br>

            <button type="submit">Consultar</button>

        </form>
    </body>

    </html>