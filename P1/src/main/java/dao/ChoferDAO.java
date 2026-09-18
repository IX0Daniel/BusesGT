package dao;

import database.ConexionDB;
import dto.ChoferDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Chofer;

/**
 *
 * @author dz
 */
public class ChoferDAO {
    
    
    public List<ChoferDTO> listar() throws SQLException {

        String sql = "SELECT u.correo, p.nombre_completo, c.no_licencia, c.ruta_foto, c.tipo_licencia, c.fecha_vencimiento, c.salario, s.nombre AS nombre_sucursal, u.estado FROM chofer c INNER JOIN usuario u ON c.correo = u.correo INNER JOIN perfil_usuario p ON u.correo = p.correo INNER JOIN sucursal s ON c.codigo_sucursal = s.codigo_sucursal ORDER BY p.nombre_completo ";
        List<ChoferDTO> choferes = new ArrayList<>();
        
        try (Connection conexion = ConexionDB.getConnection(); 
                PreparedStatement ps = conexion.prepareStatement(sql); 
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChoferDTO dto = new ChoferDTO();
                dto.setCorreo(rs.getString("correo"));
                dto.setNombreCompleto(rs.getString("nombre_completo"));
                dto.setNoLicencia(rs.getString("no_licencia"));
                dto.setRutaFoto(rs.getString("ruta_foto"));
                dto.setTipoLicencia(rs.getString("tipo_licencia"));
                dto.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                dto.setSalario(rs.getDouble("salario"));
                dto.setNombreSucursal(rs.getString("nombre_sucursal"));
                dto.setEstado(rs.getString("estado"));
                choferes.add(dto);
            }
        }
        return choferes;
    }

    public Chofer buscarPorLicencia(String noLicencia) throws SQLException {
        String sql = "SELECT correo, no_licencia, ruta_foto, tipo_licencia, fecha_vencimiento, salario, codigo_sucursal FROM chofer WHERE no_licencia = ? ";
        
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, noLicencia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Chofer chofer = new Chofer();
                    chofer.setCorreo(rs.getString("correo"));
                    chofer.setNoLicencia(rs.getString("no_licencia"));
                    chofer.setRutaFoto(rs.getString("ruta_foto"));
                    chofer.setTipoLicencia(rs.getString("tipo_licencia"));
                    chofer.setFechaVencimiento(rs.getString("fecha_vencimiento"));
                    chofer.setSalario(rs.getDouble("salario"));
                    chofer.setCodigoSucursal(rs.getInt("codigo_sucursal"));
                    return chofer;
                }
            }
        }
        return null;
    }

    public void insertar(Chofer chofer, Connection conexion) throws SQLException {
        String sql = " INSERT INTO chofer (correo, no_licencia, ruta_foto, tipo_licencia, fecha_vencimiento, salario, codigo_sucursal) VALUES (?, ?, ?, ?, ?, ?, ?) "; 
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, chofer.getCorreo());
            ps.setString(2, chofer.getNoLicencia());
            ps.setString(3, chofer.getRutaFoto());
            ps.setString(4, chofer.getTipoLicencia());
            ps.setDate(5, Date.valueOf(chofer.getFechaVencimiento()));
            ps.setDouble(6, chofer.getSalario());
            ps.setInt(7, chofer.getCodigoSucursal());
            ps.executeUpdate();
        }
    }

    public void actualizar(Chofer chofer) throws SQLException {
        String sql = "UPDATE chofer SET ruta_foto = ?, tipo_licencia = ?, fecha_vencimiento = ?, salario = ? WHERE no_licencia = ? "; 
        try (Connection conexion = ConexionDB.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, chofer.getRutaFoto());
            ps.setString(2, chofer.getTipoLicencia());
            ps.setDate(3, Date.valueOf(chofer.getFechaVencimiento()));
            ps.setDouble(4, chofer.getSalario());
            ps.setString(5, chofer.getNoLicencia());
            ps.executeUpdate();
        }
    }

    public void actualizar(Chofer chofer, Connection conexion) throws SQLException {
        String sql = " UPDATE chofer SET ruta_foto = ?, tipo_licencia = ?, fecha_vencimiento = ?, salario = ? WHERE no_licencia = ? "; 
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, chofer.getRutaFoto());
            ps.setString(2, chofer.getTipoLicencia());
            ps.setDate(3, Date.valueOf(chofer.getFechaVencimiento()));
            ps.setDouble(4, chofer.getSalario());
            ps.setString(5, chofer.getNoLicencia());
            ps.executeUpdate();
        }
    }
    
}
