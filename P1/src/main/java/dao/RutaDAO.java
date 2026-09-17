package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Bus;
import model.Ruta;

/**
 * @author dz
 */
public class RutaDAO {
    
    
     
    
    public void crear(Ruta ruta) throws SQLException{
    
        String sql = "INSERT INTO ruta(codigo_sucursal_origen , codigo_sucursal_destino, distancia, precio_boleto) VALUES(?, ?, ?, ?)";
        
        try(Connection conexion = ConexionDB.getConnection(); PreparedStatement statment = conexion.prepareStatement(sql)){
        
        
            statment.setInt(1, ruta.getCodigoSucursalOrigen());
            statment.setInt(2, ruta.getCodigoSucursalDestino());
            statment.setDouble(3, ruta.getDistancia());
            statment.setDouble(4, ruta.getPrecioBoleto());
        
            statment.executeUpdate();
        
        }                                   
    }

    public List<Ruta> obtenerTodo() throws SQLException {

        List<Ruta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ruta";

        try (Connection con = ConexionDB.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ruta ruta = new Ruta(
                        rs.getInt("id_ruta"),
                        rs.getInt("codigo_sucursal_origen"),
                        rs.getInt("codigo_sucursal_destino"),
                        rs.getDouble("distancia"),
                        rs.getDouble("precio_boleto")
                );

                lista.add(ruta);
            }

        }

        return lista;
    }

    
    public Ruta buscarPorId(int codigoSucursal) throws SQLException {

        String sql = "SELECT id_ruta, codigo_sucursal_origen, codigo_sucursal_destino, distancia, precio_boleto FROM ruta WHERE id_ruta = ?";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, codigoSucursal);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    return new Ruta(
                            resultado.getInt("id_ruta"),
                            resultado.getInt("codigo_sucursal_origen"),
                            resultado.getInt("codigo_sucursal_destino"),
                            resultado.getDouble("distancia"),
                            resultado.getDouble("precio_boleto")
                    );
                }
            }
        }

        return null;
    }
    
    
    public void actualizar(Ruta ruta) throws SQLException {

        String sql = "UPDATE ruta SET distancia = ?, precio_boleto = ? WHERE id_ruta = ? ";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setDouble(1, ruta.getDistancia());
            sentencia.setDouble(2, ruta.getPrecioBoleto());
            sentencia.setInt(3, ruta.getIdRuta());

            sentencia.executeUpdate();
        }
    }
    
    ///no funciona todav[ia]
    public void eliminar(int codigoSucursal) throws SQLException {

        String sql = "DELETE FROM sucursal WHERE codigo_sucursal = ?";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, codigoSucursal);
            sentencia.executeUpdate();
        }
    }
    
    
    public boolean tieneViajes(Ruta ruta) throws SQLException {

        String sql = "SELECT COUNT(*) AS cantidad FROM viaje WHERE id_ruta = ?";

        try (Connection conexion = ConexionDB.getConnection();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, ruta.getIdRuta());

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt("cantidad") > 0;
                }
            }
        }

        return false;
    }
    
    
    
   
    
     
    
}
