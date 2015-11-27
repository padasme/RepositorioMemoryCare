package cl.dominio;

import java.io.Serializable;

/**
 * Clase que mapea la tabla Tipo_antecedente
 */
public class Tipo_antecedente implements Serializable {
    
    private int id;
    private String descripcion;

    public Tipo_antecedente() {
    }

    public Tipo_antecedente(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Tipo_antecedente other = (Tipo_antecedente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
