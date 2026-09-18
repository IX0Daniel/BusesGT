<%-- 
    Document   : listar
    Created on : 17 sep 2026, 5:53:29 p.m.
    Author     : dz
--%>

<%@page import="dto.ChoferDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    
    

    <body> 
        <h1>Choferes</h1> 
        <a href="${pageContext.request.contextPath}/choferes?accion=nuevo"> Registrar chofer </a> 
        <br>
        <br> 
        
        
        <% 
            List<ChoferDTO> choferes = (List<ChoferDTO>) request.getAttribute("choferes"); 
        %> 
        
        
        <table border="1"> 
            <thead> 
                <tr> 
                    <th>Nombre</th> 
                    <th>Correo</th> 
                    <th>Licencia</th> 
                    <th>Tipo</th> 
                    <th>Vencimiento</th> 
                    <th>Salario</th> 
                    <th>Sucursal</th> 
                    <th>Estado</th> 
                    <th>Acciones</th>
                </tr> 
            </thead> 
            <tbody>
                <% for (ChoferDTO chofer : choferes) {%> 
                <tr> 
                    <td> <%= chofer.getNombreCompleto()%> </td>
                    <td> <%= chofer.getCorreo()%> </td> 
                    <td> <%= chofer.getNoLicencia()%> </td> 
                    <td> <%= chofer.getTipoLicencia()%> </td>
                    <td> <%= chofer.getFechaVencimiento()%> </td> 
                    <td> Q <%= chofer.getSalario()%> </td>
                    <td> <%= chofer.getNombreSucursal()%> </td>
                    <td> <%= chofer.getEstado()%> </td> 
                    <td> 
                        <a href="${pageContext.request.contextPath}/choferes?accion=editar&licencia=<%= chofer.getNoLicencia()%>"> Editar </a>
                        
                        
                        <form method="post" action="${pageContext.request.contextPath}/choferes" style="display:inline;">
                            
                            <input type="hidden" name="accion" value="cambiarEstado"> 
                            <input type="hidden" name="correo" value="<%= chofer.getCorreo()%>"> 
                            <input type="hidden" name="estado" value="<%= chofer.getEstado().equals("activo") ? "inhabilitado" : "activo"%>">
                            
                            <button type="submit"> 
                                
                                <%= chofer.getEstado().equals("activo") ? "Inhabilitar" : "Activar"%> 
                            </button> </form> </td> </tr> <% }%> </tbody> </table> 
    </body>


    
    
    
    
</html>
