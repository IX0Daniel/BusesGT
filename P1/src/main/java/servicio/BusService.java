package servicio;

import dao.BusDAO;
import dao.SucursalDAO;
import java.sql.SQLException;
import java.util.List;
import model.Bus;
import model.Sucursal;

/*
 * @author dz
 */
public class BusService {
    
    
    private BusDAO busDao;

    public BusService() {
        busDao = new BusDAO();
    } 

    public List<Bus> obtenerTodo() throws SQLException {
        return busDao.obtenerTodo();
    }

    public Bus buscarPorPlaca(String numeroPlaca) throws SQLException {
        return busDao.buscarPorPlaca(numeroPlaca);
    }

    public void crear(Bus bus) throws SQLException {
        busDao.crear(bus);
    }

    public void actualizar(Bus bus) throws SQLException {
        busDao.actualizar(bus);
    }
    
    public boolean verificarViajes(Bus bus) throws SQLException{
    
        return busDao.verificarViajesPendientes(bus);
    
    }
 
    
    
    
}
