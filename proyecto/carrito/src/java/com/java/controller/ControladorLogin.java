/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.controller;

import com.java.modelDao.UsuarioDao;
import com.java.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vfalcucci
 */
public class ControladorLogin extends HttpServlet {

    Usuario usuario;
    UsuarioDao usuarioDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        switch (accion) {
            case "formulario":
                request.getRequestDispatcher("login.jsp").forward(request, response);
            
            case "login":
                usuarioDao = new UsuarioDao();

                usuario = usuarioDao.buscarUsuario(email, password);
                String paginaDestino = "login.jsp";

                if (usuario != null) {
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
            case "logout":
                break;

            default: 
                request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
