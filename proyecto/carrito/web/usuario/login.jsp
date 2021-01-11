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

        <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script type="text/javascript" 
        src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
<style>
            body {
                background: 	#778899;
            } 
        </style>


    </head>
    <body>

        <div class="container"  style="text-align: center">
            <div class="col-md-4 offset-md-4" >
                <div class="card">
                    <div class="card-header" >

                        <h1 >LOGIN </h1>
                    </div>
                    <div class="card-body">

                        <form action="UsuarioLogin" method="get" id="loginForm" ><br/>
                            <img src="static/login.jpg" width="80%" height="80%"/>

                            <div class="form-group">
                                <label for="correo">EMAIL</label>
                                <input type="text" class="form-control" id="email" placeholder="INGRESA EL CORREO" name="email"/>
                            </div>

                            <div class="form-group">
                                <label for="clave">CLAVE</label>
                                <input type="password" class="form-control" id="password" placeholder="INGRESA LA DIRECCION" name="password"/>
                            </div>

                            <br/>
                             
                            <button type="submit" class="btn btn-primary" name="accion" value="login"  >ENVIAR</button>
                        </form>
                    </div>

                </div>

            </div>

        </div>


 

    </div>
</body>
 
</html>