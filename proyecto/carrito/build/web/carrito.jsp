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





        <div class="container mt-4">
            <div class="row"> 

                <div class="col-sm-8">
                    <table   class="table table-striped">
                        <thead>
                            <tr>
                                <th>ITEMS</th>
                                <th>NOMBRES</th>
                                <th>DESCRIPCION</th>
                                <th>PRECIO</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>
                                <th>ACCIONES</th>  
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carrito}"> 
                                <tr> 
                                    <td><c:out value = "${car.getItem()}"/></td> 


                                    <td><c:out value = "${car.getNombre()}"/></td>
                                    <td>
                                        <c:out value = "${car.getDescripcion()}"/>
                                        <img src="ControladorIMG?id=<c:out value ='${car.getIdProducto()}'/>" width="100" height="100" />
                                    </td>

                                    <td><c:out value = "${car.getPrecioCompra()}"/></td>

                                    <td>
                                        <form action="Controlador">
                                            <input type="hidden" value = "${car.getIdProducto()}" name="idProducto"/>
                                            <input type="text" value="${car.getCantidad()}"  name="cantidad" class="form-control"  />

                                            <button type="submit" class="btn btn-primary" name="accion"  value="actualizarCantidad"><img src="static/actualizar.jpg" width="20" height="20"/></button>

                                        </form> 

                                    </td> 


                                    <td><c:out value = "${car.getCantidad()*car.getPrecioCompra()}"/></td>
                                    <td>
                                        <div class="col-md-2"> 
                                            <a href="Controlador?accion=delete&id=<c:out value ='${car.getIdProducto()}'/>"   class="btn btn-danger" ><img src="static/delete.jpg" width="20" height="20"/> </a>

                                        </div>
                                    </td>
                                </tr> 


                            </c:forEach>
                        </tbody>
                    </table>
                </div>



                <div class="col-sm-4">
                    <div class="card"> 

                        <div class="card-header">
                            <h3>GENERAR COMPRA</h3>
                        </div>
                        <form action="Controlador" method="POST">
                            <div class="card-body">
                                <label>SUBTOTAL</label>
                                <input type="text" value="${totalPagar}" class="form-control" readOnly=""/>
                                <label>DESCUENTO</label>
                                <input type="text"  class="form-control"  name="porcentaje"/>
                                <label>TOTAL A PAGAR:</label>
                                <input type="text" value="${totalPagar}" class="form-control" readOnly=""/>
                                <br/>
                                <div  >
                                    <select name="pago"    > 
                                        <c:forEach var="cuota" items="${cuotas}"> 
                                            <option name="pago"    value="${cuota.getId()}">${cuota.getMonto()}</option>
                                        </c:forEach>

                                    </select>
                                </div>

                            </div>

                    </div>


                    <div class="card-footer">

                        <button type="submit" class="btn btn-primary" name="accion"  value="descontar">DESCONTAR</button>
                        <a href="Controlador?accion=generarCompra" class="btn btn-danger" >COMPRAR </a>

                    </div>
                    </form>

                </div> 
            </div>



        </div> 

    </div>




</body>
</html>
