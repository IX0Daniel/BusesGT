<%-- 
    Document   : formulario
    Created on : 16 sep 2026, 10:01:01 p.m.
    Author     : dz
--%>

<%@page import="model.Chofer"%>
<%@page import="model.Bus"%>
<%@page import="model.Viaje"%>
<%@page import="model.Ruta"%>
<%@page import="model.Ruta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>



    <body>

        <%
            Viaje viaje = (Viaje) request.getAttribute("viaje");

            boolean evento = viaje != null;

            List<Ruta> rutas = (List<Ruta>) request.getAttribute("rutas");

            List<Bus> buses = (List<Bus>) request.getAttribute("buses");

            List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
        %>

        <h1>
            <%= evento ? "Editar viaje" : "Registrar viaje"%>
        </h1>

        <form method="post" action="${pageContext.request.contextPath}/viajes">

            <input type="hidden"  name="accion" value="<%= evento ? "actualizar" : "crear"%>">

            <% if (evento) {%>

            <input type="hidden" name="id" value="<%= viaje.getIdViaje()%>">

            <% } %>


            <div>

                <label for="idRuta">Ruta:</label>

                <select id="idRuta" name="idRuta" required>

                    <%
                        for (Ruta ruta : rutas) {

                            boolean seleccionada = evento && viaje.getIdRuta() == ruta.getIdRuta();
                    %>

                    <option value="<%= ruta.getIdRuta()%>" <%= seleccionada ? "selected" : ""%>>
                        Ruta <%= ruta.getIdRuta()%>
                    </option>

                    <%
                        }
                    %>

                </select>

            </div>


            <div>

                <label for="numeroPlaca"> Bus: </label>

                <select id="numeroPlaca" name="numeroPlaca" required>

                    <%
                        for (Bus bus : buses) {

                            boolean seleccionada = evento && viaje.getNumeroPlaca().equals(bus.getNumeroPlaca());
                    %>

                    <option value="<%= bus.getNumeroPlaca()%>" <%= seleccionada ? "selected" : ""%>>                        <%= bus.getNumeroPlaca()%>

                    </option>

                    <%
                        }
                    %>

                </select>

            </div>


            <div>

                <label for="noLicencia"> Chofer:</label>

                <select id="noLicencia" name="noLicencia" required>

                    <%
                        for (Chofer chofer : choferes) {

                            boolean seleccionada= evento && viaje.getNoLicencia().equals(chofer.getNoLicencia());
                    %>

                    <option value="<%= chofer.getNoLicencia()%>" <%= seleccionada ? "selected" : ""%>>
                        <%= chofer.getNoLicencia()%>
                    </option>

                    <%
                        }
                    %>

                </select>

            </div>


            <div>

                <label for="fechaHoraSalida">
                    Fecha y hora de salida:
                </label>

                <input type="datetime-local" id="fechaHoraSalida" name="fechaHoraSalida" value="<%= evento ? viaje.getFechaHoraSalida() : ""%>" required>

            </div>


            <div>

                <label for="horaEstimadaLlegada">
                    Hora estimada de llegada:
                </label>

                <input type="time" id="horaEstimadaLlegada" name="horaEstimadaLlegada" value="<%= evento ? viaje.getHoraEstimadaLlegada() : ""%>" required>

            </div>


            <div>

                <label for="tipoViaje">
                    Tipo de viaje:
                </label>

                <select id="tipoViaje" name="tipoViaje" required>

                    <option value="regular" <%= evento && "regular".equals(viaje.getTipoViaje()) ? "selected" : ""%>>
                        Regular
                    </option>

                    <option value="privado" <%= evento && "privado".equals(viaje.getTipoViaje()) ? "selected" : ""%>>Privado</option>

                </select>

            </div>


            <% if (evento) {%>

            <div>

                <label>Estado:</label>

                <span><%= viaje.getEstado()%></span>

                <p>
                    El estado del viaje no puede modificarse
                    desde este formulario.
                </p>

            </div>

            <% } else { %>

            <p>
                El viaje será creado con estado
                <strong>programado</strong>.
            </p>

            <% }%>


            <br>

            <button type="submit">

                <%= evento ? "Guardar cambios" : "Guardar"%>

            </button>

            <a href="${pageContext.request.contextPath}/viajes">
                Cancelar
            </a>

        </form>

    </body> 



</html>




