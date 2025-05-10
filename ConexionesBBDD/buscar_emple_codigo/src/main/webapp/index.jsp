<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>BUSQUEDA</title>
        <link rel="stylesheet" type="text/css" href="css/index.css">
    </head>

    <body>
        <h1>BUSCAR EMPLEADO Y DEPARTAMENTO</h1>

        <form action="Busqueda" method="post">
            <label>Codigo empleado: <input type="text" name="cod_emple"></label>
            <label>Codigo departamento <input type="text" name="cod_depart"></label>

            <button type="submit">Buscar</button>
        </form>
    </body>

    </html>