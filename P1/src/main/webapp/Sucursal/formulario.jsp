<%-- 
    Document   : Formulario
    Created on : 11 sep 2026, 7:25:32 p.m.
    Author     : dz
--%>

<%@page import="model.Sucursal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="es">

    <%
        Sucursal sucursal = (Sucursal) request.getAttribute("sucursal");
        boolean evento = sucursal != null;

    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Sucursal</title>
    </head>

    <body>

        <h1>
            <%= evento ? "Editar sucursal" : "Nueva sucursal"%>
        </h1>

        <form method="post" action="${pageContext.request.contextPath}/sucursales">

            <input type="hidden" name="accion" value="<%= evento ? "actualizar" : "crear"%>">
            <% if (evento) {%>
            <input type="hidden" name="codigoSucursal" value="<%= sucursal.getCodigoSucursal()%>">
            <% }%>
            <div>
                <label for="nombre">Nombre:</label>

                <input type="text" id="nombre" name="nombre"
                       value="<%= evento ? sucursal.getNombre() : ""%>" required>
            </div>

            <div>
                <label for="direccion">Dirección:</label>

                <input type="text" id="direccion" name="direccion" value="<%= evento ? sucursal.getDireccion() : ""%>" required>
            </div>

            <div>
                <label for="descripcion">Descripción:</label>

                <textarea id="descripcion" name="descripcion">
                    <%= evento ? sucursal.getDescripcion() : ""%>
                </textarea>
            </div>

            <button type="submit">
                <%= evento ? "Guardar cambios" : "Guardar"%>
            </button>

            <a href="${pageContext.request.contextPath}/sucursales">
                Cancelar
            </a>

        </form>

    </body>

</html>