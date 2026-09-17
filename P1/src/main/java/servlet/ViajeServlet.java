package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Viaje;
import servicio.BusService;
import servicio.ChoferService;
import servicio.RutaService;
import servicio.ViajeService;

/**
 *
 * @author dz
 */
@WebServlet(name = "ViajeServlet", urlPatterns = {"/viajes"})
public class ViajeServlet extends HttpServlet {
    
    
    private ViajeService viajeService;
    private RutaService rutaService;
    private BusService busService;
    private ChoferService choferService;

    @Override
    public void init() {

        viajeService = new ViajeService();
        rutaService = new RutaService();
        busService = new BusService();
        choferService = new ChoferService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if (accion == null) {

                request.setAttribute("viajes",viajeService.listar());
                request.getRequestDispatcher("/viajes/lista.jsp").forward(request, response);

            } else if ("nuevo".equals(accion)) {
                cargarDatosFormulario(request);
                request.getRequestDispatcher("/viajes/formulario.jsp").forward(request, response);

            } else if ("editar".equals(accion)) {

                int idViaje = Integer.parseInt(request.getParameter("id"));
                Viaje viaje = viajeService.buscarPorId(idViaje);
                request.setAttribute("viaje", viaje);
                cargarDatosFormulario(request);

                request.getRequestDispatcher("/viajes/formulario.jsp").forward(request, response);
            }

        } catch (SQLException e) {

            throw new ServletException("Error al consultar los viajes", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {

                Viaje viaje = obtenerViajeDesdeRequest(request);
                viaje.setEstado("programado");
                viajeService.insertar(viaje);
                response.sendRedirect(request.getContextPath() + "/viajes");

            } else if ("actualizar".equals(accion)) {

                int idViaje = Integer.parseInt(request.getParameter("id"));

                Viaje viaje = obtenerViajeDesdeRequest(request);
                viaje.setIdViaje(idViaje);
                viajeService.actualizar(viaje);

                response.sendRedirect(request.getContextPath() + "/viajes");

            } else if ("eliminar".equals(accion)) {

                int idViaje = Integer.parseInt(request.getParameter("id")); 
                viajeService.eliminar(idViaje); 
                response.sendRedirect(request.getContextPath() + "/viajes");
            }

        } catch (SQLException e) {

            throw new ServletException("Error al modificar el viaje", e);
        }
    }

    private Viaje obtenerViajeDesdeRequest(HttpServletRequest request) {

        int idRuta = Integer.parseInt(request.getParameter("idRuta"));

        String numeroPlaca = request.getParameter("numeroPlaca");
        String noLicencia = request.getParameter("noLicencia");
        String fechaHoraSalida = request.getParameter("fechaHoraSalida");
        String horaEstimadaLlegada = request.getParameter("horaEstimadaLlegada");
        String tipoViaje = request.getParameter("tipoViaje");

        return new Viaje(idRuta, numeroPlaca, noLicencia, fechaHoraSalida, horaEstimadaLlegada, tipoViaje, "programado");
    }

    private void cargarDatosFormulario(HttpServletRequest request) throws SQLException {

        request.setAttribute("rutas", rutaService.listar() );

        request.setAttribute( "buses",busService.obtenerTodo());

        request.setAttribute("choferes", choferService.obtenerTodo());
    }

  
}


 
