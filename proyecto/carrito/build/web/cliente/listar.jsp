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


            <div class="col-md-10">
                <table   class="table table-striped">
                    <thead>
                        <tr>
                            <th>CODIGO</th>
                            <th>NOMBRE</th>
                            <th>DNI</th>
                            <th>DIRECCION</th>
                            <th>EMAIL</th> 
                            <th>ACCIONES</th>  
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="c" items="${clientes}"> 
                            <tr> 
                                <td><c:out value = "${c.getId()}"/></td>  
                                <td><c:out value = "${c.getNombre()}"/></td> 
                                <td><c:out value = "${c.getDni()}"/></td> 
                                <td><c:out value = "${c.getDireccion()}"/></td>
                                <td><c:out value = "${c.getCorreo()}"/></td>

                                <td>      <a href="ControladorCliente?accion=buscar&tipo=editar&id=<c:out value ='${c.getId()}'/>"   class="btn btn-info" ><img src="static/actualizar.jpg" width="20" height="20"/></a>
                                    <a href="ControladorCliente?accion=delete&id=<c:out value ='${c.getId()}'/>"   class="btn btn-danger" ><img src="static/delete.jpg" width="20" height="20"/></a>
                                    <a href="ControladorCliente?accion=buscar&tipo=ver&id=<c:out value ='${c.getId()}'/>"   class="btn btn-success"><img src="static/ver.jpg" width="20" height="20"/></a>
                                </td>
                            </tr> 


                        </c:forEach>
                    </tbody>
                </table>







            </div> 

        </div>




    </body>
</html>
