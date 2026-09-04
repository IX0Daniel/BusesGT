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
    
    public Bus create(String numeroPlaca, String rutaFoto, String marca, String modelo, int año, int capacidad, double kilometraje, String estado, int codigoSucursal, int codigoSucursalActual) throws SQLException {
        String sql = "INSERT INTO bus (numero_placa, ruta_foto, marca, modelo, año, capacidad, kilometraje, estado, codigo_sucursal, codigo_sucural_actual) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numeroPlaca);
            ps.setString(2, rutaFoto);
            ps.setString(3, marca);
            ps.setString(4, modelo);
            ps.setInt(5, año);
            ps.setInt(6, capacidad);
            ps.setDouble(7, kilometraje);
            ps.setString(8, estado);
            ps.setInt(9, codigoSucursal);
            ps.setInt(10, codigoSucursalActual);
 
            ps.executeUpdate();
            return new Bus(numeroPlaca, rutaFoto, marca, modelo, año, capacidad, kilometraje, estado, codigoSucursal, codigoSucursalActual);
            
        }catch (SQLException e) {
            System.err.println("Error al crear bus: " + e.getMessage());
            return null;
        }
    }
    
     
    
    
     public List<Bus> getAll() throws Exception {

        List<Bus> lista = new ArrayList<>();

        String sql = "SELECT * FROM categoria";

        try (Connection con = ConexionDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
    
}
