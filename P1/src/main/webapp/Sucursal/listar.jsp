<%-- 
    Document   : listar
    Created on : 9 sep 2026, 10:38:55 p.m.
    Author     : dz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="model.Sucursal" %>

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucursales</title>
    </head>
    <body>

        <h1>Sucursales Existentes</h1>

        <a href="${pageContext.request.contextPath}/sucursales?accion=nuevo">
            Nueva sucursal
        </a>

        <table border="1">
            <thead>
                <tr>
                    <th>Código</th>
                    <th>Nombre</th>
                    <th>Dirección</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

                <%
                    List<Sucursal> sucursales
                            = (List<Sucursal>) request.getAttribute("sucursales");

                    for (Sucursal sucursal : sucursales) {
                %>

                <tr>
                    <td><%= sucursal.getCodigoSucursal()%></td>
                    <td><%= sucursal.getNombre()%></td>
                    <td><%= sucursal.getDireccion()%></td>
                    <td><%= sucursal.getDescripcion()%></td>

                    <td>
                        <a href="${pageContext.request.contextPath}/sucursales?accion=editar&id=<%= sucursal.getCodigoSucursal()%>">
                            Editar
                        </a>

                        <form method="post"
                              action="${pageContext.request.contextPath}/sucursales"
                              style="display:inline;">

                            <input type="hidden"
                                   name="accion"
                                   value="eliminar">

                            <input type="hidden"
                                   name="id"
                                   value="<%= sucursal.getCodigoSucursal()%>">

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