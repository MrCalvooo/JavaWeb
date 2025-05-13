<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/mensaje.css">
        <title>Mensaje</title>
    </head>

    <body>
        <%String mensaje=(String) request.getAttribute("mensaje"); %>
            <h1>
                <%= mensaje %>
            </h1>

            <a href="<%= request.getContextPath() %>/index.jsp">Volver al menu</a>
    </body>

    </html>