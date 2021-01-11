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
        <%@include file="../usuario/login.jsp"%>

        <%
        } else {
            if (usuario.getTipo().equals("administrador")) {
        %>
        <%@include file="../login/administrador.jsp"%>

        <%
        } else if (usuario.getTipo().equals("usuario")) {
        %>
        <%@include file="../login/usuario.jsp"%>
        <% } else {
        %>
       <%@include file="../usuario/login.jsp"%>
        <%}
            }
        %>




        <div class="container mt-4">
            <div class="col-sm-4">
                <div class="card"> 

                    <div class="card-header">
                        <label>NOMBRE</label>
                        <h3> <input type="text" value="${cliente.getNombre()}" class="form-control" readOnly=""/></h3>
                        <label>DNI</label>
                        <h3> <input type="text" value="${cliente.getDni()}" class="form-control" readOnly=""/></h3>
                    </div>

                    <div class="card-body">
                        <label>DIRECCION</label>
                        <input type="text" value="${cliente.getDireccion()}" class="form-control" readOnly=""/>
                        <label>EMAIL</label>
                        <input type="text" value="${cliente.getCorreo()}" class="form-control" readOnly=""/>
                        <label>PASSWORD</label>
                        <input type="password" value="${cliente.getPassword()}" class="form-control" readOnly=""/> 
                    </div>

                    <div class="card-footer">  
                        <a href="ControladorCliente?accion=home" class="btn btn-info" >VOLVER </a> 
                    </div>


                </div> 
            </div>
        </div>





    </body>
</html>
