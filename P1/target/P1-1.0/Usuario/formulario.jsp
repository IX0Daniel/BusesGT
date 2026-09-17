<%-- 
    Document   : formulario
    Created on : 17 sep 2026, 9:05:04 a.m.
    Author     : dz
--%>

<%@page import="model.Usuario"%>
<%@page import="model.PerfilUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>




    <body>

        <%
            Usuario usuario = (Usuario) request.getAttribute("usuario");

            PerfilUsuario perfil = (PerfilUsuario) request.getAttribute("perfil");

            boolean evento = usuario != null;
        %>

        <h1>
            <%= evento ? "Editar usuario" : "Registrar usuario"%>
        </h1>

        <form method="post"
              action="${pageContext.request.contextPath}/usuarios">

            <input type="hidden"
                   name="accion"
                   value="<%= evento ? "actualizar" : "crear"%>">

            <div>

                <label for="correo">
                    Correo:
                </label>

                <input type="email"
                       id="correo"
                       name="correo"
                       maxlength="20"
                       value="<%= evento ? usuario.getCorreo() : ""%>"
                       <%= evento ? "readonly" : "required"%>>

            </div>

            <br>

            <div>

                <label for="contraseña">
                    Contraseña:
                </label>

                <input type="password"
                       id="contraseña"
                       name="contraseña"
                       maxlength="20"
                       value="<%= evento ? usuario.getContraseña() : ""%>"
                       required>

            </div>

            <br>

            <div>

                <label for="nombreCompleto">
                    Nombre completo:
                </label>

                <input type="text"
                       id="nombreCompleto"
                       name="nombreCompleto"
                       maxlength="80"
                       value="<%= evento
                               ? perfil.getNombreCompleto()
                               : ""%>"
                       required>

            </div>

            <br>

            <div>

                <label for="dpi">
                    DPI:
                </label>

                <input type="text"
                       id="dpi"
                       name="dpi"
                       maxlength="15"
                       value="<%= evento ? perfil.getDpi() : ""%>"
                       required>

            </div>

            <br>

            <div>

                <label for="nit">
                    NIT:
                </label>

                <input type="text"
                       id="nit"
                       name="nit"
                       maxlength="15"
                       value="<%= evento ? perfil.getNit() : ""%>"
                       required>

            </div>

            <br>

            <div>

                <label for="telefono">
                    Teléfono:
                </label>

                <input type="text"
                       id="telefono"
                       name="telefono"
                       maxlength="15"
                       value="<%= evento ? perfil.getTelefono() : ""%>"
                       required>

            </div>

            <br>

            <div>

                <label for="direccion">
                    Dirección:
                </label>

                <input type="text"
                       id="direccion"
                       name="direccion"
                       maxlength="100"
                       value="<%= evento ? perfil.getDireccion() : ""%>"
                       required>

            </div>

            <br>

            <% if (evento) {%>

            <div>

                <label>
                    Rol:
                </label>

                <span>
                    <%= usuario.getRol()%>
                </span>

                <p>
                    El rol no puede modificarse desde este formulario.
                </p>

            </div>

            <br>

            <div>

                <label>
                    Estado:
                </label>

                <span>
                    <%= usuario.getEstado()%>
                </span>

                <p>
                    El estado se modifica mediante la opción
                    correspondiente.
                </p>

            </div>

            <% } else { %>

            <p>
                La cuenta será registrada como
                <strong>usuario</strong> y quedará
                <strong>activa</strong>.
            </p>

            <% }%>

            <br>

            <button type="submit">
                <%= evento ? "Guardar cambios" : "Registrar usuario"%>
            </button>

            <a href="${pageContext.request.contextPath}/usuarios">
                Cancelar
            </a>

        </form>


    </body>
</html>