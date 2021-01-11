<%-- 
    Document   : Formulario
    Created on : 02-ene-2021, 19:33:41
    Author     : Valentino
--%>

<%@page import="com.java.modelo.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
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

        <div class="container">  
            <div class="col-md-4">

                <form action="ControladorCategoria" method="POST" >  
                    <div class="form-group">
                        <label for="nombre">NOMBRE</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" placeholder="INGRESA EL NOMBRE"  >
                    </div> 
                    <br/>
                    <br/>  
                    <button type="submit" class="btn btn-primary" name="accion"  value="guardar">ENVIAR</button>
                </form>

            </div>

        </div>

    </body>
</html>

