package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PerfilUsuario;

/**
 *
 * @author dz
 */
public class PerfilUsuarioDAO {

    public List<PerfilUsuario> obtenetTodo() throws SQLException {

        String sql = "SELECT correo, dpi, nit, telefono, direccion, nombre_completo, saldo FROM perfil_usuario ORDER BY nombre_completo";

        List<PerfilUsuario> perfiles = new ArrayList<>();

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                PerfilUsuario perfil = new PerfilUsuario();

                perfil.setCorreo(rs.getString("correo"));
                perfil.setDpi(rs.getString("dpi"));
                perfil.setNit(rs.getString("nit"));
                perfil.setTelefono(rs.getString("telefono"));
                perfil.setDireccion(rs.getString("direccion"));
                perfil.setNombreCompleto(rs.getString("nombre_completo"));
                perfil.setSaldo(rs.getDouble("saldo"));

                perfiles.add(perfil);
            }
        }

        return perfiles;
    }

    public PerfilUsuario buscarPorCorreo(String correo) throws SQLException {

        String sql = "SELECT correo, dpi, nit, telefono, direccion, nombre_completo, saldo FROM perfil_usuario WHERE correo = ? ";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, correo);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    PerfilUsuario perfil = new PerfilUsuario();

                    perfil.setCorreo(rs.getString("correo"));
                    perfil.setDpi(rs.getString("dpi"));
                    perfil.setNit(rs.getString("nit"));
                    perfil.setTelefono(rs.getString("telefono"));
                    perfil.setDireccion(rs.getString("direccion"));
                    perfil.setNombreCompleto( rs.getString("nombre_completo"));
                    perfil.setSaldo(rs.getDouble("saldo"));

                    return perfil;
                }
            }
        }

        return null;
    }
    
    
    
    public void crear(PerfilUsuario perfil, Connection conexion) throws SQLException {

        String sql = "INSERT INTO perfil_usuario (correo, dpi, nit, telefono, direccion, nombre_completo, saldo) VALUES (?, ?, ?, ?, ?, ?, ?) ";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, perfil.getCorreo());
            ps.setString(2, perfil.getDpi());
            ps.setString(3, perfil.getNit());
            ps.setString(4, perfil.getTelefono());
            ps.setString(5, perfil.getDireccion());
            ps.setString(6, perfil.getNombreCompleto());
            ps.setDouble(7, perfil.getSaldo());

            ps.executeUpdate();
        }
    }

    
    

    /*public void insertar(PerfilUsuario perfil) throws SQLException {

        String sql = "INSERT INTO perfil_usuario (correo, dpi, nit, telefono, direccion, nombre_completo, saldo) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, perfil.getCorreo());
            ps.setString(2, perfil.getDpi());
            ps.setString(3, perfil.getNit());
            ps.setString(4, perfil.getTelefono());
            ps.setString(5, perfil.getDireccion());
            ps.setString(6, perfil.getNombreCompleto());
            ps.setDouble(7, perfil.getSaldo());

            ps.executeUpdate();
        }
    }*/

    public void actualizar(PerfilUsuario perfil) throws SQLException {

        String sql = "UPDATE perfil_usuario SET dpi = ?, nit = ?, telefono = ?, direccion = ?, nombre_completo = ? WHERE correo = ?";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, perfil.getDpi());
            ps.setString(2, perfil.getNit());
            ps.setString(3, perfil.getTelefono());
            ps.setString(4, perfil.getDireccion());
            ps.setString(5, perfil.getNombreCompleto());
            ps.setString(6, perfil.getCorreo());

            ps.executeUpdate();
        }
    }

    public void actualizarSaldo(String correo, double saldo)
            throws SQLException {

        String sql = "UPDATE perfil_usuario SET saldo = ? WHERE correo = ? ";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setDouble(1, saldo);
            ps.setString(2, correo);

            ps.executeUpdate();
        }
    }

}
