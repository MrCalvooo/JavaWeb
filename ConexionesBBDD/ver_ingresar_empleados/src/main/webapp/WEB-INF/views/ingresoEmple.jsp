<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
        <title>Ingresar empleado</title>
    </head>

    <body>
        <h1>DATOS DEL EMPLEADO: </h1>

        <form action="IngresarEmpleado" method="post">
            <label for="nif">NIF: </label>
            <input type="text" name="nif" requiered>

            <label for="nombre">Nombre: </label>
            <input type="text" name="nombre" requiered>

            <label for="nombre">Primer apellido: </label>
            <input type="text" name="apellido1" requiered>

            <label for="nombre">Segundo apellido: </label>
            <input type="text" name="apellido2" requiered>

            <label for="nombre">Nombre del departamento: </label>
            <input type="text" name="departamento" requiered>

            <button type="submit">Enviar</button>
        </form>
    </body>

    </html>