package model;

/**
 *
 * @author dz
 */
public class Viaje { 

    private int idViaje;
    private int idRuta;
    private String numeroPlaca;
    private String noLicencia;
    private String fechaHoraSalida;
    private String horaEstimadaLlegada;
    private String tipoViaje;
    private String estado;

    public Viaje() {
    }

    public Viaje(int idViaje, int idRuta, String numeroPlaca, String noLicencia, String fechaHoraSalida,String horaEstimadaLlegada, String tipoViaje,String estado) {
        this.idViaje = idViaje;
        this.idRuta = idRuta;
        this.numeroPlaca = numeroPlaca;
        this.noLicencia = noLicencia;
        this.fechaHoraSalida = fechaHoraSalida;
        this.horaEstimadaLlegada = horaEstimadaLlegada;
        this.tipoViaje = tipoViaje;
        this.estado = estado;
    }

    public Viaje(int idRuta, String numeroPlaca,String noLicencia, String fechaHoraSalida, String horaEstimadaLlegada, String tipoViaje, String estado) {
        this.idRuta = idRuta;
        this.numeroPlaca = numeroPlaca;
        this.noLicencia = noLicencia;
        this.fechaHoraSalida = fechaHoraSalida;
        this.horaEstimadaLlegada = horaEstimadaLlegada;
        this.tipoViaje = tipoViaje;
        this.estado = estado;
    }

    public int getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(int idViaje) {
        this.idViaje = idViaje;
    }

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

    public String getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getHoraEstimadaLlegada() {
        return horaEstimadaLlegada;
    }

    public void setHoraEstimadaLlegada(String horaEstimadaLlegada) {
        this.horaEstimadaLlegada = horaEstimadaLlegada;
    }

    public String getTipoViaje() {
        return tipoViaje;
    }

    public void setTipoViaje(String tipoViaje) {
        this.tipoViaje = tipoViaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
 

    
    
}
