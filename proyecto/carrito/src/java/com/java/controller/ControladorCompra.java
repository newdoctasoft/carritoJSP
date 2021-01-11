


package com.java.controller;

import com.java.modelDao.ClienteDao;
import com.java.modelDao.CompraDao;
import com.java.modelDao.DetalleCompraDao;
import com.java.modelDao.ProductoDao;
import com.java.modelDao.UsuarioDao;
import com.java.modelo.Cliente;
import com.java.modelo.Colecciones;
import com.java.modelo.Compra;
import com.java.modelo.DetalleCompra;
import com.java.modelo.Producto;
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

public class ControladorCompra extends HttpServlet {

    Producto producto = new Producto();
    ProductoDao productoDao = new ProductoDao();
    Compra compra = new Compra();
    CompraDao compraDao = new CompraDao();
    DetalleCompra detalleCompra = new DetalleCompra();
    DetalleCompraDao dtDao = new DetalleCompraDao();
    List<Compra> compras = new ArrayList<Compra>();
    List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
    Cliente cliente = new Cliente();
    ClienteDao clienteDao = new ClienteDao();
    Usuario usuario = new Usuario();
    UsuarioDao usuarioDao = new UsuarioDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session;
        switch (accion) {
            case "listar":
                compras = compraDao.listarCompraCliente(Colecciones.usuario.getId());
                request.setAttribute("compras", compras);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("compra/listar.jsp").forward(request, response);
                break;
            case "listarTodas":
                compras = compraDao.listar();
                request.setAttribute("compras", compras);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("compra/listar.jsp").forward(request, response);
                break;

            case "listarDetalles":
                int idCompra = Integer.parseInt(request.getParameter("id"));
                detalles = dtDao.listar(idCompra);

                producto = productoDao.buscar(idCompra);
                cliente = clienteDao.buscar(Colecciones.usuario.getId());

                double subtotal = compraDao.calcularSubtotalCompra(cliente.getId(), idCompra);
                request.setAttribute("subtotal", subtotal);
                request.setAttribute("cliente", cliente);
                request.setAttribute("producto", producto);
                request.setAttribute("detalles", detalles);
                request.getRequestDispatcher("compra/listarDetalle.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("ControladorCompra/listar.jsp").forward(request, response);
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
