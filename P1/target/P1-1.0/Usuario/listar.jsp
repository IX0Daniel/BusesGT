<%-- 
    Document   : listar
    Created on : 17 sep 2026, 7:59:11 a.m.
    Author     : dz
--%>

<%@page import="model.PerfilUsuario"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    
    




    <body>

        <h1>Usuarios</h1>

        <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo">
            Registrar usuario
        </a>

        <br><br>

        <%
            List<Usuario> usuarios
                    = (List<Usuario>) request.getAttribute("usuarios");

            PerfilUsuario perfil;
        %>

        <table border="1">

            <thead>
                <tr>
                    <th>Correo</th>
                    <th>Nombre</th>
                    <th>DPI</th>
                    <th>Teléfono</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Saldo</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

                <%
                    for (Usuario usuario : usuarios) {

                        perfil = new PerfilUsuario();
                %>

                <tr>

                    <td>
                        <%= usuario.getCorreo()%>
                    </td>

                    <td>
                        -
                    </td>

                    <td>
                        -
                    </td>

                    <td>
                        -
                    </td>

                    <td>
                        <%= usuario.getRol()%>
                    </td>

                    <td>
                        <%= usuario.getEstado()%>
                    </td>

                    <td>
                        -
                    </td>

                    <td>

                        <a href="${pageContext.request.contextPath}/usuarios?accion=editar&correo=<%= usuario.getCorreo()%>">
                            Editar
                        </a>

                        <form method="post"
                              action="${pageContext.request.contextPath}/usuarios"
                              style="display:inline;">

                            <input type="hidden"
                                   name="accion"
                                   value="cambiarEstado">

                            <input type="hidden"
                                   name="correo"
                                   value="<%= usuario.getCorreo()%>">

                            <input type="hidden"
                                   name="estado"
                                   value="<%= usuario.getEstado().equals("activo")
                                       ? "inhabilitado"
                                       : "activo"%>">

                            <button type="submit">

                                <%= usuario.getEstado().equals("activo")
                                        ? "Inhabilitar"
                                        : "Activar"%>

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








