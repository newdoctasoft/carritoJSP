package com.java.controller;

import com.java.modelDao.CategoriaDao;
import com.java.modelo.Categoria;
import com.java.modelo.Colecciones;
import com.java.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControladorCategoria extends HttpServlet {

    Categoria categoria = new Categoria();
    CategoriaDao categoriaDao = new CategoriaDao();
    List<Categoria> categorias = new ArrayList<Categoria>();
    int idCategoria;
    Usuario usuario = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion"); 
        HttpSession session;

        switch (accion) {
            case "home":
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("categoria/listar.jsp").forward(request, response);
                break;
            case "nuevo":
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("categoria/Formulario.jsp").forward(request, response);
                break;
            case "guardar":
                String nombre = request.getParameter("nombre");
                categoria.setNombre(nombre);
                categoria.setEstado(1);
                categoriaDao.insertar(categoria);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("ControladorCategoria?accion=home").forward(request, response);
                break;
            case "editar":
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

                nombre = request.getParameter("nombre");
                categoria.setNombre(nombre);
                categoria.setEstado(1);
                categoriaDao.editar(categoria);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("ControladorCategoria?accion=home").forward(request, response);
                break;
            case "listar":
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("categoria/listar.jsp").forward(request, response);

                break;
            case "delete":
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                categoriaDao.eliminar(idCategoria);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                request.getRequestDispatcher("ControladorCategoria?accion=home").forward(request, response);
                break;
            case "buscar":
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
                String tipo = request.getParameter("tipo");
                categoria = categoriaDao.buscarId(idCategoria);
                request.setAttribute("categoria", categoria);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                

                switch (tipo) {
                    case "ver":
                        request.getRequestDispatcher("categoria/ver.jsp").forward(request, response);
                    case "editar":
                        request.getRequestDispatcher("categoria/editar.jsp").forward(request, response);

                }
                break;
            default:
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                
                request.getRequestDispatcher("categoria/listar.jsp").forward(request, response);

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
    }// </editor-fold>

}
