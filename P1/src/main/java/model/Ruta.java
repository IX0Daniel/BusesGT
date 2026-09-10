package model;

/**
 *
 * @author dz
 */
public class Ruta {
    
    
    private int idRuta;
    private int codigoSucursalOrigen;
    private int codigoSucursalDestino;
    private double distancia;
    private double precioBoleto;

    public Ruta(int idRuta, int codigoSucursalOrigen, int codigoSucursalDestino, double distancia, double precioBoleto) {
        this.idRuta = idRuta;
        this.codigoSucursalOrigen = codigoSucursalOrigen;
        this.codigoSucursalDestino = codigoSucursalDestino;
        this.distancia = distancia;
        this.precioBoleto = precioBoleto;
    }

    public Ruta(int codigoSucursalOrigen, int codigoSucursalDestino, double distancia, double precioBoleto) {
        this.codigoSucursalOrigen = codigoSucursalOrigen;
        this.codigoSucursalDestino = codigoSucursalDestino;
        this.distancia = distancia;
        this.precioBoleto = precioBoleto;
    }

    public Ruta() {
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public int getCodigoSucursalOrigen() {
        return codigoSucursalOrigen;
    }

    public void setCodigoSucursalOrigen(int codigoSucursalOrigen) {
        this.codigoSucursalOrigen = codigoSucursalOrigen;
    }

    public int getCodigoSucursalDestino() {
        return codigoSucursalDestino;
    }

    public void setCodigoSucursalDestino(int codigoSucursalDestino) {
        this.codigoSucursalDestino = codigoSucursalDestino;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getPrecioBoleto() {
        return precioBoleto;
    }

    public void setPrecioBoleto(double precioBoleto) {
        this.precioBoleto = precioBoleto;
    }
    
    
    
    
    
            
}
