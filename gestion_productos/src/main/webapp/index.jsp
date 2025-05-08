<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Ingresar producto</title>
    </head>

    <body>
        <h1>Ingrese datos del producto</h1>

        <form action="GestorProductos" method="POST">

            <p>Nombre: <input type="text" name="nombre"></p>
            <p>Precio: <input type="text" name="precio"></p>
            <p>Categoria: <input type="text" name="categoria"></p>
            <p>Cantidad de producto: <input type="text" name="cantidad"></p>

            <button type="submit">Enviar producto</button>
        </form>
    </body>

    </html>