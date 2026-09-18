package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Chofer;
import model.PerfilUsuario;
import model.Usuario;
import servicio.ChoferService;
import servicio.PerfilUsuarioService;
import servicio.SucursalService;
import servicio.UsuarioService;

/**
 *
 * @author dz
 */
@WebServlet(name = "ChoferServlet", urlPatterns = {"/choferes"})
public class ChoferServlet extends HttpServlet {
   
   
    private ChoferService choferService;
    private UsuarioService usuarioService;
    private PerfilUsuarioService perfilService;
    private SucursalService sucursalService;

    @Override
    public void init() {

        choferService = new ChoferService();
        usuarioService = new UsuarioService();
        perfilService = new PerfilUsuarioService();
        sucursalService = new SucursalService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if (accion == null) {

                request.setAttribute( "choferes", choferService.obtenerTodo());

                request.getRequestDispatcher("/Chofer/listar.jsp").forward(request, response);

            } else if ("nuevo".equals(accion)) {

                cargarSucursales(request);

                request.getRequestDispatcher("/Chofer/formulario.jsp").forward(request, response);

            } else if ("editar".equals(accion)) {

                String licencia = request.getParameter("licencia");
 
                Chofer chofer = choferService.buscarPorLicencia(licencia);

                if (chofer == null) {
                    throw new ServletException("No se encontró el chofer");
                }

                Usuario usuario = usuarioService.buscarPorCorreo(chofer.getCorreo());

                PerfilUsuario perfil = perfilService.buscarPorCorreo(chofer.getCorreo());

                request.setAttribute("chofer", chofer);

                request.setAttribute("usuario",usuario);

                request.setAttribute("perfil",perfil);

                cargarSucursales(request);

                request.getRequestDispatcher("/Chofer/formulario.jsp").forward(request, response);
            }

        } catch (SQLException e) {

            throw new ServletException(
                    "Error al consultar los choferes",
                    e
            );
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion =request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {

                Usuario usuario = new Usuario();

                usuario.setCorreo(request.getParameter("correo"));
                usuario.setContraseña(request.getParameter("contraseña"));
                usuario.setRol("chofer");
                usuario.setEstado("activo");


                PerfilUsuario perfil = obtenerPerfil(request);


                Chofer chofer = obtenerChofer(request);

                chofer.setCorreo(usuario.getCorreo());


                choferService.crear(usuario, perfil, chofer);

                response.sendRedirect(request.getContextPath()+ "/choferes");

            } else if ("actualizar".equals(accion)) {

                String licencia = request.getParameter("licencia");

                Chofer chofer = choferService.buscarPorLicencia(licencia);

                if (chofer == null) {
                    throw new ServletException("No se encontró el chofer");
                }

                PerfilUsuario perfil = obtenerPerfil(request);

                perfil.setCorreo(chofer.getCorreo());

                Chofer datosActualizados = obtenerChofer(request);

                datosActualizados.setCorreo(chofer.getCorreo());
                datosActualizados.setNoLicencia(chofer.getNoLicencia());              
                datosActualizados.setCodigoSucursal(chofer.getCodigoSucursal());

                choferService.actualizar(perfil, datosActualizados);

                response.sendRedirect(request.getContextPath()+ "/choferes");

            } else if ("cambiarEstado".equals(accion)) {

                String correo = request.getParameter("correo");

                String estado = request.getParameter("estado");

                usuarioService.cambiarEstado(correo, estado);

                response.sendRedirect(request.getContextPath()+ "/choferes");
            
            }

        } catch (SQLException e) {

            throw new ServletException("Error al modificar el chofer",e);
        }
    }

    private PerfilUsuario obtenerPerfil(HttpServletRequest request) {

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

    private Chofer obtenerChofer(HttpServletRequest request) {

        Chofer chofer = new Chofer();

        chofer.setNoLicencia(request.getParameter("noLicencia"));
        chofer.setRutaFoto(request.getParameter("rutaFoto"));
        chofer.setTipoLicencia(request.getParameter("tipoLicencia"));
        chofer.setFechaVencimiento(request.getParameter("fechaVencimiento"));
        chofer.setSalario(Double.parseDouble(request.getParameter("salario")));
        chofer.setCodigoSucursal(Integer.parseInt(request.getParameter("codigoSucursal")));

        return chofer;
    }

    private void cargarSucursales(HttpServletRequest request)throws SQLException {

        request.setAttribute("sucursales",sucursalService.listar());
    }         
    
}
