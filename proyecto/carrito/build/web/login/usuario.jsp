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
                <li class="nav-item">
                        <div class="dropdown">
                            <button   class="btn btn-primary bg-dark" data-toggle="dropdown">
                                PRODUCTOS
                            </button>
                            <div class="dropdown-menu"> 
                                <a class="dropdown-item bg-dark text-white" href="ControladorProducto?accion=home">GALERIA</a>
                                
                            </div>
                        </div>
                    </li>
                    
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="Controlador?accion=listar">COMPRAR</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="Controlador?accion=carrito"><i class="fas fa-cart-plus">(<label style="color:orange">${contador}</label>)</i>CARRITO</a>
                    </li>
         <li class="nav-item">
                        <a class="nav-link" href="ControladorCompra?accion=listar"><span>FACTURAS</span></a>
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
                    <a class="dropdown-item" href="ControladorCliente?accion=buscar&tipo=ver&id=${usuario.getId()}">PERFIL</a>
                    <a class="dropdown-item" href="UsuarioLogout?accion=logout">LOGOUT</a>

                </div>
            </div> 
        </nav> 


    </body>
</html>
