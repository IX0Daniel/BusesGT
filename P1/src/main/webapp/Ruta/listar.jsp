<%-- 
    Document   : listar
    Created on : 16 sep 2026, 5:06:25 p.m.
    Author     : dz
--%>
<%@page import="java.util.List"%>
<%@page import="model.Ruta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Rutas</title>
</head>

<body>

    <h1>Rutas</h1>

    <a href="${pageContext.request.contextPath}/rutas?accion=nuevo">
        Nueva ruta
    </a>

    <br><br>

    <%
        List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");
    %>

    <table border="1">

        <thead>
            <tr>
                <th>ID</th>
                <th>Sucursal origen</th>
                <th>Sucursal destino</th>
                <th>Distancia (km)</th>
                <th>Precio boleto</th>
                <th>Acciones</th>
            </tr>
        </thead>

        <tbody>

        <%
            for (Ruta ruta : rutas) {
        %>

            <tr>
                <td> <%= ruta.getIdRuta()  %> </td>

                <td><%= ruta.getCodigoSucursalOrigen() %> </td>

                <td><%= ruta.getCodigoSucursalDestino() %></td>

                <td><%= ruta.getDistancia() %></td>

                <td>Q. <%= ruta.getPrecioBoleto() %></td>

                <td>

                    <a href="${pageContext.request.contextPath}/rutas?accion=editar&idRuta=<%= ruta.getIdRuta() %>">
                        Editar
                    </a>

                    <form method="post" action="${pageContext.request.contextPath}/rutas" style="display:inline;">

                        <input type="hidden" name="accion" value="eliminar">

                        <input type="hidden" name="idRuta" value="<%= ruta.getIdRuta() %>">

                        <button type="submit">
                            Eliminar
                        </button>

                    </form>

                </td>
            </tr>

        <%
            }
        %>

        </tbody>
    </table>

</body>
 


</html>
