package cl.dominio;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import org.omg.CORBA.DATA_CONVERSION;

/**
 * Clase que mapea la tabla Ficha
 */
public class Ficha implements Serializable {

    private int rut;
    private char dv_rut;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private Date fecha_nac;
    private char sexo;
    private String actividad;
    private char estado_civil;
    private String telefono;
    private String direccion;
    private String ciudad;
    private int peso;
    private int estatura;

    public Ficha() {
    }

    public Ficha(int rut, char dv_rut, String nombre, String ap_paterno, String ap_materno, Date fecha_nac, char sexo, String actividad, char estado_civil, String telefono, String direccion, String ciudad, int peso, int estatura) {
        this.rut = rut;
        this.dv_rut = dv_rut;
        this.nombre = nombre;
        this.ap_paterno = ap_paterno;
        this.ap_materno = ap_materno;
        this.fecha_nac = fecha_nac;
        this.sexo = sexo;
        this.actividad = actividad;
        this.estado_civil = estado_civil;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.peso = peso;
        this.estatura = estatura;
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
     * @return the dv_rut
     */
    public char getDv_rut() {
        return dv_rut;
    }

    /**
     * @param dv_rut the dv_rut to set
     */
    public void setDv_rut(char dv_rut) {
        this.dv_rut = dv_rut;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ap_paterno
     */
    public String getAp_paterno() {
        return ap_paterno;
    }

    /**
     * @param ap_paterno the ap_paterno to set
     */
    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    /**
     * @return the ap_materno
     */
    public String getAp_materno() {
        return ap_materno;
    }

    /**
     * @param ap_materno the ap_materno to set
     */
    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    /**
     * @return the fecha_nac
     */
    public Date getFecha_nac() {
        return fecha_nac;
    }

    /**
     * @param fecha_nac the fecha_nac to set
     */
    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    /**
     * @return the sexo
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the actividad
     */
    public String getActividad() {
        return actividad;
    }

    /**
     * @param actividad the actividad to set
     */
    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    /**
     * @return the estado_civil
     */
    public char getEstado_civil() {
        return estado_civil;
    }

    /**
     * @param estado_civil the estado_civil to set
     */
    public void setEstado_civil(char estado_civil) {
        this.estado_civil = estado_civil;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the estatura
     */
    public int getEstatura() {
        return estatura;
    }

    /**
     * @param estatura the estatura to set
     */
    public void setEstatura(int estatura) {
        this.estatura = estatura;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.rut;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ficha other = (Ficha) obj;
        if (this.rut != other.rut) {
            return false;
        }
        return true;
    }

   

}
