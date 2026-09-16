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
import model.Sucursal;
import servicio.SucursalService;

/**
 *
 * @author dz
 */
@WebServlet(name = "Sucursal", urlPatterns = {"/sucursales"})
public class SucursalSevlet extends HttpServlet {

    
    private SucursalService sucursalService;

    @Override
    public void init() {
        sucursalService = new SucursalService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        
        System.out.println("Accion: " + accion);
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

            } else if ("eliminar".equals(accion)) {
                eliminar(request, response);

            } else {
                response.sendRedirect(
                        request.getContextPath() + "/sucursales"
                );
            }

        } catch (SQLException e) {
            throw new ServletException(
                    "Error al procesar sucursales", e);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        List<Sucursal> sucursales = sucursalService.listar();
        request.setAttribute("sucursales", sucursales);
        request.getRequestDispatcher("/Sucursal/listar.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/Sucursal/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String parametroId = request.getParameter("id");

        int codigoSucursal = Integer.parseInt(parametroId);

        Sucursal sucursal = sucursalService.buscarPorId(codigoSucursal);

        request.setAttribute("sucursal", sucursal);

        request.getRequestDispatcher("/Sucursal/formulario.jsp").forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Sucursal sucursal = new Sucursal(nombre, direccion, descripcion);

        sucursalService.crear(sucursal);

        response.sendRedirect(request.getContextPath() + "/sucursales");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int codigoSucursal = Integer.parseInt(request.getParameter("codigoSucursal"));

        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String descripcion = request.getParameter("descripcion");
        Sucursal sucursal = new Sucursal(codigoSucursal, nombre, direccion, descripcion);

        sucursalService.actualizar(sucursal);

        response.sendRedirect(request.getContextPath() + "/sucursales");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        int codigoSucursal = Integer.parseInt(request.getParameter("id"));

        sucursalService.eliminar(codigoSucursal);

        response.sendRedirect(request.getContextPath() + "/sucursales");
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
