package dto;

import model.PerfilUsuario;
import model.Usuario;

/**
 * @author dz
 */
public class PerfilUsuarioDTO {
    
    private String correo;
    private String nombreCompleto;
    private String dpi;
    private String nit;
    private String telefono;
    private String direccion;
    private double saldo;
    private String rol;
    private String estado;
    
    private PerfilUsuario perfil;
    private Usuario usuario;

    public PerfilUsuarioDTO(String correo, String nombreCompleto, String dpi, String nit, String telefono, String direccion, double saldo, String rol, String estado) {
        this.correo = correo;
        this.nombreCompleto = nombreCompleto;
        this.dpi = dpi;
        this.nit = nit;
        this.telefono = telefono;
        this.direccion = direccion;
        this.saldo = saldo;
        this.rol = rol;
        this.estado = estado;
    }

    public PerfilUsuarioDTO() {
    }

    
    public PerfilUsuarioDTO(PerfilUsuario perfil, Usuario usuario) {
        this.perfil = perfil;
        this.usuario = usuario;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    
    

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    
}
