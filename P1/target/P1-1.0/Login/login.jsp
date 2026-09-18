<%-- 
    Document   : login
    Created on : 17 sep 2026, 9:36:38 p.m.
    Author     : dz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 


    <body>

        <h1>Iniciar sesión</h1>

        <%
            String error = (String) request.getAttribute("error");
        %>

        <% 
            if (error != null) {
        %>

        <p>
            <%= error%>
        </p>

        <% 
            }
        %>

        <form method="post" action="${pageContext.request.contextPath}/login">

            <div>
                <label for="correo">Correo:</label>

                <input type="email" id="correo" name="correo" maxlength="20" required>

            </div>

            <br>

            <div>

                <label for="contraseña"> Contraseña: </label>

                <input type="password" id="contraseña" name="contraseña" maxlength="20" required>

            </div>

            <br>

            <button type="submit">Iniciar sesión</button>

        </form>

        <br>

        <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo">
            Crear una cuenta
        </a>

    </body> 

</html>
