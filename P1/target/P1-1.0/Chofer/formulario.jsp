<%-- 
    Document   : formulario
    Created on : 17 sep 2026, 6:11:13 p.m.
    Author     : dz
--%>

<%@page import="model.Sucursal"%>
<%@page import="java.util.List"%>
<%@page import="model.PerfilUsuario"%>
<%@page import="model.Chofer"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body> 

        <%

            Chofer chofer = (Chofer) request.getAttribute("chofer");
            Usuario usuario = (Usuario) request.getAttribute("usuario");
            PerfilUsuario perfil = (PerfilUsuario) request.getAttribute("perfil");
            List<Sucursal> sucursales = (List<Sucursal>) request.getAttribute("sucursales");
            boolean evento = chofer != null;
            
        %> 

        <h1> <%= evento ? "Editar chofer" : "Registrar chofer"%> </h1> 

        <form method="post" action="${pageContext.request.contextPath}/choferes">

            <input type="hidden" name="accion" value="<%= evento ? "actualizar" : "crear"%>">

            <%
                if (evento) {
            %> 
            <input type="hidden" name="licencia" value="<%= chofer.getNoLicencia()%>">
            <%
                }
            %>

            <h2>Cuenta</h2> 

            <div> 

                <label for="correo"> Correo: </label>
                <input type="email" id="correo" name="correo" maxlength="20" value="<%= evento ? usuario.getCorreo() : ""%>" <%= evento ? "readonly" : "required"%>> 

            </div> 
                
            <br> 
            <%
                if (!evento) {
            %> 
            
            <div>
                <label for="contraseña"> Contraseña: </label>
                <input type="password" id="contraseña" name="contraseña" maxlength="20" required>
            </div> 
            <br> 
            <%
                }
            %>                 

            <h2>Datos personales</h2> 

            <div> 
                <label for="nombreCompleto"> Nombre completo: </label>
                <input type="text" id="nombreCompleto" name="nombreCompleto" maxlength="80" value="<%= evento ? perfil.getNombreCompleto() : ""%>" required> 
            </div> 

            <br> 

            <div> 
                <label for="dpi"> DPI: </label> 
                <input type="text" id="dpi" name="dpi" maxlength="15" value="<%= evento ? perfil.getDpi() : ""%>" required> 
            </div> 

            <br>

            <div> 
                <label for="nit"> NIT: </label>
                <input type="text" id="nit" name="nit" maxlength="15" value="<%= evento ? perfil.getNit() : ""%>" required>
            </div>

            <br>

            <div>
                <label for="telefono"> Teléfono: </label> 
                <input type="text" id="telefono" name="telefono" maxlength="15" value="<%= evento ? perfil.getTelefono() : ""%>" required>
            </div> 

            <br>

            <div> 
                <label for="direccion"> Dirección: </label> 
                <input type="text" id="direccion" name="direccion" maxlength="100" value="<%= evento ? perfil.getDireccion() : ""%>" required>
            </div> 

            <br>

            <h2>Datos del chofer</h2> 
            <div> 
                <label for="noLicencia"> Número de licencia: </label>
                <input type="text" id="noLicencia" name="noLicencia" maxlength="15" value="<%= evento ? chofer.getNoLicencia() : ""%>" <%= evento ? "readonly" : "required"%>> 
            </div> 

            <br> 
            <div> 
                <label for="rutaFoto"> Ruta de foto: </label>
                <input type="text" id="rutaFoto" name="rutaFoto" maxlength="80" value="<%= evento ? chofer.getRutaFoto() : ""%>" required>
            </div> 

            <br>
            <div> 
                <label for="tipoLicencia"> Tipo de licencia: </label>
                <select id="tipoLicencia" name="tipoLicencia" required>
                    <option value="A" <%= evento && "A".equals(chofer.getTipoLicencia()) ? "selected" : ""%>> A </option> 
                    <option value="B" <%= evento && "B".equals(chofer.getTipoLicencia()) ? "selected" : ""%>> B </option> 
                    <option value="C" <%= evento && "C".equals(chofer.getTipoLicencia()) ? "selected" : ""%>> C </option> 
                    <option value="D" <%= evento && "D".equals(chofer.getTipoLicencia()) ? "selected" : ""%>> D </option>
                </select> 
            </div> 

            <br> 


            <div> 
                <label for="fechaVencimiento"> Fecha de vencimiento: </label> 
                <input type="date" id="fechaVencimiento" name="fechaVencimiento" value="<%= evento ? chofer.getFechaVencimiento() : ""%>" min="2026-09-19" required> 
            </div> 
            <br> 

            <div> 

                <label for="salario"> Salario por viaje: </label>
                <input type="number" id="salario" name="salario" min="0" step="0.01" value="<%= evento ? chofer.getSalario() : ""%>" required> 
            </div> 

            <br>  

            <div>

                <label for="codigoSucursal"> Sucursal: </label> 

                <%
                    if (!evento) {
                %> 
                <select id="codigoSucursal" name="codigoSucursal" required> 

                    <%
                        for (Sucursal sucursal : sucursales) {
                    %> 

                    <option value="<%= sucursal.getCodigoSucursal()%>"> <%= sucursal.getNombre()%> </option> 

                    <%
                        }
                    %> 

                </select> 

                <%
                } else {
                %> 

                <%
                    String nombreSucursal = "";
                        for (Sucursal sucursal : sucursales) {
                            if (sucursal.getCodigoSucursal() == chofer.getCodigoSucursal()) {
                                nombreSucursal = sucursal.getNombre();
                %>
                            <input type="hidden" name="codigoSucursal" value="<%= sucursal.getCodigoSucursal() %>">

                <% 
                            break;
                        }
                    }
                %> 

                <span> <%= nombreSucursal%> </span>
                <p> La sucursal no puede modificarse desde este formulario. </p>
                <%
                    }
                %>

            </div>

            <br> 


            <%
                if (evento) {
            %> 

            <p> El estado del chofer se modifica mediante la opción de activar o inhabilitar. </p> 

            <%
            } else {
            %>

            <p> La cuenta del chofer será creada como <strong>activa</strong>. </p>

            <%
                }
            %> 

            <button type="submit"> <%= evento ? "Guardar cambios" : "Registrar chofer"%> </button> 

            <a href="${pageContext.request.contextPath}/choferes"> Cancelar </a> 

        </form> 

    </body>

</html>