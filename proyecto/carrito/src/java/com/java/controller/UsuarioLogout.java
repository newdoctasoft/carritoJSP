package com.java.controller;

import com.java.modelo.Colecciones;
import com.java.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioLogout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = "logout";
        switch (accion) {

            case "logout":
                HttpSession  session = request.getSession(false);
                session = request.getSession(false);
               Usuario usuario = (Usuario) session.getAttribute("usuario");
                if (usuario != null) {
                    Colecciones.setUsuario(null);
                    
                    session.removeAttribute("usuario"); 
                    request.getRequestDispatcher("usuario/login.jsp").forward(request, response);

                }
                break;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
