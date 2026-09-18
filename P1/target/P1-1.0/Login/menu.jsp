<%-- 
    Document   : menu
    Created on : 17 sep 2026, 9:55:29 p.m.
    Author     : dz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu de Inicio</title>
    </head>



    <body>

        <%
            String correo = (String) session.getAttribute("correo");
            String rol = (String) session.getAttribute("rol");
        %>

        <h1>Buses Guatemala</h1>
        <p>Usuario:<%= correo%></p>
        <p>Rol:<%= rol%></p>

    </body>

</html>
