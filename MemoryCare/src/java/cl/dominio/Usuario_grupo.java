package cl.dominio;

import java.io.Serializable;

/**
 * Clase que mapea la tabla Usuario_grupo
 */
public class Usuario_grupo implements Serializable {

    private int id;
    private int usuario_rut;
    private int grupo_id;

    public Usuario_grupo() {
    }

    public Usuario_grupo(int id, int usuario_rut, int grupo_id) {
        this.id = id;
        this.usuario_rut = usuario_rut;
        this.grupo_id = grupo_id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the usuario_rut
     */
    public int getUsuario_rut() {
        return usuario_rut;
    }

    /**
     * @param usuario_rut the usuario_rut to set
     */
    public void setUsuario_rut(int usuario_rut) {
        this.usuario_rut = usuario_rut;
    }

    /**
     * @return the grupo_id
     */
    public int getGrupo_id() {
        return grupo_id;
    }

    /**
     * @param grupo_id the grupo_id to set
     */
    public void setGrupo_id(int grupo_id) {
        this.grupo_id = grupo_id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario_grupo other = (Usuario_grupo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
