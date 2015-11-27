package cl.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que mapea la tabla Usuario_antecedente
 */
public class Usuario_antecedente implements Serializable {

    private int usuario_rut;
    private int antecedente_id;
    private Date fecha_creacion;
    private Date fecha_actualizacion;
    private char estado;

    public Usuario_antecedente() {
    }

    public Usuario_antecedente(int usuario_rut, int antecedente_id, Date fecha_creacion, Date fecha_actualizacion, char estado) {
        this.usuario_rut = usuario_rut;
        this.antecedente_id = antecedente_id;
        this.fecha_creacion = fecha_creacion;
        this.fecha_actualizacion = fecha_actualizacion;
        this.estado = estado;
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
     * @return the antecedente_id
     */
    public int getAntecedente_id() {
        return antecedente_id;
    }

    /**
     * @param antecedente_id the antecedente_id to set
     */
    public void setAntecedente_id(int antecedente_id) {
        this.antecedente_id = antecedente_id;
    }

    /**
     * @return the fecha_creacion
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * @param fecha_creacion the fecha_creacion to set
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    /**
     * @return the fecha_actualizacion
     */
    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    /**
     * @param fecha_actualizacion the fecha_actualizacion to set
     */
    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    /**
     * @return the estado
     */
    public char getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.usuario_rut;
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
        final Usuario_antecedente other = (Usuario_antecedente) obj;
        if (this.usuario_rut != other.usuario_rut) {
            return false;
        }
        return true;
    }
}
