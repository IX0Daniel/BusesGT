package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Bus;
import model.Sucursal;
import servicio.BusService;
import servicio.SucursalService;

/**
 *
 * @author dz
 */
@WebServlet(name = "BusServlet", urlPatterns = {"/buses"})
public class BusServlet extends HttpServlet {
 
    private BusService busService;
    private SucursalService sucursalService;
    
    @Override
    public void init() {
        busService = new BusService();
        sucursalService = new SucursalService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");
        System.out.println("///////// " + accion);
        try {

            if (accion == null) {
                listar(request, response);

            } else if (accion.equals("nuevo")) {
                mostrarFormularioNuevo(request, response);

            } else if (accion.equals("editar")) {
                mostrarFormularioEditar(request, response);

            } else {
                listar(request, response);
            }

        } catch (SQLException e) {
            throw new ServletException("Error al procesar sucursales", e);
        }
    }

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if ("crear".equals(accion)) {
                crear(request, response);

            } else if ("actualizar".equals(accion)) {
                actualizar(request, response);

            } else {
                response.sendRedirect(request.getContextPath() + "/buses");
            }

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al procesar sucursales", e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        List<Bus> buses = busService.obtenerTodo();
        request.setAttribute("buses", buses);
        
                
        System.out.println("se van a listar los buses");
        request.getRequestDispatcher("/Bus/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        List<Sucursal> sucursales = sucursalService.listar();
        request.setAttribute("sucursales", sucursales);
        request.getRequestDispatcher("/Bus/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        
        List<Sucursal> sucursales = new SucursalService().listar();
        request.setAttribute("sucursales", sucursales);
        
        String numeroPlaca = request.getParameter("placa");
        System.out.println("numero placa: " + numeroPlaca);
        Bus bus = busService.buscarPorPlaca(numeroPlaca);
        

        boolean viajesPendientes = busService.verificarViajes(bus);
        request.setAttribute("viajes", viajesPendientes);
      


        request.setAttribute("bus", bus  );

        request.getRequestDispatcher("/Bus/formulario.jsp").forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String numeroPlaca = request.getParameter("numeroPlaca");
        String rutaFoto = request.getParameter("rutaFoto");
        String marca = request.getParameter("marca");
        String modelo = request.getParameter("modelo");
        int año = Integer.parseInt(request.getParameter("año"));
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        double kilometraje = Double.parseDouble(request.getParameter("kilometraje"));
        String estado = request.getParameter("estado");
        int codigoSucursal = Integer.parseInt(request.getParameter("codigoSucursal"));
        int codigoSucursalActual = Integer.parseInt(request.getParameter("codigoSucursalActual"));
       
        Bus bus = new Bus(numeroPlaca, rutaFoto, marca, modelo, año, capacidad, kilometraje, estado, codigoSucursal, codigoSucursalActual);

        busService.crear(bus);

        response.sendRedirect(request.getContextPath() + "/buses");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String numeroPlaca = request.getParameter("numeroPlaca");
        System.out.println("Buscar nueva placa"+ numeroPlaca);
        Bus bus = busService.buscarPorPlaca(numeroPlaca);        
       
        String rutaFoto = request.getParameter("rutaFoto");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        double kilometraje = Double.parseDouble(request.getParameter("kilometraje"));
        String estado = request.getParameter("estado");
        int codigoSucursal = Integer.parseInt(request.getParameter("codigoSucursal"));
         
        bus.setRutaFoto(rutaFoto);
        bus.setCapacidad(capacidad);
        bus.setKilometraje(kilometraje);
        bus.setEstado(estado);
        bus.setCodigoSucursal(codigoSucursal);
       
        busService.actualizar(bus);
        
        response.sendRedirect(request.getContextPath() + "/buses");
    }
    
    
    private void actualizarEstado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String numeroPlaca = request.getParameter("numeroPlaca");
        System.out.println("Buscar nueva placa"+ numeroPlaca);
        Bus bus = busService.buscarPorPlaca(numeroPlaca);        
       
        String rutaFoto = request.getParameter("rutaFoto");
        int capacidad = Integer.parseInt(request.getParameter("capacidad"));
        double kilometraje = Double.parseDouble(request.getParameter("kilometraje"));
        String estado = request.getParameter("estado");
        int codigoSucursal = Integer.parseInt(request.getParameter("codigoSucursal"));
         
        bus.setRutaFoto(rutaFoto);
        bus.setCapacidad(capacidad);
        bus.setKilometraje(kilometraje);
        bus.setEstado(estado);
        bus.setCodigoSucursal(codigoSucursal);
       
        busService.actualizar(bus);
        
        response.sendRedirect(request.getContextPath() + "/buses");
    }
    
    
    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
