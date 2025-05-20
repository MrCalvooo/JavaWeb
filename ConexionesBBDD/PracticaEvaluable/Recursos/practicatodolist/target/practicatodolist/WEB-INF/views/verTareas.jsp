<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="es.damut12.model.Tarea, java.util.List, java.util.Map, java.util.HashMap, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tareas del Usuario</title>
    <link rel="stylesheet" type="text/css" href="css/estilos.css">
</head>
<body>

        <h2><!--Enlace para volver a opciones--></a></h2>
     

        <div class="container">

            <h2 class="categoria-titulo"><!--nombre categoria--> : <!--identificador categoria--></h2>
            <table>
                <thead>
                    <tr>
                        <th>Título</th>
                        <th>Descripción</th>
                        <th>Estado</th>
                        <th>Fecha de creación</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <tr>
                        <td><!--obtener el título--></td>
                        <td><!--obtener la descripción--></td>
                        <td><span class=<!--mismo valor que el del estado-->><!--obtener el estado--></span></td>
                        <td><!--obtener fecha de creación--></td>
                    </tr>
                <%
                    } // fin for tareas de la categoría
                %>
                </tbody>
            </table>
        </div>
    <%
        } // fin for categorías
    %>
</body>
</html>
