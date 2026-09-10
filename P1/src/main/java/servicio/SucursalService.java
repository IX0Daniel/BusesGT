package servicio;

import dao.SucursalDAO;
import java.sql.SQLException;
import java.util.List;
import model.Sucursal;

/**
 *
 * @author dz
 */
public class SucursalService {
    
    private final SucursalDAO sucursalDAO;

    public SucursalService() {
        this.sucursalDAO = new SucursalDAO();
    }

    public List<Sucursal> listar() throws SQLException {
        return sucursalDAO.obtenerTodo();
    }

    public Sucursal buscarPorId(int codigoSucursal) throws SQLException {
        return sucursalDAO.buscarPorId(codigoSucursal);
    }

    public void crear(Sucursal sucursal) throws SQLException {
        sucursalDAO.crear(sucursal);
    }

    public void actualizar(Sucursal sucursal) throws SQLException {
        sucursalDAO.actualizar(sucursal);
    }

    public void eliminar(int codigoSucursal) throws SQLException {
        sucursalDAO.eliminar(codigoSucursal);
    }
}
