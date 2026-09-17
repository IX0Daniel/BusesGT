package servicio;

import dao.RutaDAO;
import java.sql.SQLException;
import java.util.List;
import model.Bus;
import model.Ruta;

/**
 *
 * @author dz
 */
public class RutaService {
    
     private final RutaDAO rutaDAO;

    public RutaService() {
        rutaDAO = new RutaDAO();
    }

    public List<Ruta> listar() throws SQLException {
        return rutaDAO.obtenerTodo();
    }

    public Ruta buscarPorId(int idRuta) throws SQLException {
        return rutaDAO.buscarPorId(idRuta);
    }

    public void crear(Ruta ruta) throws SQLException {
        rutaDAO.crear(ruta);
    }

    public void actualizar(Ruta ruta) throws SQLException {
        rutaDAO.actualizar(ruta);
    }

    public boolean tieneViajes(Ruta ruta) throws SQLException {
        return rutaDAO.tieneViajes(ruta);
    }

    public void eliminar(int idRuta) throws SQLException {
        rutaDAO.eliminar(idRuta);
    }
    
    
    
}
