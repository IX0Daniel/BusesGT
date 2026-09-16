<%-- 
    Document   : listar
    Created on : 13 sep 2026, 6:52:29 p.m.
    Author     : dz
--%>

<%@page import="model.Bus"%>
<%@page import="model.Sucursal"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   
    
     <body>

        <h1>Sucursales Existentes</h1>
        
        
        <a href="${pageContext.request.contextPath}/buses?accion=nuevo">
            Nueva sucursal
        </a>

        <table border="1">
            <thead>
                <tr>
                    <th>Numero de Placa</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>Año</th>
                    <th>Capacidad</th>
                    <th>Kilometraje</th>
                    <th>Ruta Foto</th>
                    <th>Estado</th>
                    <th>Sucursal de Origen</th>
                    <th>Sucursal Actual</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

                <%
                    List<Bus> buses = (List<Bus>) request.getAttribute("buses");

                    for (Bus sucursal : buses) {
                %>

                <tr>
                    <td><%= sucursal.getNumeroPlaca()%></td>
                    <td><%= sucursal.getMarca() %></td>
                    <td><%= sucursal.getModelo() %></td>
                    <td><%= sucursal.getAño() %></td>
                    <td><%= sucursal.getCapacidad()%></td>
                    <td><%= sucursal.getKilometraje()%></td>
                    <td><%= sucursal.getRutaFoto()%></td>
                    <td><%= sucursal.getEstado() %></td>
                    <td><%= sucursal.getCodigoSucursal() %></td>
                    <td><%= sucursal.getCodigoSucursalActual()%></td>

                    <td>
                        <a href="${pageContext.request.contextPath}/buses?accion=editar&placa=<%= sucursal.getNumeroPlaca()%>">
                            Editar
                        </a>

                        <form method="post"
                              action="${pageContext.request.contextPath}/buses"
                              style="display:inline;">

                            <input type="hidden" name="accion" value="eliminar">

                            <input type="hidden" name="placa" value="<%= sucursal.getNumeroPlaca() %>">
 
                            <button type="submit">
                                Desactivar
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
