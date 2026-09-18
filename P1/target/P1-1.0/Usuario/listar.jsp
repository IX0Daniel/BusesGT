<%-- 
    Document   : listar
    Created on : 17 sep 2026, 7:59:11 a.m.
    Author     : dz
--%>

<%@page import="dto.PerfilUsuarioDTO"%>
<%@page import="model.PerfilUsuario"%>
<%@page import="model.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios del Sistema</title>
    </head> 

    <body>

        <h1>Usuarios</h1>

        <a href="${pageContext.request.contextPath}/usuarios?accion=nuevo">
            Registrar usuario
        </a>

        <br><br>

        <%
            List<PerfilUsuarioDTO> usuarios = (List<PerfilUsuarioDTO>) request.getAttribute("usuarios");
 
        %>

        <table border="1">

            <thead>
                <tr>
                    <th>Correo</th>
                    <th>Nombre</th>
                    <th>DPI</th>
                    <th>Teléfono</th>
                    <th>Direccion</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Saldo</th>
                    <th>Acciones</th>
                </tr>
            </thead>

            <tbody>

                <%
                    for (PerfilUsuarioDTO usuario : usuarios) {

                       
                %>

                <tr>

                    <td>
                        <%= usuario.getPerfil().getCorreo()%>
                    </td>
                    <td>                                                
                        <%= usuario.getPerfil().getNombreCompleto()%>                         
                    </td>

                    <td>
                        <%= usuario.getPerfil().getDpi()%>                                                                                           
                    </td>
                    <td>                                                
                        <%= usuario.getPerfil().getTelefono()%>   

 
                    </td>
                    
                    <td>                                                
                        <%= usuario.getPerfil().getDireccion() %>   

 
                    </td>
                    
                    <td>
                        <%= usuario.getUsuario().getRol()%>
                    </td>
                    <td>
                        <%= usuario.getUsuario().getEstado()%>
                    </td>

                    <td> 
                        <%= usuario.getPerfil().getSaldo()%>
                    </td>

                    <td>

                        <a href="${pageContext.request.contextPath}/usuarios?accion=editar&correo=<%= usuario.getUsuario().getCorreo()%>">
                            Editar
                        </a>

                        <form method="post" action="${pageContext.request.contextPath}/usuarios" style="display:inline;">

                            <input type="hidden" name="accion" value="cambiarEstado">

                            <input type="hidden" name="correo" value="<%= usuario.getPerfil().getCorreo()%>">

                            <input type="hidden" name="estado" value="<%= usuario.getUsuario().getEstado().equals("activo") ? "inhabilitado" : "activo"%>">

                            <button type="submit">

                                <%= usuario.getUsuario().getEstado().equals("activo") ? "Inhabilitar" : "Activar"%>

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








