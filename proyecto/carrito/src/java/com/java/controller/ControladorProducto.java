package com.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.config.Fecha;
import com.java.modelDao.CategoriaDao;
import com.java.modelDao.ClienteDao;
import com.java.modelDao.CompraDao;
import com.java.modelDao.DetalleCompraDao;
import com.java.modelo.Carrito;
import com.java.modelo.Producto;
import com.java.modelDao.ProductoDao;
import com.java.modelo.Categoria;
import com.java.modelo.Cliente;
import com.java.modelo.Colecciones;
import com.java.modelo.Compra;
import com.java.modelo.DetalleCompra;
import com.java.modelo.Usuario;
import com.mysql.jdbc.StringUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig
public class ControladorProducto extends HttpServlet {

    ProductoDao productoDao = new ProductoDao();
    List<Producto> productos = new ArrayList();
    Producto producto = new Producto();
    int idProducto;

    Categoria categoria = new Categoria();
    CategoriaDao categoriaDao = new CategoriaDao();
    List<Categoria> categorias = new ArrayList<Categoria>();
    Compra compra = new Compra();
    CompraDao compraDao = new CompraDao();
    List<Compra> compras = new ArrayList();
    DetalleCompra detalleCompra = new DetalleCompra();
    DetalleCompraDao dtDao = new DetalleCompraDao();

    List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
    Cliente cliente = new Cliente();
    ClienteDao clienteDao = new ClienteDao();
    Usuario usuario = new Usuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        Producto producto = new Producto();
        HttpSession session;

        switch (accion) {
            case "home":
                productos = productoDao.listar();
                request.setAttribute("productos", productos);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/index.jsp").forward(request, response);
                break;
            case "nuevo":

                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/Formulario.jsp").forward(request, response);
                break;
            case "guardar":
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                Double precio = Double.parseDouble(request.getParameter("precio"));
                int stock = Integer.parseInt(request.getParameter("stock"));
                Part part = request.getPart("foto");
                InputStream inputStream = part.getInputStream();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setFoto(inputStream);
                producto.setPrecio(precio);
                producto.setStock(stock);
                int idCategoria = Integer.parseInt(request.getParameter("categoria"));
                producto.setIdCategoria(idCategoria);
                System.out.println("HOLLAA");
                productoDao.insertar(producto);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("ControladorProducto?accion=home").forward(request, response);
            case "editar":
                idProducto = Integer.parseInt(request.getParameter("id"));
                nombre = request.getParameter("nombre");
                descripcion = request.getParameter("descripcion");
                precio = Double.parseDouble(request.getParameter("precio"));
                stock = Integer.parseInt(request.getParameter("stock"));
                part = request.getPart("foto");
                inputStream = part.getInputStream();
                producto.setId(idProducto);
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setFoto(inputStream);
                producto.setPrecio(precio);
                producto.setStock(stock);
                idCategoria = Integer.parseInt(request.getParameter("categoria"));
                producto.setIdCategoria(idCategoria);
                productoDao.editar(producto);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("ControladorProducto?accion=listar").forward(request, response);
            case "listar":
                productos = productoDao.listar();
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);
                request.setAttribute("productos", productos);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/listar.jsp").forward(request, response);
                break;
            case "delete":
                idProducto = Integer.parseInt(request.getParameter("id"));
                productoDao.eliminar(idProducto);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("ControladorProducto?accion=listar").forward(request, response);
                break;
            case "buscar":
                idProducto = Integer.parseInt(request.getParameter("id"));
                String tipo = request.getParameter("tipo");
                producto = productoDao.buscar(idProducto);
                request.setAttribute("producto", producto);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                switch (tipo) {
                    case "ver":
                        producto=productoDao.buscar(idProducto);
                        request.getRequestDispatcher("producto/ver.jsp").forward(request, response);
                        break;
                    case "editar":
                        List<Categoria> listado = categoriaDao.listar();
                        request.setAttribute("categorias", listado);
                        request.getRequestDispatcher("producto/editar.jsp").forward(request, response);
                           break;
                            
                }
                break;
            case "buscarProducto":
                nombre = request.getParameter("nombre");
                productos = productoDao.buscarProducto(nombre);
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);
                request.setAttribute("productos", productos);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/listar.jsp").forward(request, response);
            case "buscarPrecio":
                int desde = Integer.parseInt(request.getParameter("desde"));
                int hasta = Integer.parseInt(request.getParameter("hasta"));
                productos = productoDao.buscarProductoPrecio(desde, hasta);
                categorias = categoriaDao.listar();
                request.setAttribute("categorias", categorias);
                request.setAttribute("productos", productos);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/listar.jsp").forward(request, response);

                break;
            case "buscarCategoria":
                idCategoria = Integer.parseInt(request.getParameter("idCategoria"));

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                if (idCategoria == 0) {
                    request.getRequestDispatcher("ControladorProducto?accion=listar").forward(request, response);

                } else {
                    productos = productoDao.buscarProductosCategoria(idCategoria);
                    categorias = categoriaDao.listar();
                    request.setAttribute("categorias", categorias);
                    request.setAttribute("productos", productos);
                    request.getRequestDispatcher("producto/listar.jsp").forward(request, response);
                }

                break;
            case "listarVenta":
                productos = productoDao.listar();
                request.setAttribute("productos", productos);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/listarVenta.jsp").forward(request, response);

                break;
            case "venta":
                idProducto = Integer.parseInt(request.getParameter("id"));
                compras = compraDao.listarCompraProducto(idProducto);
                producto = productoDao.buscar(idProducto);
                productos = productoDao.listar();
                request.setAttribute("productos", productos);
                request.setAttribute("compras", compras);

                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);

                request.getRequestDispatcher("producto/venta.jsp").forward(request, response);

                break;
            case "ventaDetalle":
                int idCompra = Integer.parseInt(request.getParameter("id"));
                compra = compraDao.buscarCompraId(idCompra);
                detalles = dtDao.listar(idCompra);

                cliente = clienteDao.buscar(compra.getCliente().getId());

                double subtotal = compraDao.calcularSubtotalCompra(cliente.getId(), idCompra);

                request.setAttribute("cliente", cliente);
                request.setAttribute("detalles", detalles);
                request.setAttribute("subtotal", subtotal);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                
                request.getRequestDispatcher("producto/ventaDetalle.jsp").forward(request, response);
                break;
            default:
                productos = productoDao.listar();
                request.setAttribute("productos", productos);
                
                usuario = Colecciones.getUsuario();
                session = request.getSession(false);
                session.setAttribute("usuario", usuario);
                
                
                request.getRequestDispatcher("producto/listar.jsp").forward(request, response);

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
