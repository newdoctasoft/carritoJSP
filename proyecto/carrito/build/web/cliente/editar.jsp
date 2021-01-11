<%-- 
    Document   : Formulario
    Created on : 02-ene-2021, 19:33:41
    Author     : Valentino
--%>

<%@page import="com.java.modelo.Usuario"%>
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
            <div class="col-md-6">

                <form action="ControladorCliente" method="POST" >  
                    <div class="form-group">
                        <input type="hidden" value="${cliente.getId()}" name="id" class="form-control"  />
                         <label>NOMBRE</label>
                        <h3> <input type="text" value="${cliente.getNombre()}" name="nombre" class="form-control"  /></h3>
                    </div>
                    <div class="form-group">
                        <label for="dni">DNI</label>
                        <input type="text" class="form-control" id="descripcion" name="dni" value="${cliente.getDni()}"  >
                    </div>
                    <div class="form-group">
                        <label for="direccion">DIRECCION</label>
                        <input type="text" class="form-control" id="direccion" value="${cliente.getDireccion()}" name="direccion">
                    </div>

                    <div class="form-group">
                        <label for="correo">EMAIL</label>
                        <input type="text" class="form-control" id="correo" value="${cliente.getCorreo()}" name="correo">
                    </div>

                    <div class="form-group">
                        <label for="clave">CLAVE</label>
                        <input type="password" class="form-control" id="clave" value="${cliente.getPassword()}" name="clave">
                    </div>

                    <br/>

                    <button type="submit" class="btn btn-primary" name="accion"  value="editar">ENVIAR</button>
                </form>

            </div>

        </div>

    </body>
</html>

