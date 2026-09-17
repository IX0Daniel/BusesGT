package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author dz
 */
public class UsuarioDAO {
    

    public List<Usuario> obtenerTodo() throws SQLException {

        String sql = "SELECT correo, contraseña, rol, estado FROM usuario ORDER BY correo";

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setCorreo(rs.getString("correo"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));

                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    public Usuario buscarPorCorreo(String correo) throws SQLException {

        String sql = "SELECT correo, contraseña, rol, estado FROM usuario WHERE correo = ?";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setContraseña(rs.getString("contraseña"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setEstado(rs.getString("estado"));

                    return usuario;
                }
            }
        }

        return null;
    }

    /*public void crear(Usuario usuario) throws SQLException {

        String sql = "INSERT INTO usuario (correo, contraseña, rol, estado) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEstado());

            ps.executeUpdate();
        }
    }*/

    public void actualizar(Usuario usuario) throws SQLException {

        String sql = "UPDATE usuario SET contraseña = ?, rol = ? WHERE correo = ? ";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getContraseña());
            ps.setString(2, usuario.getRol());
            ps.setString(3, usuario.getCorreo());

            ps.executeUpdate();
        }
    }

    public void cambiarEstado(String correo, String estado) throws SQLException {

        String sql = "UPDATE usuario SET estado = ? WHERE correo = ?";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setString(2, correo);

            ps.executeUpdate();
        }
    } 

    public void crear(Usuario usuario, Connection conexion) throws SQLException {

        String sql = "INSERT INTO usuario (correo, contraseña, rol, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getContraseña());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getEstado());

            ps.executeUpdate();
        }
    }
    
}