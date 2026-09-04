package model;

/**
 * @author dz
 */
public class Bus {
    
    
    
    private String numeroPlaca;
    private String rutaFoto;
    private String marca;
    private String modelo;
    private int año;
    private int capacidad;
    private double kilometraje;
    private String estado;
    private int codigoSucursal;
    private int codigoSucursalActual;

    
    public Bus(){}
    
    public Bus(String numeroPlaca, String rutaFoto, String marca, String modelo, int año, int capacidad, double kilometraje, String estado, int codigoSucursal, int codigoSucursalActual) {
        this.numeroPlaca = numeroPlaca;
        this.rutaFoto = rutaFoto;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.capacidad = capacidad;
        this.kilometraje = kilometraje;
        this.estado = estado;
        this.codigoSucursal = codigoSucursal;
        this.codigoSucursalActual = codigoSucursalActual;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(int codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public int getCodigoSucursalActual() {
        return codigoSucursalActual;
    }

    public void setCodigoSucursalActual(int codigoSucursalActual) {
        this.codigoSucursalActual = codigoSucursalActual;
    }
    
    
    
            
}
