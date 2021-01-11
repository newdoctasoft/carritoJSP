/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.controller;

import com.java.modelDao.ClienteDao;
import com.java.modelDao.ProductoDao;
import com.java.modelDao.UsuarioDao;
import com.java.modelo.Cliente;
import com.java.modelo.Colecciones;
import com.java.modelo.Producto;
import com.java.modelo.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class ControladorCliente extends HttpServlet {

    ClienteDao clienteDao = new ClienteDao();
    List<Cliente> clientes = new ArrayList();
    Cliente cliente = new Cliente();
    int idCliente;
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session;
        String accion = request.getParameter("accion");

        switch (accion) {
            case "home":
                clientes = clienteDao.listar();
                request.setAttribute("clientes", clientes);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("cliente/listar.jsp").forward(request, response);
                break;
            case "nuevo":

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("cliente/Formulario.jsp").forward(request, response);
                break;
            case "guardar":

                String nombre = request.getParameter("nombre");
                String dni = request.getParameter("dni");
                String direccion = request.getParameter("direccion");
                String correo = request.getParameter("correo");
                String clave = request.getParameter("clave");

                cliente.setNombre(nombre);
                cliente.setDni(dni);
                cliente.setDireccion(direccion);
                cliente.setCorreo(correo);
                cliente.setPassword(clave);
                cliente.setEstado(1);
                clienteDao.insertar(cliente);
                idCliente = clienteDao.buscarUltimoCliente();
                usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setEmail(correo);
                usuario.setPassword(clave);
                usuario.setEstado(1);
                usuario.setTipo("usuario");
                usuario.setId(idCliente);

                usuarioDao.insertar(usuario);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("ControladorCliente?accion=home").forward(request, response);
                break;

            case "editar":
                idCliente = Integer.parseInt(request.getParameter("id"));
                nombre = request.getParameter("nombre");
                dni = request.getParameter("dni");
                direccion = request.getParameter("direccion");
                correo = request.getParameter("correo");
                clave = request.getParameter("clave");

                cliente.setNombre(nombre);
                cliente.setDni(dni);
                cliente.setDireccion(direccion);
                cliente.setCorreo(correo);
                cliente.setPassword(clave);
                clienteDao.editar(cliente);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

            case "listar":
                clientes = clienteDao.listar();
                request.setAttribute("clientes", clientes);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("cliente/listar.jsp").forward(request, response);
                break;
            case "delete":
                idCliente = Integer.parseInt(request.getParameter("id"));
                clienteDao.eliminar(idCliente);
                usuario = usuarioDao.buscarUsuario(idCliente);
                usuarioDao.eliminar(usuario);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("ControladorCliente?accion=home").forward(request, response);
                break;
            case "buscar":
                idCliente = Integer.parseInt(request.getParameter("id"));
                String tipo = request.getParameter("tipo");
                cliente = clienteDao.buscar(idCliente);
                request.setAttribute("cliente", cliente);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                switch (tipo) {
                    case "ver":
                        request.getRequestDispatcher("cliente/ver.jsp").forward(request, response);
                    case "editar":
                        request.getRequestDispatcher("cliente/editar.jsp").forward(request, response);

                }
                break;
            default:
                clientes = clienteDao.listar();
                request.setAttribute("clientes", clientes);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("cliente/listar.jsp").forward(request, response);

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
