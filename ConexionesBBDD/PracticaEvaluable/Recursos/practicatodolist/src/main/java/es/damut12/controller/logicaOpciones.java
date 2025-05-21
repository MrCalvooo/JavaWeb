package es.damut12.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.damut12.model.Tarea;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/opcionesL")
public class logicaOpciones extends HttpServlet {

    private final String url = "jdbc:sqlite:D:\\Usuarios\\calvo\\Desktop\\DAM\\JavaWeb\\ConexionesBBDD\\PracticaEvaluable\\Recursos\\tareas.db";
    private List<Tarea> listaTareas = new ArrayList<>();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String opcion = request.getParameter("opcion");
        // Obtiene el id desde el parámetro 'id' o 'usuario'
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            idParam = request.getParameter("usuario");
        }
        int id = -1;
        if (idParam != null && !idParam.isEmpty()) {
            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                id = -1; // Valor por defecto si no es numérico
            }
        }

        switch (opcion) {
            case "ver": {
                //lógica necesaria. Pensar en gestionar llamando a otros métodos para modularizar
                //envío al jsp de respuesta lo que quiera
                //llamo al jsp que quiero que se muestre

                opcionElegida(opcion, id);
                request.setAttribute("listaTareas", listaTareas);
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

                        try (PreparedStatement ps = connection.prepareStatement("SELECT titulo, descripcion, completada, fecha_creacion, t.categoria_id, c.nombre FROM tareas t JOIN categorias c ON t.categoria_id = c.id WHERE t.usuario_id = ?")) {
                            ps.setInt(1, id);
                            try (ResultSet rs = ps.executeQuery()) {
                                while (rs.next()) {
                                    String titulo = rs.getString("titulo");
                                    String descripcion = rs.getString("descripcion");
                                    boolean completada = rs.getBoolean("completada");
                                    String fechaCreacion = rs.getString("fecha_creacion");
                                    String nombreCategoria = rs.getString("nombre");
                                    int idCategoria = rs.getInt("categoria_id");
                                    listaTareas.add(new Tarea(idCategoria, nombreCategoria, titulo, descripcion, completada, fechaCreacion));
                                }
                            } catch (SQLException e) {
                                System.out.println("No se realizo la consulta");
                            }
                        } catch (SQLException e) {
                            System.out.println("No se ha preparado la consulta");
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
