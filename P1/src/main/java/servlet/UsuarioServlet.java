package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.PerfilUsuario;
import model.Usuario;
import servicio.PerfilUsuarioService;
import servicio.UsuarioService;

/**
 * @author dz
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {

  
    private UsuarioService usuarioService;
    private PerfilUsuarioService perfilService;

    @Override
    public void init() {
        usuarioService = new UsuarioService();
        perfilService = new PerfilUsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if (accion == null) {

                request.setAttribute("usuarios", usuarioService.obtenerPerfiles());
                request.getRequestDispatcher("/Usuario/listar.jsp").forward(request, response);

            } else if ("nuevo".equals(accion)) {

                request.getRequestDispatcher("/Usuario/formulario.jsp").forward(request, response);

            } else if ("editar".equals(accion)) {

                String correo = request.getParameter("correo");
                Usuario usuario = usuarioService.buscarPorCorreo(correo);
                PerfilUsuario perfil = perfilService.buscarPorCorreo(correo);
                request.setAttribute("usuario", usuario);
                request.setAttribute("perfil", perfil);

                request.getRequestDispatcher( "/Usuario/formulario.jsp").forward(request, response);
            }

        } catch (SQLException e) {

            throw new ServletException("Error al consultar los usuarios", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {

                Usuario usuario = objetoUsuario(request);
                PerfilUsuario perfil = objetoPerfil(request);

                usuarioService.registrar(usuario, perfil);

                response.sendRedirect(request.getContextPath() + "/usuarios");

            } else if ("actualizar".equals(accion)) {

                String correo = request.getParameter("correo");

                Usuario usuario = usuarioService.buscarPorCorreo(correo);

                PerfilUsuario perfil = perfilService.buscarPorCorreo(correo);

                if (usuario == null || perfil == null) {
                    throw new ServletException("No se encontró el usuario o su perfil");
                }

                usuario.setContraseña(request.getParameter("contraseña"));
                perfil.setDpi(request.getParameter("dpi"));
                perfil.setNit(request.getParameter("nit"));
                perfil.setTelefono(request.getParameter("telefono"));
                perfil.setDireccion(request.getParameter("direccion"));
                perfil.setNombreCompleto(request.getParameter("nombreCompleto"));

                usuarioService.actualizar(usuario);
                perfilService.actualizar(perfil);

                response.sendRedirect(request.getContextPath() + "/usuarios");

            } else if ("cambiarEstado".equals(accion)) {

                String correo = request.getParameter("correo");
                String estado = request.getParameter("estado");

                usuarioService.cambiarEstado(correo, estado);

                response.sendRedirect(request.getContextPath() + "/usuarios");
            }

        } catch (SQLException e) {

            throw new ServletException("Error al modificar el usuario", e);
        }
    }

    private Usuario objetoUsuario(HttpServletRequest request) {

        Usuario usuario = new Usuario();

        usuario.setCorreo(request.getParameter("correo"));

        usuario.setContraseña(request.getParameter("contraseña"));

        usuario.setRol("usuario");

        usuario.setEstado("activo");

        return usuario;
    }

    private PerfilUsuario objetoPerfil(HttpServletRequest request) {

        PerfilUsuario perfil = new PerfilUsuario();

        perfil.setCorreo(request.getParameter("correo"));

        perfil.setDpi(request.getParameter("dpi"));

        perfil.setNit(request.getParameter("nit"));

        perfil.setTelefono(request.getParameter("telefono"));

        perfil.setDireccion(request.getParameter("direccion"));

        perfil.setNombreCompleto(request.getParameter("nombreCompleto"));

        perfil.setSaldo(0);

        return perfil;
    }

    
    
}
