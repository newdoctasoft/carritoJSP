<%-- 
    Document   : prueba
    Created on : 05/01/2021, 16:35:31
    Author     : vfalcucci
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form method="prueba">
            <input type="text" name="nombre"/>
            <br/>
            <select name="pais">
              
                <option value="argentina">ARGENTINA</option>
                  <option value="brasil">BRASIL</option>
            </select>
            <br/>
            <button type="sumit" name="accion" value="enviar">ENVIAR</button>
           <label><c:out value="${pais}"></c:out></label> 
        </form>
    </body>
</html>
