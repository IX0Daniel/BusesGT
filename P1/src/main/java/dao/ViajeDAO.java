package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Viaje;

/**
 * @author dz
 */
public class ViajeDAO {
    
   
    public List<Viaje> listar() throws SQLException {

        String sql = "SELECT id_viaje, id_ruta, numero_placa, no_licencia, fecha_hora_salida, hora_estimada_llegada, tipo_viaje, estado FROM viaje ORDER BY fecha_hora_salida ";

        List<Viaje> viajes = new ArrayList<>();

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Viaje viaje = new Viaje();

                viaje.setIdViaje(rs.getInt("id_viaje"));
                viaje.setIdRuta(rs.getInt("id_ruta"));
                viaje.setNumeroPlaca(rs.getString("numero_placa"));
                viaje.setNoLicencia(rs.getString("no_licencia"));
                viaje.setFechaHoraSalida(rs.getString("fecha_hora_salida"));
                viaje.setHoraEstimadaLlegada(rs.getString("hora_estimada_llegada"));
                viaje.setTipoViaje(rs.getString("tipo_viaje"));
                viaje.setEstado(rs.getString("estado"));

                viajes.add(viaje);
            }
        }

        return viajes;
    }

    public Viaje buscarPorId(int idViaje) throws SQLException {

        String sql = "SELECT id_viaje, id_ruta, numero_placa, no_licencia, fecha_hora_salida, hora_estimada_llegada, tipo_viaje,  estado FROM viaje WHERE id_viaje = ? ";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idViaje);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Viaje viaje = new Viaje();

                    viaje.setIdViaje(rs.getInt("id_viaje"));
                    viaje.setIdRuta(rs.getInt("id_ruta"));
                    viaje.setNumeroPlaca(rs.getString("numero_placa"));
                    viaje.setNoLicencia(rs.getString("no_licencia"));
                    viaje.setFechaHoraSalida(rs.getString("fecha_hora_salida"));
                    viaje.setHoraEstimadaLlegada(rs.getString("hora_estimada_llegada"));
                    viaje.setTipoViaje(rs.getString("tipo_viaje"));
                    viaje.setEstado(rs.getString("estado"));

                    return viaje;
                }
            }
        }

        return null;
    }

    public void insertar(Viaje viaje) throws SQLException {

        String sql = "INSERT INTO viaje (id_ruta, numero_placa, no_licencia, fecha_hora_salida, hora_estimada_llegada, tipo_viaje, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, viaje.getIdRuta());
            ps.setString(2, viaje.getNumeroPlaca());
            ps.setString(3, viaje.getNoLicencia());
            ps.setString(4, viaje.getFechaHoraSalida());
            ps.setString(5, viaje.getHoraEstimadaLlegada());
            ps.setString(6, viaje.getTipoViaje());
            ps.setString(7, viaje.getEstado());

            ps.executeUpdate();
        }
    }

    public void actualizar(Viaje viaje) throws SQLException {

        String sql = "UPDATE viaje SET id_ruta = ?, numero_placa = ?, no_licencia = ?,fecha_hora_salida = ?, hora_estimada_llegada = ?, tipo_viaje = ? WHERE id_viaje = ?";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, viaje.getIdRuta());
            ps.setString(2, viaje.getNumeroPlaca());
            ps.setString(3, viaje.getNoLicencia());
            ps.setString(4, viaje.getFechaHoraSalida());
            ps.setString(5, viaje.getHoraEstimadaLlegada());
            ps.setString(6, viaje.getTipoViaje());
            ps.setInt(7, viaje.getIdViaje());

            ps.executeUpdate();
        }
    }

    public void eliminar(int idViaje) throws SQLException {

        String sql = "DELETE FROM viaje WHERE id_viaje = ? ";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, idViaje);

            ps.executeUpdate();
        }
    } 
    
}
