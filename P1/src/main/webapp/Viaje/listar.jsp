<%-- 
    Document   : listar
    Created on : 16 sep 2026, 9:55:44 p.m.
    Author     : dz
--%>

<%@page import="model.Viaje"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>


    <body>

        <h1>Viajes</h1>

        <a href="${pageContext.request.contextPath}/viajes?accion=nuevo">
            Nuevo viaje
        </a>

        <br><br>

        <%
            List<Viaje> viajes
                    = (List<Viaje>) request.getAttribute("viajes");
        %>

        <table border="1">

            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ruta</th>
                    <th>Bus</th>
                    <th>Chofer</th>
                    <th>Salida</th>
                    <th>Llegada estimada</th>
                    <th>Tipo</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

                <%
                    for (Viaje viaje : viajes) {
                %>

                <tr>

                    <td><%= viaje.getIdViaje()%></td>
                    <td><%= viaje.getIdRuta()%></td>
                    <td><%= viaje.getNumeroPlaca()%></td>
                    <td><%= viaje.getNoLicencia()%></td>
                    <td><%= viaje.getFechaHoraSalida()%></td>
                    <td><%= viaje.getHoraEstimadaLlegada()%></td>
                    <td><%= viaje.getTipoViaje()%></td>
                    <td><%= viaje.getEstado()%></td>

                    <td>

                        <a href="${pageContext.request.contextPath}/viajes?accion=editar&id=<%= viaje.getIdViaje()%>">
                            Editar
                        </a>

                        <form method="post" action="${pageContext.request.contextPath}/viajes" style="display:inline;">

                            <input type="hidden" name="accion" value="eliminar">
                            <input type="hidden" name="id"  value="<%= viaje.getIdViaje()%>">
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



