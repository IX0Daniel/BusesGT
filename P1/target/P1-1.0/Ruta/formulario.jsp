<%-- 
    Document   : Formulario
    Created on : 11 sep 2026, 7:25:32 p.m.
    Author     : dz
--%>

<%@page import="model.Ruta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bus"%>
<%@page import="model.Sucursal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="es">

    <%
        Ruta ruta = (Ruta) request.getAttribute("ruta");  
        List<Sucursal> sucursales = (ArrayList)request.getAttribute("sucursales");
        boolean evento = (ruta != null) ? true:false;
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Ruta</title>
    </head>

    <body>

        <h1>
            <%= evento ? "Editar Ruta" : "Registrar Ruta"%>
        </h1>

        <form method="post" action="${pageContext.request.contextPath}/rutas">

            <input type="hidden" name="accion" value="<%= evento ? "actualizar" : "crear"%>">
            
            <%
                if(evento){
                
            %>
            <input type="hidden" name="id" value="<%= ruta.getIdRuta() %>">
                
            
            <%
                
                }
            %>
            
            <div>
                <label for="sucursalOrigen">Sucursak de Origen:</label>  
                <select id="sucursalOrigen" name="sucursalOrigen"   <%= evento ? "disabled":""%>> 
                    <%              
                        String activo = "";
                        
                        for(Sucursal sucursal: sucursales){
                            
                            if(evento){
                                activo = (sucursal.getCodigoSucursal()==ruta.getCodigoSucursalOrigen())? "selected" : "";
                            }

                            %>
                            <option value="<%=  sucursal.getCodigoSucursal()%>" <%= activo%>  ><%= sucursal.getNombre() %></option>
                            <% 
                        } 
                    %> 
                </select>  
                
            </div>
                
                
                <div>
             <label for="sucursalDestino">Sucursak de Destino</label>  
                <select id="sucursalDestino" name="sucursalDestino"   <%= evento ? "disabled":""%>> 
                    <%              
                        activo = "";
                        
                        for(Sucursal sucursal: sucursales){
                            
                            if(evento){
                                activo = (sucursal.getCodigoSucursal()==ruta.getCodigoSucursalDestino())? "selected" : "";
                            }

                            %>
                            <option value="<%= sucursal.getCodigoSucursal() %>" <%= activo%>  ><%= sucursal.getNombre() %></option>
                            <% 
                        } 
                    %> 
                </select>  
            </div>
            
            <div>
                <label for="distancia">Distancia:</label>
                <input type="number" id="distancia" name="distancia" value="<%= evento ? ruta.getDistancia(): ""%>"> 
            </div>
            
            <div>
                <label for="prcioBoleto">Precio Boleto:</label>
                <input type="number" id="precioBoleto" name="precioBoleto" value="<%= evento ? ruta.getPrecioBoleto(): ""%>"> 
            </div>
            
            
                    
                
            <button type="submit">
                <%= evento ? "Guardar cambios" : "Guardar"%>
            </button>

            <a href="${pageContext.request.contextPath}/rutas">
                Cancelar
            </a>
        </form>
    </body>
</html>