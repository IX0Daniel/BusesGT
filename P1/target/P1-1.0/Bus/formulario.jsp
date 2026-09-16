<%-- 
    Document   : Formulario
    Created on : 11 sep 2026, 7:25:32 p.m.
    Author     : dz
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.Bus"%>
<%@page import="model.Sucursal"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="es">

    <%
        Bus bus = (Bus) request.getAttribute("bus");  
        List<Sucursal> sucursales = (ArrayList)request.getAttribute("sucursales");
        boolean evento = (bus != null) ? true:false;
    %>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Sucursal</title>
    </head>

    <body>

        <h1>
            <%= evento ? "Editar Bus" : "Registrar Bus"%>
        </h1>

        <form method="post" action="${pageContext.request.contextPath}/buses">

            <input type="hidden" name="accion" value="<%= evento ? "actualizar" : "crear"%>">
            
            <div>
                <label for="numeroPlaca">Numero de Placa:</label>
                <input type="text" id="numeroPlaca" name="numeroPlaca" value="<%= evento ? bus.getNumeroPlaca() : ""%>" <%= evento ? "readonly":"required"%>>
            </div>
            
            <div>
                <label for="rutaFoto">Ruta Foto:</label>
                <input type="text" id="rutaFoto" name="rutaFoto" value="<%= evento ? bus.getRutaFoto() : ""%>" <%= evento ? "":"required"%>>
            </div>

            <div>
               
                <label for="marca">Marca:</label>
                <input type="text" id="marca" name="marca" value="<%= evento ? bus.getMarca(): ""%>" <%= evento ? "readonly":"required"%>>
            </div>
            
            <div>
                <label for="modelo">Modelo:</label>
                <input type="text" id="modelo" name="modelo" value="<%= evento ? bus.getModelo(): ""%>"   <%= evento ? "readonly":"required"%>>
            </div>
            
            <div>
                <label for="año">Año:</label>
                <input type="number" id="año" name="año" value="<%= evento ? bus.getAño(): ""%>" <%= evento ? "readonly":"required"%>>
            </div>
            
            
            <div>
                <label for="capacidad">Capacidad:</label>
                <input type="number" id="capacidad" name="capacidad" value="<%= evento ? bus.getCapacidad(): ""%>" <%= evento ? "":"required"%>>
            </div>
            
            <div>
                <label for="kilometraje">Kilometraje:</label>
                <input type="number" id="kilometraje" name="kilometraje" value="<%= evento ? bus.getKilometraje(): ""%>" <%= evento ? "":"required"%> step="0.1">
            </div>
            
            <div>
                

                <label>Estado:</label>

                <% 
                    boolean viajes = (boolean) request.getAttribute("viajes");
                    
                    if (viajes) {%>

                <span>
                    <%= bus.getEstado().equals("activo") ? "Activo" : "Inactivo"%>
                </span>

                <p>
                    El Bus no de puede desactuvar porque tiene al menos un vijae aún no finalizado.
                </p>

                <% } else { %>

                <select id="estado" name="estado">
                    <option value="activo">Activo</option>
                    <option value="deshabilitado">Inactivo</option>
                </select>

                <% } %>

                
                
                <%--                                
                <label for="estado">Estado:</label>                                
                <select id="estado" name="estado">                                         
                    <%
                        if(evento){                            
                            switch(bus.getEstado()){                            
                                case "activo":
                                    %>                   
                                    <option value="activo">Activo</option>
                                    <option value="deshabilitado">Inactivo</option>
                                    <% 
                                    break; 
                                case "deshabilitado":                        
                                    %> 
                                    <option value="deshabilitado">Inactivo</option>
                                    <option value="activo">Activo</option>
                                    <%    
                                    break;
                            }
                                          
                        }else{
                            %>
                            <option value="activo">Activo</option>
                            <option value="deshabilitado">Inactivo</option>
                            <%
                        }
                    %>
                </select>   --%>
                
            </div>
            
            
            <div>
                <label for="sucursal">Sucursal de Origen:</label> 
                <select id="sucursal" name="codigoSucursal"> 
                    <%             
                        int codigoSucursalTemporal=0;
                        String activo = "";
                        
                        for(Sucursal sucursal: sucursales){
                            if(evento){
                                activo = (bus.getCodigoSucursal()==sucursal.getCodigoSucursal())? "selected" : "";
                            }
                            %>
                            <option value="<%=sucursal.getCodigoSucursal() %>" <%= activo%>  ><%= sucursal.getNombre() %></option>
                            <% 
                        } 
                    %> 
                </select> 
            </div>

            <div>
                <label for="sucursalActual">Sucursal Actual:</label> 
                <select id="sucursalActual" name="codigoSucursalActual"> 
                    <%                        
                        for(Sucursal sucursal: sucursales){
                            %>
                            <option value="<%=sucursal.getCodigoSucursal() %>"><%= sucursal.getNombre() %></option>
                            <% 
                        } 
                    %> 
                </select> 
            </div>              
                
            <button type="submit">
                <%= evento ? "Guardar cambios" : "Guardar"%>
            </button>

            <a href="${pageContext.request.contextPath}/buses">
                Cancelar
            </a>
        </form>
    </body>
</html>