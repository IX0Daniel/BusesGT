package model;

/**
 * @author dz
 */
public class PerfilUsuario {
   private String dpi;
   private String nit;
   private String telefono;
   private String direccion;
   private String nombreCompleto;
   private double saldo;

    public PerfilUsuario(String dpi, String nit, String telefono, String direccion, String nombreCompleto, double saldo) {
        this.dpi = dpi;
        this.nit = nit;
        this.telefono = telefono;
        this.direccion = direccion;
        this.nombreCompleto = nombreCompleto;
        this.saldo = saldo;
    }

    public PerfilUsuario() {
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
   
   
   
}
