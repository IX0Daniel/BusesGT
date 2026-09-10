package dao;

import database.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Sucursal;

/**
 * @author dz
 */
public class SucursalDAO {
   
    public List<Sucursal> obtenerTodo() throws SQLException {

        List<Sucursal> sucursales = new ArrayList<>();

        String sql = "SELECT codigo_sucursal, nombre, direccion, descripcion FROM sucursal ORDER BY codigo_sucursal";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Sucursal sucursal = new Sucursal(
                    resultado.getInt("codigo_sucursal"),
                    resultado.getString("nombre"),
                    resultado.getString("direccion"),
                    resultado.getString("descripcion")
                );

                sucursales.add(sucursal);
            }
        }

        return sucursales;
    }
    
    
    public Sucursal buscarPorId(int codigoSucursal) throws SQLException {

        String sql = "SELECT codigo_sucursal, nombre, direccion, descripcion FROM sucursal WHERE codigo_sucursal = ?";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, codigoSucursal);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    return new Sucursal(
                        resultado.getInt("codigo_sucursal"),
                        resultado.getString("nombre"),
                        resultado.getString("direccion"),
                        resultado.getString("descripcion")
                    );
                }
            }
        }

        return null;
    }
    
    
    public void crear(Sucursal sucursal) throws SQLException {

        String sql = "INSERT INTO sucursal (nombre, direccion, descripcion) VALUES (?, ?, ?) ";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, sucursal.getNombre());
            sentencia.setString(2, sucursal.getDireccion());
            sentencia.setString(3, sucursal.getDescripcion());

            sentencia.executeUpdate();
        }
    }
    
    public void actualizar(Sucursal sucursal) throws SQLException {

        String sql = "UPDATE sucursal SET nombre = ?, direccion = ?, descripcion = ? WHERE codigo_sucursal = ?";

        try (Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, sucursal.getNombre());
            sentencia.setString(2, sucursal.getDireccion());
            sentencia.setString(3, sucursal.getDescripcion());
            sentencia.setInt(4, sucursal.getCodigoSucursal());

            sentencia.executeUpdate();
        }
    }
    
    public void eliminar(int codigoSucursal) throws SQLException {

        String sql = "DELETE FROM sucursal WHERE codigo_sucursal = ?";

        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, codigoSucursal);
            sentencia.executeUpdate();
        }
    }
}
