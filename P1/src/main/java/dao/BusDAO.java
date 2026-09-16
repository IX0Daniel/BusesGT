package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bus;

/**
 * @author dz
 */
public class BusDAO {

    public void crear(Bus bus) throws SQLException {
        String sql = "INSERT INTO bus (numero_placa, ruta_foto, marca, modelo, año, capacidad, kilometraje, estado, codigo_sucursal, codigo_sucursal_actual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bus.getNumeroPlaca());
            ps.setString(2, bus.getRutaFoto());
            ps.setString(3, bus.getMarca());
            ps.setString(4, bus.getModelo());
            ps.setInt(5, bus.getAño());
            ps.setInt(6, bus.getCapacidad());
            ps.setDouble(7, bus.getKilometraje());
            ps.setString(8, bus.getEstado());
            ps.setInt(9, bus.getCodigoSucursal());
            ps.setInt(10, bus.getCodigoSucursalActual());

            ps.executeUpdate();

        }
        /*catch (SQLException e) {
            System.err.println("Error al crear bus: " + e.getMessage());
            return null;
        }
         */
    }

    public List<Bus> obtenerTodo() throws SQLException {

        List<Bus> lista = new ArrayList<>();
        String sql = "SELECT * FROM bus";

        try (Connection con = ConexionDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getString("numero_placa"),
                        rs.getString("ruta_foto"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("año"),
                        rs.getInt("capacidad"),
                        rs.getDouble("kilometraje"),
                        rs.getString("estado"),
                        rs.getInt("codigo_sucursal"),
                        rs.getInt("codigo_sucursal_actual")
                );

                lista.add(bus);
            }

        }

        return lista;
    }

    public void actualizar(Bus bus) throws SQLException {

        String sql = "UPDATE bus SET ruta_foto = ?, capacidad = ?, kilometraje = ?, estado = ?, codigo_sucursal = ? WHERE numero_placa = ? ";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, bus.getRutaFoto());
            sentencia.setInt(2, bus.getCapacidad());
            sentencia.setDouble(3, bus.getKilometraje());
            sentencia.setString(4, bus.getEstado());
            sentencia.setInt(5, bus.getCodigoSucursal());
            sentencia.setString(6, bus.getNumeroPlaca());

            sentencia.executeUpdate();
        }
    }
    
    
    
    public void cambiatEstado(Bus bus) throws SQLException {
        
        String sql = "UPDATE bus SET estado = ? WHERE numero_placa = ? ";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setString(1, bus.getEstado());
            sentencia.setString(2, bus.getNumeroPlaca());

            sentencia.executeUpdate();
        }
    }
    
    
    //para verificar si hay algún viaje en curso o uno pendiente, solo necesito un boolean si si o no :b
    public boolean verificarViajesPendientes(Bus bus) throws SQLException {

        String sql = "SELECT COUNT(*) FROM viaje WHERE numero_placa = ? AND estado IN ('programado', 'en_curso')";
        //String sql = "SELECT COUNT(*) AS viajes FROM viaje WHERE numero_placa = ? AND estado IN ('programado', 'en_curso')";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, bus.getNumeroPlaca());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1) > 0;
                    //return rs.getInt("cantidad") > 0;
                }
            }
        }
        return false;
    }

    
    

    public Bus buscarPorPlaca(String numeroPlaca) throws SQLException {

        String sql = "SELECT * FROM bus WHERE numero_placa = ?";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, numeroPlaca);

            try (ResultSet rs = sentencia.executeQuery()) {

                if (rs.next()) {

                    return new Bus(
                            rs.getString("numero_placa"),
                            rs.getString("ruta_foto"),
                            rs.getString("marca"),
                            rs.getString("modelo"),
                            rs.getInt("año"),
                            rs.getInt("capacidad"),
                            rs.getDouble("kilometraje"),
                            rs.getString("estado"),
                            rs.getInt("codigo_sucursal"),
                            rs.getInt("codigo_sucursal_actual")
                    );
                }
            }
        }

        return null;
    }
}
