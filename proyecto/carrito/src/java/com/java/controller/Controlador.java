package com.java.controller;

import com.java.config.Fecha;
import com.java.modelDao.ClienteDao;
import com.java.modelDao.CompraDao;
import com.java.modelDao.PagoDao;
import com.java.modelo.Carrito;
import com.java.modelo.Producto;
import com.java.modelDao.ProductoDao;
import com.java.modelo.Cliente;
import com.java.modelo.Colecciones;
import com.java.modelo.Compra;
import com.java.modelo.Pago;
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
public class Controlador extends HttpServlet {

    ProductoDao productoDao = new ProductoDao();
    List<Producto> productos = new ArrayList();
    Producto producto = new Producto();
    List<Carrito> listaCarrito = new ArrayList<Carrito>();
    int item;
    double totalPagar = 0.0;
    int cantidad = 1;
    int idProducto;
    Carrito carrito;
    Pago pago = new Pago();
    PagoDao pagoDao = new PagoDao();
    List<Pago> cuotas = new ArrayList<Pago>();
    ClienteDao clienteDao = new ClienteDao();

    Usuario usuario = Colecciones.getUsuario();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        productos = productoDao.listar();

        Producto producto = new Producto();
        switch (accion) {
            case "home":
                request.setAttribute("productos", productos);

                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "nuevo":

                request.getRequestDispatcher("producto/Formulario.jsp").forward(request, response);
                break;
            case "generarCompra":

                Cliente cliente = new Cliente();
                //   HttpSession session = request.getSession();  
                //     usuario= (Usuario) session.getAttribute("usuario"); 
                cliente = clienteDao.listarId(Colecciones.usuario.getId());

                CompraDao dao = new CompraDao();
                //Compra compra = new Compra(cliente, 19, Fecha.fechaBD(), totalPagar, "cancelado", listaCarrito);
                //insert into compras(idCliente,idPago,fechaCompras,monto,estado) values (12,19,now(),100,'ACTIVO');
                Compra compra = new Compra();
                compra.setCliente(cliente);
                //compra.setIdpago(1);
                compra.setFecha(Fecha.fechaBD());
                compra.setMonto(totalPagar);
                compra.setEstado("ACTIVO");
                compra.setDetalleCompras(listaCarrito);

                int rs = dao.GenerarCompra(compra);
                if (rs != 0 && totalPagar > 0) {
                    request.getRequestDispatcher("vistas/mensaje.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("vistas/error.jsp").forward(request, response);
                }

                break;
            case "descontar":
                int porcentaje = Integer.parseInt(request.getParameter("porcentaje"));

                double nuevo = totalPagar - ((porcentaje * totalPagar) / 100);
                request.setAttribute("totalPagar", nuevo);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());

                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "actualizarCantidad":
                int cantidadNueva = Integer.parseInt(request.getParameter("cantidad"));
                idProducto = Integer.parseInt(request.getParameter("idProducto"));
                for (int i = 0; i < listaCarrito.size(); i++) {

                    if (listaCarrito.get(i).getIdProducto() == idProducto) {
                        cantidad = cantidadNueva;
                        listaCarrito.get(i).setCantidad(cantidad);

                    }
                }

                totalPagar = 0.0;
                for (int i = 0; i < listaCarrito.size(); i++) {
                    double total = listaCarrito.get(i).getCantidad() * listaCarrito.get(i).getPrecioCompra();
                    totalPagar = totalPagar + total;
                }
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);
                request.setAttribute("contador", listaCarrito.size());

                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;

            case "AgregarCarrito":
                idProducto = Integer.parseInt(request.getParameter("id"));
                boolean repetido = false;

                for (int i = 0; i < listaCarrito.size(); i++) {

                    if (listaCarrito.get(i).getIdProducto() == idProducto) {
                        repetido = true;
                    }
                }

                if (repetido) {
                    for (int i = 0; i < listaCarrito.size(); i++) {

                        if (listaCarrito.get(i).getIdProducto() == idProducto) {
                            cantidad = cantidad + 1;
                            listaCarrito.get(i).setCantidad(cantidad);

                        }
                    }
                } else {
                    cantidad = 1;
                    producto = productoDao.listarId(idProducto);
                    item = item + 1;
                    carrito = new Carrito();
                    carrito.setItem(item);
                    carrito.setIdProducto(producto.getId());
                    carrito.setNombre(producto.getNombre());
                    carrito.setDescripcion(producto.getDescripcion());
                    carrito.setPrecioCompra(producto.getPrecio());
                    carrito.setCantidad(cantidad);
                    carrito.setSubtotal(cantidad * producto.getPrecio());

                    listaCarrito.add(carrito);
                }

                request.setAttribute("contador", listaCarrito.size());

                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;

            case "Comprar":
                idProducto = Integer.parseInt(request.getParameter("id"));
                producto = productoDao.listarId(idProducto);
                item = item + 1;
                carrito = new Carrito();
                carrito.setItem(item);
                carrito.setIdProducto(producto.getId());
                carrito.setNombre(producto.getNombre());
                carrito.setDescripcion(producto.getDescripcion());
                carrito.setPrecioCompra(producto.getPrecio());
                carrito.setCantidad(cantidad);
                carrito.setSubtotal(cantidad * producto.getPrecio());
                listaCarrito.add(carrito);
                request.setAttribute("carrito", listaCarrito);

                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubtotal();
                }
                cuotas = pagoDao.listar();
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("contador", listaCarrito.size());

                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
            case "carrito":
                totalPagar = 0.0;
                request.setAttribute("carrito", listaCarrito);

                for (int i = 0; i < listaCarrito.size(); i++) {
                    double total = listaCarrito.get(i).getCantidad() * listaCarrito.get(i).getPrecioCompra();
                    totalPagar = totalPagar + total;
                }
                cuotas = pagoDao.listar();
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("totalPagar", totalPagar);

                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;

            case "delete":
                totalPagar = 0.0;
                idProducto = Integer.parseInt(request.getParameter("id"));
                for (int i = 0; i < listaCarrito.size(); i++) {
                    if (listaCarrito.get(i).getIdProducto() == idProducto) {
                        listaCarrito.remove(i);
                    }
                }

                for (int i = 0; i < listaCarrito.size(); i++) {
                    double total = listaCarrito.get(i).getCantidad() * listaCarrito.get(i).getPrecioCompra();
                    totalPagar = totalPagar + total;
                }

                cuotas = pagoDao.listar();

                request.setAttribute("totalPagar", totalPagar);
                request.setAttribute("carrito", listaCarrito);

                request.getRequestDispatcher("carrito.jsp").forward(request, response);
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
                System.out.println("HOLLAA");
                productoDao.insertar(producto);

                request.getRequestDispatcher("Controlador?accion=home").forward(request, response);
                break;
           
            default:
                request.setAttribute("productos", productos);

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
