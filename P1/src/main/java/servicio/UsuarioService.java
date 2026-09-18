package servicio;

import dao.PerfilUsuarioDAO;
import dao.UsuarioDAO;
import database.ConexionDB;
import dto.PerfilUsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public List<PerfilUsuarioDTO> obtenerPerfiles() throws SQLException{
        
        List<PerfilUsuarioDTO> perfiles = new ArrayList<>();
        
        for(Usuario usuario: usuarioDAO.obtenerTodo() ){
        
            perfiles.add(new PerfilUsuarioDTO(perfilDAO.buscarPorCorreo(usuario.getCorreo()), usuario));
        
        }
        return perfiles;
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
    
    
    public Usuario autenticar(String correo, String contraseña) throws SQLException {

        Usuario usuario = usuarioDAO.buscarPorCorreo(correo);

        if (usuario == null) {
            return null;
        }

        if (!usuario.getContraseña().equals(contraseña)) {
            return null;
        }

        if (!"activo".equals(usuario.getEstado())) {
            return null;
        }

        return usuario;
    }
    
    
}
