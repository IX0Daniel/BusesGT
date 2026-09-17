package servicio;

import dao.PerfilUsuarioDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.PerfilUsuario;

/**
 * @author dz
 */
public class PerfilUsuarioService {
    
    private final PerfilUsuarioDAO perfilDAO;

    public PerfilUsuarioService() {
        perfilDAO = new PerfilUsuarioDAO();
    }

    public List<PerfilUsuario> obtenerTodo() throws SQLException {
        return perfilDAO.obtenetTodo();
    }

    public PerfilUsuario buscarPorCorreo(String correo) throws SQLException {

        return perfilDAO.buscarPorCorreo(correo);
    }

    public void crear(PerfilUsuario perfil, Connection conexion) throws SQLException {

        perfilDAO.crear(perfil, conexion);
    }

    public void actualizar(PerfilUsuario perfil) throws SQLException {

        perfilDAO.actualizar(perfil);
    }

    public void actualizarSaldo(String correo, double saldo) throws SQLException {

        perfilDAO.actualizarSaldo(correo, saldo);
    }

}
