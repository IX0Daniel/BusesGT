package servicio;

import dao.ChoferDAO;
import dao.PerfilUsuarioDAO;
import dao.UsuarioDAO;
import database.ConexionDB;
import dto.ChoferDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Chofer;
import model.PerfilUsuario;
import model.Usuario;

/**
 *
 * @author dz
 */
public class ChoferService {

    private final ChoferDAO choferDAO;
    private final UsuarioDAO usuarioDAO;
    private final PerfilUsuarioDAO perfilDAO;

    public ChoferService() {
        choferDAO = new ChoferDAO();
        usuarioDAO = new UsuarioDAO();
        perfilDAO = new PerfilUsuarioDAO();
    }

    public List<ChoferDTO> obtenerTodo() throws SQLException {
        return choferDAO.listar();
    }

    public Chofer buscarPorLicencia(String noLicencia) throws SQLException {
        return choferDAO.buscarPorLicencia(noLicencia);
    }

    public void crear(Usuario usuario, PerfilUsuario perfil, Chofer chofer) throws SQLException {
        try (Connection conexion = ConexionDB.getConnection()) {
            try {
                conexion.setAutoCommit(false);
                usuarioDAO.crear(usuario, conexion);
                perfilDAO.crear(perfil, conexion);
                choferDAO.insertar(chofer, conexion);
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }

    public void actualizar(PerfilUsuario perfil, Chofer chofer) throws SQLException {
        try (Connection conexion = ConexionDB.getConnection()) {
            try {
                conexion.setAutoCommit(false);
                perfilDAO.actualizarPerfil(perfil, conexion);
                choferDAO.actualizar(chofer, conexion);
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                throw e;
            }
        }
    }


    
    
    
    
    
    
    /*public List<Chofer> obtenerTodo() {
        
       
        return null;
    }*/
    
}
