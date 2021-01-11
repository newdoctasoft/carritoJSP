<%-- 
    Document   : index
    Created on : 12-oct-2020, 9:41:25
    Author     : Valentino
--%>

<%@page import="com.java.modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>

<html>
    <head>
        <title>Bootstrap Example</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
   <style>
            body {
                background: 	#778899;
            } 
        </style>
    </head>
    <body>

        <%
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if (usuario == null) {
        %>            
        <%@include file="usuario/login.jsp"%>

        <%
        } else {
            if (usuario.getTipo().equals("administrador")) {
        %>
        <%@include file="login/administrador.jsp"%>

        <%
        } else if (usuario.getTipo().equals("usuario")) {
        %>
        <%@include file="login/usuario.jsp"%>
        <% } else {
        %>
        <%@include file="usuario/login.jsp"%>
        <%}
            }
        %>


        <div class="container mt-4 ">



            <div class="row">

                <c:forEach var="p" items="${productos}">
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header">
                                <label> <c:out value = "${p.getNombre()}"/></label>    
                            </div>
                            <div class="card-body">
                                <label><c:out value = "${p.getPrecio()}"/></label>
                                <img src="ControladorIMG?id=<c:out value ='${p.getId()}'/>" width="300" height="300" />
                            </div>
                            <div class="card-footer text-center">
                                <label><c:out value = "${p.getDescripcion()}"/></label>
                                <div>
                                    <a href="Controlador?accion=AgregarCarrito&id=<c:out value ='${p.getId()}'/>"   class="btn btn-outline-info">Agregar a Carrito</a>
                                    <a href="Controlador?accion=Comprar&id=<c:out value ='${p.getId()}'/>"  class="btn btn-danger">Comprar</a>
                                </div>
                            </div>

                        </div>
                    </div>
                </c:forEach>  
            </div> 
        </div>
 
    </body>
</html>
