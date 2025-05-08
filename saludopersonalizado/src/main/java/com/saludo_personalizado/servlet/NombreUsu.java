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
package com.saludo_personalizado.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "NombreUsu", value = "/NombreUsu")
public class NombreUsu extends HttpServlet {

    Set<String> setNombres = new HashSet<>();

    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");

        setNombres.add(nombre);

        // Establecemos los valores a los atributos de la siguiente pagina
        req.setAttribute("nombres", setNombres);
        req.setAttribute("nombre", nombre);

        // Mandamos la info a la siguiente pagina
        req.getRequestDispatcher("/WEB-INF/views/mostrarNombres.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
    }
}
