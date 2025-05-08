/*
 * Copyright 2020 Diego Silva <diego.silva at apuntesdejava.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.productos.servlet;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import com.productos.model.Producto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "gestorProductos", value = "/GestorProductos")
public class GestorProductos extends HttpServlet {

    private String message;
    Set<Producto> setProductos = new TreeSet<>();

    @Override
    public void init() {
        message = "Hello World from Servlet!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        double precio;
        String categoria = req.getParameter("categoria");
        int cantidad;

        try {
            precio = Double.parseDouble(req.getParameter("precio"));
            cantidad = Integer.parseInt(req.getParameter("cantidad"));
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Ingrese un número válido para precio y cantidad.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return; // Evita continuar con datos incorrectos
        }

        // Si el producto existe pero queremos agregar una nueva cantidad, usamos el iterador para ello
        boolean existe = false;

        for (Producto producto : setProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                producto.setCantidad(producto.getCantidad() + cantidad);
                existe = true;
                break;
            }
        }

        // Si no existe el producto se crea
        if (!existe) {
            setProductos.add(new Producto(nombre, precio, categoria, cantidad));
        }

        req.setAttribute("setProductos", setProductos);

        req.getRequestDispatcher("/WEB-INF/views/mostrarProductos.jsp").forward(req, resp);
    }
}
