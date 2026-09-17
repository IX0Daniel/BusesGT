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
import model.Ruta;
import model.Sucursal;
import servicio.RutaService;
import servicio.SucursalService;

/**
 *
 * @author dz
 */
@WebServlet(name = "RutaServlet", urlPatterns = {"/rutas"})
public class RutaServlet extends HttpServlet {

   
    
    private RutaService rutaService;
    private SucursalService sucursalService;

    @Override
    public void init() {
        rutaService = new RutaService();
        sucursalService = new SucursalService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {

            if (accion == null) { 
                listar(request, response); 
            } else if (accion.equals("editar")) { 
                mostrarFormularioEditar(request, response);
            } else if (accion.equals("nuevo")){
                mostrarFormularioNuevo(request, response);
            
            }

        } catch (SQLException e) {
            throw new ServletException("Error al consultar las rutas", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        try {

            if ("actualizar".equals(accion)) {

                int idRuta = Integer.parseInt(request.getParameter("id"));
                double distancia = Double.parseDouble(request.getParameter("distancia"));
                double precioBoleto = Double.parseDouble(request.getParameter("precioBoleto"));

                Ruta ruta = new Ruta();

                ruta.setIdRuta(idRuta); 
                ruta.setDistancia(distancia);
                ruta.setPrecioBoleto(precioBoleto);

                rutaService.actualizar(ruta);

                response.sendRedirect(request.getContextPath() + "/rutas");

            } else if ("eliminar".equals(accion)) {

                
                int idRuta = Integer.parseInt(request.getParameter("id"));
                Ruta ruta = new Ruta();
                ruta.setIdRuta(idRuta);
                
                if (rutaService.tieneViajes(ruta)) {
                    response.sendRedirect(request.getContextPath()+ "/rutas?error=tieneViajes");
                    return;
                }

                rutaService.eliminar(idRuta);

                response.sendRedirect(request.getContextPath() + "/rutas");
            } else {
            
                crear(request, response);
                
            }

        } catch (SQLException e) {
            throw new ServletException("Error al modificar la ruta", e);
        }
    }
    
  
      private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

          List<Ruta> rutas = rutaService.listar();

          request.setAttribute("rutas", rutas);

          request.getRequestDispatcher("/Ruta/listar.jsp").forward(request, response);

          
        
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
        List<Sucursal> sucursales = sucursalService.listar();
        request.setAttribute("sucursales", sucursales);
        request.getRequestDispatcher("/Ruta/formulario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
     
        int idRuta = Integer.parseInt(request.getParameter("idRuta"));

        Ruta ruta = rutaService.buscarPorId(idRuta);

        request.setAttribute("ruta", ruta);
 
        List<Sucursal> sucursales = new SucursalService().listar();
        request.setAttribute("sucursales", sucursales);
         


        request.getRequestDispatcher("/Ruta/formulario.jsp").forward(request, response);
    }

    private void crear(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        
        
        
        int codigoSucursalOrigen = Integer.parseInt(request.getParameter("sucursalOrigen"));
        int codigoSucursalDestino = Integer.parseInt(request.getParameter("sucursalDestino"));
        double distancia = Double.parseDouble(request.getParameter("distancia"));
        double precioBoleto = Double.parseDouble(request.getParameter("precioBoleto"));
        
        Ruta ruta = new Ruta(codigoSucursalOrigen, codigoSucursalDestino, distancia, precioBoleto);
        
        rutaService.crear(ruta);

        response.sendRedirect(request.getContextPath() + "/rutas");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
 
        double distancia = Double.parseDouble(request.getParameter("distancia"));
        double precioBoleto = Double.parseDouble(request.getParameter("precio_boleto"));
        
        Ruta ruta = new Ruta();
        ruta.setDistancia(distancia);
        ruta.setPrecioBoleto(precioBoleto);
        
        
       
        rutaService.actualizar(ruta);
        
        response.sendRedirect(request.getContextPath() + "/rutas");
    }
     
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
