package servicio;

import dao.PerfilUsuarioDAO;
import dao.UsuarioDAO;
import database.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.PerfilUsuario;
import model.Usuario;

/**
 *
 * @author dz
 */
public class UsuarioService { 
 
    private UsuarioDAO usuarioDAO;
    private PerfilUsuarioDAO perfilDAO;

    public UsuarioService() {
        usuarioDAO = new UsuarioDAO();
        perfilDAO = new PerfilUsuarioDAO();
    }

    public List<Usuario> obtenerTodo() throws SQLException {
        return usuarioDAO.obtenerTodo();
    }

    public Usuario buscarPorCorreo(String correo)
            throws SQLException {

        return usuarioDAO.buscarPorCorreo(correo);
    }

    public void actualizar(Usuario usuario)
            throws SQLException {

        usuarioDAO.actualizar(usuario);
    }

    public void cambiarEstado(String correo, String estado)
            throws SQLException {

        usuarioDAO.cambiarEstado(correo, estado);
    }

    public void registrar(Usuario usuario, PerfilUsuario perfil) throws SQLException {

        try (Connection conexion = ConexionDB.getConnection()) {

            try {
                conexion.setAutoCommit(false);

                usuarioDAO.crear(usuario, conexion);
                perfilDAO.crear(perfil, conexion);

                conexion.commit(); 
                
            } catch (SQLException e) {

                conexion.rollback();

                throw e;
            }
        }
    } 
    
}
