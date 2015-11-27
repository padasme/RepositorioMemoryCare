package cl.dominio;

import java.io.Serializable;

/**
 * Clase que mapea la tabla Usuario
 */
public class Usuario implements Serializable {

    private int rut;
    private char dvrut;
    private String email;
    private char perfil;
    private String password;
    private char estado;

    public Usuario() {
    }

    public Usuario(int rut, char dvrut, String email, char perfil, String password, char estado) {
        this.rut = rut;
        this.dvrut = dvrut;
        this.email = email;
        this.perfil = perfil;
        this.password = password;
        this.estado = estado;
    }

    /**
     * @return the rut
     */
    public int getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(int rut) {
        this.rut = rut;
    }

    /**
     * @return the dvrut
     */
    public char getDvrut() {
        return dvrut;
    }

    /**
     * @param dvrut the dvrut to set
     */
    public void setDvrut(char dvrut) {
        this.dvrut = dvrut;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the perfil
     */
    public char getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(char perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
        hash = 47 * hash + this.rut;
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
        final Usuario other = (Usuario) obj;
        if (this.rut != other.rut) {
            return false;
        }
        return true;
    }
}
