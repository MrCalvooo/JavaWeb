package es.damut12.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/opcionesL")
public class logicaOpciones extends HttpServlet {

    private final String url = "jdbc:sqlite:D:\\Usuarios\\calvo\\Desktop\\DAM\\JavaWeb\\ConexionesBBDD\\PracticaEvaluable\\Recursos\\tareas.db";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //con qué opción se llama a este servlet
        String opcion = request.getParameter("opcion");
        int id = Integer.parseInt(request.getParameter("id"));

        switch (opcion) {
            case "ver": {
                //lógica necesaria. Pensar en gestionar llamando a otros métodos para modularizar
                //envío al jsp de respuesta lo que quiera
                //llamo al jsp que quiero que se muestre

                opcionElegida(opcion, id);

                request.getRequestDispatcher("/WEB-INF/views/verTareas.jsp").forward(request, response);
                break;
            }

            case "insertar": {
                //recojo informacion de opciones.jsp
                //lógica relacionada con la inserción. Pensar en gestionar llamando a otros métodos para modularizar
                //envío al jsp de respuesta lo que quiera
                //llamo al jsp que quiero que se muestre
                break;
            }
            case "eliminar": {
                //recojo informacion de opciones.jsp
                //lógica relacionada con la eliminación. Pensar en gestionar llamando a otros métodos para modularizar
                //envío al jsp de respuesta lo que quiera
                //llamo al jsp que quiero que se muestre
                break;
            }
        }
    }

    // estructura métodos que modularizan las opciones
    public void opcionElegida(String opcion, int id) {

        try {
            Class.forName("org.sqlite.JDBC");

            // Intentamos conectar a la BBDD
            try (Connection connection = DriverManager.getConnection(url)) {
                System.out.println("Conectado a BBDD");

                switch (opcion) {
                    case "ver": {

                        try (PreparedStatement ps = connection.prepareStatement("select titulo, descripcion, completada, fecha_crecion from tareas where id = ?")) {
                            ps.setInt(1, id);

                            try (ResultSet rs = ps.executeQuery()) {

                            } catch (Exception e) {
                                // TODO: handle exception
                            }

                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        break;
                    }

                    default:
                        break;
                }
            } catch (Exception e) {

            }
        } catch (ClassNotFoundException e) {

        }
        //conexión String url ruta absoluta tareas.db

        //consulta o consultas que necesite realizar
        //conecto
        //completo consulta si lo necesito
        //ejecuto sentencia guardando en ResultSet o recibiendo en un int resultado que compruebe
    }
}
