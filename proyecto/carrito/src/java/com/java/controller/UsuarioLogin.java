package com.java.controller;

import com.java.modelDao.UsuarioDao;
import com.java.modelo.Colecciones;
import com.java.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
  
 
        switch (accion) {
            case "loguearse":

                request.getRequestDispatcher("usuario/login.jsp").forward(request, response);
                break;
            case "login":

                String email = request.getParameter("email");
                String password = request.getParameter("password");

                UsuarioDao usuarioDao = new UsuarioDao();
                Usuario usuario = usuarioDao.buscarUsuario(email, password);
              
                String paginaDestino = "usuario/login.jsp";

                if (usuario != null) {
                    Colecciones.usuario = usuario;
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                     
                    paginaDestino = "index.jsp";
                } else {
                    String message = "EMAIL O PASSWORD IVALIDO";
                    request.setAttribute("message", message);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
                dispatcher.forward(request, response);
                break;
           default: 
                request.getRequestDispatcher("index.jsp").forward(request, response);

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
