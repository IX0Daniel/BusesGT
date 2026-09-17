package servicio;

import dao.ViajeDAO;
import java.sql.SQLException;
import java.util.List;
import model.Viaje;

/**
 * @author dz
 */
public class ViajeService {
    
    private final ViajeDAO viajeDAO;

    public ViajeService() {
        viajeDAO = new ViajeDAO();
    }

    public List<Viaje> listar() throws SQLException {
        return viajeDAO.listar();
    }

    public Viaje buscarPorId(int idViaje) throws SQLException {
        return viajeDAO.buscarPorId(idViaje);
    }

    public void insertar(Viaje viaje) throws SQLException {
        viajeDAO.insertar(viaje);
    }

    public void actualizar(Viaje viaje) throws SQLException {
        viajeDAO.actualizar(viaje);
    }

    public void eliminar(int idViaje) throws SQLException {
        viajeDAO.eliminar(idViaje);
    } 

    
}
