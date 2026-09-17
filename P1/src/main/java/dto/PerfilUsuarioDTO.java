package dto;

import model.PerfilUsuario;
import model.Usuario;

/**
 * @author dz
 */
public class PerfilUsuarioDTO {
    
    

    private Usuario usuario;
    private PerfilUsuario perfil;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PerfilUsuario getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilUsuario perfil) {
        this.perfil = perfil;
    }
    
    

    
}
