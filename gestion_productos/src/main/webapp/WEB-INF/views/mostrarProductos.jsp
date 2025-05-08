<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ page import="java.util.TreeSet, java.util.Set, com.productos.model.Producto" %>
        <!DOCTYPE html>
        <html lang="es">

        <head>
            <meta charset="UTF-8">
            <title>Listado de productos</title>
        </head>

        <body>
            <% Set<Producto> productos = (Set<Producto>) request.getAttribute("setProductos"); %>
                    <% if (productos !=null && !productos.isEmpty()) { %>
                        <% for (Producto producto : productos) { %>
                            <p>Nombre: <%= producto.getNombre() %>
                            </p>
                            <p>Categoría: <%= producto.getCategoria() %>
                            </p>
                            <p>Precio: $<%= producto.getPrecio() %>
                            </p>
                            <p>Cantidad disponible: <%= producto.getCantidad() %>
                            </p>
                            <hr>
                            <% } %>
                                <% } else { %>
                                    <p>No hay productos registrados.</p>
                                    <% } %>
                                        <a href="GestorProductos">Volver al menú de ingreso</a>
        </body>

        </html>