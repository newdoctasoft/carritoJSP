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
            <div class="row">
                <div class="col-md-3">


                    <div class="container">

                        <div class="form-group">
                            <div class="card">
                                <div class="card-header">
                                    <h4>NOMBRE</h4><br/>
                                </div>  
                                <div class="card-body">
                                    <form  action="ControladorProducto" class="form-inline my-2 my-lg-0" method="post">
                                        <input name="nombre" class="form-control mr-sm-2" type="text" placeholder="BUSCAR"><br/>
                                        <button name="accion" value="buscarProducto"  class="btn btn-success my-2 my-sm-0" type="submit">BUSCAR</button>
                                    </form>
                                </div> 
                            </div>

                            <br/>
                            <div class="card">
                                <div class="card-header">
                                    <h4>PRECIO</h4> 
                                </div>
                                <div class="card-body">
                                    <form class="form-inline" action="ControladorProducto" method="post">
                                        <div class="form-group">
                                            <label for="desde" class="col-md-3">DESDE</label>
                                            <input type="text" name="desde" class="form-control col-md-3"  >
                                        </div>
                                        <div class="form-group">
                                            <label for="hasta" class="col-md-3">HASTA</label>
                                            <input type="text" name="hasta" class="form-control col-md-3"  >
                                        </div>
                                        <button name="accion" value="buscarPrecio"  class="btn btn-success my-2 my-sm-0" type="submit">BUSCAR</button>
                                    </form>
                                </div>
                            </div>


                            <br/>
                            <h4>CATEGORIA</h4>
                            <div class="list-group" name="categoria"  >
                                <a href="ControladorProducto?accion=buscarCategoria&idCategoria=0" class="list-group-item active btn btn-primary">LISTADO COMPLETO</a>  

                                <c:forEach var="categoria" items="${categorias}"> 
                                    <a href="ControladorProducto?accion=buscarCategoria&idCategoria=${categoria.getIdCategoria()}" class="list-group-item active btn btn-info">${categoria.getNombre()}</a>  
                                </c:forEach>  
                            </div>
                        </div> 

                    </div>
                </div> 

                <div class="col-md-9">
                    <table   class="table table-striped">
                        <thead>
                            <tr>
                                <th>CODIGO</th>
                                <th>NOMBRE</th>
                                <th>FOTO</th>
                                <th>DESCRIPCION</th>
                                <th>CATEGORIA</th>
                                <th>PRECIO</th>
                                <th>STOCK</th> 
                                <th>ACCIONES</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${productos}"> 
                                <tr> 
                                    <td><c:out value = "${p.getId()}"/></td> 


                                    <td><c:out value = "${p.getNombre()}"/></td>
                                    <td> <img src="ControladorIMG?id=<c:out value ='${p.getId()}'/>" width="100" height="100" /></td>
                                    <td><c:out value = "${p.getDescripcion()}"/></td>
                                    <td><c:out value = "${p.getCategoria().getNombre()}"/></td>
                                    <td><c:out value = "${p.getPrecio()}"/></td>
                                    <td><c:out value = "${p.getStock()}"/></td>

                                    <td>      <a href="ControladorProducto?accion=buscar&tipo=editar&id=<c:out value ='${p.getId()}'/>"   class="btn btn-info" ><img src="static/actualizar.jpg" width="20" height="20"/></a>
                                        <a href="ControladorProducto?accion=delete&id=<c:out value ='${p.getId()}'/>"   class="btn btn-danger" ><img src="static/delete.jpg" width="20" height="20"/></a>
                                    </td>
                                </tr> 


                            </c:forEach>
                        </tbody>
                    </table>







                </div> 
            </div>
        </div>



    </body>
</html>
