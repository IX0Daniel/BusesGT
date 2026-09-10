package model;

/**
 * @author dz
 */
public class Sucursal {
    
    private int codigoSucursal;
    private String nombre;
    private String direccion;
    private String descripcion;

    public Sucursal() {}
    
    public Sucursal(String nombre, String direccion, String descripcion) { 
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }

    public Sucursal(int codigoSucursal, String nombre, String direccion, String descripcion) {
        this.codigoSucursal = codigoSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
    
}
