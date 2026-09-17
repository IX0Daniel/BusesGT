package model;

/**
 * @author dz
 */
public class Chofer {
    
    private String noLicencia;
    private String rutaFoto;
    private String tipoLicencia;
    
    
    
    public Chofer(String noLicencia, String rutaFoto, String tipoLicencia) {
        this.noLicencia = noLicencia;
        this.rutaFoto = rutaFoto;
        this.tipoLicencia = tipoLicencia;
    }
    
    
    
    public String getNoLicencia() {
        return noLicencia;
    }

    public void setNoLicencia(String noLicencia) {
        this.noLicencia = noLicencia;
    }

    

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }
    
    
    
    
    
}
