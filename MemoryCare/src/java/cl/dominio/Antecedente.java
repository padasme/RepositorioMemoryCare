package cl.dominio;

import java.io.Serializable;

/**
 * Clase que mapea la tabla Antecedente
 */
public class Antecedente implements Serializable {

    private int id;
    private String descripcion;
    private int tipo_antecedente_id;
    private char estado;

    public Antecedente() {
    }

    public Antecedente(int id, String descripcion, int tipo_antecedent_id, char estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo_antecedente_id = tipo_antecedent_id;
        this.estado = estado;
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipo_antecedente_id
     */
    public int getTipo_antecedente_id() {
        return tipo_antecedente_id;
    }

    /**
     * @param tipo_antecedente_id the tipo_antecedente_id to set
     */
    public void setTipo_antecedente_id(int tipo_antecedente_id) {
        this.tipo_antecedente_id = tipo_antecedente_id;
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
        hash = 89 * hash + this.id;
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
        final Antecedente other = (Antecedente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
}
