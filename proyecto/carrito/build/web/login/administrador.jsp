<%-- 
    Document   : administrador
    Created on : 08/01/2021, 15:53:59
    Author     : vfalcucci
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>



        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin:24px 0;">
            <a class="navbar-brand" href="javascript:void(0)">PROYECTO CARRITO</a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navb">
                <ul class="navbar-nav mr-auto">
 
                    <li class="nav-item">
                        <div class="dropdown">
                            <button   class="btn btn-primary bg-dark" data-toggle="dropdown">
                                CLIENTE
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item bg-dark text-white" href="ControladorCliente?accion=nuevo">ALTA</a>
                                <a class="dropdown-item bg-dark text-white" href="ControladorCliente?accion=listar">LISTAR</a> 
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown">
                            <button   class="btn btn-primary bg-dark" data-toggle="dropdown">
                                CATEGORIAS
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item bg-dark text-white" href="ControladorCategoria?accion=nuevo">ALTA</a>
                                <a class="dropdown-item bg-dark text-white" href="ControladorCategoria?accion=listar">LISTAR</a> 
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <div class="dropdown">
                            <button   class="btn btn-primary bg-dark" data-toggle="dropdown">
                                PRODUCTOS
                            </button>
                            <div class="dropdown-menu">
                                <a class="dropdown-item bg-dark text-white" href="ControladorProducto?accion=nuevo">ALTA</a>
                                <a class="dropdown-item bg-dark text-white" href="ControladorProducto?accion=listar">LISTAR</a>
                                <a class="dropdown-item bg-dark text-white" href="ControladorProducto?accion=home">GALERIA</a>
                                <a class="dropdown-item bg-dark text-white" href="ControladorProducto?accion=listarVenta">VENTA</a>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="ControladorCompra?accion=listarTodas"><span>TODAS LAS FACTURAS</span></a>
                    </li>  
                </ul> 
            </div>


            <div class="dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    INICIAR SESION
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">${usuario.getNombre()}</a>
                    <div class="text-center">
                        <img src="static/login.jpg" width="60%" height="60%"/>
                    </div>
                     
                    <a class="dropdown-item" href="UsuarioLogout?accion=logout">LOGOUT</a>

                </div>
            </div> 
        </nav> 


    </body>
</html>
