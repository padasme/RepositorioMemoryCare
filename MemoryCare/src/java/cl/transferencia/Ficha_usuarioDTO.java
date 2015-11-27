package cl.transferencia;

import cl.dominio.Ficha;
import cl.dominio.Usuario;
import java.io.Serializable;

public class Ficha_usuarioDTO implements Serializable {
    private Usuario usuario;
    private Ficha ficha;

    public Ficha_usuarioDTO() {
    }

    public Ficha_usuarioDTO(Usuario usuario, Ficha ficha) {
        this.usuario = usuario;
        this.ficha = ficha;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the ficha
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * @param ficha the ficha to set
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }
}
